/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service.request;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: BankRequestServiceImpl
 * @version 1.0
 * @Desc: BankRequestServiceImpl
 * @author xiaojun.zhou
 * @date 2017年6月27日下午5:10:56
 * @history v1.0
 *
 */
public interface BankRequestService {

	/**
	 * 
	 * 描述：保存请求信息
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午5:31:01
	 * @param requestParams
	 * @return
	 */
	public int saveRequest(Map<String, Object> requestParams);

	/**
	 * 
	 * 描述：保存响应信息
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午5:31:09
	 * @param response
	 * @return
	 */
	public void saveResponse(JSONObject responseData);
}
