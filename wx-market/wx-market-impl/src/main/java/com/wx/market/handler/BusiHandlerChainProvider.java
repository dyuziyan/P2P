package com.wx.market.handler ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wx.enums.common.BusiEvent;

public class BusiHandlerChainProvider implements ApplicationContextAware {

	Map<BusiEvent, BusiHandlerChain> chains = new HashMap<BusiEvent, BusiHandlerChain>();

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, BusiHandlerChain> map = ctx.getBeansOfType(BusiHandlerChain.class);
		for (BusiHandlerChain chain : map.values()) {
			chains.put(chain.getEvent(), chain);
		}
	}

	public BusiHandlerChain get(BusiEvent event) {
		return chains.get(event);
	}

}
