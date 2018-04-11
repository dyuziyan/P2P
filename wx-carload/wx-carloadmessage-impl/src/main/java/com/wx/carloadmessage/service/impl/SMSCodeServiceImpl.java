package com.wx.carloadmessage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadmember.dto.login.UserlistDTO;
import com.wx.carloadmessage.dao.LogSmsSendDao;
import com.wx.carloadmessage.domain.LogSmsSend;
import com.wx.carloadmessage.service.SMSCodeService;
import com.wx.carloadmessage.utils.SmsUtil;
import com.wx.carloadmessage.utils.ValidateCodeUtil;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.support.Messages;
import com.wx.util.JSONUtils;

import my.comp.sms.SmsService;
import my.comp.sms.constant.YHMSmsDto;
import my.comp.sms.impl.YHMSmsServiceImpl;
import net.sf.json.JSONObject;

@Component
@Service
public class SMSCodeServiceImpl implements SMSCodeService{

	private static final Logger logger = LoggerFactory.getLogger(SMSCodeServiceImpl.class);
	
	@Resource
    private LogSmsSendDao logSmsSendDao;
	
	@Override
	public String sendSMSCode(String mobiles, String randomCode,String code,String type,String ip) {
		try {
			
			if(ValidateCodeUtil.heartValidate(ip, mobiles)){
				String str = SmsUtil.sendSms(mobiles, randomCode, code,"");
				return  str;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addSMSCodeCache(String ip, String phone, String randomCode) {
		String[] temp = phone.split(",");
		for (int i = 0; i < temp.length; i++) {
			ValidateCodeUtil.add(ip, temp[i], randomCode);
		}
	}

	@Override
	public String vidateSMSCode(String requestPhone, String requsetCode) {
		
		return ValidateCodeUtil.vidateSMSCode(requestPhone,requsetCode);
	}

	@Override
	public int saveLogSmsSend(String mobiles,String randomCode,String code,int status,int type,String taskId) {
		try {
			String[] temp = mobiles.split(",");
			List<LogSmsSend> lsit =new ArrayList<LogSmsSend>();
			for (int i = 0; i < temp.length; i++) {
				LogSmsSend lss = new LogSmsSend();
				lss.setInputtime(new Date());
				lss.setPhoneNum(temp[i]);
				lss.setSendTime(new Date());
				lss.setSmsContent(randomCode);
				lss.setSmsState(status);
				lss.setSmsType(type);
				lss.setSendTime(new Date());
				lss.setTaskId(taskId);
				lsit.add(lss);
			}
			return logSmsSendDao.saveLogSmsSend(lsit);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void createJob(String content,String jobName) {
		try {
			SmsUtil.createJob(content,jobName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Result<String> sendSMS(String mobiles, String ip, String type) {
		/*
		 * try { String str = SmsUtil.sendSms(mobile, good, "", jobName);
		 * logger.info("*********发送短信,mobile:{},good:{},jobName:{"+jobName+
		 * "},smsReturn:{"+str+"}",mobile,good); JSONObject obj =
		 * JSONUtils.toJSONObject(str); Integer status = obj.get("Status") ==
		 * null ? null : Integer.parseInt(obj.get("Status").toString()); String
		 * taskId = obj.get("TaskId") == null ? "" :
		 * obj.get("TaskId").toString(); saveLogSmsSend(mobile, good, null,
		 * status, 0, taskId); if (status != null && status == 1) { return
		 * Results.success(); } return Results.error(); } catch (Exception e) {
		 * e.printStackTrace(); return Results.error(); }
		 */

		try {
			// 随机产生6位数字
			int intCount = 0;
			intCount = (new Random()).nextInt(999999); // 最大值位999999
			if (intCount < 100000)
				intCount += 100000; // 最小值位100001
			// 注册短信
			String good = "";
			if ("1".equals(type)) {
				good = "注册验证码为：" + intCount + "，请在5分钟内输入";
			} else if ("2".equals(type)) {// 修改短信短信
				good = "账号安全验证码为：" + intCount + "，请在5分钟内输入";
			}

			String userName = Messages.getMessage("userName");// 用户名
			String password = Messages.getMessage("passWord");// 密码
			String signature = Messages.getMessage("signature");// 签名
			String jobName = Messages.getMessage("jobName");// 模板名称

			// 手机集合可多个130xxxx,132xxxx,135xxxx
			List<String> mobilesList = new ArrayList<String>();
			mobilesList.add(mobiles);

			// 对应手机号发送的短信内容code0对应第一个手机号发送的内容以此类推
			List<String> codeList = new ArrayList<String>();
			codeList.add(good);
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (int i = 0; i < mobilesList.size(); i++) {
				map.put("code"+i, codeList);
			}

			YHMSmsDto smsDto = new YHMSmsDto();
			smsDto.setMobiles(mobilesList);// 手机号
			smsDto.setSmsTemplet(jobName);// 模板名称
			smsDto.setCode(map);// 手机对应的内容
			SmsService smsService = new YHMSmsServiceImpl(userName, password, signature, ""); // 实例化短信接口
			String str = smsService.send(smsDto);// 发送短信
			logger.info("发送短信结束：出参:{}", str);
			if (StringUtils.isBlank(str)) {
				return Results.byMessage("0", "发送短信验证码失败");
			}

			JSONObject obj = JSONUtils.toJSONObject(str);
			Integer status = obj.get("Status") == null ? null : Integer.parseInt(obj.get("Status").toString());
			String taskId = obj.get("TaskId") == null ? null : obj.get("TaskId").toString();
		//	String message = obj.get("Message") == null ? "" : obj.get("Message").toString();
			// 写入短信日志表
			this.saveLogSmsSend(mobiles, good, null, status, Integer.parseInt(type), taskId);
			if (status != null && status == 1) {
				// 将短信码存入内存
				this.addSMSCodeCache(ip, mobiles, intCount+"");
				return Results.byMessage("1", "发送短信验证码成功");
			} else {
				return Results.byMessage("0", "发送短信验证码失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Results.byMessage("-1", "系统异常");
		}
	}

	@Override
	public List<Map> getPhoneCodeMap(String phone) {
		return logSmsSendDao.getPhoneCodeMap(phone);
	}

}
