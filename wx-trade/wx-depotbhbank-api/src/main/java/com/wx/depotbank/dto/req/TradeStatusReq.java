/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import com.wx.depotbank.enums.BizType;

/**
 * @ClassName: TradeStatusReq
 * @version 1.0
 * @Desc: TradeStatusReq
 * @author xiaojun.zhou
 * @date 2017年8月30日下午2:06:36
 * @history v1.0
 *
 */
public class TradeStatusReq extends BaseReq {

	private static final long serialVersionUID = 2326759304642370657L;

	// 查询交易类型(举例：“用户充值（页面方式）-WebRecharge”)
	public String QueryTransType;

	public String getQueryTransType() {
		return QueryTransType;
	}

	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}

	/**
	 * 
	 * 描述：交易状态查询（后台方式）-- 签名
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年8月30日下午2:10:14
	 * @param partner_id
	 * @param version_no
	 * @return
	 */
	public String getQueryTransStatMac(String partner_id, String version_no) {
		this.biz_type = BizType.QueryTransStat.getKey();
		String macHead = getMacHead(partner_id, version_no);
		String str = macHead + getQueryTransType();
		logger.info("待签名串：mac={}", str);
		return str;
	}
}
