/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import com.wx.depotbank.enums.BizType;

/**
 * @ClassName: InvestReq
 * @version 1.0
 * @Desc: 用户投标
 * @author xiaojun.zhou
 * @date 2017年6月23日上午11:00:59
 * @history v1.0
 *
 */
public class InvestReq extends BaseReq {

	private static final long serialVersionUID = 1638466365784029386L;
	
	public String BorrowId;// 标的ID
	public String OldMerBillNo;// 原商户流水号
	public String BusiType="1";// 业务类型---默认传1---2目前为先解冻后投，先不处理，初步估计是存留标的处理用
	public String SmsCode;// 短信验证码
	public Long TransAmt;// 交易金额
	public String MarketAmt;// 营销金额

	public String getOldMerBillNo() {
		return OldMerBillNo;
	}

	public void setOldMerBillNo(String oldMerBillNo) {
		OldMerBillNo = oldMerBillNo;
	}

	public String getBusiType() {
		return BusiType;
	}

	public void setBusiType(String busiType) {
		BusiType = busiType;
	}

	public String getSmsCode() {
		return SmsCode;
	}

	public void setSmsCode(String smsCode) {
		SmsCode = smsCode;
	}

	public Long getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(Long transAmt) {
		TransAmt = transAmt;
	}

	public String getMarketAmt() {
		if (MarketAmt == null)
			return "";
		return MarketAmt;
	}

	public void setMarketAmt(String marketAmt) {
		MarketAmt = marketAmt;
	}
	
	public String getBorrowId() {
		return BorrowId;
	}

	public void setBorrowId(String borrowId) {
		BorrowId = borrowId;
	}

	/**
	 * 
	 * 描述：用户投标（后台方式）-- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午4:44:07
	 * @return
	 */
	public String getBackInvestMac(String partner_id, String version_no) {
		this.biz_type = BizType.BackInvest.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String busiType="1".equals(getBusiType())?getBusiType():getOldMerBillNo() + getBusiType() ;
		String str = macHead + busiType+ getPlaCustId() + getSmsCode() + getTransAmt()+ getMarketAmt() + getBorrowId() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

}
