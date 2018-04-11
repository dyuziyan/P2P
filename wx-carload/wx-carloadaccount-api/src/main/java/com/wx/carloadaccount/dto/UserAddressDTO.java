package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;


public class UserAddressDTO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9051750747423606165L;
	private Long id;
	private Long userId;//用户ID t_user_userlist-id
	private String receiverName;//联系人（收货人）
	private String province;//省份
	private String city	;//城市
	private String country;//区县
	private String street;//街道
	private String addressDetail;//地址详情
	private String linkPhone;//联系电话
	private String postCode;//邮编
	private int isDefault;//是否设置为默认 0：否 1：是
	private Date createTime;//创建时间
	private int isDel;//是否已删除 0：未删除 1：已删除

	/*
	private Long user_id;//用户ID t_user_userlist-id
	private String receiver_name;//联系人（收货人）
	private String province;//省份
	private String city;//城市
	private String country;//区县
	private String street	;//街道
	private String address_detail;//地址详情
	private String link_phone;//联系电话
	private String post_code;//邮编
	private int is_default;//是否设置为默认 0：否 1：是
	private Date create_time	;//创建时间
	private int is_del;//是否已删除 0：未删除 1：已删除
	*/
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
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
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
