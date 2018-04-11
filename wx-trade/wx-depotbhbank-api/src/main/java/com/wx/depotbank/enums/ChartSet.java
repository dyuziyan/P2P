/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.enums;

/**
 * @ClassName: ChartSet
 * @version 1.0
 * @Desc: ChartSet
 * @author xiaojun.zhou
 * @date 2017年6月6日下午4:43:46
 * @history v1.0
 *
 */
public enum ChartSet {
	
	GBK("GBK", "00"), GB2312("GB2312", "01"), UTF8("UTF-8", "02");

	private ChartSet(String key, String value) {
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
