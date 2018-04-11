package com.wx.depotbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.req.RechargeCustReq;
import com.wx.depotbank.dto.ret.RechargeCustRet;
import com.wx.depotbank.enums.BizType;
import com.wx.depotbank.enums.RequestType;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.utils.RequestUtils;

@Service
public class RechargeMangerServiceImpl implements RechargeMangerService{

	
	private static Logger logger = LoggerFactory.getLogger(RechargeMangerServiceImpl.class);
	
	
	public RechargeCustRet rechargeByCust(RechargeCustReq rechargeCustReq) throws BankException {
		rechargeCustReq.setBiz_type(BizType.MercRecharge.getKey());
		logger.info("商户自主充值(后台方式)，rechargeCustReq = {}", rechargeCustReq);
		return RequestUtils.sendParam(rechargeCustReq, RechargeCustRet.class, RequestType.BACKSTAGE,
				rechargeCustReq.getRechargeMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO)); 
	}

	@Override
	public RechargeCustRet withdrawByCust(RechargeCustReq rechargeCustReq){
		rechargeCustReq.setBiz_type(BizType.MercWithdraw.getKey());
		logger.info("商户自主提现(后台方式)，rechargeCustReq = {}", rechargeCustReq);
		RechargeCustRet ret = new RechargeCustRet();
		try {
			ret = RequestUtils.sendParam(rechargeCustReq, RechargeCustRet.class, RequestType.BACKSTAGE,
					rechargeCustReq.getRechargeMac(BankConstant.PARTNER_ID, BankConstant.VERSION_NO));
		} catch (BankException e) {
			logger.info("商户自主提现失败：{}",ret.getRespDesc());
		} 
		return ret;
	}
}
