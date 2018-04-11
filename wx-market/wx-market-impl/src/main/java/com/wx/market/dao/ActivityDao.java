package com.wx.market.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;
import com.wx.market.dto.ActivityDto;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

@MybatisDao
public interface ActivityDao extends BaseDao{
	
	/**
	 * 获取活动列表
	 * @param onTime 是否取活动生效期间的活动
	 * @param handoperationCashBackTime 是否取手工返现生效时间活动
	 * @param handoperation是否手工返现活动
	 */
	ArrayList<ActivityDto> getActiveList(@Param("onTime") boolean onTime,
			@Param("handoperation") boolean handoperation,@Param("handoperationCashBackTime") boolean handoperationCashBackTime) ;
	/**
	 * 查询活动是否存在
	 */
	ActivityDto getActivityById(@Param("activityId") String activityId);
}
