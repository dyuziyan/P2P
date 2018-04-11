/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.service;

import com.wx.depotbank.dto.req.BidReq;
import com.wx.depotbank.dto.req.InvestReq;
import com.wx.depotbank.dto.req.RepaymentReq;
import com.wx.depotbank.dto.ret.BaseRet;
import com.wx.depotbank.dto.ret.BidRet;
import com.wx.depotbank.exception.BankException;
import com.wx.market.dto.InvestMessage;

import my.comp.rmi.annotation.RemoteService;

/**
 * @ClassName: BorrowService
 * @version 1.0
 * @Desc: 标的service
 * @author xiaojun.zhou
 * @date 2017年6月23日上午11:09:15
 * @history v1.0
 *
 */
@RemoteService("/borrowService")
public interface BorrowService {

	/**
	 * 
	 * 描述：用户投标（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月23日上午11:10:45
	 * @param bidReq
	 * @return
	 */
	public BidRet createBid(BidReq bidReq) throws BankException;

	/**
	 * 
	 * 描述：用户投标（后台方式）
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月23日上午11:10:50
	 * @param investReq
	 * @return
	 */
	public BidRet backInvest(InvestReq investReq,InvestMessage investMessage) throws BankException;
	
	/**
	 * 描述：放款
	 * 
	 * @author shiliang.feng
	 * @date 2017年7月24日上午11:10:50
	 * @param BidReq
	 * @return
	 */
	public BaseRet releaseBid(BidReq bidReq) throws BankException;
	
	/**
	 * 描述：还款
	 * 
	 * @author shiliang.feng
	 * @date 2017年7月24日上午11:10:50
	 * @param BidReq
	 * @return
	 * @throws Exception 
	 */
	public BaseRet repaymentBid(RepaymentReq repaymentReq) throws BankException, Exception;

}
