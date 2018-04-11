package my.comp.jms;

import my.comp.lang.StringUtils;

public class JmsException extends Exception {

	private static final long serialVersionUID = 1L;

	public JmsException(Throwable cause) {
		super(cause);
	}

	public JmsException(String message) {
		super(message);
	}

	public JmsException(String pattern, Object... args) {
		super(StringUtils.format(pattern, args));
	}

}
