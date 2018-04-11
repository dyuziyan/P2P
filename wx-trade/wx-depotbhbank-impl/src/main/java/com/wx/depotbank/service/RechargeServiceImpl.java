/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

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
public class RechargeServiceImpl implements RechargeService {

	private static Logger logger = LoggerFactory.getLogger(RechargeServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> recharge(RechargeReq rechargeReq) throws BankException {
		logger.info("用户充值(页面方式)，rechargeReq = {}", rechargeReq);
		return RequestUtils.sendParam(rechargeReq, Map.class, RequestType.WEB,
				rechargeReq.getWebRechargeMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> withdraw(RechargeReq rechargeReq) throws BankException {
		logger.info("用户提现(页面方式)，rechargeReq = {}", rechargeReq);
		return RequestUtils.sendParam(rechargeReq, Map.class, RequestType.WEB,
				rechargeReq.getDrawingsMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public String webResultApp(RechargeReq rechargeReq) throws BankException {
		logger.info("APP(页面方式)，rechargeReq = {}", rechargeReq);
		return RequestUtils.sendParam(rechargeReq, String.class, RequestType.WEB_APP,
				rechargeReq.toJson(BankConstant.PARTNER_ID), rechargeReq.getTransid());
	}

}
