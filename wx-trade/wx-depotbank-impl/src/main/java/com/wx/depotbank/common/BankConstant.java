/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.common;

import com.wx.support.PropertyLoader;

/**
 * @ClassName: BankConstant
 * @version 1.0
 * @Desc: BankConstant
 * @author xiaojun.zhou
 * @date 2018年3月19日下午3:53:06
 * @history v1.0
 */
public class BankConstant
{
    public static String BANK_PUBLIC_KEY = "";// 公钥
    public static String BANK_PRIVATE_KEY = "";// 私钥
    public static final String BANK_URL = PropertyLoader.getKey("bank.properties", "BANK_URL");// 接口地址
    public static final String RFT_KEY = PropertyLoader.getKey("bank.properties", "RFT_KEY");// 商户从钜石平台获取的Key
    public static final String RFT_ORG = PropertyLoader.getKey("bank.properties", "RFT_ORG");// 机构用户（钜石分配）
    public static final String RFT_SECRET = PropertyLoader.getKey("bank.properties", "RFT_SECRET");// 商户从钜石平台获取的Token
    public static final String VERSION_NO = PropertyLoader.getKey("bank.properties", "VERSION_NO");
    public static final String SIGN_TYPE = PropertyLoader.getKey("bank.properties", "SIGN_TYPE");
    
    // SFTP IP 地址
 	public static String SFTP_IP = PropertyLoader.getKey("bank.properties", "SFTP_IP");
 	// SFTP 端口号
 	public static String SFTP_PORT = PropertyLoader.getKey("bank.properties", "SFTP_PORT");
 	// SFTP 用户名
 	public static String SFTP_NAME = PropertyLoader.getKey("bank.properties", "SFTP_NAME");
 	// SFTP 密码
 	public static String SFTP_PWD = PropertyLoader.getKey("bank.properties", "SFTP_PWD");

}