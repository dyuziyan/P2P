package com.wx.carloadtrade.dto;

import com.wx.dto.BaseObject;

/**
 * 退款原因类型
 */
public class OrderRefundReasonDto extends BaseObject {
	
	private Long id;//id
	private String reasonName ;//原因类型名称
//	private String reasonDesc	 ;//原因详情描述
//	private Long serialNumber ;//排序序号
//	private int	reasonState ;//原因状态 是否启用 1：启用 2：禁用
//	private Date createTime ;//创建时间
//	private Long createUserId ;//创建者ID t_admin
//	private String createUserName ;//创建者名称 t_admin
//	private int	isDel ;//是否已删除 0：未删除 1：已删除
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReasonName() {
		return reasonName;
	}
	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}
//	public String getReasonDesc() {
//		return reasonDesc;
//	}
//	public void setReasonDesc(String reasonDesc) {
//		this.reasonDesc = reasonDesc;
//	}
//	public Long getSerialNumber() {
//		return serialNumber;
//	}
//	public void setSerialNumber(Long serialNumber) {
//		this.serialNumber = serialNumber;
//	}
//	public int getReasonState() {
//		return reasonState;
//	}
//	public void setReasonState(int reasonState) {
//		this.reasonState = reasonState;
//	}
//	public Date getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//	public Long getCreateUserId() {
//		return createUserId;
//	}
//	public void setCreateUserId(Long createUserId) {
//		this.createUserId = createUserId;
//	}
//	public String getCreateUserName() {
//		return createUserName;
//	}
//	public void setCreateUserName(String createUserName) {
//		this.createUserName = createUserName;
//	}
//	public int getIsDel() {
//		return isDel;
//	}
//	public void setIsDel(int isDel) {
//		this.isDel = isDel;
//	}
	
}
