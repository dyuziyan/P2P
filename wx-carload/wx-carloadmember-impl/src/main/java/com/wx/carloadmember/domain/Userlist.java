package com.wx.carloadmember.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;
@Alias("userlist")
public class Userlist  extends BaseModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3196627368720034320L;
	private Long id;
	private String userAccount;
	private int userType;
	private int userState;
	private int isPhoneCheck;
	private int isEmailCheck;
	private int isQqBind;
	private Date registerTime;
	private Date lastLoginTime;
	private Long loginCount;
	private Long userScore;
	private Date inputtime;
	private String userPwd;
	private String 	userPhone;
	private String deviceId = "";
	public String userPictureUrl;
	private int userSex;
	

	
	public Userlist() {
		Date date = new Date();
		this.registerTime = date;
		this.lastLoginTime = date;
		this.inputtime = date;
		this.isEmailCheck = 0;
		this.isPhoneCheck = 0;
		this.isQqBind = 0;
		this.loginCount = 1L;
		this.userScore = 1L;
		this.userType = 0;
		this.userState = 1;
	}
	
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
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public int getIsPhoneCheck() {
		return isPhoneCheck;
	}
	public void setIsPhoneCheck(int isPhoneCheck) {
		this.isPhoneCheck = isPhoneCheck;
	}
	public int getIsEmailCheck() {
		return isEmailCheck;
	}
	public void setIsEmailCheck(int isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}
	public int getIsQqBind() {
		return isQqBind;
	}
	public void setIsQqBind(int isQqBind) {
		this.isQqBind = isQqBind;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Long getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
	public Long getUserScore() {
		return userScore;
	}
	public void setUserScore(Long userScore) {
		this.userScore = userScore;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserPictureUrl() {
		return userPictureUrl;
	}

	public void setUserPictureUrl(String userPictureUrl) {
		this.userPictureUrl = userPictureUrl;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
}
