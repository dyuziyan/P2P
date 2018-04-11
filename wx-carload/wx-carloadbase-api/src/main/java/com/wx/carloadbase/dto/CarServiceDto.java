package com.wx.carloadbase.dto;

import java.math.BigDecimal;

import com.wx.dto.BaseObject;

/**
 * 汽车服务对象
 */
public class CarServiceDto extends BaseObject {
	private Long id;
	private String serviceProNum;//	服务项目编号
	private String serviceProName;//服务项目名称
	private String serviceTypeCode;//服务类型代号 （保养 、养护、维修的代号）
	private BigDecimal serviceProMarketPrice;//服务项目价格（市场价格/原价）
	private BigDecimal serviceProBusiPrice;//服务项目价格（商家价格/实际价格）
	private BigDecimal manHourFee;//工时费
	private String unitName;//零件单位
	private Long unitCount;//零件数量
	private int isRecommend;//是否推荐 0：否 1：是
	private String remark;//备注
	
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
	public String getServiceProName() {
		return serviceProName;
	}
	public void setServiceProName(String serviceProName) {
		this.serviceProName = serviceProName;
	}
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
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
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
