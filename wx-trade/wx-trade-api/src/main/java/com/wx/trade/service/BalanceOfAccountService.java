package com.wx.trade.service;

import com.wx.service.Result;
import com.wx.trade.enums.CompareTypeEnum;

import my.comp.rmi.annotation.RemoteService;


/**
 * 对账接口
 */
@Deprecated
@RemoteService("/balanceOfAccountService")
public interface BalanceOfAccountService {

	/**
	 * 红包，提现，充值对账
	 */
	Result<String> balanceOfAccount(CompareTypeEnum compareType);
}
