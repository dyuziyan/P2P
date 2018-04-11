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
    
	RD000014("RD000014","该卡号已解绑"),
    RD99("RD99", "银行异常");
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
