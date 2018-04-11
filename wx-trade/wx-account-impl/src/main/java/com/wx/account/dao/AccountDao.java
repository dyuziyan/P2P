package com.wx.account.dao;

import my.comp.dao.mybatis.BaseDao;
import my.comp.dao.mybatis.MybatisDao;

import org.apache.ibatis.annotations.Param;

import com.wx.account.dto.AccountDto;

@MybatisDao
public interface AccountDao extends BaseDao<AccountDto>{
	
	/**
	 * 获取账户信息并加锁
	 */
	AccountDto getAccountForUpdate(@Param("accountId") long accountId) ;
	
	/**
	 * 查询账户信息
	 */
	AccountDto getAccount(@Param("accountId") long accountId) ;
	
	/**
	 * 获取账户信息并加锁(存管ID)
	 */
	AccountDto getAccountByDepositIdForUpdate(@Param("deposit_account") String deposit_account) ;
	
	/**
	 * 查询账户信息(存管ID)
	 */
	AccountDto getAccountByDepositId(@Param("deposit_account") String deposit_account) ;
	
	/**
	 * 更新账户信息(存管ID)
	 */
	int updateAccountByDepositId(AccountDto accountDto) ;
}
