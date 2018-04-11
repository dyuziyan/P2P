/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.common;

import com.wx.depotbank.utils.PropertyLoader;

/**
 * @ClassName: BankConstant
 * @version 1.0
 * @Desc: BankConstant
 * @author xiaojun.zhou
 * @date 2017年6月6日下午4:39:36
 * @history v1.0
 *
 */
public class BankConstant {
	// app加密密钥
	public static final String AES_KEY = PropertyLoader.getKey("bank.properties", "AES_KEY");
	// 响应成功
	public static final String RESPONSE_SUCCESS = PropertyLoader.getKey("bank.properties", "BANK_RESPONSE_SUCCESS");
	// 商户号
	public static final String PARTNER_ID = PropertyLoader.getKey("bank.properties", "BANK_PARTNER_ID");
	// 版本号
	public static final String VERSION_NO = PropertyLoader.getKey("bank.properties", "BANK_VERSION_NO");
	// 签名方式
	public static final String SIGN_TYPE = PropertyLoader.getKey("bank.properties", "BANK_SIGN_TYPE");
	// 银行接口地址PC
	public static final String BANK_URL = PropertyLoader.getKey("bank.properties", "BANK_URL");
	// 银行接口地址APP
	public static final String BANK_URL_APP = PropertyLoader.getKey("bank.properties", "BANK_URL_APP");
	// 证书密码
	public static final String MERCHANT_CERT_PASS = PropertyLoader.getKey("bank.properties", "BANK_MERCHANT_CERT_PASS");
	// 商家证书地址
	public static String MERCHANT_CERT_PATH = null;
	// 银行证书地址
	public static String BANK_CERT_PATH = null;

	// SFTP IP 地址
	public static String SFTP_IP = PropertyLoader.getKey("bank.properties", "SFTP_IP");
	// SFTP 端口号
	public static String SFTP_PORT = PropertyLoader.getKey("bank.properties", "SFTP_PORT");
	// SFTP 用户名
	public static String SFTP_NAME = PropertyLoader.getKey("bank.properties", "SFTP_NAME");
	// SFTP 密码
	public static String SFTP_PWD = PropertyLoader.getKey("bank.properties", "SFTP_PWD");
	/**
	 * 投资对账文件名
	 */
	public static String INVEST_FILENAME = PropertyLoader.getKey("bank.properties", "INVEST_FILENAME");
	/**
	 * 充值对账文件名
	 */
	public static String RECHARGE_FILENAME = PropertyLoader.getKey("bank.properties", "RECHARGE_FILENAME");
	/**
	 * 提现对账文件名
	 */
	public static String WITHDRAW_FILENAME = PropertyLoader.getKey("bank.properties", "WITHDRAW_FILENAME");
	/**
	 * 红包对账文件名
	 */
	public static String REDPACKET_FILENAME = PropertyLoader.getKey("bank.properties", "REDPACKET_FILENAME");
	/**
	 * 自己迁移文件名
	 */
	public static String TRANSFER_FILENAME = PropertyLoader.getKey("bank.properties", "TRANSFER_FILENAME");

}
