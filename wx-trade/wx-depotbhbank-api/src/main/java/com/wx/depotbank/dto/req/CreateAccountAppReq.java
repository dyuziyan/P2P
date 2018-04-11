/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName: CreateAccountAppReq
 * @version 1.0
 * @Desc: CreateAccountAppReq
 * @author xiaojun.zhou
 * @date 2017年7月27日下午2:25:53
 * @history v1.0
 *
 */
public class CreateAccountAppReq extends CreateAccountReq {

	private static final long serialVersionUID = 3074682783952841267L;

	public String PartnerId;
	public String UsrFlag;// 新老用户标识：UsrFlag 0：新用户；1：老用户
	public String NewMobileNo;// 新手机号

	@JSONField(name = "NewMobileNo")
	public String getNewMobileNo() {
		return NewMobileNo;
	}

	public void setNewMobileNo(String newMobileNo) {
		NewMobileNo = newMobileNo;
	}

	@JSONField(name = "PartnerId")
	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	@JSONField(name = "UsrFlag")
	public String getUsrFlag() {
		return UsrFlag;
	}

	public void setUsrFlag(String usrFlag) {
		UsrFlag = usrFlag;
	}

	public String toJson(String partnerId) {
		this.PartnerId = partnerId;
		return JSONObject.toJSONString(this);
	}

	public static void main(String[] args) {
		CreateAccountAppReq app = new CreateAccountAppReq();
		app.setBgRetUrl("aaa");
		app.setPageReturnUrl("bbb");
		app.setUsrFlag("0");
		app.setBackUrl("setBackUrl");
		System.out.println(app.toJson("setPartnerId"));
		System.out.println(JSON.toJSONString(app));
	}
}
