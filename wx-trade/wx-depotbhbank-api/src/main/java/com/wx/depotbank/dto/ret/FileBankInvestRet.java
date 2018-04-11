/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

import java.math.BigDecimal;

/**
 * @ClassName: FileBankInvestRet SFTP文件模式 投资对账接受银行返回实体
 * @version 1.0
 * @Desc: FileBankInvestRet
 * @author shiliang.feng
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class FileBankInvestRet{
	
	private String TransId;
	
	private String MercId;
	
	private String PlaCustId;
	
	private BigDecimal TransAmt;
	
	private String BorrowId;
	
	private String CreDt;
	
	private String CreTm;
	
	private String OrdSts;
	
	private String MerBillNo;

	/**
	 * 订单号
	 * @return
	 */
	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}
	/**
	 * 商户号
	 * @return
	 */
	public String getMercId() {
		return MercId;
	}

	public void setMercId(String mercId) {
		MercId = mercId;
	}
	/**
	 * 账户存管平台ID
	 * @return
	 */
	public String getPlaCustId() {
		return PlaCustId;
	}

	public void setPlaCustId(String plaCustId) {
		PlaCustId = plaCustId;
	}
	/**
	 * 交易金额
	 * @return
	 */
	public BigDecimal getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		TransAmt = transAmt;
	}
	/**
	 * 标的 ID  
	 * @return
	 */
	public String getBorrowId() {
		return BorrowId;
	}

	public void setBorrowId(String borrowId) {
		BorrowId = borrowId;
	}
	/**
	 * 订单日期
	 * @return
	 */
	public String getCreDt() {
		return CreDt;
	}

	public void setCreDt(String creDt) {
		CreDt = creDt;
	}
	/**
	 * 订单时间
	 * @return
	 */
	public String getCreTm() {
		return CreTm;
	}

	public void setCreTm(String creTm) {
		CreTm = creTm;
	}
	/**
	 * 订单状态
	 * @return
	 */
	public String getOrdSts() {
		return OrdSts;
	}

	public void setOrdSts(String ordSts) {
		OrdSts = ordSts;
	}
	/**
	 * 商户流水号
	 * @return
	 */
	public String getMerBillNo() {
		return MerBillNo;
	}

	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}
	
	

}
