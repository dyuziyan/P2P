package com.wx.account.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.account.dao.FundRecordDao;
import com.wx.account.domain.FundRecord;
import com.wx.enums.common.BusiEvent;
import com.wx.service.BaseService;

//TODO 待重构
@Service
public class FundRecordService extends BaseService {
	@Resource
	private FundRecordDao fundRecordDao;

	public void createRefundRecord(FundRecord fundRecord) {

		// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓此處業務考虑是否可以整合↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		BigDecimal inMoney = fundRecord.getHandleSum();
		BigDecimal incomeMoney = BigDecimal.ZERO;
		BigDecimal spendingMoney = BigDecimal.ZERO;

		// 支出的时候为负数
		if (inMoney.compareTo(BigDecimal.ZERO) < 0) {
			spendingMoney = inMoney.abs();
		} else {
			incomeMoney = inMoney;
		}

		// 是否为冻结（提现申请和项目投资均为冻结）
		String fundMode = fundRecord.getFundMode();
		if (BusiEvent.WITHDRAW_APPLY.getCode().equals(fundMode) || BusiEvent.INVEST.getCode().equals(fundMode)) {
			incomeMoney = BigDecimal.ZERO;
			spendingMoney = BigDecimal.ZERO;
		}
		fundRecord.setHandleSum(inMoney.abs());
		fundRecord.setIncome(incomeMoney);
		fundRecord.setSpending(spendingMoney);
		// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑此處業務考虑是否可以整合↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

		fundRecordDao.create(fundRecord);
	}

	public String queryWithdrawPoundage(String serialNumber) {
		return fundRecordDao.queryWithdrawPoundage(serialNumber);
	}
	
	public boolean haveFundRecord(String serialNumber) {
		if(fundRecordDao.haveFundRecord(serialNumber)>0){
			return true;
		}
		return false;
	}
}
