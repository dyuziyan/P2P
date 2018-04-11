/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: RechargeRet
 * @version 1.0
 * @Desc: RechargeRet
 * @author xiaojun.zhou
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class RechargeRet extends BaseRet {

	private static final long serialVersionUID = -6409053504959958741L;

	private String TransAmt;// 交易金额
	private String TransId;// 账户存管平台流水号
	private String MerFeeAmt;// 商户手续费收入
	private String FeeAmt;// 手续费

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	public String getMerFeeAmt() {
		return MerFeeAmt;
	}

	public void setMerFeeAmt(String merFeeAmt) {
		MerFeeAmt = merFeeAmt;
	}

	public String getFeeAmt() {
		return FeeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}

}
