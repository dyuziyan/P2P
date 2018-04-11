package com.wx.carloadtrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;
/**
 * 订单明细dto
 * @author 13697
 *
 */
public class OrderDetailsDto  extends BaseObject{
	
	
	private Long id;//ID
	private String mainOrderNum;//主订单号码 t_order_main_list 的main_order_num
	private String orderNum;//订单号码 t_order_repair_list 的 order_num
	private Long userId;//用户ID
	private String serviceProNum;//服务项目编号
	private Long goodsId;//商品ID （更换零件时关联的零件ID）
	private String goodsInfo;//商品信息
	private BigDecimal unitPrice;//单价
	private Integer buyCount;//购买数量
	private BigDecimal marketPrice	;//市场价
	private BigDecimal busiPrice;//商家价
	private BigDecimal settlePrice;//结算价
	private Integer isAdditional;//是否新加标识位 0：是 1：否
	private Date createTime;//创建时间
	private BigDecimal manHourFee;//工时费
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMainOrderNum() {
		return mainOrderNum;
	}
	public void setMainOrderNum(String mainOrderNum) {
		this.mainOrderNum = mainOrderNum;
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
	public String getServiceProNum() {
		return serviceProNum;
	}
	public void setServiceProNum(String serviceProNum) {
		this.serviceProNum = serviceProNum;
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getBusiPrice() {
		return busiPrice;
	}
	public void setBusiPrice(BigDecimal busiPrice) {
		this.busiPrice = busiPrice;
	}
	public BigDecimal getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}
	public Integer getIsAdditional() {
		return isAdditional;
	}
	public void setIsAdditional(Integer isAdditional) {
		this.isAdditional = isAdditional;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getManHourFee() {
		return manHourFee;
	}
	public void setManHourFee(BigDecimal manHourFee) {
		this.manHourFee = manHourFee;
	}
}
