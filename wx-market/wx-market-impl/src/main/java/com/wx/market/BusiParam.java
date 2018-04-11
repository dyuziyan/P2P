package com.wx.market;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wx.enums.common.BusiEvent;
import com.wx.enums.common.ClientType;

public class BusiParam {
	
	/**
	 * 注册
	 */
	public static final String REGISTER = "_busiparam_register_" ;
	/**
	 * 是否邮箱注册
	 */
	public static final String REGISTER_IS_EMAIL = "_busiparam_is_email_" ;
	/**
	 * 是否移动端注册（APP、M站）
	 */
	public static final String REGISTER_IS_MOBILECLIENT = "_busiparam_is_mobile_" ;
	
	/**
	 * 操作平台来源
	 */
	public static final String CLIENT_TYPE = "_busiparam_client_type_" ;
	/**
	 * 交易金额
	 */
	public static final String TRANS_AMOUNT = "_busiparam_trans_amount_";

	/**
	 * 交易金额字符串
	 */
	public static final String TRANS_AMOUNT_STR = "_busiparam_trans_amount_string_";

	/**
	 * 实际交易金额
	 */
	public static final String ACTUAL_TRANS_AMOUNT = "_busiparam_actual_trans_amount_";

	/**
	 * 参照时间
	 */
	public static final String REFERENCE_DATE = "_busiparam_reference_date_";

	public static final String RELATION_ID = "_busiparam_relation_id_";

	public static final String USER_PAY_ORDER = "_user_pay_order_info_";

	public static final String BUSI_TYPE = "_busiparam_busi_type_";

	public static final String BUSI_ID = "_busiparam_busi_id_";
	
	
	public static final String ORDER_ID = "_busiparam_orderid_" ; //投资订单ID
	
	public static final String IS_PENDING = "_busiparam_invest_ispending_" ; //是否满标
	
	public static final String MESSAGES = "_busiresult_messages_";

	private Long investorId;
	private BusiEvent busiEvent;
	private boolean firstInvest = false ; //全平台首次投资
	private boolean firstClientInvest = false ; //客户端首次投资
	private boolean firstMobileInvest = false ; //移动端首次投资
	private ClientType clientType ; //用户投资平台
	private Long channelId ; //用户渠道号
	private boolean firstRecharge = false ; //是否首次充值
	private boolean isPendingOrder = false ; //是否满标订单
	private Map<String, Object> params;
	
	private String cashRedPacketId;   //使用现金红包ID

	protected BusiParam(BusiEvent busiEvent, Long userId) {
		this.busiEvent = busiEvent;
		this.investorId = userId;
		this.params = new HashMap<String, Object>();
	}

	public static BusiParam create(BusiEvent event, Long userId) {
		return new BusiParam(event, userId);
	}

	public static BusiParam create(BusiEvent event, Long userId, Long relationId) {
		BusiParam param = new BusiParam(event, userId);
		if (relationId != null) param.setRelationId(relationId);
		return param;
	}

	public Date getSystime() {
		Date systime = this.get(REFERENCE_DATE, Date.class);
		if (systime == null) {
			this.add(REFERENCE_DATE, new Date());
		}
		return this.get(REFERENCE_DATE, Date.class);
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	public void setRelationId(Long bpId) {
		this.add(RELATION_ID, bpId);
	}

	public Long getRelationId() {
		return this.get(RELATION_ID, Long.class, 0L);
	}

	public BusiEvent getBusiEvent() {
		return busiEvent;
	}

	public void setBusiEvent(BusiEvent busiEvent) {
		this.busiEvent = busiEvent;
	}

	public boolean isFirstInvest() {
		return firstInvest;
	}
	public void setFirstInvest(boolean firstInvest) {
		this.firstInvest = firstInvest;
	}

	public boolean isFirstClientInvest() {
		return firstClientInvest;
	}
	public void setFirstClientInvest(boolean firstClientInvest) {
		this.firstClientInvest = firstClientInvest;
	}

	public boolean isFirstMobileInvest() {
		return firstMobileInvest;
	}
	public void setFirstMobileInvest(boolean firstMobileInvest) {
		this.firstMobileInvest = firstMobileInvest;
	}

	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public boolean isFirstRecharge() {
		return firstRecharge;
	}
	public void setFirstRecharge(boolean firstRecharge) {
		this.firstRecharge = firstRecharge;
	}

	public boolean isPendingOrder() {
		return isPendingOrder;
	}
	public void setPendingOrder(boolean isPendingOrder) {
		this.isPendingOrder = isPendingOrder;
	}
	
	public String getCashRedPacketId() {
		return cashRedPacketId;
	}

	public void setCashRedPacketId(String cashRedPacketId) {
		this.cashRedPacketId = cashRedPacketId;
	}

	public void setBusiId(Integer busiId) {
		this.add(BUSI_ID, busiId);
	}

	public Integer getBusiId() {
		return this.get(BUSI_ID, Integer.class, -1);
	}

	public void add(Map<String, Object> params) {
		this.params.putAll(params);
	}

	public void add(String key, Object value) {
		this.params.put(key, value);
	}

	public Object remove(String key) {
		return this.params.remove(key);
	}

	public Object get(String key) {
		return this.params.get(key);
	}

	public <T> T get(String key, Class<T> clazz) {
		return get(key, clazz, null);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> Collection<T> getCollection(String key, Class<T> clazz) {
		Object val = this.params.get(key);
		if (val == null) return null;
		return (Collection<T>) val;
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T get(String key, Class<T> clazz, T defaultValue) {
		Object val = this.params.get(key);
		if (val == null || (val instanceof String && ((String) val).trim().isEmpty() && defaultValue != null)) {
			return defaultValue;
		}
		return (T) val;
	}

	public boolean containsKey(String key) {
		return this.params.containsKey(key);
	}
}
