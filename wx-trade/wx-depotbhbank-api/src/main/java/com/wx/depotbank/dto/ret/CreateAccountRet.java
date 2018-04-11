/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: CreateAccountRet
 * @version 1.0
 * @Desc: CreateAccountRet
 * @author xiaojun.zhou
 * @date 2017年7月19日上午10:38:08
 * @history v1.0
 *
 */
public class CreateAccountRet extends BaseRet {

	private static final long serialVersionUID = -8597973667461348931L;

	private String PlaCustId; // 账户存管平台客户号
	private String OpenBankId; // 开户银行代号
	private String OpenAcctId;// 开户银行账号
	private String IdentType;// 证件类型
	private String IdentNo;// 证件号码
	private String UsrName;// 姓名
	private String MobileNo;// 手机号
	private String FEE_AMT;// 手续费
	private String RtnCod;// 短信识别码
	private Long AvlBal;// 可用余额
	private Long AcctBal;// 账户余额
	private Long FrzBal;// 冻结余额

	// app
	private String PartnerId;
	private String Version_No;
	private String Char_Set;

	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	public String getVersion_No() {
		return Version_No;
	}

	public void setVersion_No(String version_No) {
		Version_No = version_No;
	}

	public String getChar_Set() {
		return Char_Set;
	}

	public void setChar_Set(String char_Set) {
		Char_Set = char_Set;
	}

	public String getPlaCustId() {
		return PlaCustId;
	}

	public void setPlaCustId(String plaCustId) {
		PlaCustId = plaCustId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getIdentType() {
		return IdentType;
	}

	public void setIdentType(String identType) {
		IdentType = identType;
	}

	public String getIdentNo() {
		return IdentNo;
	}

	public void setIdentNo(String identNo) {
		IdentNo = identNo;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getFEE_AMT() {
		if (FEE_AMT == null)
			return "";
		return FEE_AMT;
	}

	public void setFEE_AMT(String fEE_AMT) {
		FEE_AMT = fEE_AMT;
	}

	public String getRtnCod() {
		if (RtnCod == null)
			return "";
		return RtnCod;
	}

	public void setRtnCod(String rtnCod) {
		RtnCod = rtnCod;
	}

	public Long getAvlBal() {
		return AvlBal;
	}

	public void setAvlBal(Long avlBal) {
		AvlBal = avlBal;
	}

	public Long getAcctBal() {
		return AcctBal;
	}

	public void setAcctBal(Long acctBal) {
		AcctBal = acctBal;
	}

	public Long getFrzBal() {
		return FrzBal;
	}

	public void setFrzBal(Long frzBal) {
		FrzBal = frzBal;
	}
}
