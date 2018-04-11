/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: QueryMerchantAcctsRet 商户账户查询（后台方式）
 * @version 1.0
 * @Desc: RechargeRet
 * @author shiliang.feng
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class QueryMerchantAcctsRet extends BaseRet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685803662680414184L;

	/**
	 * 流水号
	 */
	private String TransId;
	/**
	 * 交易日期
	 */
	private String CreDt;
	/**
	 * 账户类型
	 */
	private String AcTyp;
	/**
	 * 交易金额
	 */
	private String TransAmt;
	/**
	 * 交易描述
	 */
	private String TransDesc;
	
	
	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}

	public String getCreDt() {
		return CreDt;
	}

	public void setCreDt(String creDt) {
		CreDt = creDt;
	}

	public String getAcTyp() {
		return AcTyp;
	}

	public void setAcTyp(String acTyp) {
		AcTyp = acTyp;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getTransDesc() {
		return TransDesc;
	}

	public void setTransDesc(String transDesc) {
		TransDesc = transDesc;
	}

	public String RespData;

	public String getRespData() {
		return RespData;
	}

	public void setRespData(String respData) {
		RespData = respData;
	}
}
