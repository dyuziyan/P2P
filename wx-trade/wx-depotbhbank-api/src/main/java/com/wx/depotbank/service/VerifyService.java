/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: VerifyService
 * @version 1.0
 * @Desc: VerifyService
 * @author xiaojun.zhou
 * @date 2017年9月8日下午5:37:08
 * @history v1.0
 *
 */
@RemoteService("/verifyService")
public interface VerifyService {

	/**
	 * 
	 * 描述：验证签名是否正确
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月8日下午5:38:41
	 * @param str
	 *            待验证的字符串
	 * @param mac
	 *            签名
	 * @return
	 */
	public boolean verifyMac(String str, String mac);
}
