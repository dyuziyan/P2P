/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dao.QueryUserListDao;
import com.wx.depotbank.dto.req.CreateAccountAppReq;
import com.wx.depotbank.dto.req.CreateAccountReq;
import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.dto.req.SearchBankReq;
import com.wx.depotbank.dto.ret.BaseRet;
import com.wx.depotbank.dto.ret.CreateAccountRet;
import com.wx.depotbank.dto.ret.RechargeRet;
import com.wx.depotbank.dto.ret.SearchBankRet;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

/**
 * @ClassName: MemberServiceImpl
 * @version 1.0
 * @Desc: MemberServiceImpl
 * @author xiaojun.zhou
 * @date 2017年6月5日下午4:58:07
 * @history v1.0
 *
 */
@Service
public class MemberServiceImpl implements MemberService {

	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Resource
	private QueryUserListDao QueryUserListDao;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> realNameWebResult(CreateAccountReq accountReq) throws BankException {
		logger.info("新用户注册(页面方式)，accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, Map.class, RequestType.WEB,
				accountReq.getRealNameWebMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> bindCardWeb(CreateAccountReq accountReq) throws BankException {
		logger.info("修改绑定银行卡(页面方式)，accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, Map.class, RequestType.WEB,
				accountReq.getBindCardWebMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> bindMobileNo(CreateAccountReq accountReq) throws BankException {
		logger.info("修改手机号(页面方式)，accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, Map.class, RequestType.WEB,
				accountReq.getBindMobileNoMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> bindPass(CreateAccountReq accountReq) throws BankException {
		logger.info("修改/找回支付密码（页面方式），accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, Map.class, RequestType.WEB,
				accountReq.getBindPassMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public CreateAccountRet sendUapMsg(CreateAccountReq accountReq) throws BankException {
		logger.info("动态口令申请（后台方式），accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, CreateAccountRet.class, RequestType.BACKSTAGE,
				accountReq.getSendUapMsgMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public CreateAccountRet queryUserInf(CreateAccountReq accountReq) throws BankException {
		logger.info("用户信息查询（后台方式），accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, CreateAccountRet.class, RequestType.BACKSTAGE,
				accountReq.getQueryUserInfMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public BaseRet batchRegisterToDepotbank() throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String webResultApp(CreateAccountAppReq accountReq) throws BankException {
		logger.info("APP(页面方式)，accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, String.class, RequestType.WEB_APP,
				accountReq.toJson(BankConstant.PARTNER_ID), accountReq.getTransid());
	}

	@Override
	public CreateAccountRet QueryBalance(CreateAccountReq accountReq) throws BankException {
		logger.info("用户余额查询（后台方式），accountReq = {}", accountReq);
		return RequestUtils.sendParam(accountReq, CreateAccountRet.class, RequestType.BACKSTAGE,
				accountReq.getQueryBalanceMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public RechargeRet userBalTransfer(RechargeReq rechargeReq) throws BankException {
		logger.info("用户资金转移（后台方式），rechargeReq = {}", rechargeReq);
		return RequestUtils.sendParam(rechargeReq, RechargeRet.class, RequestType.BACKSTAGE,
				rechargeReq.getUserBalTransfer(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

	@Override
	public SearchBankRet OpenChargeAccount(SearchBankReq searchBankReq) throws BankException {
		logger.info("非渤海对公账号开设充值户（后台方式），searchBankReq = {}", searchBankReq);
		return RequestUtils.sendParam(searchBankReq, SearchBankRet.class, RequestType.BACKSTAGE,
				searchBankReq.getOpenChargeAccountMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

}
