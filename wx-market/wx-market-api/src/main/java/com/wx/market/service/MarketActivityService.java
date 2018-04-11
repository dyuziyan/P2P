package com.wx.market.service;

import my.comp.rmi.annotation.RemoteService;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.wx.market.dto.ActivityDto;
import com.wx.service.Result;

@RemoteService("/marketActivityService")
public interface MarketActivityService {


	/**
	 * 获取活动列表
	 * @param onTime 是否取活动生效期间的活动
	 * @param handoperationCashBackTime 是否取手工返现生效时间活动
	 * @param handoperation是否手工返现活动
	 */
	ArrayList<ActivityDto> getActiveList(boolean onTime,boolean handoperation,boolean handoperationCashBackTime);
	
	/**
	 * 手工返现
	 * @param userId
	 * @param cashbackMoney
	 * @param activityName
	 * @return
	 */
	Result<String> handoperationCashback(long userId,BigDecimal cashbackMoney,String activityId);

}
