package com.wx.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.account.dao.AccountDao;
import com.wx.account.domain.FundRecord;
import com.wx.account.domain.MSGDomain;
import com.wx.account.dto.AccountDto;
import com.wx.account.service.AccountService;
import com.wx.account.service.FundRecordService;
import com.wx.account.service.MSGService;
import com.wx.account.service.NoticeTemplateService;
import com.wx.depotbank.utils.OrderUtil;
import com.wx.enums.common.BusiEvent;
import com.wx.exception.MarketServiceException;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.trade.dto.OrderDto;
import com.wx.trade.enums.CompareWithdrawStateEnum;

import my.comp.lang.Num;
import my.comp.transaction.WriteTransactional;

@Service
public class AccountServiceImpl implements AccountService {

	private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Resource
	private AccountDao accountDao;
	@Resource
	private FundRecordService fundRecordService;
//	@Resource
//	private MarketingService marketingService;
	@Resource
	private NoticeTemplateService noticeTemplateService;
	@Resource
	private MSGService MSGService;

	@Override
	public Result<AccountDto> get(long accountId) {
		AccountDto accountDto = accountDao.getAccount(accountId);
		if (accountDto == null) {
			return Results.error();
		}
		return Results.success(accountDto);
	}

	@Override
	@WriteTransactional
	public Result<String> grantReward(BusiEvent busiEvent, OrderDto order, BigDecimal reward, String sn) {
		// 增加收益
		AccountDto account = accountDao.getAccountForUpdate(order.getInvestor());
		account.setUsable_sum(account.getUsable_sum().add(reward));
		accountDao.update(account);
		String serialNumber = OrderUtil.nextIdToString();
		// 流水
		FundRecord fundRecord = new FundRecord();
		fundRecord.setUserId(order.getInvestor());
		fundRecord.setRecordTime(new Date());
		fundRecord.setOperateType(busiEvent.getCode());
		fundRecord.setFundMode(busiEvent.getLable());
		fundRecord.setHandleSum(reward);
		fundRecord.setUsableSum(account.getUsable_sum());
		fundRecord.setFreezeSum(account.getFreeze_sum());
		fundRecord.setOperateTableId(order.getProductId());
		fundRecord.setSerial_number(serialNumber);

		// 不同类型收益业务处理------此处后期改为message模块统一处理站内信
		String remark = "";
		if (BusiEvent.CASHBACK.getCode() == busiEvent.getCode()) {
			remark = noticeTemplateService.queryMsgTemplate("tender_red");
		} else if ((BusiEvent.CASHREDPACKET.getCode() == busiEvent.getCode())) {
			remark = noticeTemplateService.queryMsgTemplate("red_charge");
		}else if ((BusiEvent.HANDOPERATION_CASHBACK.getCode() == busiEvent.getCode())) {
			remark = noticeTemplateService.queryMsgTemplate("activity_success");
		}
		remark = StringUtils.replace(remark, "title", order.getProductName());
		remark = StringUtils.replace(remark, "money", reward.toString());
		fundRecord.setRemarks(remark);
		fundRecordService.createRefundRecord(fundRecord);
		MSGDomain msg=new MSGDomain();
		msg.setMailTitle(busiEvent.getLable());
		msg.setMailContent(remark);
		msg.setReciver(order.getInvestor());
		MSGService.createMsg(msg);
		// 请求存管现金红包
		Result<String> result = null;
		try {
			  result = cashRedPacket(account, reward, sn);
		} catch (MarketServiceException e) {
			return Results.error("发送现金红包失败");
		}
		if(!result.success()){
			return Results.error();
		}
		return Results.success();
	}

	public Result<String> cashRedPacket(AccountDto account, BigDecimal reward, String sn)
			throws MarketServiceException {
		// 使用分进行提交
		reward = Num.create(reward).mul(new BigDecimal("100")).bigValue();
		// 请求存管现金红包
//		RechargeReq rechargeReq = new RechargeReq();
//		rechargeReq.setPlaCustId(account.getDeposit_account());
//		rechargeReq.setMerBillNo(serialNumber);
//		rechargeReq.setMerFeeAmt((long) 0);
//		rechargeReq.setTransAmt(reward.longValue());
//		rechargeReq.setMerPriv(account.getId() + "");
//		rechargeReq.setSNum(sn);
//		BidRet result = null;
//		try {
//			result = marketingService.cashRedPacket(rechargeReq);
//			logger.info("发送现金红包响应的信息BidRet：{}", result);
//		} catch (BankException e) {
//			logger.info("发送现金红包error响应的信息BidRet：{}", result);
//			return Results.error("发送现金红包失败");
//		}
//		if (!result.success()) {
//			logger.info("发送现金红包error响应的信息BidRet：{}", result);
//			throw MarketServiceMsgException.byMsg("发送现金红包失败");
//		}
		return Results.success();
	}

