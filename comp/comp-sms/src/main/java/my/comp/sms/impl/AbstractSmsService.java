package my.comp.sms.impl;

import my.comp.sms.SignPosition;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractSmsService {
	private String sign;
	private SignPosition signPosition = SignPosition.NONE;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public SignPosition getSignPosition() {
		return signPosition;
	}

	public void setSignPosition(SignPosition signPosition) {
		this.signPosition = signPosition;
	}

	protected String signMessage(String message) {
		if (StringUtils.contains(message, sign)) return message;
		switch (signPosition) {
		case LEFT:
			return sign + message;
		case RIGHT:
			return message + sign;
		case NONE:
			break;
		}
		return message;
	}

}
