package my.comp.sms;

import my.comp.sms.constant.YHMSmsDto;

public interface SmsService {
	
	/**
	 * 发送短信
	 * @param mobile
	 * @param message
	 * @return 
	 */
	String send(String mobile, String message);
	
	/**
	 * 发送短信---模板模型
	 * @return
	 */
	String send(YHMSmsDto yHMSmsDto);
}
