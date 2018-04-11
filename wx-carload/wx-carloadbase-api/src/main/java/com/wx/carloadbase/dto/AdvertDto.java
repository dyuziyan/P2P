package com.wx.carloadbase.dto;

import java.util.Date;

import com.wx.dto.BaseObject;

/**
 * 登录实体对象
 */
public class AdvertDto extends BaseObject {

	private static final long serialVersionUID = 826769306143200901L;
	
	private Long advertId	;//	广告ID
	private Long advertTypeId;//广告位类型ID t_sys_public_advert_type 的id
	private String advertAddr;//广告位置 'IDX1' 首页
	private String advertName;//广告名称
	private String pictureUrl;//图片地址
	private String linkUrl	;//链接地址
	private Date startTime;//投放时间
	private Date endTime;//结束时间
	private Integer isCarousel;//是否轮播
	private Integer isShow;//是否显示
	private Long clickCount;//点击量
	private Integer isDel;//是否已删除 0：未删除 1：已删除
	private Integer advertPicWidth;//广告图片宽度
	private Integer advertPicHeight;//广告图片高度
	private String advertPlaceName;//广告位名称
	
	public Long getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}
	public Long getAdvertTypeId() {
		return advertTypeId;
	}
	public void setAdvertTypeId(Long advertTypeId) {
		this.advertTypeId = advertTypeId;
	}
	public String getAdvertAddr() {
		return advertAddr;
	}
	public void setAdvertAddr(String advertAddr) {
		this.advertAddr = advertAddr;
	}
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getIsCarousel() {
		return isCarousel;
	}
	public void setIsCarousel(Integer isCarousel) {
		this.isCarousel = isCarousel;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Long getClickCount() {
		return clickCount;
	}
	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getAdvertPicWidth() {
		return advertPicWidth;
	}
	public void setAdvertPicWidth(Integer advertPicWidth) {
		this.advertPicWidth = advertPicWidth;
	}
	public Integer getAdvertPicHeight() {
		return advertPicHeight;
	}
	public void setAdvertPicHeight(Integer advertPicHeight) {
		this.advertPicHeight = advertPicHeight;
	}
	public String getAdvertPlaceName() {
		return advertPlaceName;
	}
	public void setAdvertPlaceName(String advertPlaceName) {
		this.advertPlaceName = advertPlaceName;
	}
	
}
