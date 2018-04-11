package com.wx.carloadaccount.domain;

import org.apache.ibatis.type.Alias;


import com.wx.support.BaseModel;
@Alias("carBrandBs")
public class CarBrandBs extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5062095743377885811L;
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
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
