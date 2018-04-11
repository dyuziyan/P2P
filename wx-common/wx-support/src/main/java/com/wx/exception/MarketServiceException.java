package com.wx.exception;

/**
 * 业务异常类
 * 
 * @author Administrator
 *
 */
public class MarketServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3837659312079505063L;

	private String code;

	protected MarketServiceException() {
		super();
	}

	protected MarketServiceException(String message) {
		super(message);
	}

	protected MarketServiceException(String message, String code) {
		super(message);
		this.code = code;
	}

	protected MarketServiceException(Throwable cause) {
		super(cause);
	}

	protected MarketServiceException(Throwable cause, String message) {
		super(message, cause);
	}

	protected MarketServiceException(Throwable cause, String message, String code) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
