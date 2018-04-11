package my.comp.sms;

import java.util.HashMap;
import java.util.Map;

import my.comp.sms.constant.SmsResult;
import my.comp.sms.constant.SmsTypeEnum;
import my.comp.sms.constant.YHMSmsDto;

public class SmsUtils {
private static Map<SmsTypeEnum, SmsService> smsServices;
	
	public void setSmsService(SmsService smsService) {
		SmsUtils.smsServices = new HashMap<SmsTypeEnum, SmsService>();
		SmsUtils.smsServices.put(SmsTypeEnum.YHM, smsService);
	}
	
	public void setSmsServices(Map<SmsTypeEnum, SmsService> smsServices) {
		SmsUtils.smsServices = smsServices;
	}
	
	/**
	 * 指定通道发短信
	 * @param type
	 * @param mobile
	 * @param message
	 * @return
	 */
	public static SmsResult send(SmsTypeEnum type, String mobile, String message) {
		String org_result = smsServices.get(type).send(mobile, message);
		return SmsChannelResultConverter.resultConverter(type.getName(), org_result);
	}
	
	/**
	 * 指定短信模板发短信---YHM
	 * @return
	 */
	public static SmsResult send(YHMSmsDto YHMSmsDto) {
		String org_result = smsServices.get(SmsTypeEnum.YHM).send(YHMSmsDto);
		return SmsChannelResultConverter.resultConverter(SmsTypeEnum.YHM.getName(), org_result);
	}
}
