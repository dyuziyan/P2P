/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hisun.merchant.utils.HexStringByte;
import com.security.bssp.ext.SignatureExt;
import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.enums.ChartSet;
import com.wx.depotbank.utils.AesUtils;

import my.comp.lang.StringUtils;

/**
 * @ClassName: VerifyServiceImpl
 * @version 1.0
 * @Desc: VerifyServiceImpl
 * @author xiaojun.zhou
 * @date 2017年9月8日下午5:39:37
 * @history v1.0
 *
 */
@Service
public class VerifyServiceImpl implements VerifyService {

	private static final Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);

	@Override
	public boolean verifyMac(String str, String mac) {
		logger.info("str={},mac={}", str, mac);
		if (StringUtils.isEmpty(str)) {
			// app解密
			String appmac = AesUtils.AES_Decrypt(mac);
			logger.info("appmac={}", appmac);
			return !StringUtils.isEmpty(appmac);
		}
		boolean flag = rsaVerify(str, mac, BankConstant.BANK_CERT_PATH);
		return flag;
	}

	/**
	 * 
	 * 描述：签名验证
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月8日下午5:32:59
	 * @param str
	 * @param mac
	 * @param cerPath
	 * @return
	 */
	private static boolean rsaVerify(String str, String mac, String cerPath) {
		SignatureExt se = new SignatureExt();
		try {
			str = URLDecoder.decode(str, ChartSet.GBK.getKey());
			byte[] signDatab = HexStringByte.hexToByte(mac.getBytes());
			byte[] indatab = str.getBytes(ChartSet.GBK.getKey());
			X509Certificate cert = getCertfromPath(cerPath);
			return se.verifySignatureWithCert(cert, signDatab, indatab);
		} catch (Exception e) {
			logger.error("签名验证出错", e);
			return false;
		}
	}

	/**
	 * 
	 * @param crt_path
	 *            证书全路径
	 * @return
	 * @throws SecurityException
	 */
	private static X509Certificate getCertfromPath(String crt_path) {
		X509Certificate cert = null;
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(crt_path));
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) cf.generateCertificate(inStream);
		} catch (Exception e) {
			logger.error("crt_path=" + crt_path);
			logger.error("获取证书X509Certificate出错", e);
			return null;
		}
		return cert;
	}
}
