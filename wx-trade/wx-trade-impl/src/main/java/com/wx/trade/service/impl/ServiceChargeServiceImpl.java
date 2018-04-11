package com.wx.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.service.BaseService;
import com.wx.trade.dao.ServiceChargeDao;
import com.wx.trade.service.ServiceChargeService;

/**
 * 手续费
 */
@Service
public class ServiceChargeServiceImpl extends BaseService implements ServiceChargeService {

	@Resource
	private ServiceChargeDao serviceChargeDao;
	
	@Override
	public double getUserFreeCharge(long userId) {
		return serviceChargeDao.getUserFreeCharge(userId).doubleValue();
	}

}