	@Override
	public Result<String> withdrawStateFinal(String PlaCustId, String ChargeCorg, BigDecimal TransAmt, String MerBillNo) {
		// 增加收益
		AccountDto account = accountDao.getAccountByDepositIdForUpdate(PlaCustId);
		if(null==account){
			return Results.error("数据异常--用户不存在");
		}
		Integer busiEventCode = null;
		String busiEventLable = null;
		String remark = "";
		String serialNumber="";
		// 提现成功 减扣冻结金额 S1
		if (CompareWithdrawStateEnum.WITHDRAWSUCCESS.getKey().equals(ChargeCorg)) {
			//判断流水记录是否已经存在 已存在不做处理
			serialNumber=MerBillNo+"-S1";
			if(fundRecordService.haveFundRecord(serialNumber)){
				logger.debug("提现对账资金更改:存管用户--"+PlaCustId+"的流水订单已经存在--"+serialNumber);
				return Results.error("数据异常--该流水已经存在");
			}
			account.setFreeze_sum(account.getFreeze_sum().subtract(TransAmt));
			busiEventCode = BusiEvent.WITHDRAW_SUCCESS.getCode();
			busiEventLable = BusiEvent.WITHDRAW_SUCCESS.getLable();
			remark = noticeTemplateService.queryMsgTemplate("cash_success");
			remark = StringUtils.replace(remark, "account", StringUtils.substring(PlaCustId, 12));
			remark = StringUtils.replace(remark, "money", TransAmt.toString());
			String charge_money = fundRecordService.queryWithdrawPoundage(MerBillNo);
			remark = StringUtils.replace(remark, "charge_amount", charge_money);
			logger.debug("提现对账资金更改:存管用户-" + PlaCustId + "提现状态为--" + ChargeCorg + "--减扣冻结金额"+TransAmt);
			// 提现失败 减扣冻结金额 增加可用余额 F1 F2 R9
		} else if (CompareWithdrawStateEnum.WITHDRAWFAIL.getKey().equals(ChargeCorg)
				|| CompareWithdrawStateEnum.CANCELAFTERVERIFICATION.getKey().equals(ChargeCorg)
				|| CompareWithdrawStateEnum.WITHDRAWREFUSE.getKey().equals(ChargeCorg)) {
			//判断流水记录是否已经存在 已存在不做处理
			serialNumber=MerBillNo+"-F1";
			if(fundRecordService.haveFundRecord(serialNumber)){
				logger.debug("提现对账资金更改:存管用户--"+PlaCustId+"的流水订单已经存在--"+serialNumber);
				return Results.error("数据异常--该流水已经存在");
			}
			account.setFreeze_sum(account.getFreeze_sum().subtract(TransAmt));
			account.setUsable_sum(account.getUsable_sum().add(TransAmt));
			busiEventCode = BusiEvent.WITHDRAW_FAIL.getCode();
			busiEventLable = BusiEvent.WITHDRAW_FAIL.getLable();
			remark = noticeTemplateService.queryMsgTemplate("cash_error");
			remark = StringUtils.replace(remark, "money", TransAmt.toString());
			logger.debug("提现对账资金更改:存管用户-" + PlaCustId + "提现状态为--" + ChargeCorg + "--减扣冻结金额"+TransAmt+"同时增加可用余额");
			// 其他中间状态不做处理
		} else {
			logger.debug("提现对账资金更改:存管用户-" + PlaCustId + "提现状态为--" + ChargeCorg + "--中间状态不做处理");
			return Results.success();
		}

		accountDao.update(account);
		// 流水
		FundRecord fundRecord = new FundRecord();
		fundRecord.setUserId(account.getUser_id());
		fundRecord.setRecordTime(new Date());
		fundRecord.setOperateType(busiEventCode);
		fundRecord.setFundMode(busiEventLable);
		fundRecord.setHandleSum(TransAmt);
		fundRecord.setUsableSum(account.getUsable_sum());
		fundRecord.setFreezeSum(account.getFreeze_sum());
		fundRecord.setSerial_number(serialNumber);

		fundRecord.setRemarks(remark);
		fundRecordService.createRefundRecord(fundRecord);

		return Results.success();
	}
}
