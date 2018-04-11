package com.wx.carloadtrade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadtrade.dao.ShopsPaymentDao;
import com.wx.carloadtrade.service.PayService;
import com.wx.carloadtrade.service.ZfbPayService;
import com.wx.service.Result;
import com.wx.service.Results;

@Component
@Service
public class PayServiceImpl implements PayService{
	
	@Resource
	private ZfbPayService zfbPayService;
	
	@Resource
    private ShopsPaymentDao shopsPaymentDao;

	@Override
	public Result<String> subZfbZfPayService(Map<String, String> paramMap) {
		try {
			String str = zfbPayService.subZfbZfPayService(paramMap);
			return Results.success(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}


	@Override
	public Result<String> zfbZfSyncService(Map<String, String> zfbSyncMap) {
		try {
			String str = zfbPayService.zfbZfSyncService(zfbSyncMap);
			if("success".equals(str)){
				return Results.success(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}


	@Override
	public Result<String> zfbSynchroNotify(String outTradeNo,String  tradeNo) {
		try {
			String str = zfbPayService.zfbCheckZfResultService(outTradeNo,tradeNo);
			if(StringUtils.isNotBlank(str) && "1".equals(str)){
				return Results.success(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}

    @Override
    public String savePaymentSerial(String mainOrderNum, String body, BigDecimal orderTotalPrice,String payType)
    {
        
        return zfbPayService.savePaymentSerial(mainOrderNum, body, orderTotalPrice,payType);
    }
}
