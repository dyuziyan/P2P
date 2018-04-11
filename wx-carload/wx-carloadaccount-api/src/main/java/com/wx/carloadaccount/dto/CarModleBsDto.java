package com.wx.carloadaccount.dto;

import com.wx.dto.BaseObject;
/**
 *汽车车型表
 * @author 13697
 *
 */
public class CarModleBsDto  extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5118821752085384700L;
	
	
	private Long modleId;
	private Long brandId;
	private Long brandTypeId;
	private String brandTypeName;
	private String modleName;
	
	
	
	
	public String getBrandTypeName() {
		return brandTypeName;
	}
	public void setBrandTypeName(String brandTypeName) {
		this.brandTypeName = brandTypeName;
	}
	public Long getModleId() {
		return modleId;
	}
	public void setModleId(Long modleId) {
		this.modleId = modleId;
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
}
