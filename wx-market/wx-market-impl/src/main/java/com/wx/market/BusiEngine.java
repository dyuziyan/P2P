package com.wx.market;

import com.wx.account.dto.AccountDto;
import com.wx.exception.MarketServiceException;
import com.wx.product.dto.ProductDto;
import com.wx.trade.dto.OrderDto;


public abstract class BusiEngine {

	public static BusiEngine getInstance() {
		return BusiEngineUtils.getEngine();
	}

	public abstract BusiResult doFilter(BusiParam param) throws MarketServiceException;
	
	public abstract void doHandler(BusiParam param) throws MarketServiceException;

	public abstract BusiEngine setContext(String key, Object value);

	public abstract void clearContext();

	public abstract BusiEngine setContext(AccountDto account);
	
	public abstract BusiEngine setContext(ProductDto product);
	
	public abstract BusiEngine setContext(OrderDto order);
}
