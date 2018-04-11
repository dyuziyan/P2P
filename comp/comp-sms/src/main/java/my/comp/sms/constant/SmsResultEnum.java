package my.comp.sms.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送结果枚举值
 */
public enum SmsResultEnum {
	/* 发送成功 */
	SUCCESS(0),
	/* 发送失败 */
	FAIL(1);

	private final Integer value;

	private static final Map<Integer, SmsResultEnum> map = new HashMap<Integer, SmsResultEnum>();

	static {
		SmsResultEnum[] values = SmsResultEnum.values();
		for (SmsResultEnum smsResultEnum : values) {
			map.put(smsResultEnum.value, smsResultEnum);
		}
	}

	public static SmsResultEnum getEnum(Integer value) {
		return map.get(value);
	}

	private SmsResultEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
