/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: TradeStatusRet
 * @version 1.0
 * @Desc: TradeStatusRet
 * @author xiaojun.zhou
 * @date 2017年8月30日下午2:10:57
 * @history v1.0
 *
 */
public class TradeStatusRet extends BaseRet {

	private static final long serialVersionUID = -4752387365894761296L;

	public String TransStat;// 交易状态
	public String FreezeId;// 冻结编号
	public String FalRsn;// 失败原因

	public String getTransStat() {
		return TransStat;
	}

	public void setTransStat(String transStat) {
		TransStat = transStat;
	}

	public String getFreezeId() {
		return FreezeId;
	}

	public void setFreezeId(String freezeId) {
		FreezeId = freezeId;
	}

	public String getFalRsn() {
		return FalRsn;
	}

	public void setFalRsn(String falRsn) {
		FalRsn = falRsn;
	}
}
