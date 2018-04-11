/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.util.Map;

import com.wx.depotbank.dto.req.CreateAccountAppReq;
import com.wx.depotbank.dto.req.CreateAccountReq;
import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.dto.req.SearchBankReq;
import com.wx.depotbank.dto.ret.BaseRet;
import com.wx.depotbank.dto.ret.CreateAccountRet;
import com.wx.depotbank.dto.ret.RechargeRet;
import com.wx.depotbank.dto.ret.SearchBankRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: MemberService
 * @version 1.0
 * @Desc: 用户管理接口
 * @author xiaojun.zhou
 * @date 2017年6月5日下午4:11:33
 * @history v1.0
 *
 */
@RemoteService("/memberService")
public interface MemberService {
	/**
	 * 用户批注册(SFTP) 待考虑执行完后是否要保留
	 */
	public BaseRet batchRegisterToDepotbank() throws BankException;

	/**
	 * 
	 * 描述：新用户注册(页面方式)
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午11:12:59
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> realNameWebResult(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：修改绑定银行卡(页面方式)
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午2:34:27
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> bindCardWeb(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：修改手机号(页面方式)
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午2:35:38
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> bindMobileNo(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：修改/找回支付密码（页面方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午2:36:00
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public Map<String, Object> bindPass(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：动态口令申请（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午2:35:43
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public CreateAccountRet sendUapMsg(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：用户信息查询（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月29日下午4:08:05
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public CreateAccountRet queryUserInf(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：app请求
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日上午11:12:59
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public String webResultApp(CreateAccountAppReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：用户余额查询（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年8月8日上午9:22:56
	 * @param accountReq
	 * @return
	 * @throws BankException
	 */
	public CreateAccountRet QueryBalance(CreateAccountReq accountReq) throws BankException;

	/**
	 * 
	 * 描述：用户资金迁移
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年8月15日上午11:02:14
	 * @param rechargeReq
	 * @return
	 * @throws BankException
	 */
	public RechargeRet userBalTransfer(RechargeReq rechargeReq) throws BankException;

	/**
	 * 
	 * 描述：非渤海对公账号开设充值户（后台方式）
	 * @author xiaojun.zhou 
	 * @date 2017年9月25日下午2:22:59
	 * @param searchBankReq
	 * @return
	 * @throws BankException
	 */
	public SearchBankRet OpenChargeAccount(SearchBankReq searchBankReq) throws BankException;
}
