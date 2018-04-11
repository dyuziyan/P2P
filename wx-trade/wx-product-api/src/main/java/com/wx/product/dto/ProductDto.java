package com.wx.product.dto ;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;


public class ProductDto extends BaseObject{

	private static final long serialVersionUID = 8156769965503939813L;
	
	private Long id; 					//项目ID
	private String productTitle;   		//项目名称
	private BigDecimal productAmount;	//项目总额
	private BigDecimal hasInvestAmount;	//已售额度
	private Integer status;				//标的状态（ 2投资中，3已幕满，4兑付中，5已完成）
	private BigDecimal cashback;		//返现比例
	private Integer deadline;   		//项目周期
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	public BigDecimal getHasInvestAmount() {
		return hasInvestAmount;
	}
	public void setHasInvestAmount(BigDecimal hasInvestAmount) {
		this.hasInvestAmount = hasInvestAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getCashback() {
		return cashback;
	}
	public void setCashback(BigDecimal cashback) {
		this.cashback = cashback;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
}
