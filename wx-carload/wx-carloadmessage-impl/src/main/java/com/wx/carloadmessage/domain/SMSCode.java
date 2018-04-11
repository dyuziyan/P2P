package com.wx.carloadmessage.domain;

public class SMSCode {


	/**
	 * 发送次数
	 */
	private int sendCount;
	/**
	 * 验证次数
	 */
	private int validateCount;
	
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 发送时间
	 */
	private String time;
	
	
	public SMSCode()
	{
		
	}
	
	public  SMSCode(String phone,String code,String time)
	{
		this.sendCount = 1;
		this.validateCount = 0;
		this.phone = phone;
		this.code = code;
		this.time = time;
	}
	public void setSendCount(int sendCount)
	{
		this.sendCount = sendCount;
	}
	public int getSendCount()
	{
		return this.sendCount;
	}
	
	public void setValidateCount(int validateCount)
	{
		this.validateCount = validateCount;
	}
	public int getValidateCount()
	{
		return this.validateCount;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPhone()
	{
		return this.phone;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getCode()
	{
		return this.code;
	}
	public void setTime(String time)
	{
		this.time= time;
	}
	public String getTime()
	{
		return this.time;
	}
	
}
