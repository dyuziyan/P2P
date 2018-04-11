/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hisun.merchant.atc.RSASignUtil;
import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.BaseReq;
import com.wx.depotbank.enums.ChartSet;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankErrorCode;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.service.request.BankRequestService;
import com.wx.depotbank.service.request.ResponceThread;
import com.wx.depotbank.service.request.ResponceThreadPool;

import my.comp.http.HttpException;
import my.comp.http.impl.DefaultGetMethod;
import my.comp.lang.StringUtils;

/**
 * @ClassName: RequestUtils
 * @version 1.0
 * @Desc: RequestUtils
 * @author xiaojun.zhou
 * @date 2017年6月6日下午4:59:36
 * @history v1.0
 *
 */
public class RequestUtils {

	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	/**
	 * 
	 * 描述：封装公用的参数
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月7日上午11:43:48
	 * @param notifyUrl
	 * @param returnUrl
	 * @param merPriv
	 * @return
	 */
	public static Map<String, Object> packageBasicParams(Object obj) throws BankException {
		try {
			Map<String, Object> requestParams = new HashMap<String, Object>();
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.get(obj) == null)
					continue;
				requestParams.put(field.getName(), field.get(obj));
			}
			requestParams.put("partner_id", BankConstant.PARTNER_ID);
			requestParams.put("version_no", BankConstant.VERSION_NO);
			return requestParams;
		} catch (Exception e) {
			throw new BankException(BankErrorCode.WXCF01.getCode(), "封装公用的参数异常");
		}
	}

	/**
	 * @param req
	 *            实体
	 * @param clazz
	 *            返回class实体
	 * @param requestType
	 *            请求类型
	 * @param sign
	 *            拼装签名串
	 * @return
	 */
	public static <T> T sendParam(BaseReq req, Class<T> clazz, RequestType requestType, String sign, String... Transid)
			throws BankException {
		if (RequestType.WEB_APP == requestType) {// app请求
			String NetLoanInfo = AesUtils.AES_Encrypt(sign);
			Map<String, Object> requestParamsApp = new HashMap<String, Object>();
			requestParamsApp.put("NetLoanInfo", NetLoanInfo);
			requestParamsApp.put("Transid", Transid[0]);
			requestParamsApp.put("MerBillNo", req.getMerBillNo());
			requestParamsApp.put("PlaCustId", req.getPlaCustId());
			requestParamsApp.put("biz_type", req.getTransid());
			return RequestUtils.doRequestWebAPP(requestParamsApp, clazz, BankConstant.BANK_URL_APP);
		} else {// pc请求
			Map<String, Object> requestParams = RequestUtils.packageBasicParams(req);
			try {
				// RSA签名
				RSASignUtil util = new RSASignUtil(BankConstant.MERCHANT_CERT_PATH, BankConstant.MERCHANT_CERT_PASS);
				String merchantSign = util.sign(sign, ChartSet.GBK.getKey());
				requestParams.put("mac", merchantSign);
			} catch (UnsupportedEncodingException e) {
				logger.error("RSA签名异常", e);
				throw new BankException(BankErrorCode.WXCF01.getCode(), "RSA签名异常");
			}
			if (requestType == RequestType.WEB || requestType == RequestType.BACKSTAGE) {
				return RequestUtils.doRequest(requestParams, clazz, requestType);
			} else {
				throw new BankException(BankErrorCode.WXCF01.getCode(), "请求类型不正确，只支持WEB, BACKSTAGE");
			}
		}
	}

	/**
	 * 
	 * 描述：发起请求
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月7日上午11:44:21
	 * @param requestParams
	 * @return
	 * @throws BankException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T doRequest(Map<String, Object> requestParams, Class<T> clazz, RequestType requestType)
			throws BankException {
		logger.info("请求的参数，requestParams={}", requestParams);
		// 保存银行请求信息
		BankRequestService bankRequestService = (BankRequestService) SpringSupport.getBean("bankRequestServiceImpl");
		int requestId = bankRequestService.saveRequest(requestParams);
		if (requestId == -1) {
			logger.error("保存银行请求信息失败:数据异常");
			throw new BankException(BankErrorCode.WXCF01.getCode(), "数据异常");
		}
		if (requestId == -2) {
			logger.error("保存银行请求信息失败:请求过于频繁，请稍后再试");
			throw new BankException(BankErrorCode.WXCF02.getCode(), "请求过于频繁，请稍后再试");
		}

		if (RequestType.WEB == requestType) {
			requestParams.put("bank_url", BankConstant.BANK_URL);
			return (T) requestParams;
		}

		String response = null;
		try {
			// 请求银行接口
			DefaultGetMethod defaultGetMethod = new DefaultGetMethod(ChartSet.GBK.getKey());
			response = defaultGetMethod.invoke(BankConstant.BANK_URL, requestParams);
		} catch (HttpException e) {
			logger.error("请求银行接口异常", e);
			throw new BankException(BankErrorCode.WXCF04.getCode(), "请求银行HTTP接口异常 :" + e.getMessage());
		}
		logger.info("银行返回信息response：{}", response);
		if (StringUtils.isNull(response)) {
			throw new BankException(BankErrorCode.WXCF03.getCode(), "银行没有返回信息");
		}
		// 转换银行数据成jsonObject
		JSONObject responcemap = convertDataToJSONObject(response);

		// 保存银行返回信息
		ResponceThreadPool.pull(new ResponceThread(responcemap));
		
		// 将json转换成map
		String respCode = StringUtils.isEmpty(responcemap.getString("RespCode")) ? responcemap.getString("respCode")
				: responcemap.getString("RespCode");
		String respDesc = StringUtils.isEmpty(responcemap.getString("RespDesc")) ? responcemap.getString("respDesc")
				: responcemap.getString("RespDesc");
		if (!StringUtils.equals(BankConstant.RESPONSE_SUCCESS, respCode)) {
			throw new BankException(respCode, respDesc);
		}
		return JSONObject.parseObject(responcemap.toJSONString(), clazz);
	}

	/**
	 * 
	 * 描述：web请求方式返回form表单
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年7月11日下午8:58:05
	 * @param requestParams
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T doRequestWeb(Map<String, Object> requestParams, Class<T> clazz, String bankUrl)
			throws BankException {
		logger.info("请求的参数，requestParams={}", requestParams);
		// 保存银行请求信息
		BankRequestService bankRequestService = (BankRequestService) SpringSupport.getBean("bankRequestServiceImpl");
		int requestId = bankRequestService.saveRequest(requestParams);
		if (requestId == -1) {
			logger.error("保存银行请求信息失败:数据异常");
			throw new BankException(BankErrorCode.WXCF01.getCode(), "数据异常");
		}
		if (requestId == -2) {
			logger.error("保存银行请求信息失败:请求过于频繁，请稍后再试");
			throw new BankException(BankErrorCode.WXCF02.getCode(), "请求过于频繁，请稍后再试");
		}

		// 构造表单
		StringBuffer html = new StringBuffer();
		html.append("<html><head> ");
		html.append("<title>跳转渤海银行，请等待...</title>");
		html.append("</head><body>");
		html.append("<form id='bank_from' name='bank_from' method=\"post\" action='").append(bankUrl).append("'>");

		for (Entry<String, Object> entry : requestParams.entrySet()) {
			html.append("<input type=\"hidden\" name='").append(entry.getKey()).append("'").append(" value='")
					.append(entry.getValue()).append("' />");
		}

		html.append("</form>");
		html.append("<script>document.forms['bank_from'].submit();</script>");
		html.append(" </body></html>");
		logger.info("表单html：{}", html.toString());
		return (T) html.toString();
	}

	/**
	 * 
	 * 描述：app请求方式为get
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年7月27日下午5:52:20
	 * @param requestParams
	 * @param clazz
	 * @param bankUrl
	 * @return
	 * @throws BankException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T doRequestWebAPP(Map<String, Object> requestParams, Class<T> clazz, String bankUrl)
			throws BankException {
		logger.info("请求的参数，requestParams={}", requestParams);
		requestParams.put("biz_type", requestParams.get("Transid"));
		// 保存银行请求信息
		BankRequestService bankRequestService = (BankRequestService) SpringSupport.getBean("bankRequestServiceImpl");
		int requestId = bankRequestService.saveRequest(requestParams);
		if (requestId == -1) {
			logger.error("保存银行请求信息失败:数据异常");
			throw new BankException(BankErrorCode.WXCF01.getCode(), "数据异常");
		}
		if (requestId == -2) {
			logger.error("保存银行请求信息失败:请求过于频繁，请稍后再试");
			throw new BankException(BankErrorCode.WXCF02.getCode(), "请求过于频繁，请稍后再试");
		}

		requestParams.remove("biz_type");// 移除
		requestParams.remove("MerBillNo");// 移除
		requestParams.remove("PlaCustId");// 移除
		StringBuffer url = new StringBuffer(bankUrl);
		url.append("?");
		for (Entry<String, Object> entry : requestParams.entrySet()) {
			url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		url.deleteCharAt(url.length() - 1);

		// 构造表单
		StringBuffer html = new StringBuffer();
		html.append("<html><head> ");
		html.append("<title>跳转渤海银行，请等待...</title>");
		html.append("<script type='text/javascript'>window.location.href='").append(url).append("'</script>");
		html.append("</head></html>");
		logger.info("表单html：{}", html.toString());
		return (T) html.toString();
	}

	/**
	 * 
	 * 描述：转换银行返回的数据拼装成jsonObject
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年7月12日下午7:29:12
	 * @param data（biz_type=QueryUserInf&respCode=IPS000）
	 * @return
	 */
	private static JSONObject convertDataToJSONObject(String data) {
		JSONObject jsonObject = new JSONObject();
		for (String str : data.split("&")) {
			String[] childs = str.split("=");
			if (childs.length != 2)
				continue;
			jsonObject.put(childs[0], childs[1]);
		}
		return jsonObject;
	}
}
