/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: BizType
 * @version 1.0
 * @Desc: BizType
 * @author xiaojun.zhou
 * @date 2017年6月6日下午4:45:57
 * @history v1.0
 *
 */
public enum BizType {
	RealNameWeb("RealNameWeb", "新用户注册(页面方式)"), 
	BindCardWeb("BindCardWeb", "修改绑定银行卡(页面方式)"),
	BindMobileNo("BindMobileNo", "修改手机号(页面方式)"),
	BindPass("BindPass", "修改/找回支付密码(页面方式)"),
	sendUapMsg("sendUapMsg", "动态口令申请(页面方式)"),
	WebRecharge("WebRecharge", "用户充值(页面方式)"),
	Drawings("Drawings", "用户提现(页面方式)"),
	CreateBid("CreateBid", "建标（后台方式)"),
	BackInvest("BackInvest", "用户投标(后台方式)"),
	QueryUserInf("QueryUserInf", "用户信息查询(后台方式)"),
	ExperBonus("ExperBonus", "现金红包(后台方式)"),
	CBHBNetLoanRegister("CBHBNetLoanRegister","用户注册开户APP"),
	CBHBNetLoanRecharge("CBHBNetLoanRecharge","充值APP"),
	CBHBNetLoanWithdraw("CBHBNetLoanWithdraw","提现APP"),
	CBHBNetLoanUpdatePhone("CBHBNetLoanUpdatePhone","修改手机号APP"),
	CBHBNetLoanUpdatePassword("CBHBNetLoanUpdatePassword","修改密码APP"),
	CBHBNetLoanGetPassword("CBHBNetLoanGetPassword","找回密码APP"),
	CBHBNetLoanBindCardMessage("CBHBNetLoanBindCardMessage","修改绑定卡APP"),
	QueryBalance("QueryBalance","用户余额查询（后台方式）"),
	QueryTransStat("QueryTransStat","交易状态查询（后台方式）"),
	
	MercRecharge("MercRecharge", "商户自主充值(后台方式)"),
	MercWithdraw("MercWithdraw", "商户自主提现(后台方式)"),
	FileRelease("FileRelease","放款"),
	FileRepayment("FileRepayment","还款"),
	
	QueryMerchantTrans("QueryMerchantTrans","商户 账户 交易查询"),
	QueryChargeAccount("QueryChargeAccount","大额充值账号查询"),
	QueryChargeDetail("QueryChargeDetail","大额充值记录查询"),
	QueryMerchantAccts("QueryMerchantAccts","商户账户查询（后台方式）"),
	
	UserBalTransfer("UserBalTransfer","用户资金转移（后台方式）"),
	OpenChargeAccount("OpenChargeAccount","非渤海对公账号开设充值户"),
	
	RealNameWebResult("RealNameWebResult","新用户注册(页面方式)返回"),
	BindCardWebResult("BindCardWebResult","修改绑定银行卡(页面方式)返回"),
	BindMobileNoResult("BindMobileNoResult","修改手机号(页面方式)返回"),
	BindPassResult("BindPassResult","修改/找回支付密码(页面方式)返回"),
	WebRechargeResult("WebRechargeResult","用户充值(页面方式)返回"),
	DrawingsResult("DrawingsResult","用户提现（页面方式）返回")
	;

	private BizType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public static BizType get(String key) {
		BizType[] codes = BizType.values();
		for (BizType code : codes) {
			if (code.getKey().equals(key)) {
				return code;
			}
		}
		return null;
	}
}
