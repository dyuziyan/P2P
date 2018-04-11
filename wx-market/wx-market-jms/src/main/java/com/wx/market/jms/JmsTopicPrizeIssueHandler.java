package com.wx.market.jms;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import my.comp.jms.DestType;
import my.comp.jms.core.JmsTopicSender;
import my.comp.jms.logger.JmsLogService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wx.market.dto.InvestMessage;
import com.wx.market.dto.MarketMessage;
import com.wx.market.service.MarketException;
import com.wx.market.service.handler.PrizeIssueHandler;
import com.wx.message.dto.MsgDto;

@Component
public class JmsTopicPrizeIssueHandler implements PrizeIssueHandler, InitializingBean {

	/**
	 * 营销_投资
	 */
	String MK_INVEST = "mk.invest";

	@Autowired
	private JmsTopicSender sender;

	@Autowired
	private JmsLogService jmsLogService;

	public void afterPropertiesSet() throws Exception {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Iterator<MsgDto<?>> getResult() {
		Collection collection = Collections.emptyList();
		return (Iterator<MsgDto<?>>) collection.iterator();
	}

	private void send(final String destination, final MarketMessage message) throws MarketException {
		try {

			jmsLogService.logSendBefore(DestType.topic, destination, message);

			MarketMessageEntry entry = new MarketMessageEntry(message, DestType.topic, destination);

			MarketMessageWrapper wrapper = MarketMessageHolder.get();
			if (wrapper == null) {
				MarketMessageHolder.set(entry);
			} else {
				if (wrapper instanceof MarketMessageList) {
					((MarketMessageList) wrapper).add(entry);
				} else if (wrapper instanceof MarketMessageEntry) {
					MarketMessageList list = new MarketMessageList();
					list.add((MarketMessageEntry) wrapper);
					list.add(entry);
					MarketMessageHolder.set(list);
				}
			}

		} catch (Exception e) {
			throw new MarketException(e);
		}
	}

	@Override
	public Iterator<MsgDto<?>> investSuccess(InvestMessage message) throws MarketException {

		send(MK_INVEST, message);

		return getResult();
	}

}
