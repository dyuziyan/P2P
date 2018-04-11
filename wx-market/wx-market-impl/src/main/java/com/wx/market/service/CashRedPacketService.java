package com.wx.market.service;

import com.wx.market.domain.CashRedPacket;

public interface CashRedPacketService {
	
	//查询红包具体信息--带锁  state=CashRedPacketState
	CashRedPacket getCashRedPacketForUpdate(String cashRedPacketId,int state,long userId);
	
	//更新红包
	int updateCashRedPacket(CashRedPacket cashRedPacket);
}
