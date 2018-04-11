/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: TradeStatus
 * @version 1.0
 * @Desc: 银行交易状态
 * @author xiaojun.zhou
 * @date 2017年8月30日上午11:46:06
 * @history v1.0
 *
 */
public enum TradeStatus {
	S1("S1", "交易成功,已清分"),
	F1("F1", "交易失败,未清分"),
	W2("W2", "请求处理中"),
	W3("W3", "系统受理中"),
	W4("W4", "银行受理中"),
	S2("S2", "撤标解冻成功"),
	S3("S3", "放款解冻成功"),
	B1("B1", "部分成功,部分冻结"),
	R9("R9", "审批拒绝"),
	F2("F2", "撤标解冻失败");

	private TradeStatus(String key, String value) {
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
}
