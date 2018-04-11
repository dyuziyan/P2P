package com.wx.carloadaccount.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;

public class BusiShopsListDto extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4941007931050055366L;
	private Long id;
	private String busiShopPoiId;//	商家高德地图POI_ID
	private String busiShopName;//	商家名称
	private String 	addressDetail;//	详情地址
	private String 	linkPhone;//	联系人手机号码
	private String 	linkTel;//	联系座机号码
	private String busiShopPicUrl;//	商家缩略图图片地址
	private Date busiBeginTime;//	营业起始时间
	private Date 	busiEndTime;//	营业终止时间
	private String 	compreScore;//	综合评分
	private BigDecimal busiShopLatitude;//	商家纬度（高德地图）
	private BigDecimal busiShopLongitude;//	商家经度（高德地图）
	private String prmyOperBrand;//主经营品牌
	private String province;//所在省份
	private String city	;//所在城市
	private String cacheountry;//所在区县
	private String startRow;
	private String rows;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBusiShopPoiId() {
		return busiShopPoiId;
	}
	public void setBusiShopPoiId(String busiShopPoiId) {
		this.busiShopPoiId = busiShopPoiId;
	}
	public String getBusiShopName() {
		return busiShopName;
	}
	public void setBusiShopName(String busiShopName) {
		this.busiShopName = busiShopName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getBusiShopPicUrl() {
		return busiShopPicUrl;
	}
	public void setBusiShopPicUrl(String busiShopPicUrl) {
		this.busiShopPicUrl = busiShopPicUrl;
	}
	public Date getBusiBeginTime() {
		return busiBeginTime;
	}
	public void setBusiBeginTime(Date busiBeginTime) {
		this.busiBeginTime = busiBeginTime;
	}
	public Date getBusiEndTime() {
		return busiEndTime;
	}
	public void setBusiEndTime(Date busiEndTime) {
		this.busiEndTime = busiEndTime;
	}
	public String getCompreScore() {
		return compreScore;
	}
	public void setCompreScore(String compreScore) {
		this.compreScore = compreScore;
	}
	public BigDecimal getBusiShopLatitude() {
		return busiShopLatitude;
	}
	public void setBusiShopLatitude(BigDecimal busiShopLatitude) {
		this.busiShopLatitude = busiShopLatitude;
	}
	public BigDecimal getBusiShopLongitude() {
		return busiShopLongitude;
	}
	public void setBusiShopLongitude(BigDecimal busiShopLongitude) {
		this.busiShopLongitude = busiShopLongitude;
	}
	public String getPrmyOperBrand() {
		return prmyOperBrand;
	}
	public void setPrmyOperBrand(String prmyOperBrand) {
		this.prmyOperBrand = prmyOperBrand;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCacheountry() {
		return cacheountry;
	}
	public void setCacheountry(String cacheountry) {
		this.cacheountry = cacheountry;
	}
	public String getStartRow() {
		return startRow;
	}
	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
}
