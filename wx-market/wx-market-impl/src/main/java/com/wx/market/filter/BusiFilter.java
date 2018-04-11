package com.wx.market.filter;

import com.wx.exception.MarketServiceException;
import com.wx.market.BusiParam;

public interface BusiFilter {
	void doFilter(BusiParam param) throws MarketServiceException;
}
