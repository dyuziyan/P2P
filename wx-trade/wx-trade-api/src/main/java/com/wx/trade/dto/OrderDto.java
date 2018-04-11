package com.wx.trade.dto ;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;


/**
 * 投标订单
 */
public class OrderDto extends BaseObject{

	private static final long serialVersionUID = 2700379382117086479L;
	
	private Long id ;            		//订单ID
	private Long investor ;				//投资用户
	private BigDecimal investAmount;	//投资金额
	private BigDecimal monthRate ;		//订单利率
	private	Long productId ;			//项目ID
	private Date investTime;			//投资时间
	
	private	String productName ;		//项目名称-------考虑是否把Product实体作为参数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInvestor() {
		return investor;
	}
	public void setInvestor(Long investor) {
		this.investor = investor;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public BigDecimal getMonthRate() {
		return monthRate;
	}
	public void setMonthRate(BigDecimal monthRate) {
		this.monthRate = monthRate;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
