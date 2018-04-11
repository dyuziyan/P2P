package com.wx.trade.service;

import my.comp.rmi.annotation.RemoteService;


/**
 * 手续费接口
 */
@RemoteService("/serviceChargeService")
public interface ServiceChargeService {

	/**
	 * 获取用户免提额度
	 */
    double getUserFreeCharge(long userId);
}
