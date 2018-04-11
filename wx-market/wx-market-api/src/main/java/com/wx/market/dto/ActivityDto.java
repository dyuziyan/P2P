package com.wx.market.dto;

import java.util.Date;

import com.wx.dto.BaseObject;

//对应表 t_wx_activity_draw
public class ActivityDto extends BaseObject{
	
	private static final long serialVersionUID = -5773033171321737941L;
	
	private String activity_key ;    					//活动key
	private String act_Name;  							//抽奖活动名称
	private String act_desc;							//抽奖活动描述
	private Date start_time;							//开始时间
	private Date end_time;								//结束时间
	private Integer valid ;    		 					//是否有效
	private Integer handoperation_cashback;				//是否需要人工返现 0否1是
	private Date handoperation_cashback_startTime;		//手工返现操作开放开始时间
	private Date handoperation_cashback_endTime;		//手工返现操作开放结束时间
	
	public String getActivity_key() {
		return activity_key;
	}
	public void setActivity_key(String activity_key) {
		this.activity_key = activity_key;
	}
	public String getAct_Name() {
		return act_Name;
	}
	public void setAct_Name(String act_Name) {
		this.act_Name = act_Name;
	}
	public String getAct_desc() {
		return act_desc;
	}
	public void setAct_desc(String act_desc) {
		this.act_desc = act_desc;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Integer getHandoperation_cashback() {
		return handoperation_cashback;
	}
	public void setHandoperation_cashback(Integer handoperation_cashback) {
		this.handoperation_cashback = handoperation_cashback;
	}
	public Date getHandoperation_cashback_startTime() {
		return handoperation_cashback_startTime;
	}
	public void setHandoperation_cashback_startTime(Date handoperation_cashback_startTime) {
		this.handoperation_cashback_startTime = handoperation_cashback_startTime;
	}
	public Date getHandoperation_cashback_endTime() {
		return handoperation_cashback_endTime;
	}
	public void setHandoperation_cashback_endTime(Date handoperation_cashback_endTime) {
		this.handoperation_cashback_endTime = handoperation_cashback_endTime;
	}
	
		
}
