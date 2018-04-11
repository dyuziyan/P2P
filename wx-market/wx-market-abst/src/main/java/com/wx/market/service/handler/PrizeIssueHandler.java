package com.wx.market.service.handler;

import java.util.Iterator;

import com.wx.market.dto.InvestMessage;
import com.wx.market.service.MarketException;
import com.wx.message.dto.MsgDto;

public interface PrizeIssueHandler {

	/**
	 * 投资消息处理
	 * @param invest TODO
	 * @throws MarketException
	 */
	Iterator<MsgDto<?>> investSuccess(InvestMessage invest) throws MarketException;


}