package com.wx.carloadaccount.domain;

import org.apache.ibatis.type.Alias;

import com.wx.support.BaseModel;
/**
 * 汽车品牌类型
 * @author 13697
 *
 */
@Alias("carBrandTypeBs")
public class CarBrandTypeBs  extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Long brandTypeId;
	public Long 	brandId;//车辆品牌ID
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
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
