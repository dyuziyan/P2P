package com.wx.trade.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;
/**
 * 整合对账数据实体（红包，充值，提现）
 */
@Alias("toCompareDomain")
public class ToCompareDomain implements Serializable{
	private static final long serialVersionUID = 167233423223L;
	
	private String MerBillNo ;    	 //订单号
	private BigDecimal TransAmt;	 //订单金额（元）
	private String CreDt;	 	 	 //对账日期
	private String type;		 	 //CompareType
	
	private Integer compareState=0;	 //对账数据状态
	
	public Integer getCompareState() {
		return compareState;
	}
	public void setCompareState(Integer compareState) {
		this.compareState = compareState;
	}
	public String getMerBillNo() {
		return MerBillNo;
	}
	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}
	public BigDecimal getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		TransAmt = transAmt;
	}
	public String getCreDt() {
		return CreDt;
	}
	public void setCreDt(String creDt) {
		CreDt = creDt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "MerBillNo:"+MerBillNo+"--TransAmt:"+TransAmt+"--CreDt"+CreDt+"--type"+type;
	}
	
}




