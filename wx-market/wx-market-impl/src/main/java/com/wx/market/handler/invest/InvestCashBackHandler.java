package com.wx.market.handler.invest;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wx.account.dto.AccountDto;
import com.wx.account.service.AccountService;
import com.wx.enums.common.BusiEvent;
import com.wx.enums.market.BusiType;
import com.wx.enums.trade.ProductState;
import com.wx.exception.MarketServiceException;
import com.wx.exception.MarketServiceMsgException;
import com.wx.market.BusiContext;
import com.wx.market.BusiParam;
import com.wx.market.handler.TypedBusiHandler;
import com.wx.market.service.SNCheckUpService;
import com.wx.product.dto.ProductDto;
import com.wx.service.Result;
import com.wx.trade.dto.OrderDto;

import my.comp.sn.SnBuilder;

/**
 * 标的返利
 */
@Service
public class InvestCashBackHandler extends TypedBusiHandler {
	
	@Resource SNCheckUpService snCheckUpService;
	@Resource AccountService accountService;
	@Override
	protected void internalHandle(BusiParam param) throws MarketServiceException {
		//TODO 考虑是否以活动的模式来实现此处，通过活动表启动来判断触发器是否执行，业务唯一性也可通过活动ID 标的ID 用户ID 订单ID 日期来确定
		
		ProductDto product = BusiContext.getProduct();
		Assert.notNull(product, "product could not be null.") ;
		OrderDto order = BusiContext.getOrder() ;
		Assert.notNull(order, "order could not be null.");
		
		//判断标的是否返利 
		if(product.getCashback() == null||product.getCashback().compareTo(BigDecimal.ZERO)<=0){
			logger.debug("标的返利:此标的并非返利标的");
			return ;
		}
		//补发业务不适用--会导致补发的信息在标的状态非投标中的时候不触发业务
//		if(product.getStatus()!=ProductState.INVESTING.code()){
//			logger.debug("标的返利:标的不处于投标状态");
//			return ;
//		}
		
		//计算应该给用户返利的金额
		BigDecimal cashback=order.getInvestAmount().multiply(product.getCashback()).divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP);
		
		//业务唯一性---目前先查询流水里面的---后面应为不同业务流水表里面插入失败返回异常，不做判断
		SnBuilder sn = SnBuilder.create(param.getBusiEvent().getCode(), param.getInvestorId(), product.getId(),order.getId(), getType().getCode()) ;
//SN号业务暂时不做开发，需要在红包表另加字段
		//		if(snCheckUpService.checkSnExist(sn.toString())){
//			logger.debug("标的返利:订单:"+order.getId()+" 奖励已经发放,不再重复发放");
//			return ;
//		};
		
		//给用户账户添加获得的营销奖励并写流水
		//项目命暂时先通过这种方式存入
		order.setProductName(product.getProductTitle());
		
		Result grantRewardResult=accountService.grantReward(BusiEvent.CASHBACK,order,cashback,sn.toString());
		if(!grantRewardResult.success()){
			throw MarketServiceMsgException.byMsg("执行奖励营销奖励数据录入异常");
		}
		logger.debug("标的返利:标的"+order.getProductId()+"给用户:"+order.getInvestor()+" 的返利金额为: "+cashback);
	}

}
