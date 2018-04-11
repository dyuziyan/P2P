package com.wx.carloadtrade.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wx.dto.BaseObject;

@Alias("orderMaintDetails")
public class OrderMaintDetails extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1707066493315807754L;
	private Long id;// ID
	private String orderNum;// 订单号码
	private Long userId	;// 用户ID
	private String carModleName;// 车辆车型
	private Long goodsId;// 商品ID
	private String goodsInfo;// 商品信息
	private double unitPrice;//	 单价
	private int buyCount;// 购买数量
	private Date createTime;// 创建时间
	private Integer isAdditional;//是否新加标识位 0：是，1：否
	private Long carPrmyPartsId;
	private Long carSubPartsId;
	private Long carYearstyleId;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCarModleName() {
		return carModleName;
	}
	public void setCarModleName(String carModleName) {
		this.carModleName = carModleName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsAdditional() {
		return isAdditional;
	}
	public void setIsAdditional(Integer isAdditional) {
		this.isAdditional = isAdditional;
	}
	public Long getCarPrmyPartsId() {
		return carPrmyPartsId;
	}
	public void setCarPrmyPartsId(Long carPrmyPartsId) {
		this.carPrmyPartsId = carPrmyPartsId;
	}
	public Long getCarSubPartsId() {
		return carSubPartsId;
	}
	public void setCarSubPartsId(Long carSubPartsId) {
		this.carSubPartsId = carSubPartsId;
	}
	public Long getCarYearstyleId() {
		return carYearstyleId;
	}
	public void setCarYearstyleId(Long carYearstyleId) {
		this.carYearstyleId = carYearstyleId;
	}
	
}
