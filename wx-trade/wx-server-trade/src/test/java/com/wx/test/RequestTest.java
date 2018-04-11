/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.test;

/**
 * @ClassName: RequestTest
 * @version 1.0
 * @Desc: TODO
 * @author xiaojun.zhou
 * @date 2017年6月27日下午5:46:16
 * @history v1.0
 *
 */
public class RequestTest {

//	private BankRequestService bankRequestService;
//	private ClassPathXmlApplicationContext context = null;
//
//	@Before
//	public void init() {
//		context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
//		bankRequestService = (BankRequestService) context.getBean("bankRequestServiceImpl");
//	}
//
//	@After
//	public void end() {
//		context.close();
//	}
//
//	@Test
//	public void saveRequest() {
//		Map<String, Object> requestParams = new HashMap<String, Object>();
//		requestParams.put("PlaCustId", "abc");
//		requestParams.put("biz_type", "RealNameWeb");
//		requestParams.put("MerBillNo", "123121222");
//		bankRequestService.saveRequest(requestParams);
//	}
//
//	@Test
//	public void saveResponse() {
//		String response = "{RespCode:11,RespDesc:'success成功'}";
//		bankRequestService.saveResponse(JSONObject.parseObject(response));
//	}
//
//	public static void main(String[] args) throws Exception {
//		// {"RespCode":"000000","version_no":"2.0","partner_id":"800010000010003","biz_type":"BindPassResult","PlaCustId":"0003100000007704","char_set":"00",sign_type":"RSA","MerBillNo":"3469916764489728","mac":"5F6D48AE6241C8855F3FE473F9934137314425630A093BC28D4188B801EF9B583F9BF9261468F53920D73B16A1139F5538CB56892C5DC622C78B58343C09A0AAD18A0A0585D27339B5898F32B2E167F1BFF3374F365DC50C943278468A7E7ED259E7E3ED8C2D7CB9352CE5F79FCBCAB937B6421155F00902B8FAFEBB9693D8B7"}
//		String sign = "008000100000100032.0BindPassResultRSA34699167644897280000000003100000007704";
//		String signdata = "5F6D48AE6241C8855F3FE473F9934137314425630A093BC28D4188B801EF9B583F9BF9261468F53920D73B16A1139F5538CB56892C5DC622C78B58343C09A0AAD18A0A0585D27339B5898F32B2E167F1BFF3374F365DC50C943278468A7E7ED259E7E3ED8C2D7CB9352CE5F79FCBCAB937B6421155F00902B8FAFEBB9693D8B7";
//
//		// boolean xxxxxx=xxx.verify(sign, signdata, loadPublicKeyByFile(),
//		// "GBK");
//
//		// String
//		// xxxxxx=RSASignUtil.encryptData(sign,"GBK","F:\\渤海银行网贷存管公钥.cer");
//		// boolean verify2=verify();
//
//		System.out.println(doCheck(sign, signdata, "GBK"));
//
//	}
//
//	/**
//	 * RSA验签名检查
//	 * 
//	 * @param content
//	 *            待签名数据
//	 * @param sign
//	 *            签名值
//	 * @param encode
//	 *            字符集编码
//	 * @return 布尔值
//	 */
//	public static boolean doCheck(String content, String sign, String encode) {
//		try {
//			PublicKey pubKey = RSASignUtil.getPublicKeyfromPath("F:\\渤海银行网贷存管公钥.cer");
//
//			java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");
//
//			signature.initVerify(pubKey);
//			signature.update(content.getBytes(encode));
//
//			boolean bverify = signature.verify(hexToByte(sign.getBytes()));
//			return bverify;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//
//	public static byte[] hexToByte(byte[] b) {
//		if (b.length % 2 != 0)
//			throw new IllegalArgumentException("----");
//
//		byte[] b2 = new byte[b.length / 2];
//		for (int n = 0; n < b.length; n += 2) {
//			String item = new String(b, n, 2);
//
//			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
//		}
//		b = (byte[]) null;
//		return b2;
//	}

}
