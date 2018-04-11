package com.wx.depotbank.dao;

import java.util.List;

import com.wx.depotbank.dto.ret.FileBankInvestRet;
import com.wx.depotbank.dto.ret.FileBankRet;

import my.comp.dao.mybatis.MybatisDao;

@MybatisDao
public interface SearchBankDao {

	/**
	 * 保存银行返回投资对账文件
	 * @param bankInvestRets
	 * @return
	 */
	public int saveFileBankInvest(List<FileBankInvestRet> bankInvestRets);
	
	/**
	 * 保存银行返回 充值、提现、红包对账文件
	 * @param saveFileBanks
	 * @return
	 */
	public int saveFileBank(List<FileBankRet> saveFileBanks);
	
	/**
	 * 删除当日投资对账记录
	 * @param date
	 */
	public void deleteBankInvestToDate(List<String> transIds);
	
	/**
	 * 删除银行返回 充值、提现、红包对账文件
	 * @param saveFileBanks
	 * @return
	 */
	public void deleteFileBankToDate(List<String> ordNos);
	
	int resettingInvest(List<String> investList);
	int resettingWithdraw(List<String> withdrawList);
	int resettingRecharge(List<String> rechargeList);
	int resettingRedpacket(List<String> redpacketList);
	
	
}
