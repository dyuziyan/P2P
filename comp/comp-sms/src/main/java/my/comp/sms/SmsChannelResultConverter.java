package my.comp.sms;

import my.comp.sms.constant.SmsResult;
import my.comp.sms.constant.SmsResultEnum;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

/**
 * 暂且做硬编码，依据SmsTypeEnum识别
 */
public class SmsChannelResultConverter {

	public static SmsResult resultConverter(Integer channel,String channelResult) {
		if (null == channel || StringUtils.isEmpty(channelResult)) return new SmsResult(SmsResultEnum.FAIL.getValue(), "短信通道返回参数错误");
		switch (channel) {
		case 1:
			return yhm(channelResult);
		default:
			return new SmsResult(SmsResultEnum.FAIL.getValue(), "短信通道枚举参数错误");
		}
	}

	/* 北京易回眸 */
	private static SmsResult yhm(String channelResult) {
		if (!StringUtils.isNotBlank(channelResult)) {
			return new SmsResult(SmsResultEnum.FAIL.getValue(), channelResult);
		}
		JSONObject resultObject = JSONObject.fromObject(channelResult);
		String status = resultObject.get("Status") == null ? "" : resultObject.get("Status").toString();
		String  message = resultObject.get("Message") == null ? "" : resultObject.get("Message").toString();
		if ("1".equals(status)) {
			return new SmsResult(SmsResultEnum.SUCCESS.getValue(), message);
		}
		return new SmsResult(SmsResultEnum.FAIL.getValue(), message);
	}
}
