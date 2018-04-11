package com.wx.carloadaccount.dto;

import java.io.Serializable;

/**
 * 类型代号
 * @author 13697
 *
 */
public class TypeCodeLibDTO  implements Serializable {
	
	private Long id;
	private String	typeName;//代号名称
	private String 	typeKindNum;//代号名称代号种类编号
	private Integer isShow;//是否显示 0：不显示 1：显示
	private String serviceTypeCode;//服务代号
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeKindNum() {
		return typeKindNum;
	}
	public void setTypeKindNum(String typeKindNum) {
		this.typeKindNum = typeKindNum;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
}
