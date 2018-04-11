package com.wx.market.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.account.dto.AccountDto;
import com.wx.account.service.AccountService;
import com.wx.enums.common.BusiEvent;
import com.wx.market.dao.ActivityDao;
import com.wx.market.service.MarketActivityService;
import com.wx.market.service.SNCheckUpService;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.trade.dto.OrderDto;

import my.comp.lang.Num;
import my.comp.sn.SnBuilder;

import com.wx.market.dto.ActivityDto;

@Service
public class MarketActivityServiceImpl extends BaseService implements MarketActivityService {

	@Resource 
	private ActivityDao activityDao;
	@Resource
	private AccountService accountService ;
	@Resource 
	private SNCheckUpService snCheckUpService;

	@Override
	public ArrayList<ActivityDto> getActiveList(boolean onTime, boolean handoperation, boolean handoperationCashBackTime) {
		return activityDao.getActiveList(onTime, handoperation, handoperationCashBackTime);
	}

	@Override
	public Result<String> handoperationCashback(long userId, BigDecimal cashbackMoney, String activityId) {
		ActivityDto activityDto=activityDao.getActivityById(activityId);
		
		BigDecimal _cashbackMoney = cashbackMoney.setScale(2 ,BigDecimal.ROUND_DOWN);
		
		if(activityDto==null||activityDto.getValid()==0){
			logger.debug("通过活动"+activityId+"手工返现给用户"+userId+"的返现金额"+_cashbackMoney+"元处理失败-----活动不存在");
			return Results.error("活动不存在");
		}
		if(activityDto.getHandoperation_cashback()==0){
			logger.debug("通过活动"+activityId+"手工返现给用户"+userId+"的返现金额"+_cashbackMoney+"元处理失败-----活动不处于返现可用状态");
			return Results.error("活动不存在");
		}
		Date nowDate=new Date();
		if(!(activityDto.getHandoperation_cashback_startTime().before(nowDate)
				&&activityDto.getHandoperation_cashback_endTime().after(nowDate))){
			logger.debug("通过活动"+activityId+"手工返现给用户"+userId+"的返现金额"+_cashbackMoney+"元处理失败-----活动在返现时间范围内");
			return Results.error("活动不存在");
		}
		Result<AccountDto> accountResult = accountService.get(userId) ;
		if(!accountResult.success()){
			logger.debug("通过活动"+activityId+"手工返现给用户"+userId+"的返现金额"+_cashbackMoney+"元处理失败-----用户不存在");
			return Results.error("用户不存在");
		}
		
		//业务唯一性---目前先查询流水里面的---后面应为不同业务流水表里面插入失败返回异常，不做判断
		
		SnBuilder sn = SnBuilder.create(activityId,userId,_cashbackMoney.multiply(new BigDecimal("100")).longValue()) ;
		if(snCheckUpService.checkSnExist(sn.toString())){
			logger.debug("手工返现:活动奖励返现SN:"+sn+" 奖励已经发放,不再重复发放");
			return Results.error("已经发放,不再重复发放");
		};
		OrderDto orderDto=new OrderDto();
		orderDto.setInvestor(userId);
		//此处需从数据库开始重构
		orderDto.setProductId(99999L);
		orderDto.setProductName(activityDto.getAct_Name());
		Result grantRewardResult=accountService.grantReward(BusiEvent.HANDOPERATION_CASHBACK,orderDto,cashbackMoney,sn.toString());
		if(!grantRewardResult.success()){
			return Results.error("执行奖励营销奖励数据录入异常");
		}
		logger.debug("活动"+activityDto.getAct_Name()+"给用户:"+userId+" 的返现金额为: "+cashbackMoney);
		return Results.success();
	}

}
