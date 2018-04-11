package com.wx.market.service;

import my.comp.rmi.annotation.LocalService;
import my.comp.rmi.annotation.RemoteService;

import com.wx.market.dto.InvestMessage;
import com.wx.market.dto.MarketResult;

@RemoteService("/marketService")
@LocalService(ref = "marketServiceImpl")
public interface MarketService {


	/**
	 * 投资消息处理
	 * @param invest
	 */
	MarketResult investSuccess(InvestMessage invest);

}
