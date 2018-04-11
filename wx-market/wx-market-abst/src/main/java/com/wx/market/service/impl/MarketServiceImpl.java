package com.wx.market.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.market.dto.InvestMessage;
import com.wx.market.dto.MarketResult;
import com.wx.market.service.MarketException;
import com.wx.market.service.MarketService;
import com.wx.market.service.handler.PrizeIssueHandler;
import com.wx.message.dto.MsgDto;
//import com.wx.message.service.MessageService;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired
	private PrizeIssueHandler prizeIssueHandler;
//	@Autowired
//	private MessageService messageService;

//	private void handleMessage(Iterator<MsgDto<?>> it) {
//		if (it == null) return;
//		while (it.hasNext()) {
//			MsgDto<?> dto = it.next();
//			if (dto == null) continue;
//			messageService.send(dto);
//		}
//	}

	/**
	 * 投资消息处理
	 * 
	 */
	@Override
	public MarketResult investSuccess(InvestMessage invest) {
		try {
			Iterator<MsgDto<?>> result = prizeIssueHandler.investSuccess(invest);
//			handleMessage(result);
		} catch (MarketException e) {
			e.printStackTrace();
			return MarketResult.byExc(e);
		}
		return MarketResult.bySuc();
	}

}
