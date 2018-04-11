package com.wx.carloadaccount.domain;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;
/**
 *汽车车型表
 * @author 13697
 *
 */
@Alias("carModleBs")
public class CarModleBs  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long modleId;
	private Long brandId;
	private Long brandTypeId;
	private String brandTypeName;
	private String modleName;
	
	
	
	public Long getModleId() {
		return modleId;
	}
	public void setModleId(Long modleId) {
		this.modleId = modleId;
	}
	public String getBrandTypeName() {
		return brandTypeName;
	}
	public void setBrandTypeName(String brandTypeName) {
		this.brandTypeName = brandTypeName;
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
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
