package my.comp.sms.constant;

import java.io.Serializable;

/**
 * 短信发送结果
 */
public class SmsResult implements Serializable {

	private static final long serialVersionUID = -995840011189126455L;
	private Integer result;    //对应SmsResultEnum
	private String original;

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public SmsResult(Integer result, String original) {
		super();
		this.result = result;
		this.original = original;
	}

	public SmsResult() {
		super();
	}
}
