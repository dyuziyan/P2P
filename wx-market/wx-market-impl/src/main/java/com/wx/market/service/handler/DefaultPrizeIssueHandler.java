package com.wx.market.service.handler;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wx.account.dto.AccountDto;
import com.wx.account.service.AccountService;
import com.wx.enums.common.BusiEvent;
import com.wx.exception.MarketServiceException;
import com.wx.exception.MarketServiceMsgException;
import com.wx.market.BusiEngine;
import com.wx.market.BusiParam;
import com.wx.market.dto.InvestMessage;
import com.wx.market.service.MarketException;
import com.wx.message.dto.MsgDto;
import com.wx.product.dto.ProductDto;
import com.wx.product.service.ProductService;
import com.wx.service.Result;
import com.wx.trade.dto.OrderDto;
import com.wx.trade.service.OrderService;

import my.comp.transaction.WriteTransactional;

@Component
@WriteTransactional
public class DefaultPrizeIssueHandler implements PrizeIssueHandler {

	@Resource
	private ProductService productService;
	@Resource
	private OrderService orderService;
	@Resource
	private AccountService accountService ;
	
	protected static Logger logger = LoggerFactory.getLogger(DefaultPrizeIssueHandler.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Iterator<MsgDto<?>> getResult(BusiParam param) {
		Collection collection = param.getCollection(BusiParam.MESSAGES, MsgDto.class);
		if (collection == null) collection = Collections.emptyList();
		return collection.iterator();
	}


	@Override
	public Iterator<MsgDto<?>> investSuccess(InvestMessage invest) throws MarketException {
		try {
			//目前客户端的存储过程在方法调用前不一定处于执行完毕状态，可能导致方法后面的实体取值失败或者信息滞留，先阻塞进程5秒用以简单处理
			Thread.sleep(5000);
			
			logger.info("营销处理器链:"+invest.getSn()+"---Product："+invest.getProductId()+"---CashRedpacketId："+invest.getCashRedpacketId());
			
			long userId = invest.getUserId();
			long orderId = invest.getOrderId();

			BusiEngine engine = BusiEngine.getInstance();

			//TODO  基础信息和账户信息要拆分---member--account
			Result<AccountDto> accountResult = accountService.get(userId) ;
			if(!accountResult.success()){
				throw MarketServiceMsgException.byMsg("用户不存在");
			}
			AccountDto account = accountResult.getData();
			engine.setContext(account);
			//TODO 订单回款信息需拆分
			Result<OrderDto> orderResult = orderService.getOrder(orderId);
			logger.debug(invest.getSn()+":"+orderResult.getCode()+orderResult.getMessage());
			if(!orderResult.success()){
				throw MarketServiceMsgException.byMsg("未找到投资订单");
			}
			OrderDto order = orderResult.getData();
			Result<ProductDto> productResult = productService.getProduct(order.getProductId());
			logger.debug(invest.getSn()+":"+productResult.getCode()+productResult.getMessage());
			if(!productResult.success()){
				throw MarketServiceMsgException.byMsg("未找到投资项目");
			}
			ProductDto product=productResult.getData();

			engine.setContext(product).setContext(order);
			
			BusiParam param = BusiParam.create(BusiEvent.INVEST, userId, orderId);
			
			//TODO 红包使用待考虑改变方式，最好考虑从业务上改变使用方式
			if(invest.getCashRedpacketId()!=null&&!"".equals(invest.getCashRedpacketId())){
				param.setCashRedPacketId(invest.getCashRedpacketId());
			}
			
			param.setClientType(invest.getClientType()); // 操作平台来源
			engine.doHandler(param);
			return getResult(param);
		} catch (MarketServiceException e) {
			throw new MarketException(e);
		} catch (InterruptedException e) {
			throw new MarketException(e);
		}

	}

}
