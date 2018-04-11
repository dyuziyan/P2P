package com.wx.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wx.trade.domain.BalanceOfAccount;
import com.wx.trade.domain.ToCompareDomain;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;


@MybatisDao
public interface BalanceOfAccountDao extends BaseDao<BalanceOfAccount>{
	//TODO
	//该处sql存着很多死值，后期考虑拆分成校验部分和取值部分，需重构
	/**
	 * 获取 表t_wx_bank_charge_wdc_redpaper_file,t_wx_bank_invest_file所有未对账处理数据的总条数
	 */
	Integer getCountForBalanceOfAccount(@Param("type") String type);
	Integer getCountForInvestBalanceOfAccount();
	
	/**
	 * 获取 表t_invest，t_withdraw，t_fundrecord t_invest所有未对账处理数据的总条数(今天之前所有未处理数据)
	 */
	Integer getCountForRecharge();
	Integer getCountForRedPacket();
	Integer getCountForWithdraw();
	Integer getCountForFundTransfer();
	Integer getCountForInvest();
	
	/**
	 * 根据ID取模分页获取表t_wx_bank_charge_wdc_redpaper_file,t_wx_bank_invest_file数据
	 * @Param modNum   取模分页总页数
	 * @Param modPage  取模分页展示页数
	 */
	List<BalanceOfAccount> getBalanceOfAccountList(@Param("type") String type,@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	List<BalanceOfAccount> getInvestBalanceOfAccountList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	/**
	 * 根据订单号取模分页获取t_invest，t_withdraw，t_fundrecord,t_invest整合数据(今天之前所有未处理数据)
	 * @Param modNum   取模分页总页数
	 * @Param modPage  取模分页展示页数
	 */
	List<ToCompareDomain> getRechargeCompareList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	List<ToCompareDomain> getRedPacketCompareList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	List<ToCompareDomain> getWithdrawCompareList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	List<ToCompareDomain> getFundTransferCompareList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);
	List<ToCompareDomain> getInvestCompareList(@Param("modNum") Integer modNum,@Param("modPage") Integer modPage);

	/**
	 * 已对账数据状态更改
	 */
	int batchUpdateBalanceOfAccount(List<BalanceOfAccount> BalanceOfAccountList);
	int batchUpdateInvestBalanceOfAccount(List<BalanceOfAccount> BalanceOfAccountList);
	
	
	int batchUpdateRecharge(List<BalanceOfAccount> BalanceOfAccountList);
	int batchUpdateRedPacket(List<BalanceOfAccount> BalanceOfAccountList);
	int batchUpdateFundTransfer(List<BalanceOfAccount> BalanceOfAccountList);
	int batchUpdateInvest(List<BalanceOfAccount> BalanceOfAccountList);
	/**
	 * SQL里有校验更新条件，如果提现还为中间状态，不改为对账状态
	 */
	int batchUpdateWithdraw(List<BalanceOfAccount> BalanceOfAccountList);
	
	/**
	 * 异常数据保存
	 */
	int batchUpdateToBeCompare(List<ToCompareDomain> toCompareDomainList);
	
	List<ToCompareDomain> getCompareList(List<ToCompareDomain> toCompareDomainList);

}
