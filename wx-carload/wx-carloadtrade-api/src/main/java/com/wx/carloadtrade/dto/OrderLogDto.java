package com.wx.carloadtrade.dto;

import java.util.Date;

import com.wx.dto.BaseObject;
/**
 * 我的车辆
 * @author 13697
 *
 */
public class OrderLogDto  extends BaseObject{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8923886924843593734L;
	private Long id;//订单主键
	private   int orderState ;//订单状态
	private String statusInfo ;// 状态描述
	private String orderNum ;//订单号码
	private String busiShopName ;//商家名称
	private String busiShopAddress;// 商家地址
	private Date maintTime;// 保养时间
	private Date orderTime;// 下单时间
	private String busiShopPhone;//  商家电话
	private Date carFetchTime;// 取车时间
	private Date handleTime;//
	private Date busiMaintTime;//商家调整保养时间
	private Date busiCarFetchTime;//商家调整取车时间
	private int userIsAgree;//用户是否同意商家调整时间 0：未处理 1：同意 2：拒绝
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public String getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getBusiShopName() {
		return busiShopName;
	}
	public void setBusiShopName(String busiShopName) {
		this.busiShopName = busiShopName;
	}
	public String getBusiShopAddress() {
		return busiShopAddress;
	}
	public void setBusiShopAddress(String busiShopAddress) {
		this.busiShopAddress = busiShopAddress;
	}
	public Date getMaintTime() {
		return maintTime;
	}
	public void setMaintTime(Date maintTime) {
		this.maintTime = maintTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getBusiShopPhone() {
		return busiShopPhone;
	}
	public void setBusiShopPhone(String busiShopPhone) {
		this.busiShopPhone = busiShopPhone;
	}
	public Date getCarFetchTime() {
		return carFetchTime;
	}
	public void setCarFetchTime(Date carFetchTime) {
		this.carFetchTime = carFetchTime;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public Date getBusiMaintTime() {
		return busiMaintTime;
	}
	public void setBusiMaintTime(Date busiMaintTime) {
		this.busiMaintTime = busiMaintTime;
	}
	public Date getBusiCarFetchTime() {
		return busiCarFetchTime;
	}
	public void setBusiCarFetchTime(Date busiCarFetchTime) {
		this.busiCarFetchTime = busiCarFetchTime;
	}
	public int getUserIsAgree() {
		return userIsAgree;
	}
	public void setUserIsAgree(int userIsAgree) {
		this.userIsAgree = userIsAgree;
	}
}
