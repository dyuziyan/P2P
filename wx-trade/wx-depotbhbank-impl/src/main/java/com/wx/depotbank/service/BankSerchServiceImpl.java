/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.BaseReq;
import com.wx.depotbank.dto.ret.QueryMerchantAcctsRet;
import com.wx.depotbank.enums.BizType;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @ClassName: TransactionServiceImpl
 * @version 1.0
 * @Desc: TransactionServiceImpl
 * @author xiaojun.zhou
 * @date 2017年6月22日下午4:40:00
 * @history v1.0
 *
 */
@Service
public class BankSerchServiceImpl implements BankSerchService {

	private static Logger logger = LoggerFactory.getLogger(BankSerchServiceImpl.class);
	
	@Override
	public QueryMerchantAcctsRet queryMerchantAccts() throws BankException {
		BaseReq baseReq = new BaseReq();
		baseReq.setBiz_type(BizType.QueryMerchantAccts.getKey());
		logger.info("商户自主充值(后台方式)，rechargeCustReq = {}", baseReq);
		return RequestUtils.sendParam(baseReq, QueryMerchantAcctsRet.class, RequestType.BACKSTAGE,
				baseReq.getMacHead(BankConstant.PARTNER_ID, BankConstant.VERSION_NO)); 
	} 

}
