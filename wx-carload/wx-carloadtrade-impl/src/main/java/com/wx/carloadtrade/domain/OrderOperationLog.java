package com.wx.carloadtrade.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;
@Alias("orderOperationLog")
public class OrderOperationLog  extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2998633769029859516L;
	private long id;
	private String orderNum;
	private int handleType;	
	private String handleInfo;
	private String remark;
	private Date handleTime;
	
	public OrderOperationLog() {
		this.handleTime = new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public int getHandleType() {
		return handleType;
	}
	public void setHandleType(int handleType) {
		this.handleType = handleType;
	}
	public String getHandleInfo() {
		return handleInfo;
	}
	public void setHandleInfo(String handleInfo) {
		this.handleInfo = handleInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
}
