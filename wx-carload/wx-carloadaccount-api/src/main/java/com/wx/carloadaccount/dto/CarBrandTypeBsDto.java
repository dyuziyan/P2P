package com.wx.carloadaccount.dto;

import com.wx.dto.BaseObject;
/**
 * 汽车品牌类型
 * @author 13697
 *
 */
public class CarBrandTypeBsDto  extends BaseObject{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 865960772199590769L;
	
	public Long brandTypeId;
	public Long 	brandId;
	public String 	brandType;
	
	
	public Long getBrandTypeId() {
		return brandTypeId;
	}
	public void setBrandTypeId(Long brandTypeId) {
		this.brandTypeId = brandTypeId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
}
