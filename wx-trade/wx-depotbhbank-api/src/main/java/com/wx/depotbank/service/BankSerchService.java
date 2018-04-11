/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;
 

import com.wx.depotbank.dto.ret.QueryMerchantAcctsRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: BankSerchService
 * @version 1.0
 * @Desc: 标的service
 * @author shiliang.feng
 * @date 2017年7月21日上午11:09:15
 * @history v1.0
 *
 */
@RemoteService("/bankSerchService")
public interface BankSerchService {
	
	/**
	 * 查询商户账户
	 * @param baseReq
	 * @return
	 */
	public QueryMerchantAcctsRet queryMerchantAccts() throws BankException ;

}
