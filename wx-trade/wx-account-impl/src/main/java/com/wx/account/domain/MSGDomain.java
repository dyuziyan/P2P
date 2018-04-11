package com.wx.account.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
/**
 * 站内信--待重构
 */
@Alias("msgDomain")
public class MSGDomain implements Serializable{
	private static final long serialVersionUID = 167233422232223L;
	
	private String mailTitle ;    	 	//站内信标题
	private String mailContent ;     	//站内信内容
	private Date sendTime=new Date();	//记录时间
	private Long sender=-1L;			//发件人ID
	private Long reciver;	 		 	//收件人
	private Integer mailType=2;	 	 	//站内信类型(1 一般信息 2 系统信息)
	private Integer mailStatus=1;	 	//站内信状态(1 默认未读 2 删除 3 已读)
	private Integer mailMode=2;    	 	//站内信模式(1 前台 默认2 后台)
	private Integer backgroundStatus=1;	//后台状态（1.默认 显示，2.删除，）
	private Integer is_deposit=1;	 	//是否存管 1：是，0：否
	
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Long getReciver() {
		return reciver;
	}
	public void setReciver(Long reciver) {
		this.reciver = reciver;
	}
	public Integer getMailType() {
		return mailType;
	}
	public void setMailType(Integer mailType) {
		this.mailType = mailType;
	}
	public Integer getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}
	public Integer getMailMode() {
		return mailMode;
	}
	public void setMailMode(Integer mailMode) {
		this.mailMode = mailMode;
	}
	public Integer getBackgroundStatus() {
		return backgroundStatus;
	}
	public void setBackgroundStatus(Integer backgroundStatus) {
		this.backgroundStatus = backgroundStatus;
	}
	public Integer getIs_deposit() {
		return is_deposit;
	}
	public void setIs_deposit(Integer is_deposit) {
		this.is_deposit = is_deposit;
	}
	
	
}




