/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: QueryMerchantAcctsRet 商户账户查询（后台方式）
 * @version 1.0
 * @Desc: QueryMerchantAcctsReq
 * @author shiliang.feng
 * @date 2017年7月19日上午11:21:32
 * @history v1.0
 *
 */
public class QueryMerchantAcctsReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685803662680414184L;

	public String partner_id;

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
	/**
	 * 
	 * 描述：商户账户充值（后台方式） -- 签名
	 * 
	 * @author shiliang.feng
	 * @date 2017年7月17日下午3:49:53
	 * @return
	 */
	public String getQueryMerchantAcctsMac(String partner_id, String version_no) {
		String macHead = getMacHead(partner_id,version_no);
		String str = macHead + getPartner_id() + getMerPriv();
		return str;
	}

	 
}
