package com.wx.depotbank.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dao.SearchBankDao;
import com.wx.depotbank.dto.req.SearchBankReq;
import com.wx.depotbank.dto.req.TradeStatusReq;
import com.wx.depotbank.dto.ret.FileBankInvestRet;
import com.wx.depotbank.dto.ret.FileBankRet;
import com.wx.depotbank.dto.ret.QueryMerchantAcctsRet;
import com.wx.depotbank.dto.ret.SearchBankRet;
import com.wx.depotbank.dto.ret.TradeStatusRet;
import com.wx.depotbank.enums.BizType;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

import my.comp.lang.DateUtils;
import my.comp.vfs.SFTPUtils;


/**
 * @ClassName: SearchBankServiceImpl
 * @version 1.0
 * @Desc: TransactionServiceImpl
 * @author shiliang.feng
 * @date 2017年7月28日下午4:40:00
 * @history v1.0
 *
 */
@Service
public class SearchBankServiceImpl implements SearchBankService {
	
	
	private static Logger logger = LoggerFactory.getLogger(SearchBankServiceImpl.class);
	@Autowired
	private SearchBankDao searchBankDao;
	
	@Override
	public List<QueryMerchantAcctsRet> queryMerchantTrans(String beginDate, String endDate) throws Exception {
		SearchBankReq searchReq = new SearchBankReq();
		searchReq.setBiz_type(BizType.QueryMerchantTrans.getKey());
		searchReq.setStartDate(beginDate);
		searchReq.setEndDate(endDate);
		
		logger.info("商户账户交易查询，searchReq = {}", searchReq);
		SearchBankRet ret = RequestUtils.sendParam(searchReq, SearchBankRet.class, RequestType.BACKSTAGE,
				searchReq.getQueryMerchantTransMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO)); 
		if(ret == null){
			return null;
		}
		if(StringUtils.isBlank(ret.getFileName())){
			logger.info("商户账户交易查询，返回文件名为空！");
			return null;
		}
		Calendar curr = Calendar.getInstance();
		String currDate = DateUtils.formatDate(curr.getTime(), "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = ret.getFileName();
		List<String> resultList = downFileFTP(path, fileName);
		List<QueryMerchantAcctsRet> save = new ArrayList<QueryMerchantAcctsRet>();
		for (String str : resultList) {
			QueryMerchantAcctsRet bankInvestRet = new QueryMerchantAcctsRet();
			String[] result = str.split("\\|");
			bankInvestRet.setTransId(result[0]);
			bankInvestRet.setCreDt(result[1]);
			bankInvestRet.setAcTyp(result[2]);
			BigDecimal TransAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));
			bankInvestRet.setTransAmt(TransAmt.toString());
			bankInvestRet.setTransDesc(result[4]);
			save.add(bankInvestRet);
		}
		return save;
		
	}

	@Override
	public SearchBankRet queryChargeAccount(String accountTyp, String accountNo) throws BankException {
		SearchBankReq searchReq = new SearchBankReq();
		searchReq.setAccountTyp(accountTyp);
		searchReq.setAccountNo(accountNo);
		searchReq.setBiz_type(BizType.QueryChargeAccount.getKey());
		
		logger.info("大额充值账号查询，searchReq = {}", searchReq);
		return RequestUtils.sendParam(searchReq, SearchBankRet.class, RequestType.BACKSTAGE,
				searchReq.getQueryChargeAccountMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO)); 
	}

	@Override
	public SearchBankRet queryChargeDetail(SearchBankReq searchReq) throws BankException {
		searchReq.setBiz_type(BizType.QueryChargeDetail.getKey());
		logger.info("大额充值记录查询，searchReq = {}", searchReq);
		return RequestUtils.sendParam(searchReq, SearchBankRet.class, RequestType.BACKSTAGE,
				searchReq.getQueryChargeDetailMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO)); 
	}

	@Override
	public long queryInvestFTP(Date currTime) throws Exception{
		Calendar curr = Calendar.getInstance();
		curr.setTime(currTime);
		curr.add(Calendar.DATE , -1);
		Date frontDate = curr.getTime();
		String currDate = DateUtils.formatDate(currTime, "yyyyMMdd");
		String frontDates = DateUtils.formatDate(frontDate, "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = BankConstant.PARTNER_ID+"_"+frontDates+BankConstant.INVEST_FILENAME;
		List<String> resultList = downFileFTP(path, fileName);
		List<FileBankInvestRet> save = new ArrayList<FileBankInvestRet>();
		StringBuffer errorMessage = new StringBuffer();
		List<String> transIds = new ArrayList<String>();//防止有重复数据
		for (String str : resultList) {
			FileBankInvestRet bankInvestRet = new FileBankInvestRet();
			String[] result = str.split("\\|");
//			if(result.length ){}
			bankInvestRet.setTransId(result[0]);
			transIds.add(result[0]);
			bankInvestRet.setMercId(result[1]);
			bankInvestRet.setPlaCustId(result[2]);
			BigDecimal transAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));//分
			bankInvestRet.setTransAmt(transAmt);
			bankInvestRet.setBorrowId(result[4]);
			bankInvestRet.setCreDt(result[5]);
			bankInvestRet.setCreTm(result[6]);
			bankInvestRet.setOrdSts(result[7]);
			bankInvestRet.setMerBillNo(result[8]);
			errorMessage.append(result[8]).append(",");
			save.add(bankInvestRet);
		}
		long result = 0l;
		if(save.size()>0){
			try{
				searchBankDao.resettingInvest(transIds);
				searchBankDao.deleteBankInvestToDate(transIds);
				result = searchBankDao.saveFileBankInvest(save);
			}catch (Exception e) {
				logger.error("充值对账定时任务执行异常：{}",errorMessage);
			}
		}
		//保存到数据库
		return result;
	}
	
	
	@Override
	public long queryRechargeFTP(Date currTime) throws Exception{
		Calendar curr = Calendar.getInstance();
		curr.setTime(currTime);
		curr.add(Calendar.DATE , -1);
		Date frontDate = curr.getTime();
		String currDate = DateUtils.formatDate(currTime, "yyyyMMdd");
		String frontDates = DateUtils.formatDate(frontDate, "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = BankConstant.PARTNER_ID+"_"+frontDates+BankConstant.RECHARGE_FILENAME;
		List<String> resultList = downFileFTP(path, fileName);
		if(resultList.size() <=  0){
			logger.error("充值对账定时任务执行异常,数据为空{}",resultList.size());
		}
		List<String> ordNos = new ArrayList<String>();//防止有重复数据
		List<FileBankRet> save = new ArrayList<FileBankRet>();
		StringBuffer errorMessage = new StringBuffer();
		for (String str : resultList) {
			FileBankRet bankRet = new FileBankRet();
			String[] result = str.split("\\|");
//			if(result.length ){}
			bankRet.setOrdNo(result[0]);
			ordNos.add(result[0]); 
			bankRet.setCreDt(result[1]);
			BigDecimal transAmt = new BigDecimal(result[2]).divide(new BigDecimal(100));//分
			bankRet.setTransAmt(transAmt);
			BigDecimal FeeAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));//分
			bankRet.setFeeAmt(FeeAmt);
			bankRet.setPlaCustId(result[4]);
			bankRet.setMerBillNo(result[5]);
			errorMessage.append(result[5]).append(",");
			bankRet.setChargeCorg(result[6]);
			bankRet.setType("1");//充值
			save.add(bankRet);
		}
		long result = 0l;
		if(save.size()>0){
			try{
				searchBankDao.resettingRecharge(ordNos);
				searchBankDao.deleteFileBankToDate(ordNos);
				result = searchBankDao.saveFileBank(save);
			}catch (Exception e) {
				logger.error("充值对账定时任务执行异常：{}",errorMessage);
			}
		}
		return result;
	}
	
	@Override
	public long withdrawFTP(Date currTime) throws Exception{
		Calendar curr = Calendar.getInstance();
		curr.setTime(currTime);
		curr.add(Calendar.DATE , -1);
		Date frontDate = curr.getTime();
		String currDate = DateUtils.formatDate(currTime, "yyyyMMdd");
		String frontDates = DateUtils.formatDate(frontDate, "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = BankConstant.PARTNER_ID+"_"+frontDates+BankConstant.WITHDRAW_FILENAME;
		List<String> resultList = downFileFTP(path, fileName);
		if(resultList.size() <=  0){
			logger.error("提现对账定时任务执行异常,数据为空{}",resultList.size());
		}
		List<String> ordNos = new ArrayList<String>();//防止有重复数据
		List<FileBankRet> save = new ArrayList<FileBankRet>();
		StringBuffer errorMessage = new StringBuffer();
		for (String str : resultList) {
			FileBankRet bankRet = new FileBankRet();
			String[] result = str.split("\\|");
//			if(result.length ){}
			bankRet.setOrdNo(result[0]);
			ordNos.add(result[0]);
			bankRet.setMerBillNo(result[1]);
			errorMessage.append(result[1]).append(",");
			bankRet.setCreDt(result[2]);
			BigDecimal transAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));//分
			bankRet.setTransAmt(transAmt);
			BigDecimal FeeAmt = new BigDecimal(result[4]).divide(new BigDecimal(100));//分
			bankRet.setFeeAmt(FeeAmt);
			bankRet.setPlaCustId(result[5]);
			bankRet.setChargeCorg(result[6]);
			if(result.length >= 8){
			bankRet.setFalRsn(result[7]);
			}
			bankRet.setType("2");//提现
			save.add(bankRet);
		}
		long result = 0l;
		if(save.size()>0){
			try{
				searchBankDao.resettingWithdraw(ordNos);
				searchBankDao.deleteFileBankToDate(ordNos);
				result = searchBankDao.saveFileBank(save);
			}catch (Exception e) {
				logger.error("提现对账定时任务执行异常：{}",errorMessage);
			}
		}
		return result;
	}

	@Override
	public long redPacketFTP(Date currTime) throws Exception{
		Calendar curr = Calendar.getInstance();
		curr.setTime(currTime);
		curr.add(Calendar.DATE , -1);
		Date frontDate = curr.getTime();
		String currDate = DateUtils.formatDate(currTime, "yyyyMMdd");
		String frontDates = DateUtils.formatDate(frontDate, "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = BankConstant.PARTNER_ID+"_"+frontDates+BankConstant.REDPACKET_FILENAME;
		List<String> resultList = downFileFTP(path, fileName);
		if(resultList.size() <=  0){
			logger.error("实时红包对账定时任务执行异常,数据为空{}",resultList.size());
		}
		List<String> ordNos = new ArrayList<String>();//防止有重复数据
		List<FileBankRet> save = new ArrayList<FileBankRet>();
		StringBuffer errorMessage = new StringBuffer();
		for (String str : resultList) {
			FileBankRet bankRet = new FileBankRet();
			String[] result = str.split("\\|");
//			if(result.length ){}
			bankRet.setOrdNo(result[0]);
			ordNos.add(result[0]);
			bankRet.setMerBillNo(result[1]);
			errorMessage.append(result[1]).append(",");
			bankRet.setCreDt(result[2]);
			BigDecimal transAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));//分
			bankRet.setTransAmt(transAmt);
			bankRet.setPlaCustId(result[4]);
			bankRet.setType("3");//红包
			save.add(bankRet);
		}
		long result = 0l;
		if(save.size()>0){
			try{
				searchBankDao.resettingRedpacket(ordNos);
				searchBankDao.deleteFileBankToDate(ordNos);
				result = searchBankDao.saveFileBank(save);
			}catch (Exception e) {
				logger.error("实时红包对账定时任务执行异常：{}",errorMessage);
			}
		}
		return result;
	}
	@Override
	public long transferFTP(Date currTime) throws Exception {
		Calendar curr = Calendar.getInstance();
//		curr.set(2017, 07, 19);
		curr.setTime(currTime);
		String currDate = DateUtils.formatDate(curr.getTime(), "yyyyMMdd");
		curr.add(Calendar.DATE , -1);
		Date frontDate = curr.getTime();
		String frontDates = DateUtils.formatDate(frontDate, "yyyyMMdd");
		String path = "CHK/"+currDate+"/";
		String fileName = BankConstant.PARTNER_ID+"_"+frontDates+BankConstant.TRANSFER_FILENAME;
		List<String> resultList = downFileFTP(path, fileName);
		if(resultList.size() <=  0){
			logger.error("资金迁移定时任务执行异常,数据为空{}",resultList.size());
		}
		List<String> ordNos = new ArrayList<String>();//防止有重复数据
		List<FileBankRet> save = new ArrayList<FileBankRet>();
		StringBuffer errorMessage = new StringBuffer();
		for (String str : resultList) {
			FileBankRet bankRet = new FileBankRet();
			String[] result = str.split("\\|");
			bankRet.setOrdNo(result[0]);
			ordNos.add(result[0]);
			bankRet.setMerBillNo(result[1]);
			errorMessage.append(result[1]).append(",");
			bankRet.setCreDt(result[2]);
			BigDecimal transAmt = new BigDecimal(result[3]).divide(new BigDecimal(100));//分
			bankRet.setTransAmt(transAmt);
			bankRet.setPlaCustId(result[4]);
			bankRet.setType("4");//资金迁移
			save.add(bankRet);
		}
		long result = 0l;
		if(save.size()>0){
			try{
				searchBankDao.deleteFileBankToDate(ordNos);
				result = searchBankDao.saveFileBank(save);
			}catch (Exception e) {
				logger.error("资金迁移对账定时任务执行异常：{}",errorMessage);
			}
		}
		return result;
	}
	
	public List<String> downFileFTP(String path, String fileName) throws Exception {
		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String line = null;
		SFTPUtils utils = new SFTPUtils(BankConstant.SFTP_NAME, BankConstant.SFTP_PWD, BankConstant.SFTP_IP,
				new Integer(BankConstant.SFTP_PORT));
		InputStream inputStream = utils.downFile(path, fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
		try {
			while ((line = reader.readLine()) != null) {
				list.add(line);
				sb.append(line);
			}
			logger.info(DateUtils.formatDate(new Date(), "yyyy年MM月dd日") + "银行存管，" + fileName + "文件返回值为：{}",
					sb.toString());
		} finally {
			inputStream.close();
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		String transId = "201708310001795679,201708310001797190,";
		System.out.println(transId.lastIndexOf(","));
		System.out.println(transId.length());
		System.out.println(transId.substring( 0 , transId.lastIndexOf(",")));
	}

	@Override
	public TradeStatusRet QueryTransStat(TradeStatusReq tradeStatusReq) throws BankException {
		logger.info("交易状态查询（后台方式），tradeStatusReq = {}", tradeStatusReq);
		return RequestUtils.sendParam(tradeStatusReq, TradeStatusRet.class, RequestType.BACKSTAGE,
				tradeStatusReq.getQueryTransStatMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

}
