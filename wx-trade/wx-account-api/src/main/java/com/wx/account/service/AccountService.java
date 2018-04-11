package com.wx.account.service;

import my.comp.rmi.annotation.RemoteService;

import java.math.BigDecimal;

import com.wx.account.dto.AccountDto;
import com.wx.enums.common.BusiEvent;
import com.wx.service.Result;
import com.wx.trade.dto.OrderDto;

@RemoteService("/accountService")
public interface AccountService {

	/**
	 * 获取账户------执行跨系统账户处理，务必在调用业务重新加锁获取重要数据
	 */
	Result<AccountDto> get(long accountId);
	
	/**
	 * 发放收益
	 */
	Result<String> grantReward(BusiEvent busiEvent,OrderDto orderId,BigDecimal reward,String sn);
	
	/**
	 * 提现最终状态账户变动
	 * @param PlaCustId 存管账户ID
	 * @param ChargeCorg 提现对账状态  对应CompareWithdrawState
	 * @param TransAmt 处理金额
	 * @param MerBillNo 流水号
	 * @param FeeAmt 手续费
	 */
	Result<String> withdrawStateFinal(String PlaCustId, String ChargeCorg, BigDecimal TransAmt, String MerBillNo);
}
