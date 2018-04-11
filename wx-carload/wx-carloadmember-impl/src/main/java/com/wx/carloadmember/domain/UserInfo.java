package com.wx.carloadmember.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;

@Alias("userInfo")
public class UserInfo extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8828271997381650764L;

	private Long id;
	private Long userId;//用户ID
	private Long userName;//用户姓名
	private Integer userSex;//用户性别
	private Long userPictureUrl;//用户头像图片地址
	private Date userBirthday;//出生日期
	private String userIdentityNum;//身份证号码
	private Date createTime;//	创建时间
	private Date inputtime	;//入库时间（时间戳）
	
	public UserInfo() {
		Date date = new Date();
		this.createTime = date;
		this.inputtime = date;
	}
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
	public Long getUserName() {
		return userName;
	}
	public void setUserName(Long userName) {
		this.userName = userName;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	public Long getUserPictureUrl() {
		return userPictureUrl;
	}
	public void setUserPictureUrl(Long userPictureUrl) {
		this.userPictureUrl = userPictureUrl;
	}
	public String getUserIdentityNum() {
		return userIdentityNum;
	}
	public void setUserIdentityNum(String userIdentityNum) {
		this.userIdentityNum = userIdentityNum;
	}
	

	
}
