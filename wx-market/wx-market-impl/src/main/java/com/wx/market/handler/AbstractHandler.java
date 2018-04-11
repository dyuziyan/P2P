package com.wx.market.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.wx.exception.MarketServiceException;
import com.wx.market.BusiParam;

public abstract class AbstractHandler implements BusiHandler {

	protected static Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

	protected abstract void internalHandle(BusiParam param) throws MarketServiceException;
	
	@Override
	public void handle(BusiParam param) throws MarketServiceException {
		logger.debug("业务处理器: " + this.getClass() + " start ...");
		internalHandle(param);
		logger.debug("业务处理器: " + this.getClass() + " end");
	}

}
