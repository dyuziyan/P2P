package com.wx.market ;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wx.account.dto.AccountDto;
import com.wx.exception.MarketServiceException;
import com.wx.market.filter.BusiFilterChain;
import com.wx.market.filter.BusiFilterChainProvider;
import com.wx.market.handler.BusiHandlerChain;
import com.wx.market.handler.BusiHandlerChainProvider;
import com.wx.product.dto.ProductDto;
import com.wx.trade.dto.OrderDto;

public class BusiEngineImpl extends BusiEngine {

	protected static Log logger = LogFactory.getLog(BusiEngineImpl.class);

	private BusiFilterChainProvider filterChainProvider;
	
	private BusiHandlerChainProvider handlerChainProvider;

	private BusiContextImpl busiContext;

	public BusiEngineImpl() {

	}

	public void setBusiContext(BusiContextImpl busiContext) {
		this.busiContext = busiContext;
		BusiContext.setContext(busiContext);
	}

	public void setFilterChainProvider(BusiFilterChainProvider filterChainProvider) {
		this.filterChainProvider = filterChainProvider;
	}

	public void setHandlerChainProvider(
			BusiHandlerChainProvider handlerChainProvider) {
		this.handlerChainProvider = handlerChainProvider;
	}

	@Override
	public BusiResult doFilter(BusiParam param) {
		BusiResult result = BusiResult.success();
		BusiFilterChain chain = filterChainProvider.get(param.getBusiEvent());
		if (chain != null) {
			result = chain.doFilter(param);
		}
		return result;
	}
	
	@Override
	public void doHandler(BusiParam param) throws MarketServiceException {
		BusiHandlerChain chain = handlerChainProvider.get(param.getBusiEvent()) ;
		if(chain != null)
			chain.handle(param) ;
	}

	public BusiEngine setContext(String key, Object value) {
		this.busiContext.set(key, value);
		return this;
	}

	public void clearContext() {
		busiContext.clear();
	}

	@Override
	public BusiEngine setContext(AccountDto account) {
		this.busiContext.setAccount(account);
		return this;
	}

	@Override
	public BusiEngine setContext(ProductDto product) {
		this.busiContext.setProduct(product);
		return this;
	}

	@Override
	public BusiEngine setContext(OrderDto order) {
		this.busiContext.setOrder(order);
		return this;
	}



}
