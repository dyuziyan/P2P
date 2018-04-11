/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.ret;

/**
 * @ClassName: RechargeCustRet 商户自主充值提现 （后台方式）
 * @version 1.0
 * @Desc: RechargeRet
 * @author shiliang.feng
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class RechargeCustRet extends BaseRet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1391814526028857978L;

	private String TransId;

	public String getTransId() {
		return TransId;
	}

	public void setTransId(String transId) {
		TransId = transId;
	}
	
	
}
