/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: BankCode
 * @version 1.0
 * @Desc: 银行编码
 * @author xiaojun.zhou
 * @date 2017年7月5日下午5:16:42
 * @history v1.0
 *
 */
public enum BankCode {
	ICBC("ICBC", "工商银行"), ABC("ABC", "农业银行"), CMB("CMB", "招商银行"), CCB("CCB", "建设银行"), BOC("BOC", "中国银行"), BCOM("BCOM",
			"交通银行"), CMBC("CMBC", "民生银行"), CIB("CIB", "兴业银行"), CBHB("CBHB", "渤海银行"), GDB("GDB", "广发银行"), HXB("HXB",
					"华夏银行"), PAB("PAB", "平安银行"), PSBC("PSBC", "邮储银行"), SPDB("SPDB", "上海浦东发展银行"), CEB("CEB", "光大银行");

	private BankCode(String key, String value) {
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

	public static BankCode get(String key) {
		BankCode[] codes = BankCode.values();
		for (BankCode code : codes) {
			if (code.getKey().equals(key)) {
				return code;
			}
		}
		return null;
	}
}
