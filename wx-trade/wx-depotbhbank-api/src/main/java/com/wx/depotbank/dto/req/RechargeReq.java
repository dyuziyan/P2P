/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import com.alibaba.fastjson.JSONObject;
import com.wx.depotbank.enums.BizType;

/**
 * @ClassName: RechargeReq
 * @version 1.0
 * @Desc: 充值提现Req
 * @author xiaojun.zhou
 * @date 2017年6月22日下午3:49:53
 * @history v1.0
 *
 */
public class RechargeReq extends BaseReq {

	private static final long serialVersionUID = 452478712767248828L;

	public Long TransAmt;// 交易金额
	public String FeeType;// 手续费模式 对应FeeTypeEnums枚举
	public Long MerFeeAmt;// 商户手续费收入
	public String MarketAmt;// 营销金额（保留字段，暂不启用）
	public String PartnerId;
	
	public String SNum;		//唯一标识符

	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	public Long getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(Long transAmt) {
		TransAmt = transAmt;
	}

	public String getFeeType() {
		return FeeType;
	}

	public void setFeeType(String feeType) {
		FeeType = feeType;
	}

	public Long getMerFeeAmt() {
		return MerFeeAmt;
	}

	public void setMerFeeAmt(Long merFeeAmt) {
		MerFeeAmt = merFeeAmt;
	}

	public String getMarketAmt() {
		if (MarketAmt == null)
			return "";
		return MarketAmt;
	}

	public void setMarketAmt(String marketAmt) {
		MarketAmt = marketAmt;
	}
	public String getSNum() {
		return SNum;
	}

	public void setSNum(String sNum) {
		SNum = sNum;
	}

	public String toJson(String partnerId) {
		this.PartnerId = partnerId;
		return JSONObject.toJSONString(this);
	}

	/**
	 * 
	 * 描述：用户充值(页面方式) -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午3:55:39
	 * @return
	 */
	public String getWebRechargeMac(String partner_id, String version_no) {
		this.biz_type = BizType.WebRecharge.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getTransAmt() + getFeeType() + getMerFeeAmt() + getMarketAmt()
				+ getPageReturnUrl() + getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}

	/**
	 * 
	 * 描述：用户提现(页面方式) -- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月22日下午4:44:07
	 * @return
	 */
	public String getDrawingsMac(String partner_id, String version_no) {
		this.biz_type = BizType.Drawings.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getTransAmt() + getFeeType() + getMerFeeAmt() + getPageReturnUrl()
				+ getBgRetUrl() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}
	
	
	/**
	 * 现金红包 -- 签名
	 */
	public String getCashRedPacket(String partner_id, String version_no) {
		this.biz_type = BizType.ExperBonus.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getTransAmt() + getMerFeeAmt() + getMerPriv();
		logger.info("待签名串：mac={}", str);
		return str;
	}
	
	/**
	 * 
	 * 描述：用户资金转移（后台方式）-- 签名
	 * @author xiaojun.zhou 
	 * @date 2017年8月15日上午11:06:29
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getUserBalTransfer(String partner_id, String version_no) {
		this.biz_type = BizType.UserBalTransfer.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getPlaCustId() + getTransAmt();
		logger.info("待签名串：mac={}", str);
		return str;
	}
}
