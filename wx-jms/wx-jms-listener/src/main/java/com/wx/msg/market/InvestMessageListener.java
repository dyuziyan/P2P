package com.wx.msg.market;

import javax.annotation.Resource;

import my.comp.jms.logger.JmsLogger;

import org.springframework.stereotype.Component;

import com.wx.market.dto.InvestMessage;
import com.wx.market.service.MarketService;

@Component
public class InvestMessageListener {

	@Resource
	MarketService marketService;

	@JmsLogger
	public void onMessage(InvestMessage message) {
		System.out.println("接收到投资消息：" + message.getSn());
		marketService.investSuccess(message);
	}

}
