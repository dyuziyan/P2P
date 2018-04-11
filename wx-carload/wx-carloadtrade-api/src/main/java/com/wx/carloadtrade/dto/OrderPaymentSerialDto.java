package com.wx.carloadtrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;
/**
 * 订单流水dto
 * @author 13697
 *
 */
public class OrderPaymentSerialDto  extends BaseObject{
	
	private Long id;//ID
	private String payNum;//支付流水单号/交易单号（保证唯一性）
	private String mainOrderNum;//主订单号码 t_order_main_list 的main_order_num
	private int payClass;//支付分类 0 未定 1 线上 2线下
	private int paySubClass;//支付分类子类 （二维码等方式子类）（扩展项）
	private String goodsDesc;//商品描述(简要描述 可空)
	private String payTypeCode;//支付方式代号（t_base_payment_list的支付代号）
	private String payTypeName;//支付方式名称（冗余字段，请带入）
	private String payAccountName;//支付方
	private String payAccountNum;//支付账号
	private String payeeAccountNum;//收款方账号
	private String payeeAccountName;//收款方户名
	private BigDecimal payAmount;//付款金额
	private BigDecimal factorage;//手续费
	private int payState;//支付状态 0:支付中 1:支付成功 2:支付失败
	private Date payTime;//支付时间
	private String remark;//备注
	private int isDel;//是否已删除 0：未删除 1：已删除
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayNum() {
		return payNum;
	}
	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}
	public String getMainOrderNum() {
		return mainOrderNum;
	}
	public void setMainOrderNum(String mainOrderNum) {
		this.mainOrderNum = mainOrderNum;
	}
	public int getPayClass() {
		return payClass;
	}
	public void setPayClass(int payClass) {
		this.payClass = payClass;
	}
	public int getPaySubClass() {
		return paySubClass;
	}
	public void setPaySubClass(int paySubClass) {
		this.paySubClass = paySubClass;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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
	public String getPayAccountName() {
		return payAccountName;
	}
	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}
	public String getPayAccountNum() {
		return payAccountNum;
	}
	public void setPayAccountNum(String payAccountNum) {
		this.payAccountNum = payAccountNum;
	}
	public String getPayeeAccountNum() {
		return payeeAccountNum;
	}
	public void setPayeeAccountNum(String payeeAccountNum) {
		this.payeeAccountNum = payeeAccountNum;
	}
	public String getPayeeAccountName() {
		return payeeAccountName;
	}
	public void setPayeeAccountName(String payeeAccountName) {
		this.payeeAccountName = payeeAccountName;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getFactorage() {
		return factorage;
	}
	public void setFactorage(BigDecimal factorage) {
		this.factorage = factorage;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
