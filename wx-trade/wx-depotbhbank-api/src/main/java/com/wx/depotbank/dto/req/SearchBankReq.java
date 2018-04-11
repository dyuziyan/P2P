/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import com.wx.depotbank.enums.BizType;

/**
 * @ClassName: SearchReq
 * @version 1.0
 * @Desc: 查询类Req
 * @author xiaojun.zhou
 * @date 2017年6月22日下午3:49:53
 * @history v1.0
 *
 */
public class SearchBankReq extends BaseReq {

	private static final long serialVersionUID = 2581356639196276623L;

	public String AccountTyp;

	public String AccountNo;

	public String QueryTyp;

	public String StartDate;

	public String EndDate;

	public String TransId;

	public String PageNo;
	public String TxnTyp;// 交易类别(1-新开 2-修改（修改户名和清算行号）)
	public String AccountName;// 对公账户户名
	public String AccountBk;// 清算行号

	/**
	 * 账号属性 1-对私 2-对公
	 * 
	 * @return
	 */
	public String getAccountTyp() {
		return AccountTyp;
	}

	public void setAccountTyp(String accountTyp) {
		AccountTyp = accountTyp;
	}

	/**
	 * 存管平台 客户号或对公账号 根据 AccountTyp 取不同值 1-账户存管平台客户号 2-对公账号
	 * 
	 * @return
	 */
	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	/**
	 * 查询方式 1-历史记录查询 2-当前记录查询
	 * 
	 * @return
	 */
	public String getQueryTyp() {
		return QueryTyp;
	}

	public void setQueryTyp(String queryTyp) {
		QueryTyp = queryTyp;
	}

	/**
	 * 开始日期 QueryTyp-1 必输。yyyyMMdd
	 * 
	 * @return
	 */
	public String getStartDate() {
		if (this.StartDate == null) {
			return "";
		}
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	/**
	 * 结束 日期 QueryTyp-1 必输。yyyyMMdd。 起始日期与结束日期最大间隔为 7 日 （如
	 * 20170101-20170107）且结束日最 大为 T-2 日
	 * 
	 * @return
	 */
	public String getEndDate() {
		if (this.EndDate == null) {
			return "";
		}
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	/**
	 * 账户存管平台 流水号 QueryTyp-2 必输 存管平台返回两个自然日内此流水号至 最新一笔流水间所有记录（若无历史流 水送
	 * 0，则返回两天内所有记录
	 * 
	 * @return
	 */
	public String getTransId() {
		if (TransId == null) {
			return "";
		}
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	/**
	 * 页数 当前查询页数（首次查询送 1）
	 * 
	 * @return
	 */
	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	public String getTxnTyp() {
		return TxnTyp;
	}

	public void setTxnTyp(String txnTyp) {
		TxnTyp = txnTyp;
	}

	public String getAccountName() {
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public String getAccountBk() {
		return AccountBk;
	}

	public void setAccountBk(String accountBk) {
		AccountBk = accountBk;
	}

	/**
	 * 商户账户 交易查询
	 * 
	 * @param partner_id
	 * @param version_no
	 * @param StartDate
	 * @param EndDate
	 * @return
	 */
	public String getQueryMerchantTransMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getStartDate() + getEndDate() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 大额充值账号查询
	 * 
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getQueryChargeAccountMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getAccountTyp() + getAccountNo() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 大额充值记录查询
	 * 
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getQueryChargeDetailMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getAccountTyp() + getAccountNo() + getQueryTyp() + getStartDate() + getEndDate()
				+ getTransId() + getPageNo() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：非渤海对公账号开设充值户（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月25日下午2:22:23
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getOpenChargeAccountMac(String partner_id, String version_no) {
		this.biz_type = BizType.OpenChargeAccount.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getTxnTyp() + getAccountTyp() + getAccountNo() + getAccountName() + getAccountBk();
		logger.info("待签名串：mac={}", str);
		return str;
	}
}
