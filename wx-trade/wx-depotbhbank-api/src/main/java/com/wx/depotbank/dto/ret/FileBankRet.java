/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

import java.math.BigDecimal;

/**
 * @ClassName: FileBankRet SFTP文件模式 充值、提现、红包对账
 * @version 1.0
 * @Desc: FileBankRet
 * @author shiliang.feng
 * @date 2017年8月17日上午11:21:32
 * @history v1.0
 *
 */
public class FileBankRet {
	/**
	 * 订单号
	 */
	private String OrdNo;
	/**
	 * 订单日期
	 */
	private String CreDt;
	/**
	 * 手续费（元）
	 */
	private BigDecimal FeeAmt;

	/**
	 * 交易金额（元）
	 */
	private BigDecimal TransAmt;
	/**
	 * 账户存管平台Id
	 */
	private String PlaCustId;
	/**
	 * 商户流水号 唯一约束
	 */
	private String MerBillNo;
	/**
	 * 充值渠道(WWW-PC,CAS-手机端,SPS-线下大额)
	 * 提现状态(W3:系统受理中,W4:银行受理中,S1:银行交易成功,F1:付款失败,F2:付款核销,R9:审批拒绝)
	 */
	private String ChargeCorg;
	/**
	 * 失败原因
	 */
	private String type;
	/**
	 * 失败原因
	 */
	private String FalRsn;
	public String getOrdNo() {
		return OrdNo;
	}
	public void setOrdNo(String ordNo) {
		OrdNo = ordNo;
	}
	public String getCreDt() {
		return CreDt;
	}
	public void setCreDt(String creDt) {
		CreDt = creDt;
	}
	public BigDecimal getFeeAmt() {
		return FeeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		FeeAmt = feeAmt;
	}
	public BigDecimal getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		TransAmt = transAmt;
	}
	public String getPlaCustId() {
		return PlaCustId;
	}
	public void setPlaCustId(String plaCustId) {
		PlaCustId = plaCustId;
	}
	public String getMerBillNo() {
		return MerBillNo;
	}
	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}
	public String getChargeCorg() {
		return ChargeCorg;
	}
	public void setChargeCorg(String chargeCorg) {
		ChargeCorg = chargeCorg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFalRsn() {
		return FalRsn;
	}
	public void setFalRsn(String falRsn) {
		FalRsn = falRsn;
	}

	 

}
