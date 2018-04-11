/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import com.wx.depotbank.dto.req.MarketingReq;
import com.wx.depotbank.dto.ret.MarketingRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: RechargeMangerService
 * @version 1.0
 * @Desc: 营销账户 交易
 * @author shiliang.feng
 * @date 2018年3月28日下午2:09:46
 * @history v1.0
 */
@RemoteService("/rechargeMangerService")
public interface RechargeMangerService {


	/**
	 * 
	 * 描述：营销账户充值
	 * @author shiliang.feng 
	 * @date 2018年3月28日下午2:47:40
	 * @param marketingReq
	 * @return
	 * @throws BankException
	 */
	public MarketingRet rechargeByMarketing(MarketingReq marketingReq)throws BankException;
	
	/**
	 * 
	 * 描述：营销账户提现
	 * @author shiliang.feng 
	 * @date 2018年3月28日下午2:47:46
	 * @param marketingReq
	 * @return
	 * @throws BankException
	 */
	public MarketingRet withdrawByMarketing(MarketingReq marketingReq) throws BankException;
	
	
	/**
	 * 
	 * 描述：营销账户查询
	 * @author shiliang.feng 
	 * @date 2018年3月28日下午2:49:09
	 * @param marketingReq
	 * @return
	 * @throws BankException
	 */
	public MarketingRet queryMarketing(MarketingReq marketingReq) throws BankException;
}
