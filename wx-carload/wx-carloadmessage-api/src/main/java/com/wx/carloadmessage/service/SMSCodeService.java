package com.wx.carloadmessage.service;

import java.util.List;
import java.util.Map;

import com.wx.service.Result;

public interface SMSCodeService {

	/**
	 * 发送验证码
	 * @param mobiles 手机号码
	 * @param randomCode 短信验证码
	 * @param 增加参数 code ，纯数字或英文字母  可不传为
	 *  @param ip
	 * @return
	 */
	public String sendSMSCode(String mobiles, String randomCode,String code,String type,String ip);
	
	/**
	 * 添加短信模板
	 * @param content 模板
	 * @param jobName 模板名称
	 */
	public void createJob(String content,String jobName);
	
	/**
	 * 将短信码存入内存
	 * @param ip
	 * @param phone
	 * @param randomCode
	 */
	public void addSMSCodeCache(String ip,String phone,String randomCode);
	
	 public List<Map> getPhoneCodeMap(String phone);
	
	/**
	 * 验证短信码
	 * @param requestPhone
	 * @param requsetCode
	 * @return
	 */
	public String vidateSMSCode(String requestPhone, String requsetCode);
	
	/**
	 * 写入短信日志表
	 * @param mobiles 手机号码
	 * @param randomCode 验证码
	 * @param code 增加参数 code ，纯数字或英文字母  可不传为0
	 * @param status 短信状态
	 * @param type 短信类型
	 * @param taskId 操作任务号（短信平台）
	 * @return
	 */
	public int saveLogSmsSend(String mobiles,String randomCode,String code,int status,int type,String taskId);
	
	/**
	 * 发送短信
	 * @param jobName 模板名称
	 * @param ip 发送短信ip
	 * @param type 短信类型
	 * @return
	 */
	public Result<String> sendSMS(String mobiles, String ip, String type);
	
}
