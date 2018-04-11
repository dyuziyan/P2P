package com.wx.carloadmessage.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;

@Alias("logSmsSend")
public class LogSmsSend extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2660663286916397802L;
	private Long id;//ID
	private String phoneNum;//手机号码
	private String smsContent;//短信内容
	private int	smsType;//短信类型 1：注册 (其他类型待扩)
	private int	smsState;//短信状态 0：未发送 1：已发送 2：发送失败
	private Date sendTime;//发送时间
	private Date inputtime;//入库时间（时间戳）
	private String taskId;
	
	
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
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	
}
