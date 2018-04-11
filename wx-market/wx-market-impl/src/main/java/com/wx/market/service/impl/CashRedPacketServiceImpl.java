package com.wx.market.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.market.dao.CashRedPacketDao;
import com.wx.market.domain.CashRedPacket;
import com.wx.market.service.CashRedPacketService;

@Service
public class CashRedPacketServiceImpl implements CashRedPacketService{

	@Resource CashRedPacketDao cashRedPacketDao;
	
	@Override
	public CashRedPacket getCashRedPacketForUpdate(String cashRedPacketId,int state,long userId) {
		return cashRedPacketDao.getCashRedPacketForUpdate(cashRedPacketId,state,userId);
	}

	@Override
	public int updateCashRedPacket(CashRedPacket cashRedPacket) {
		return cashRedPacketDao.update(cashRedPacket);
	}

}
