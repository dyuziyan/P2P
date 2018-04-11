package com.wx.carloadbase.domain;

import org.apache.ibatis.type.Alias;

import com.wx.enums.common.ClientType;
import com.wx.enums.message.SmsType;
import com.wx.support.BaseModel;

@Alias("phoneCode")
public class PhoneCode extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	private Long phoneCodeId;
	private Long loginInfoId; //用户ID
	private String mobilePhone; //手机号码
	private String verifyCode; //验证码
	private String resultCode; //返回码
	private int countOfDay = 0; //当天已经发送的次数
	private ClientType clientType; // 客户端类型
	private SmsType smsType; //业务类型
	
	@Override
	public Long getId() {
		return this.getPhoneCodeId();
	}
	public Long getPhoneCodeId() {
		return phoneCodeId;
	}
	public void setPhoneCodeId(Long phoneCodeId) {
		this.phoneCodeId = phoneCodeId;
	}
	
	public Long getLoginInfoId() {
		return loginInfoId;
	}
	public void setLoginInfoId(Long loginInfoId) {
		this.loginInfoId = loginInfoId;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public int getCountOfDay() {
		return countOfDay;
	}
	public void setCountOfDay(int countOfDay) {
		this.countOfDay = countOfDay;
	}
	
	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	public SmsType getSmsType() {
		return smsType;
	}
	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}

}
