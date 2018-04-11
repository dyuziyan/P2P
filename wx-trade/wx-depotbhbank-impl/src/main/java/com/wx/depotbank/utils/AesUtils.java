/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.wx.depotbank.common.BankConstant;

/**
 * @ClassName: AesUtils
 * @version 1.0
 * @Desc: AesUtils
 * @author xiaojun.zhou
 * @date 2017年7月27日下午3:20:11
 * @history v1.0
 *
 */
public class AesUtils {

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";

	private static Key generateKey() throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(BankConstant.AES_KEY.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 
	 * 描述：加密
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年7月27日下午3:21:33
	 * @param keyStr
	 * @param plainText
	 * @return
	 */
	public static String AES_Encrypt(String plainText) {

		byte[] encrypt = null;
		try {
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
			String str = new String(Base64.encodeBase64(encrypt));
			return URLEncoder.encode(str, "utf-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 描述：解密
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年7月27日下午3:21:38
	 * @param keyStr
	 * @param encryptData
	 * @return
	 */
	public static String AES_Decrypt(String encryptData) {
		byte[] decrypt = null;
		try {
			String str = URLDecoder.decode(encryptData, "utf-8");
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher.doFinal(Base64.decodeBase64(str.getBytes()));
			return new String(decrypt).trim();
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String encText = AES_Encrypt( "{“phone”:”15131169211”}");
		System.out.println(encText);
		System.out.println(AES_Decrypt(encText));
	}
}
