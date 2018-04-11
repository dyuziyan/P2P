package com.wx.depotbank.service;

import java.util.Date;
import java.util.List;

import com.wx.depotbank.dto.req.SearchBankReq;
import com.wx.depotbank.dto.req.TradeStatusReq;
import com.wx.depotbank.dto.ret.QueryMerchantAcctsRet;
import com.wx.depotbank.dto.ret.SearchBankRet;
import com.wx.depotbank.dto.ret.TradeStatusRet;
import com.wx.depotbank.exception.BankException;

import my.comp.rmi.annotation.RemoteService;

@RemoteService("/searchBankService")
public interface SearchBankService {

	/**
	 * 商户账户 交易查询
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<QueryMerchantAcctsRet> queryMerchantTrans(String beginDate,String endDate)throws Exception;
	
	
	/**
	 * 大额充值账号查询
	 * 
	 * @param accountTyp 账号 属性（1-对私 2-对公）
	 * @param accountNo 存管平台客户号或对公账号
	 * 					根据 AccountTyp 取不同值
	 *					1-账户存管平台客户号
	 *					2-对公账号
	 * @return
	 * @throws BankException
	 */
	public SearchBankRet queryChargeAccount(String accountTyp, String accountNo) throws BankException;
	
	
	/**
	 * 大额充值记录查询
	 * 
	 * @param searchReq
	 * @return
	 * @throws BankException
	 */
	public SearchBankRet queryChargeDetail(SearchBankReq searchReq)throws Exception;
	
	
	/**
	 * 投标对账 
	 * @return
	 * @throws BankException
	 */
	public long queryInvestFTP(Date currTime)throws Exception;


	/**
	 * 充值对账
	 * @return
	 * @throws BankException
	 */
	public long queryRechargeFTP(Date currTime)throws Exception;
	
	/**
	 * 提现对账
	 * @return
	 * @throws BankException
	 */
	public long withdrawFTP(Date currTime)throws Exception;
	
	/**
	 * 实时红包对账
	 * @return
	 * @throws BankException
	 */
	public long redPacketFTP(Date currTime)throws Exception;
	
	public long transferFTP(Date currTime)throws Exception;
	
	/**
	 * 
	 * 描述：交易状态查询（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年8月30日下午2:14:18
	 * @param tradeStatusReq
	 * @return
	 */
	public TradeStatusRet QueryTransStat(TradeStatusReq tradeStatusReq) throws BankException;

}
