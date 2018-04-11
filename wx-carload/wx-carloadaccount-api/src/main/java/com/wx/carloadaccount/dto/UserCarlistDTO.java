package com.wx.carloadaccount.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserCarlistDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2821956250618496113L;
	
	private Long id;
	private Long userId;
	private Long carBrandId;
	private String carBrand;//车辆品牌
	private Long carBrandTypeId;//车辆品牌类型ID
	private String carBrandType;//车辆品牌类型（冗余）
	private Long carModleId;//车辆车型ID
	private String carModle;//车辆车型（冗余）
	private Long carYearstyleId ;//车辆年款ID
	private String carYearstyle;//	车辆年款（冗余）
	private Integer carUseyears	;//使用年限
	private String carNumber ;//车牌号码
	private int carUsekind;//使用性质
	private Date carBuyTime;//购买时间
	private Double carRunKm;//行驶里程（KM）
	private String vinNumber;//车架号
	private String engineNumber;//发动机号码
	private Date lastMaintainTime;//最近保养日期
	private Date nextYearlyCheckTime;//下次年审时间
	private Date nextPolicyTime;//下次保险时间
	private Date createTime ;//创建时间
	private String carinfo;
	private int isDefault;
	private String brandPictureUrl;
	
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
	public Long getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(Long carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public Long getCarBrandTypeId() {
		return carBrandTypeId;
	}
	public void setCarBrandTypeId(Long carBrandTypeId) {
		this.carBrandTypeId = carBrandTypeId;
	}
	public String getCarBrandType() {
		return carBrandType;
	}
	public void setCarBrandType(String carBrandType) {
		this.carBrandType = carBrandType;
	}
	public Long getCarModleId() {
		return carModleId;
	}
	public void setCarModleId(Long carModleId) {
		this.carModleId = carModleId;
	}
	public String getCarModle() {
		return carModle;
	}
	public void setCarModle(String carModle) {
		this.carModle = carModle;
	}
	public Long getCarYearstyleId() {
		return carYearstyleId;
	}
	public void setCarYearstyleId(Long carYearstyleId) {
		this.carYearstyleId = carYearstyleId;
	}
	public String getCarYearstyle() {
		return carYearstyle;
	}
	public void setCarYearstyle(String carYearstyle) {
		this.carYearstyle = carYearstyle;
	}
	public Integer getCarUseyears() {
		return carUseyears;
	}
	public void setCarUseyears(Integer carUseyears) {
		this.carUseyears = carUseyears;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getCarUsekind() {
		return carUsekind;
	}
	public void setCarUsekind(int carUsekind) {
		this.carUsekind = carUsekind;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCarBuyTime() {
		return carBuyTime;
	}
	public void setCarBuyTime(Date carBuyTime) {
		this.carBuyTime = carBuyTime;
	}
	public Double getCarRunKm() {
		return carRunKm;
	}
	public void setCarRunKm(Double carRunKm) {
		this.carRunKm = carRunKm;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCarinfo() {
		return carinfo;
	}
	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public String getBrandPictureUrl() {
		return brandPictureUrl;
	}
	public void setBrandPictureUrl(String brandPictureUrl) {
		this.brandPictureUrl = brandPictureUrl;
	}
	public String getVinNumber() {
		return vinNumber;
	}
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}
	
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getLastMaintainTime() {
		return lastMaintainTime;
	}
	public void setLastMaintainTime(Date lastMaintainTime) {
		this.lastMaintainTime = lastMaintainTime;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getNextYearlyCheckTime() {
		return nextYearlyCheckTime;
	}
	public void setNextYearlyCheckTime(Date nextYearlyCheckTime) {
		this.nextYearlyCheckTime = nextYearlyCheckTime;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getNextPolicyTime() {
		return nextPolicyTime;
	}
	public void setNextPolicyTime(Date nextPolicyTime) {
		this.nextPolicyTime = nextPolicyTime;
	}
	
}
