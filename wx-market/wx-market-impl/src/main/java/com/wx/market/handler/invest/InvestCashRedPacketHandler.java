package com.wx.market.handler.invest;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wx.account.dto.AccountDto;
import com.wx.account.service.AccountService;
import com.wx.enums.common.BusiEvent;
import com.wx.exception.MarketServiceException;
import com.wx.exception.MarketServiceMsgException;
import com.wx.market.BusiContext;
import com.wx.market.BusiParam;
import com.wx.market.domain.CashRedPacket;
import com.wx.market.enums.CashRedPacketState;
import com.wx.market.handler.TypedBusiHandler;
import com.wx.market.service.CashRedPacketService;
import com.wx.market.service.SNCheckUpService;
import com.wx.product.dto.ProductDto;
import com.wx.service.Result;
import com.wx.trade.dto.OrderDto;

import my.comp.config.SysConf;
import my.comp.sn.SnBuilder;

/**
 * 现金红包
 */
@Service
public class InvestCashRedPacketHandler extends TypedBusiHandler {
	
	@Resource CashRedPacketService cashRedPacketService;
	@Resource AccountService accountService;
	@Resource SNCheckUpService snCheckUpService;
	@Override
	protected void internalHandle(BusiParam param) throws MarketServiceException {
		
		AccountDto account = BusiContext.getAccount();
		Assert.notNull(account, "account could not be null.") ;
		ProductDto product = BusiContext.getProduct();
		Assert.notNull(product, "product could not be null.") ;
		OrderDto order = BusiContext.getOrder() ;
		Assert.notNull(order, "order could not be null.");
		//没有红包ID不执行此触发器
		if(param.getCashRedPacketId()==null){
			logger.debug("现金红包:不符合使用现金红包条件");
			return ;
		}
		//获取红包,查询红包是否存在,是否可用
		CashRedPacket cashRedPacket=cashRedPacketService.getCashRedPacketForUpdate(param.getCashRedPacketId()
				,CashRedPacketState.USING.getKey(),order.getInvestor());
		if(cashRedPacket==null){
			logger.debug("现金红包:现金红包已使用或者红包不存在");
			return ;
		}
//		String env = SysConf.get("env.mode");
//		BigDecimal money=BigDecimal.ZERO;
//		if("product".equals(env)){
//			//正式该怎么取就怎么取
//		}else{
//			money=new BigDecimal("0.01");
//		}
		//判断是否符合红包使用条件
		if(order.getInvestAmount().compareTo(new BigDecimal(cashRedPacket.getRedproportion()))<0){
			logger.debug("现金红包:投资金额小于红包需投金额");
			return ;
		}
		if(product.getDeadline()<cashRedPacket.getProductday()){
			logger.debug("现金红包:项目投资周期小于红包所需投投资周期");
			return ;
		}
		//业务唯一性---目前先查询流水里面的---后面应为不同业务流水表里面插入失败返回异常，不做判断
		SnBuilder sn = SnBuilder.create(param.getBusiEvent().getCode(), param.getInvestorId(), product.getId(),order.getId(), getType().getCode(),param.getCashRedPacketId()) ;
		if(snCheckUpService.checkSnExist(sn.toString())){
			logger.debug("现金红包:订单:"+order.getId()+" 红包奖励已经发放");
			return ;
		};
	
		//使用红包--更改红包状态位
		cashRedPacket.setUsedate(new Date());
		cashRedPacket.setUse_state(CashRedPacketState.USED.getKey());
		cashRedPacketService.updateCashRedPacket(cashRedPacket);
		
		//TODO account增加方法--给用户账户添加获得的营销奖励并写流水 --需在一个事务 account方法返回失败该触发器回滚
		//项目命暂时先通过这种方式存入
		order.setProductName(product.getProductTitle());
		
		Result grantRewardResult=accountService.grantReward(BusiEvent.CASHREDPACKET,order,cashRedPacket.getRedwrap(),sn.toString());
		if(!grantRewardResult.success()){
			throw MarketServiceMsgException.byMsg("执行奖励营销奖励数据录入异常");
		}
		logger.debug("现金红包:标的"+order.getProductId()+"--用户:"+order.getInvestor()+"--使用的红包ID为: "+cashRedPacket.getRedpacketCode()+"--红包金额为:"+cashRedPacket.getRedwrap());
	}

}
