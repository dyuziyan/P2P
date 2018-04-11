package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppInfoDTO implements Serializable{
	private Long id	;//物理ID主键
	private int appType;//App类型，1:android，2:ios
	private String versionNum;//版本号
	private String updateLog;//版本更新日志
	private String appUrl;//app下载地址
	private int forceUpdate;//是否强制升级 0:非强制，1：强制
	private Date updateTime;//更新时间
	
	public Long getId() {
		return id;
	}
	public int getAppType() {
		return appType;
	}
	public void setAppType(int appType) {
		this.appType = appType;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getUpdateLog() {
		return updateLog;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public int getForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(int forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
