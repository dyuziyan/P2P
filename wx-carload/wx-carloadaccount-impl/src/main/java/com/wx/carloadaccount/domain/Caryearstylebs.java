package com.wx.carloadaccount.domain;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;
/**
 * 汽车年款
 * @author 13697
 *
 */
@Alias("caryearstylebs")
public class Caryearstylebs  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long yearstyleId;
	private Long brandId;
	private String brandName;
	private Long brandTypeId;
	private String brandType;
	private Long modleId;
	private String modleName;
	private String yearstyleName;
	
	public Long getYearstyleId() {
		return yearstyleId;
	}
	public void setYearstyleId(Long yearstyleId) {
		this.yearstyleId = yearstyleId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getBrandTypeId() {
		return brandTypeId;
	}
	public void setBrandTypeId(Long brandTypeId) {
		this.brandTypeId = brandTypeId;
	}
	public String getModleName() {
		return modleName;
	}
	public void setModleName(String modleName) {
		this.modleName = modleName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
	public Long getModleId() {
		return modleId;
	}
	public void setModleId(Long modleId) {
		this.modleId = modleId;
	}
	public String getYearstyleName() {
		return yearstyleName;
	}
	public void setYearstyleName(String yearstyleName) {
		this.yearstyleName = yearstyleName;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
