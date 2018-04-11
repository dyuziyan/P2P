package com.wx.market.handler;

import com.wx.exception.MarketServiceException;
import com.wx.market.BusiParam;

/**
 * 后置业务处理接口
 */
public interface BusiHandler {
	void handle(BusiParam param) throws MarketServiceException;
}
