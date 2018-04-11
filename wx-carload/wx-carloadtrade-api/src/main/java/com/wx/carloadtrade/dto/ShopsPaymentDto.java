package com.wx.carloadtrade.dto;

import java.util.Date;

import com.wx.dto.BaseObject;
/**
 * 获取支付参数
 * @author 13697
 *
 */
public class ShopsPaymentDto  extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3133515473967913833L;
	
	
	private Long id;//商家-商家支付方式编号
	private String  payTypeConfig;//支付方式代号
	private String  payTypeCode;//支付方式配置项
	private String payTypeName;//支付方式
	private Long busiShopId;//商家ID
	private int payTypeState;//0：禁用 1：启用
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayTypeConfig() {
		return payTypeConfig;
	}
	public void setPayTypeConfig(String payTypeConfig) {
		this.payTypeConfig = payTypeConfig;
	}
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public Long getBusiShopId() {
		return busiShopId;
	}
	public void setBusiShopId(Long busiShopId) {
		this.busiShopId = busiShopId;
	}
	public int getPayTypeState() {
		return payTypeState;
	}
	public void setPayTypeState(int payTypeState) {
		this.payTypeState = payTypeState;
	}
}
