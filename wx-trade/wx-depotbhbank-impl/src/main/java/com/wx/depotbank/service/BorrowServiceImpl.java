/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dao.BankProductDao;
import com.wx.depotbank.dao.BankUserDao;
import com.wx.depotbank.dto.req.BidReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.req.RepaymentReq;
import com.wx.depotbank.dto.ret.BaseRet;
import com.wx.depotbank.dto.ret.BidRet;
import com.wx.depotbank.enums.BizType;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.FileUtils;
import com.wx.depotbank.utils.OrderUtil;
import com.wx.depotbank.utils.RequestUtils;
import com.wx.market.dto.InvestMessage;
import com.wx.market.jms.MarketMessageSender;
import com.wx.market.service.MarketService;

import my.comp.lang.DateUtils;
import my.comp.vfs.SFTPUtils;

/**
 * @ClassName: BorrowServiceImpl
 * @version 1.0
 * @Desc: 标的service
 * @author xiaojun.zhou
 * @date 2017年6月23日上午11:11:21
 * @history v1.0
 *
 */
@Service
public class BorrowServiceImpl implements BorrowService {

	private static Logger logger = LoggerFactory.getLogger(BorrowServiceImpl.class);

	@Autowired
	private BankProductDao bankProductDao;
	@Autowired
	private BankUserDao bankUserDao;
	@Resource
	private MarketService marketService;
	
