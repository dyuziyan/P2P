package com.wx.carloadtrade.domain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;
@Alias("carServicePart")
public class CarServicePart  extends BaseObject{
	
	private Long id;
	private String serviceProNum;//服务项目编号
	private BigDecimal serviceProMarketPrice;//服务项目价格（市场价格/原价）
	private BigDecimal serviceProBusiPrice;//服务项目价格（商家价格/实际价格）
	private BigDecimal manHourFee;//工时费
	private String serviceTypeCode;//服务类型代号 （保养 、养护、维修的代号）
	private String serviceTypeName;//服务类型名称 保养 、养护、维修
	private String serviceProName;//服务项目名称
	private String unitName;//零件单位
	private Long unitCount;//零件数量
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceProNum() {
		return serviceProNum;
	}
	public void setServiceProNum(String serviceProNum) {
		this.serviceProNum = serviceProNum;
	}
	public BigDecimal getServiceProMarketPrice() {
		return serviceProMarketPrice;
	}
	public void setServiceProMarketPrice(BigDecimal serviceProMarketPrice) {
		this.serviceProMarketPrice = serviceProMarketPrice;
	}
	public BigDecimal getServiceProBusiPrice() {
		return serviceProBusiPrice;
	}
	public void setServiceProBusiPrice(BigDecimal serviceProBusiPrice) {
		this.serviceProBusiPrice = serviceProBusiPrice;
	}
	public BigDecimal getManHourFee() {
		return manHourFee;
	}
	public void setManHourFee(BigDecimal manHourFee) {
		this.manHourFee = manHourFee;
	}
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public String getServiceProName() {
		return serviceProName;
	}
	public void setServiceProName(String serviceProName) {
		this.serviceProName = serviceProName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(Long unitCount) {
		this.unitCount = unitCount;
	}
	
}
