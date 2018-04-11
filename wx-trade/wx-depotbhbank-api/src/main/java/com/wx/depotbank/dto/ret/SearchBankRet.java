/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: SearchBankRet 商户余额返回实体(商户账户交易查询返回实体)
 * @version 1.0
 * @Desc: RechargeRet 商户余额返回实体
 * @author shiliang.feng
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class SearchBankRet extends BaseRet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957601802328507750L;
	
	public String FileName;
	
	public String ChargeAccount;
	
	public String AccountName;
	
	public String AvlBal;
	
	public String AcctBal;
	
	public String FrzBal;

	/**
	 * 商户账户交易查询 返回文件名
	 * @return
	 */
	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	/**
	 * 大额充值账号
	 * @return
	 */
	public String getChargeAccount() {
		return ChargeAccount;
	}

	public void setChargeAccount(String chargeAccount) {
		ChargeAccount = chargeAccount;
	}

	/**
	 * 大额充值账户 户名
	 * @return
	 */
	public String getAccountName() {
		return AccountName;
	}

	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	/**
	 * 可用余额
	 * 
	 * @return
	 */
	public String getAvlBal() {
		return AvlBal;
	}

	public void setAvlBal(String avlBal) {
		AvlBal = avlBal;
	}

	/**
	 * 账户余额
	 * 
	 * @return
	 */
	public String getAcctBal() {
		return AcctBal;
	}

	public void setAcctBal(String acctBal) {
		AcctBal = acctBal;
	}

	/**
	 * 冻结余额
	 * 
	 * @return
	 */
	public String getFrzBal() {
		return FrzBal;
	}

	public void setFrzBal(String frzBal) {
		FrzBal = frzBal;
	}
	
	
	

}
