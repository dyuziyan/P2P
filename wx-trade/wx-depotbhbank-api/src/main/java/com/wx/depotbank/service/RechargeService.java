/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: TransactionService
 * @version 1.0
 * @Desc: TransactionService
 * @author xiaojun.zhou
 * @date 2017年6月22日下午4:11:55
 * @history v1.0
 *
 */
@RemoteService("/rechargeService")
public interface RechargeService {

	/**
	 * 
	 * 描述：充值
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午4:39:22
	 * @param rechargeReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> recharge(RechargeReq rechargeReq) throws BankException;

	/**
	 * 
	 * 描述：提现
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午4:39:29
	 * @param rechargeReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> withdraw(RechargeReq rechargeReq) throws BankException;
	
	/**
	 * 
	 * 描述：app请求
	 * @author xiaojun.zhou 
	 * @date 2017年7月31日上午11:00:06
	 * @param rechargeReq
	 * @return
	 * @throws BankException
	 */
	public String webResultApp(RechargeReq rechargeReq) throws BankException;
}
