package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class UserLicenseDTO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6592370200325563241L;
	private Long id;
	private Long userId;//用户ID
	private String licenseNumber;//驾驶证编号（档案编号）
	private String userIdentityNum;//身份证号码
	private Date getLicenseTime;//领证时间
	private Date createTime;//创建时间
	private int isDel	;//是否已删除 0：未删除 1：已删除
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getUserIdentityNum() {
		return userIdentityNum;
	}
	public void setUserIdentityNum(String userIdentityNum) {
		this.userIdentityNum = userIdentityNum;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getGetLicenseTime() {
		return getLicenseTime;
	}
	public void setGetLicenseTime(Date getLicenseTime) {
		this.getLicenseTime = getLicenseTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
