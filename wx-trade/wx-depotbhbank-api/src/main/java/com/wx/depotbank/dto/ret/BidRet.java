/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: BidRet
 * @version 1.0
 * @Desc: BidRet
 * @author xiaojun.zhou
 * @date 2017年7月19日上午11:12:22
 * @history v1.0
 *
 */
public class BidRet extends BaseRet {

	private static final long serialVersionUID = 22830831956132842L;

	private String TransId;// 账户存管平台流水
	private String BorrowId;// 标的ID
	private String BorrowerAmt;// 标的金额
	private String TransAmt;// 交易金额
	private String FeeAmt;// 手续费
	private String FreezeId;// 冻结编号

	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	public String getBorrowId() {
		return BorrowId;
	}

	public void setBorrowId(String borrowId) {
		BorrowId = borrowId;
	}

	public String getBorrowerAmt() {
		return BorrowerAmt;
	}

	public void setBorrowerAmt(String borrowerAmt) {
		BorrowerAmt = borrowerAmt;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getFeeAmt() {
		return FeeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}

	public String getFreezeId() {
		return FreezeId;
	}

	public void setFreezeId(String freezeId) {
		FreezeId = freezeId;
	}

}
