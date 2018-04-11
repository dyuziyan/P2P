/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.exception;

/**
 * @ClassName: BankErrorCode
 * @version 1.0
 * @Desc: BankErrorCode
 * @author xiaojun.zhou
 * @date 2017年9月7日上午10:34:18
 * @history v1.0
 *
 */
public enum BankErrorCode {
	
	WXCF01("WXCF01", "数据异常"), 
	WXCF02("WXCF02", "请求过于频繁，请稍后再试"), 
	WXCF03("WXCF03", "返回数据为空"), 
	WXCF04("WXCF04", "请求银行失败，请稍后再试"),

	// 银行异常
//	MCG99993("MCG99993","迁移资金失败，请联系客服"),
	IPS0008("IPS0008", "数据异常"), 
	PWM00015("PWM00015", "获取短信验证码失败"), 
	URM80015("URM80015", "该用户不存在，请开通存管帐号"), 
	URM90003("URM90003", "该用户不存在，请开通存管帐号"), 
	RPM66004("RPM66004", "对不起您无此交易权限，请联系客服处理"), 
	URM30050("URM30050", "该用户不存在，请开通存管帐号"), 
	TAM20401("TAM20401", "该标的不是存管标"), 
	URM80009("URM80009", "您输入的手机号码不正确"), 
	PWM21422("PWM21422", "对不起，金额超限"), 
	URM21002("URM21002", "余额不足"), 
	CMM20164("CMM20164", "验证码错误"),
	PWM25538("PWM25538","清算行号上送不正确，请核对后再试");

	private BankErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static BankErrorCode get(String key) {
		BankErrorCode[] codes = BankErrorCode.values();
		for (BankErrorCode code : codes) {
			if (code.getCode().equals(key)) {
				return code;
			}
		}
		return null;
	}
}
