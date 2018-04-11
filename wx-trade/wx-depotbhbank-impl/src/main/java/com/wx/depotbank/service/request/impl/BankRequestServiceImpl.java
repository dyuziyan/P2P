/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service.request.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wx.depotbank.dao.BankRequestDao;
import com.wx.depotbank.enums.BizType;
import com.wx.depotbank.service.request.BankRequestService;

import my.comp.lang.DateUtils;
import my.comp.lang.StringUtils;
import my.comp.sn.SnBuilder;

/**
 * @ClassName: BankRequestService
 * @version 1.0
 * @Desc: BankRequestService
 * @author xiaojun.zhou
 * @date 2017年6月27日下午5:15:04
 * @history v1.0
 *
 */
@Service
public class BankRequestServiceImpl implements BankRequestService {

	private static final Logger logger = LoggerFactory.getLogger(BankRequestServiceImpl.class);

	@Resource
	private BankRequestDao bankRequestDao;

	@Override
	public int saveRequest(Map<String, Object> requestParams) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_id", requestParams.get("PlaCustId"));
			params.put("request_data", requestParams.toString());
			String service = (String) requestParams.get("biz_type");
			BizType bizType = BizType.valueOf(service);
			// 获取请求号
			params.put("mer_bill_no", requestParams.get("MerBillNo"));
			params.put("biz_type", bizType.getKey());
			params.put("biz_type_desc", bizType.getValue());
			params.put("id", "-1");
			params.put("merPriv", requestParams.get("MerPriv"));// 保存扩展参数值
			SnBuilder sn = null;
			// 唯一序列号（同一行为一分钟只能有一笔数据提交） 干他爹..这个位置参数待修改，会存在不同业务字段缺失问题
			if (bizType.getKey().equals(BizType.RealNameWeb.getKey())
					|| bizType.getKey().equals(BizType.CBHBNetLoanRegister.getKey())) {
				sn = SnBuilder.create(
						requestParams.get("MobileNo") == null ? "HaveNoId" : requestParams.get("MobileNo"),
						bizType.getKey(), DateUtils.formatDate(new Date(), "yyyyMMddHHmm"));
			} else {
				sn = SnBuilder.create(
						requestParams.get("PlaCustId") == null ? "HaveNoId" : requestParams.get("PlaCustId"),
						bizType.getKey(), DateUtils.formatDate(new Date(), "yyyyMMddHHmm"));
			}
			// 投资,现金红包有短信校验，再把sn分钟内唯一性去除
			if (bizType.getKey().equals(BizType.BackInvest.getKey())) {
				sn.append(requestParams.get("MerBillNo"));
			}
			String snValue = sn.toString();
			if (bizType.getKey().equals(BizType.ExperBonus.getKey())) {
				snValue = requestParams.get("SNum").toString();
			}
			if (snValue.indexOf("HaveNoId") != -1) {
				snValue = null;
			}
			
			if (!StringUtils.isEmpty(snValue)) {
				// 查询存在的记录数
				int count = bankRequestDao.queryBankRequestBySerialNumber(snValue);
				if (count > 2) {// 一分钟之内不能提交超过3次
					logger.info("提交太频繁，{}提交次数{}", snValue, count);
					return -2;
				}
			}
			params.put("sn", snValue);
			bankRequestDao.saveBankRequest(params);
			return Integer.valueOf(params.get("id") + "");
		} catch (Exception e) {
			logger.error("保存银行请求信息失败", e);
			return -1;
		}
	}
	
	@Override
	public void saveResponse(JSONObject responseData) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("request_id", responseData.getString("MerBillNo"));
			String respCode = StringUtils.isEmpty(responseData.getString("RespCode"))
					? responseData.getString("respCode") : responseData.getString("RespCode");
			String respDesc = StringUtils.isEmpty(responseData.getString("RespDesc"))
					? responseData.getString("respDesc") : responseData.getString("RespDesc");
			params.put("responce_code", respCode);
			params.put("responce_msg", respDesc);
			params.put("responce_data", responseData.toString());
			bankRequestDao.saveBankResponse(params);
		} catch (Exception e) {
			logger.error("保存银行返回信息失败", e);
		}
	}
}
