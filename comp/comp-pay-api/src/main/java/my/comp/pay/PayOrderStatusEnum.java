package my.comp.pay;

public enum PayOrderStatusEnum {
	/**
	 * 交易成功
	 */
	SUCCESS,
	/**
	 * 银行处理中。有可能成功或失败。
	 */
	WAIT,
	/**
	 * 交易失败
	 */
	FAIL
}
