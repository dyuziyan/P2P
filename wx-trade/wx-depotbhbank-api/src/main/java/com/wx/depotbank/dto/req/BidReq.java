/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;


import com.wx.depotbank.enums.BizType;

/**
 * @ClassName: BidReq
 * @version 1.0
 * @Desc: 建标
 * @author xiaojun.zhou
 * @date 2017年6月23日上午10:45:58
 * @history v1.0
 *
 */
public class BidReq extends BaseReq {

	private static final long serialVersionUID = -7846636868921169446L;

	public String BorrowId;// 标的ID
	public String BorrowTyp;// 标的属性
	public long BorrowerAmt;// 标的金额
	public double BorrowerInterestAmt;// 标利息
	public String BorrCustId;// 借款人账户存管平台客户号
	public String AccountName;//对公 账户户名
	public String GuaranteeNo;// 担保人账号
	public String BorrowerStartDate;// 募集开始日
	public String BorrowerEndDate;// 募集到期日
	public String BorrowerRepayDate;// 标到期日
	public int ReleaseType;// 放款方式
	public String InvestDateType;// 投资期限类型
	public String InvestPeriod;// 投资期限
	public String BorrowerDetails;// 标的详细信息

	
	public String FileName;//放款文件名
	
	
	
	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getBorrowId() {
		return BorrowId;
	}

	public void setBorrowId(String borrowId) {
		BorrowId = borrowId;
	}

	public String getBorrowTyp() {
		return BorrowTyp;
	}

	public void setBorrowTyp(String borrowTyp) {
		BorrowTyp = borrowTyp;
	}

	public String getAccountName() {
		if(AccountName == null ){
			return "";
		}
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public long getBorrowerAmt() {
		return BorrowerAmt;
	}

	public void setBorrowerAmt(long borrowerAmt) {
		BorrowerAmt = borrowerAmt;
	}

	public double getBorrowerInterestAmt() {
		return BorrowerInterestAmt;
	}

	public void setBorrowerInterestAmt(double borrowerInterestAmt) {
		BorrowerInterestAmt = borrowerInterestAmt;
	}

	public String getBorrCustId() {
		return BorrCustId;
	}

	public void setBorrCustId(String borrCustId) {
		BorrCustId = borrCustId;
	}

	public String getGuaranteeNo() {
		if (GuaranteeNo == null)
			return "";
		return GuaranteeNo;
	}

	public void setGuaranteeNo(String guaranteeNo) {
		GuaranteeNo = guaranteeNo;
	}

	public String getBorrowerStartDate() {
		return BorrowerStartDate;
	}

	public void setBorrowerStartDate(String borrowerStartDate) {
		BorrowerStartDate = borrowerStartDate;
	}

	public String getBorrowerEndDate() {
		return BorrowerEndDate;
	}

	public void setBorrowerEndDate(String borrowerEndDate) {
		BorrowerEndDate = borrowerEndDate;
	}

	public String getBorrowerRepayDate() {
		return BorrowerRepayDate;
	}

	public void setBorrowerRepayDate(String borrowerRepayDate) {
		BorrowerRepayDate = borrowerRepayDate;
	}

	public int getReleaseType() {
		return ReleaseType;
	}

	public void setReleaseType(int releaseType) {
		ReleaseType = releaseType;
	}

	public String getInvestDateType() {
		if (InvestDateType == null)
			return "";
		return InvestDateType;
	}

	public void setInvestDateType(String investDateType) {
		InvestDateType = investDateType;
	}

	public String getInvestPeriod() {
		if (InvestPeriod == null)
			return "";
		return InvestPeriod;
	}

	public void setInvestPeriod(String investPeriod) {
		InvestPeriod = investPeriod;
	}

	public String getBorrowerDetails() {
		if (BorrowerDetails == null)
			return "";
		return BorrowerDetails;
	}

	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

	/**
	 * 
	 * 描述：建标（后台方式） -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月23日上午10:53:13
	 * @return
	 */
	public String getCreateBidMac(String partner_id, String version_no) {
		this.biz_type = BizType.CreateBid.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getBorrowId() + getBorrowTyp() + getBorrowerAmt() + getBorrowerInterestAmt()
				+ getBorrCustId()+getAccountName() + getGuaranteeNo() + getBorrowerStartDate()+ getBorrowerEndDate()+getBorrowerRepayDate() + getReleaseType() + getInvestDateType()
				+ getInvestPeriod() + getBorrowerDetails() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}
	
	/**
	 * 放款
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getReleaseBidMac(String partner_id, String version_no){
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getBorrowId() + getFileName() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
		
	}
}
