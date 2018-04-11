package com.wx.market.handler;

import java.util.ArrayList;
import java.util.List;

import com.wx.enums.common.BusiEvent;
import com.wx.exception.MarketServiceException;
import com.wx.market.BusiParam;

public class BusiHandlerChain {
	
	private BusiEvent event;

	private List<BusiHandler> handlers;
	
	public BusiHandlerChain() {
		handlers = new ArrayList<BusiHandler>();
	}

	public BusiEvent getEvent() {
		return event;
	}
	public void setEvent(BusiEvent event) {
		this.event = event;
	}

	public List<BusiHandler> getHandlers() {
		return handlers;
	}
	public void setHandlers(List<BusiHandler> handlers) {
		this.handlers = handlers;
	}

	/**
	 * 执行业务处理器
	 * @param param
	 */
	public void handle(BusiParam param) throws MarketServiceException {
		for (BusiHandler handler : handlers) {
			handler.handle(param);
		}
	}


}
