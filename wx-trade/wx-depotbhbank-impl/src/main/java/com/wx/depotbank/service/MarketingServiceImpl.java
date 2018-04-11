package com.wx.depotbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.RechargeReq;
import com.wx.depotbank.dto.ret.BidRet;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

@Service
public class MarketingServiceImpl implements MarketingService {

	private static Logger logger = LoggerFactory.getLogger(MarketingServiceImpl.class);

	@Override
	public BidRet cashRedPacket(RechargeReq rechargeReq) throws BankException {
		logger.info("现金红包(后台方式)，rechargeReq = {}", rechargeReq);
		return RequestUtils.sendParam(rechargeReq, BidRet.class, RequestType.BACKSTAGE,
				rechargeReq.getCashRedPacket(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
	}

}
