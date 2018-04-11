/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;


/**
 * @ClassName: RechargeReq
 * @version 1.0
 * @Desc: 商户账户充值提现 	（后台方式）Req
 * @author shiliang.feng
 * @date 2017年7月17日下午3:49:53
 * @history v1.0
 *
 */
public class RechargeCustReq extends BaseReq {

	private static final long serialVersionUID = 452478712767248828L;

	public long TransAmt;// 交易金额
	public String MerAccTyp;// 手续费模式
	public String MerPriv;// 营销金额（保留字段，暂不启用）
 
	public long getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(long transAmt) {
		this.TransAmt = transAmt;
	}

	public String getMerAccTyp() {
		if(MerAccTyp == null){
			return "";
		}
		return MerAccTyp;
	}

	public void setMerAccTyp(String merAccTyp) {
		this.MerAccTyp = merAccTyp;
	}

	public String getMerPriv() {
		if(MerPriv == null){
			return "";
		}
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		this.MerPriv = merPriv;
	}

	/**
	 * 
	 * 描述：商户账户充值（后台方式） -- 签名
	 * 
	 * @author shiliang.feng
	 * @date 2017年7月17日下午3:49:53
	 * @return
	 */
	public String getRechargeMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id,version_no);
		String str = macHead + getTransAmt() + getMerAccTyp() + getMerPriv();
		return str;
	}

	/**
	 * 
	 * 描述：商户账户提现（后台方式） -- 签名
	 * 
	* @author shiliang.feng
	* @date 2017年7月17日下午3:49:53
	 * @return
	 */
	public String getDrawingsMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id,version_no);
		String str = macHead + getTransAmt() + getMerPriv();
		return str;
	}
}
