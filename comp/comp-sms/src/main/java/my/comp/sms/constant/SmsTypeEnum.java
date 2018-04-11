package my.comp.sms.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *  短信通道
 */
public enum SmsTypeEnum {

	/* 默认 ---北京易回眸*/
	YHM(1, "YHM");

	private final Integer name;
	private final String value;

	private static final Map<String, SmsTypeEnum> map = new HashMap<String, SmsTypeEnum>();

	static {
		SmsTypeEnum[] values = SmsTypeEnum.values();
		for (SmsTypeEnum smsTypeEnum : values) {
			map.put(smsTypeEnum.getValue(), smsTypeEnum);
		}
	}

	public static SmsTypeEnum getEnum(String value) {
		return map.get(value);
	}

	private SmsTypeEnum(Integer name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public Integer getName() {
		return name;
	}
}
