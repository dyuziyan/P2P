package com.wx.carloadtrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wx.dto.BaseObject;
/**
 * 退款单dto
 * @author 13697
 *
 */
public class OrderRefundDto  extends BaseObject{
	
	private Long id;//ID
	private String refund_num;//退款编号
	private String order_num;//订单号码
	private Date apply_time;//申请时间
	private Integer kind_type;//类型 退货/换货 1：仅退款 2：退货退款
	private BigDecimal refund_amount;//退款金额
	private int refund_state;//退款状态 0 待处理 1 已处理 2 已拒绝
	private Long user_id;//客户ID
	private String refund_reason;//退款原因
	private String refund_desc;//退款问题描述
	private String remark;//备注（商家备注）
	private Date handle_time;//处理时间
	private String refund_way;//退款方式
	private String refund_type;//退款类型
	private int is_del;//是否已删除 0：未删除 1：已删除
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRefund_num() {
		return refund_num;
	}
	public void setRefund_num(String refund_num) {
		this.refund_num = refund_num;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public Integer getKind_type() {
		return kind_type;
	}
	public void setKind_type(Integer kind_type) {
		this.kind_type = kind_type;
	}
	public BigDecimal getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(BigDecimal refund_amount) {
		this.refund_amount = refund_amount;
	}
	public int getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(int refund_state) {
		this.refund_state = refund_state;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public String getRefund_desc() {
		return refund_desc;
	}
	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(Date handle_time) {
		this.handle_time = handle_time;
	}
	public String getRefund_way() {
		return refund_way;
	}
	public void setRefund_way(String refund_way) {
		this.refund_way = refund_way;
	}
	public String getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(String refund_type) {
		this.refund_type = refund_type;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
}
