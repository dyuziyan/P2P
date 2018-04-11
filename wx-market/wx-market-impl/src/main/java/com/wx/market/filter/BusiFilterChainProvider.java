package com.wx.market.filter ;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wx.enums.common.BusiEvent;

public class BusiFilterChainProvider implements ApplicationContextAware {

	Map<BusiEvent, BusiFilterChain> chains = new HashMap<BusiEvent, BusiFilterChain>();

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, BusiFilterChain> map = ctx.getBeansOfType(BusiFilterChain.class);
		for (BusiFilterChain chain : map.values()) {
			chains.put(chain.getEvent(), chain);
		}
	}

	public BusiFilterChain get(BusiEvent event) {
		return chains.get(event);
	}

}
