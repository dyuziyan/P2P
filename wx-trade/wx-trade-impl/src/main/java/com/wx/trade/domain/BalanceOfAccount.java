package com.wx.trade.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
/**
 * 存管对账实体（红包，充值，提现）
 */
@Deprecated
@Alias("balanceOfAccount")
public class BalanceOfAccount implements Serializable{
	private static final long serialVersionUID = 167233422223L;
	
	private String OrdNo ;    		 //订单号
	private String CreDt ;    		 //订单日期
	private BigDecimal TransAmt;	 //交易金额（元）
	private BigDecimal FeeAmt;		 //手续费（元）
	private String PlaCustId;	 	 //账户存管平台Id
	private String MerBillNo;	 	 //商户流水号
	private String ChargeCorg;		 //充值渠道(WWW-PC,CAS-手机端,SPS-线下大额)提现状态(W3:系统受理中,W4:银行受理中,S1:银行交易成功,F1:付款失败,F2:付款核销,R9:审批拒绝)
	private String type;    	 	 //对账类型（1：充值对账，2：提现对账，3：红包对账）
	private String FalRsn;			 //失败原因  
	private Date updateTime;	 	 //更新时间
	private Integer comparestate;	 //BalanceOfAccountState
	
	public Integer getComparestate() {
		return comparestate;
	}
	public void setComparestate(Integer comparestate) {
		this.comparestate = comparestate;
	}
	public String getOrdNo() {
		return OrdNo;
	}
	public void setOrdNo(String ordNo) {
		OrdNo = ordNo;
	}
	public String getCreDt() {
		return CreDt;
	}
	public void setCreDt(String creDt) {
		CreDt = creDt;
	}
	public BigDecimal getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		TransAmt = transAmt;
	}
	public BigDecimal getFeeAmt() {
		return FeeAmt;
	}
	public void setFeeAmt(BigDecimal feeAmt) {
		FeeAmt = feeAmt;
	}
	public String getPlaCustId() {
		return PlaCustId;
	}
	public void setPlaCustId(String plaCustId) {
		PlaCustId = plaCustId;
	}
	public String getMerBillNo() {
		return MerBillNo;
	}
	public void setMerBillNo(String merBillNo) {
		MerBillNo = merBillNo;
	}
	public String getChargeCorg() {
		return ChargeCorg;
	}
	public void setChargeCorg(String chargeCorg) {
		ChargeCorg = chargeCorg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFalRsn() {
		return FalRsn;
	}
	public void setFalRsn(String falRsn) {
		FalRsn = falRsn;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public static BalanceOfAccount getBalanceOfAccount(Map<String, Object> map){  
	        if(null == map) return null;  
	        BalanceOfAccount balanceOfAccount = new BalanceOfAccount();  
	        String MerBillNo = map.get("MerBillNo").toString();  
	        if(null != MerBillNo)  
	        	balanceOfAccount.setMerBillNo(MerBillNo);  
//	        	balanceOfAccount.setCompanyNumber(map.get("COMPANY_NUMBER").toString());  
	        return balanceOfAccount;  
	}  
	      
	public static List<BalanceOfAccount> getList(List<Map<String, Object>> mapList){  
	        if(null == mapList) return null;  
	        List<BalanceOfAccount> list = new ArrayList<>();  
	        for(Map<String, Object> map:mapList){  
	        	BalanceOfAccount balanceOfAccount = getBalanceOfAccount(map);  
	            if(null != balanceOfAccount)  
	                list.add(balanceOfAccount);  
	        }  
	        return list;  
	 }  
	
}




