package com.wx.enums.common;

/**
 *   草他妈，code后面改回英文见名识义，此处因之前业务逻辑繁重，暂时保留以前的数字编号
 */
public enum BusiEvent {
	
	REGIST(0001, "注册"),
	REGIST_RECOMMEND(0002,"好友注册"),
	REGIST_APPAL(0003, "发送注册验证码"),
	REGIST_DEPOSIT(0004, "开通存管账户"),
	
	RECHAGE_ONLINE(1001, "线上充值"),
	RECHAGE_MANUAL(1002,"手动充值"),
	RECHAGE_OFFLINE(1003, "线下充值"),
	CHARGE_MANUAL(1004, "手动扣除"),
	RECHAGE_BY_PHONE(1005, "移动充值"),
	
	REWARD(2001, "奖励"),
	INVEST_RECOMMEND_COMMISSION(2002, "好友投资提成"),
	CASHREDPACKET(2003, "投资红包返现"),
	CASHREDPACKET_IMD(2004,"现金红包"),
	
	INVEST(3001, "投资项目"),
	REFUND(3002,"项目回款"),
	INVEST_PROCEEDS(3003, "投资项目收益"),
	PRODUCT_MANAGEMENTFEE(3004, "项目管理费"),
	INVEST_PROCEEDS_MANAGEMENTFEE(3005, "投资项目收益管理费"),
	PRODUCT_REFUND_INTEREST(3006, "项目还款利息"),
	BORROWER_REFUND(3007, "借款人项目还款"),
	PRODUCT_LOAN(3008, "项目放款"),
	
	WITHDRAW_APPLY(4001, "提现申请"),
	WITHDRAW_SERVICECHARGE(4002,"提现手续费"),
	WITHDRAW_FAIL(4003, "提现失败"),
	WITHDRAW_SUCCESS(4004, "提现成功"),

	EXPERIENCE_MONEY_REFUND(6001, "体验金回款"),
	EXPERIENCE_MONEY_PROCEEDS(6002,"体验金收益"),
	
	CASHBACK(7001, "投资返现"),
	HANDOPERATION_CASHBACK(7002, "手工返现"),
	
	BALTRANSFER(8001, "资金迁移"),
	BIGRECHARGE(8002, "大额充值")
    ;

	private String lable;
	private Integer code;

	BusiEvent(Integer code, String lable) {
		this.code = code;
		this.lable = lable;
	}

	BusiEvent(String lable) {
		this.code = 0000;
		this.lable = lable;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
