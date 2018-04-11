package com.wx.carloadmessage.dto;

import java.util.Date;

import com.wx.dto.BaseObject;


public class LogSmsSendDTO extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5519356450011954447L;
	private Long id;//ID
	private String phoneNum;//手机号码
	private String smsContent;//短信内容
	private int	smsType;//短信类型 1：注册 (其他类型待扩)
	private int	smsState;//短信状态 0：未发送 1：已发送 2：发送失败
	private Date sendTime;//发送时间
	private Date inputtime;//入库时间（时间戳）
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public int getSmsType() {
		return smsType;
	}
	public void setSmsType(int smsType) {
		this.smsType = smsType;
	}
	public int getSmsState() {
		return smsState;
	}
	public void setSmsState(int smsState) {
		this.smsState = smsState;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	
	
}
