package com.wx.account.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

import com.wx.account.domain.FundRecord;

@MybatisDao
public interface FundRecordDao extends BaseDao<FundRecord> {

	void create();

	/**
	 * 
	 * 描述：查询提现手续费
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年9月20日上午10:29:41
	 * @param serialNumber
	 * @return
	 */
	String queryWithdrawPoundage(String serialNumber);
	
	int haveFundRecord(String serialNumber);
}
