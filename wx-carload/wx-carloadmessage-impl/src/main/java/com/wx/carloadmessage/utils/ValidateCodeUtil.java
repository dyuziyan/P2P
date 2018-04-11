package com.wx.carloadmessage.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.wx.carloadmessage.domain.SMSCode;

public class ValidateCodeUtil {
	

	//每条短信有效时长 (秒)
	public static int SMS_VALIDATE_TIME = 100;
	
	//每条短信验可验证次数
	public static int SMS_PHONE_VALIDATE_COUNT = 5;
	
	/**
	 * 短信 每个号码当天最大条数
	 */
	public static int SMS_PHONE_MAX_COUNT = 20;
	
	/**
	 * 短信 每个号码间隔时间 单位:秒
	 */
	public static int SMS_HEART_TIME = 10;



	//发送频率
	static Map<String, Date> heartsMap = new HashMap<String, Date>();

	static Map<String,Object> phoneCodeMap = new HashMap<String,Object>();

//	/**
//	 * 域名访问拦截
//	 * */
//	public static boolean isSmsAndUserCenter(HttpServletRequest request){
//
//		String referer = request.getHeader("Referer");
//		String WEB_URL_L = removeString_Http2(IConstants.WEB_URL);
//		referer = removeString_Http2(referer);
//		
//		if(referer!=null&&referer.startsWith(WEB_URL_L)){
//			return true;
//		}
//		return false;
//	}
//		
		
	public static String removeString_Http(String s){
		if(s == null){
			return "";
		}
		int a = s.indexOf("://");
		if(a>=0){
			s = s.substring(a+3);
		}
		return s;
	}
	
	public static boolean heartValidate(String ip, String phone) {
		
		/*
		// ip次数限制
		if (ipCount.containsKey(ip)
				&& ipCount.get(ip).intValue() >= SMS_IP_MAX_COUNT) {
			throw new ActionException("您今天发送次数已达到当天上限,如有疑问请联系客服");
		}
		// 号码条数限制
		if (phoneCounts.containsKey(phone)
				&& phoneCounts.get(phone).intValue() >= SMS_PHONE_MAX_COUNT) {
			throw new ActionException("您今天发送次数已达到当天上限,如有疑问请联系客服");
		}
		*/
		
		if(phoneCodeMap.containsKey(phone))
		{
			SMSCode obj = (SMSCode)phoneCodeMap.get(phone);
			if(obj.getSendCount()>=SMS_PHONE_MAX_COUNT)
			{
				return false;
			}
		}
		
		/** 一个IP 两次发送时间间隔 */
		if (heartsMap.containsKey(ip)
				&& DateUtils.addSeconds(heartsMap.get(ip),SMS_HEART_TIME).compareTo(new Date()) > 0) {
			System.out.println("----------------"+heartsMap.containsKey(ip));
			System.out.println("---------------------------------------"+DateUtils.addSeconds(heartsMap.get(ip),SMS_HEART_TIME));
			System.out.println("---------------------------------------"+new Date());
			System.out.println("---------------------------------------:"+DateUtils.addSeconds(heartsMap.get(ip),SMS_HEART_TIME).compareTo(new Date()));
			return false;
		}
		return true;
	}
	
	
	public static void add(String ip,String phone,String code)
	{
		
		if(phoneCodeMap.containsKey(phone))
		{
			SMSCode obj = (SMSCode)phoneCodeMap.get(phone);
			obj.setSendCount(obj.getSendCount()+1);
			obj.setPhone(phone);
			obj.setCode(code);
			obj.setTime(DateUtil.getOrderNum());
			
		}else
		{
			phoneCodeMap.put(phone, new SMSCode(phone,code,DateUtil.getOrderNum()));
		}
		
		heartsMap.put(ip, new Date());
		
	}
	
	public static void addValidateCount(String phone)
	{
		if(phoneCodeMap.containsKey(phone))
		{
			SMSCode obj = (SMSCode)phoneCodeMap.get(phone);
			obj.setValidateCount(obj.getValidateCount()+1);
		}
	}
	
	
	public static Object getPhoneCode(String phone)
	{
		if(phoneCodeMap.containsKey(phone))
		{
			return phoneCodeMap.get(phone);
		}
		return null;
	}
	
	
	public static Object getPhoneCodeMap(String phone)
	{
		if(phoneCodeMap.containsKey(phone))
		{
			Object obj = phoneCodeMap.get(phone);
			if(null != obj){
				SMSCode ccc = (SMSCode)obj;
				return ccc.getCode();
			}
			return null;
		}
		return null;
	}
	
	public static void remove(String phone)
	{
		if(phoneCodeMap.containsKey(phone))
		{
			phoneCodeMap.remove(phone);
		}
	}
	
	public static void clear()
	{
		heartsMap.clear();
		phoneCodeMap.clear();

	}
	
	
//	public static void main(String[] args) {
//		String a ="http://www.wuxingjinrong.com";
//		String b ="https://www.wuxingjinrong.com";
//		String c ="http://m.wuxingjinrong.com";
//		String d ="http://mm.wuxingjinrong.com";
//		
//		System.out.println(removeString_Http(a));
//		System.out.println(removeString_Http(b));
//		System.out.println(removeString_Http(c));
//		System.out.println(removeString_Http(d));
//
//		System.out.println(removeString_Http2(a));
//		System.out.println(removeString_Http2(b));
//		System.out.println(removeString_Http2(c));
//		System.out.println(removeString_Http2(d));
//		
//		
//		
//	}
	
	
	public static String removeString_Http2(String s){
		if(s == null){
			return "";
		}
		int a = s.indexOf(".");
		if(a>=0){
			s = s.substring(a+1);
		}
		return s;
	}
	
	/**
	 * 验证短信码 有效期为180秒(3分钟)
	 * 
	 * @param requestPhone
	 * @param requsetCode
	 * @param code
	 * @return
	 */
	public static String vidateSMSCode(String requestPhone, String requsetCode) {
		/*SMSCode obj = new SMSCode();
		obj.setSendCount(obj.getSendCount()+1);
		obj.setPhone(requestPhone);
		obj.setCode(requsetCode);
		obj.setTime(DateUtil.getOrderNum());
		phoneCodeMap.put(requestPhone, obj);*/
		SMSCode code = (SMSCode) phoneCodeMap.get(requestPhone);
		if (code != null) {
			if (code.getPhone().equals(requestPhone) && code.getCode().equals(requsetCode)) {
				/*if (Convert.strToLong(DateUtil.getOrderNum(), 0L) > Convert.strToLong(code.getTime(), 0L) + 600) {
					return	"0";//验证码已过期
				}*/
			} else {
				return  "-1";//验证码错误
			}
		} else {
			
			return  "-1";//验证码错误
		}
		phoneCodeMap.remove(requestPhone);
		return "1";
	}
	
}
