package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class UserInfoDTO  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7446586013628111538L;
	private Long id;
	private String userAccount;//用户账号
	private String 	userPhone;//用户手机号码
	public String userPictureUrl;//	用户头像图片地址
	private Integer userSex;//用户性别
	private String userPwd;//用户账号密码
	private String nickName;//用户昵称
	private String userName;//用戶名
	private Date userBirthday;//生日
	private String licenseNumber;//驾驶证编号
	private String userAddress;//详细地址
	private String userIdentityNum;//身份证号码
	private String addressDetail;//详细地址
	private Date getLicenseTime;//驾驶证领证时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPictureUrl() {
		return userPictureUrl;
	}
	public void setUserPictureUrl(String userPictureUrl) {
		this.userPictureUrl = userPictureUrl;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	@Override
	public String toString() {
		return "UserInfoDTO [id=" + id + ", userAccount=" + userAccount + ", userPhone=" + userPhone
				+ ", userPictureUrl=" + userPictureUrl + ", userSex=" + userSex + ", userPwd=" + userPwd + ", nickName="
				+ nickName + ", userName=" + userName + ", userBirthday=" + userBirthday + ", licenseNumber="
				+ licenseNumber + ", userAddress=" + userAddress + ", userIdentityNum=" + userIdentityNum
				+ ", addressDetail=" + addressDetail + ", getLicenseTime=" + getLicenseTime + "]";
	}
	
}
