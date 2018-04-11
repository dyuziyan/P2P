package com.wx.account.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
/**
 * 业务流水后期得拆分   该实体多个字段无意义
 */
@Alias("fundRecord")
public class FundRecord implements Serializable{
	private static final long serialVersionUID = 167233422223L;
	
	private long userId ;    		 //用户ID
	private Integer operateType ;    //操作类型
	private Date recordTime;	 	 //记录时间
	private String fundMode;		 //类型名称    ----BusiEvent
	private BigDecimal handleSum;	 //操作金额
	private BigDecimal usableSum;	 //可用金额
	private BigDecimal freezeSum;	 //冻结金额
	private Integer trader=-1;    	 //交易者
	private String remarks;			 //描述
	private BigDecimal income;	 	 //收入金额
	private BigDecimal spending;	 //支出金额
	private long operateTableId ; 	 //操作表ID---项目ID
	private Integer handle_state ;   //处理状态
	private String serial_number;	 //流水号
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getFundMode() {
		return fundMode;
	}
	public void setFundMode(String fundMode) {
		this.fundMode = fundMode;
	}
	public BigDecimal getHandleSum() {
		return handleSum;
	}
	public void setHandleSum(BigDecimal handleSum) {
		this.handleSum = handleSum;
	}
	public BigDecimal getUsableSum() {
		return usableSum;
	}
	public void setUsableSum(BigDecimal usableSum) {
		this.usableSum = usableSum;
	}
	public BigDecimal getFreezeSum() {
		return freezeSum;
	}
	public void setFreezeSum(BigDecimal freezeSum) {
		this.freezeSum = freezeSum;
	}
	public Integer getTrader() {
		return trader;
	}
	public void setTrader(Integer trader) {
		this.trader = trader;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public BigDecimal getSpending() {
		return spending;
	}
	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}
	public long getOperateTableId() {
		return operateTableId;
	}
	public void setOperateTableId(long operateTableId) {
		this.operateTableId = operateTableId;
	}
	public Integer getHandle_state() {
		return handle_state;
	}
	public void setHandle_state(Integer handle_state) {
		this.handle_state = handle_state;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	
	
}




