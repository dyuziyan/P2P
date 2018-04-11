package com.wx.depotbank.service;

import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.dto.ret.BidRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

@RemoteService("/marketingService")
public interface MarketingService {
	/**
	 * 现金红包---直接到账
	 */
	public BidRet cashRedPacket(RechargeReq rechargeReq) throws BankException;
}