	@Override
	public BidRet createBid(BidReq bidReq) throws BankException {
		logger.info("建标（后台方式)，bidReq = {}", bidReq);
		return RequestUtils.sendParam(bidReq, BidRet.class, RequestType.BACKSTAGE,
				bidReq.getCreateBidMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}


	@Override
	@MarketMessageSender
	public BidRet backInvest(InvestReq investReq,InvestMessage investMessage) throws BankException {
		logger.info("用户投标（后台方式)，investReq = {}", investReq);
		BidRet bidReuslt= RequestUtils.sendParam(investReq, BidRet.class, RequestType.BACKSTAGE,
				investReq.getBackInvestMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
		if(bidReuslt.success()){
			marketService.investSuccess(investMessage);
		}
		return bidReuslt;
	} 

	@Override
	public BaseRet releaseBid(BidReq bidReq) throws BankException {
		synchronized (this) {
			bidReq.setBiz_type(BizType.FileRelease.getKey());
			bidReq.setMerBillNo(OrderUtil.nextIdToString());
			bidReq.setMerPriv(bidReq.getBorrowId());
			Date currentTime = new Date();
			String filePath = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(currentTime);
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				filePath = "D:\\file\\" + dateString + "\\";
			}else{
				filePath = "/file/";
			}
			
			String fileName = BankConstant.PARTNER_ID + "_" + dateString + "_FileRelease_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss")+".txt";
			if(!createFile(bidReq, fileName , filePath)){
				BaseRet ret = new BaseRet();
				ret.setRespCode("500");
				ret.setRespDesc("文件上传失败");
				return ret;
			}
			bidReq.setFileName(fileName);
			logger.info("放款，bidReq = {}", bidReq);

			return RequestUtils.sendParam(bidReq, BidRet.class, RequestType.BACKSTAGE,
					bidReq.getReleaseBidMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
		}
	}

	/**
	 * 描述：放款 创建 上传文件
	 * 
	 * @param bidReq
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	private boolean createFile(BidReq bidReq, String fileName ,String filePath) {
		StringBuffer sb = new StringBuffer();
		try {
			HashMap<String,String> productResult = bankProductDao.queryProductInfoById(bidReq.getBorrowId());
			HashMap<String,String> repaymentInfo = bankProductDao.queryRepaymentByPid(bidReq.getBorrowId());
			if(productResult.isEmpty() || productResult.get("borrowerId") == null || "0".equals(productResult.get("borrowerId"))){
				throw new BankException("标的是否为空："+productResult.isEmpty()+"，借款人："+productResult.get("borrowerId"));
			}
			String borrowerId = String.valueOf(productResult.get("borrowerId"));
			HashMap<String,String> userResult = bankUserDao.queryUserInfoById(borrowerId);
			if(userResult.isEmpty()){
				throw new BankException("借款人信息为空！");
			}
			List<HashMap<String,String>> investResult = bankProductDao.queryInvestInfoById(bidReq.getBorrowId());
			
//			BigDecimal transAmt = new BigDecimal(String.valueOf(productResult.get("investAmount"))).multiply(new BigDecimal(100));// 交易金额（分）
			BigDecimal transAmt = new BigDecimal(String.valueOf(repaymentInfo.get("stillPrincipal"))).multiply(new BigDecimal(100));// t_repayment 待还本金stillPrincipal
			String feeAmt = "0";// 借款人手续费
			BigDecimal borrowerAmt =  new BigDecimal(String.valueOf(productResult.get("productAmount"))).multiply(new BigDecimal(1000000));// 标的金额（分）
			String borrCustId = userResult.get("deposit_account");// 账户存管平台借款人ID号
			int totalNum = investResult.size();// 投资总笔数
			sb.append(bidReq.getChar_set() + "|" + BankConstant.PARTNER_ID + "|" + bidReq.getMerBillNo() + "|"+ transAmt.longValue() + "|" + feeAmt + "|" + bidReq.getBorrowId() + "|" + borrowerAmt.longValue() + "|" + borrCustId	+ "|0||" + totalNum + "|");
			// 拼接内容
			FileUtils.createFile( filePath+fileName , sb.toString());
			FileUtils.createFile( filePath+fileName+".OK" , "");
			for (int i = 0; i < investResult.size(); i++) {
				sb = new StringBuffer();
				Map<String,String> investMap = investResult.get(i);
				String PlaCustId = investMap.get("deposit_account"); //账户存管平台 客户号
				BigDecimal TransAmt = new BigDecimal(String.valueOf(investMap.get("investAmount"))).multiply(new BigDecimal(100)); //交易金额
				String FreezeId = String.valueOf(investMap.get("freezeId"));//冻结编号
				sb.append(i + "|"+PlaCustId+"|" + TransAmt.longValue() + "|"+FreezeId+"|");
				FileUtils.writeFileContent( filePath+fileName , sb.toString());
			} 
			SFTPUtils utils = new SFTPUtils(BankConstant.SFTP_NAME,BankConstant.SFTP_PWD,BankConstant.SFTP_IP,new Integer(BankConstant.SFTP_PORT));
			boolean flag =utils.upFile(DateUtils.formatDate(new Date(), "yyyyMMdd"), fileName, filePath+fileName, false, true );
			if(flag){
				flag = utils.upFile("", fileName + ".OK", filePath+fileName + ".OK", true, false );
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 描述：还款 创建 上传文件
	 * 
	 * @param bidReq
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	@Override
	public BaseRet repaymentBid(RepaymentReq repaymentReq) throws Exception {
		synchronized (this) {
			repaymentReq.setBiz_type(BizType.FileRepayment.getKey());
			repaymentReq.setMerBillNo(OrderUtil.nextIdToString());
			repaymentReq.setMerPriv(repaymentReq.getBorrowId());
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(currentTime);
			String filePath = "";
			String os = System.getProperty("os.name");
			if(os.toLowerCase().startsWith("win")){  
				filePath = "D:\\file\\" + dateString + "\\";
			}else{
				filePath = "/file/";
			}
			String fileName = BankConstant.PARTNER_ID + "_" + dateString + "_FileRepayment_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss")+ ".txt";
			//文件上传 SFTP
			if(!repaymentCreateFile(repaymentReq, fileName , filePath)){
				BaseRet ret = new BaseRet();
				ret.setRespCode("500");
				ret.setRespDesc("文件上传失败");
				return ret;
			}
			repaymentReq.setFileName(fileName);
			logger.info("还款，repaymentReq = {}", repaymentReq);

			return RequestUtils.sendParam(repaymentReq, BidRet.class, RequestType.BACKSTAGE,
					repaymentReq.getRepaymentMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
		}
	}
	
	/**
	 * 还款创建，上传文件
	 * @param bidReq
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	private boolean repaymentCreateFile(RepaymentReq bidReq, String fileName, String filePath) throws Exception {
		StringBuffer sb = new StringBuffer();
		HashMap<String, String> productResult = bankProductDao.queryProductInfoById(bidReq.getBorrowId());
		HashMap<String, String> repaymentInfo = bankProductDao.queryRepaymentByPid(bidReq.getBorrowId());
		if (productResult.isEmpty() || productResult.get("borrowerId") == null
				|| "0".equals(productResult.get("borrowerId"))) {
			throw new BankException("标的是否为空：" + productResult.isEmpty() + "，借款人：" + productResult.get("borrowerId"));
		}
		String borrowerId = String.valueOf(productResult.get("borrowerId"));
		HashMap<String, String> userResult = bankUserDao.queryUserInfoById(borrowerId);
		if (userResult.isEmpty()) {
			throw new BankException("借款人信息为空！");
		}
		List<HashMap<String, String>> investResult = bankProductDao.queryInvestInfoById(bidReq.getBorrowId());

		// 为借款人还款总金额，包含本金+收益+商户收借款人手续费
		// BigDecimal transAmt = new
		// BigDecimal(String.valueOf(productResult.get("productAmount"))).multiply(new
		// BigDecimal(1000000)).add(InterestSum);// 交易金额（分）
		// 待还利息 = 待还利息-已还罚息
		BigDecimal InterestSum = new BigDecimal(String.valueOf(repaymentInfo.get("stillInterest")))
				.subtract(new BigDecimal(String.valueOf(repaymentInfo.get("hasPI"))));
		BigDecimal transAmt = new BigDecimal(String.valueOf(repaymentInfo.get("stillPrincipal"))).add(InterestSum)
				.multiply(new BigDecimal(100));// 交易金额（分）
		String feeAmt = "0";// 借款人手续费
		BigDecimal borrowerAmt = new BigDecimal(String.valueOf(productResult.get("productAmount")))
				.multiply(new BigDecimal(1000000));// 标的金额（分）

		String borrCustId = userResult.get("deposit_account");// 账户存管平台借款人ID号
		int totalNum = investResult.size();// 投资总笔数

		sb.append(bidReq.getChar_set() + "|" + BankConstant.PARTNER_ID + "|" + bidReq.getMerBillNo() + "|"
				+ transAmt.longValue() + "|" + feeAmt + "|" + bidReq.getBorrowId() + "|" + borrowerAmt.longValue() + "|"
				+ borrCustId + "||" + totalNum + "|");
		// 拼接内容
		FileUtils.createFile(filePath + fileName, sb.toString());
		FileUtils.createFile(filePath + fileName + ".OK", "");
		for (int i = 0; i < investResult.size(); i++) {
			sb = new StringBuffer();
			Map<String, String> investMap = investResult.get(i);
			String PlaCustId = investMap.get("deposit_account"); // 账户存管平台 客户号
			BigDecimal TransAmt = new BigDecimal(String.valueOf(investMap.get("investAmount")))
					.multiply(new BigDecimal(100)); // 交易金额
			BigDecimal Interest = new BigDecimal(String.valueOf(investMap.get("recievedInterest")))
					.multiply(new BigDecimal(100)); // 利息收益
			BigDecimal Inves_fee = new BigDecimal(String.valueOf(investMap.get("manageFee")))
					.multiply(new BigDecimal(100)); //// 手续费
			sb.append(i + "|" + PlaCustId + "|" + TransAmt.longValue() + "|" + Interest.longValue() + "|"
					+ Inves_fee.longValue() + "|");
			FileUtils.writeFileContent(filePath + fileName, sb.toString());
		}
		SFTPUtils utils = new SFTPUtils(BankConstant.SFTP_NAME, BankConstant.SFTP_PWD, BankConstant.SFTP_IP,
				new Integer(BankConstant.SFTP_PORT));
		boolean flag = utils.upFile(DateUtils.formatDate(new Date(), "yyyyMMdd"), fileName, filePath + fileName, false,
				true);
		if (flag) {
			flag = utils.upFile("", fileName + ".OK", filePath + fileName + ".OK", true, false);
		}
		return flag;
	}
}
