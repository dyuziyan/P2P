package com.wx.carloadaccount.dto;

import com.wx.dto.BaseObject;
/**
 * 汽车品牌
 * @author 13697
 *
 */
public class CarBrandBsDto  extends BaseObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1351367042524920132L;
	public Long brandId;
	public String initialLetter;
	public String brandPictureUrl;
	public String 	brandName;
	
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getInitialLetter() {
		return initialLetter;
	}
	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
	}
	public String getBrandPictureUrl() {
		return brandPictureUrl;
	}
	public void setBrandPictureUrl(String brandPictureUrl) {
		this.brandPictureUrl = brandPictureUrl;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
