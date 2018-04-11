/******************************************************************************
 * Copyright (C) 2015 ShenZhen HeShiDai Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为合时代控股有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.carloadtrade.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.wx.carloadtrade.dao.OrderDao;
import com.wx.carloadtrade.dao.ShopsPaymentDao;
import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.carloadtrade.dto.OrderPaymentSerialDto;
import com.wx.carloadtrade.enums.MakeOrderNumber;
import com.wx.carloadtrade.enums.OrderState;
import com.wx.carloadtrade.enums.PayState;
import com.wx.carloadtrade.service.OrderDetailsService;
import com.wx.carloadtrade.service.ZfbPayService;
import com.wx.util.JSONUtils;

import my.comp.pay.zfb.zfbUtils;
import my.comp.transaction.WriteTransactional;
import net.sf.json.JSONObject;

/**
 * @ClassName: ZfbPayService
 * @version 1.0
 * @Desc: TODO
 * @history v1.0
 */
@Service("zfbPayService")
@SuppressWarnings("static-access")
public class ZfbPayServiceImpl implements ZfbPayService {
	private static final Log logger = LogFactory.getLog(ZfbPayServiceImpl.class);
	@Resource
	OrderDao orderDao;
	@Resource
	ShopsPaymentDao shopsPaymentDao;
	@Resource
	OrderDetailsService orderDetailsService;

	@Override
	@WriteTransactional
	public String subZfbZfPayService(Map<String, String> paramMap) {
		List<OrderMainListDto> orderList = orderDao.queryOrderMainList(paramMap.get("out_trade_no"), null,
				paramMap.get("userId"), null, null);
		if (null != orderList && orderList.size() > 0) {
			int statu = orderList.get(0).getPayState();
			if (statu == 1 || orderList.size() > 1) {
				return null;
			}
		}

		logger.info("app计算金额：" + paramMap.get("total_fee") + ",主订单金额：" + orderList.get(0).getOrderTotalPrice());
		BigDecimal totalPrice = orderList.get(0).getOrderTotalPrice();
		String payNum = this.savePaymentSerial(paramMap.get("out_trade_no"), paramMap.get("body"), totalPrice,"1");
		return zfbUtils.subZfbZfPay(payNum, totalPrice + "", "明智车服-"+orderList.get(0).getOrderTypeName(), "明智车服");
	}

	/**
	 * 描述： 支付宝服务端异步通知，返回订单支付结果
	 * 
	 * @param zfbSyncMap
	 * @return
	 */
	@Override
	@WriteTransactional
	public String zfbZfSyncService(Map<String, String> zfbSyncMap) {
		try {
			// boolean falg = AlipaySignature.rsaCheckV1(zfbSyncMap,
			// sfv.ALI_PUBLIC_KEY, "utf-8", "RSA2");//验签
			logger.info(" ************ 支付宝服务端异步通知响应参数 = " + JSONUtils.toJSONObject(zfbSyncMap));
			String outTradeNo = zfbSyncMap.get("out_trade_no");
			String tradeNo = zfbSyncMap.get("trade_no");

			//查询平台支付流水
			List<Map<String, String>> orderList = shopsPaymentDao.queryPaymentSerial(outTradeNo, null, null, null);
			if (null != orderList && orderList.size() > 0) {
				if(orderList.size() < 1){
					logger.info("异步回调,支付宝订单号:" + outTradeNo + "***************平台找不到该订单*************");
					return "success";
				}
				Object payState = orderList.get(0).get("payState");
				if ("1".equals(payState.toString())) {
					logger.info("异步回调,支付宝订单号:" + outTradeNo + ",平台同步回调以做处理 ****************************");
					return "success";
				}
			}else{
				logger.info("异步回调,支付宝订单号:" + outTradeNo + "***************平台找不到该订单,订单集合为null*************");
				return "success";
			}
			
			// 获取平台主订单号
			String mainOrderNum = orderList.get(0).get("mainOrderNum");
			// 查询支付宝接口
			JSONObject zfbResp = zfbUtils.alipayTradeQuery(outTradeNo, tradeNo);
			logger.info("异步回调************************查询支付宝交易接口，返回值为：" + zfbResp);

			if (null == zfbResp) {
				logger.info("***************检查ZFB响应参数错误 !*************");
				return "fail";
			}

			String trade_status = zfbResp.get("tradeStatus") + "";
			String out_trade_no = zfbResp.get("outTradeNo") + "";
			logger.info("异步通知**************订单流水号为：" + out_trade_no + "********交易状态为" + trade_status);
			if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
				logger.info("异步通知***********恭喜你订单流水号为：" + out_trade_no + "*********交易成功，更新订单状态为已支付");
				// 更新订单表订单状态为已支付
				orderDetailsService.udpateOrderStatus(OrderState.IN_SERVICE.getIndex() + "",
						PayState.ALREADY_PAID.getIndex() + "", mainOrderNum, orderDao);
				// 更新支付流水表状态为已支付
				shopsPaymentDao.udpateMainPaymentSerial(out_trade_no, PayState.ALREADY_PAID.getIndex() + "");
			} else {
				// 更新订单表订单状态为支付失败
				orderDetailsService.udpateOrderStatus(null, PayState.section_PAID.getIndex() + "", mainOrderNum,
						orderDao);
				// 支付流水表修改状态为支付失败
				shopsPaymentDao.udpateMainPaymentSerial(out_trade_no, PayState.section_PAID.getIndex() + "");
				return "fail";
			}

		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	/**
	 * 支付宝同步回调时客户端主动查询支付结果方法 查询该订单是否支付成功 1:成功 -1:不成功
	 */
	@Override
	@WriteTransactional
	public String zfbCheckZfResultService(String outTradeNo, String tradeNo) {
		String resStr = "-1";
		String mainOrderNum = "";
		logger.info("支付宝同步回调开始************outTradeNo:"+outTradeNo+",tradeNo:"+tradeNo);
		try {
			List<Map<String, String>> orderList = shopsPaymentDao.queryPaymentSerial(outTradeNo, null, null, null);
			if (null != orderList && orderList.size() > 0) {
				Object payState = orderList.get(0).get("payState");
				mainOrderNum = orderList.get(0).get("mainOrderNum");
				if ("1".equals(payState.toString())) {
					logger.info("同步回调,支付宝订单号:" + outTradeNo + ",支付宝异步回调以做处理 ****************************");
					return "1";
				}
			}else{
				logger.info("同步回调,支付宝订单号:" + outTradeNo + "***************平台找不到该订单,订单集合为null*************");
				return "-1";
			}

			//支付宝交易查询接口
			JSONObject zfbResp = zfbUtils.alipayTradeQuery(outTradeNo, tradeNo);
			logger.info("平台主订单号："+mainOrderNum+",同步回调************************查询支付交易宝接口，返回值为：" + zfbResp);

			if (null == zfbResp) {
				return "-1";
			}
			//支付宝交易查询,返回的数据
			String tradeStatus = zfbResp.get("tradeStatus").toString();
			String out_trade_no = zfbResp.get("outTradeNo").toString();
			
			// 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
			// TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
			if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
				// 更新订单表订单状态为已支付未消费
				orderDetailsService.udpateOrderStatus(OrderState.IN_SERVICE.getIndex() + "",
						PayState.ALREADY_PAID.getIndex() + "", mainOrderNum, orderDao);
				// 更新支付流水表状态为已支付
				shopsPaymentDao.udpateMainPaymentSerial(out_trade_no,PayState.ALREADY_PAID.getIndex() + "");
				resStr = "1";
			} else {
				// 更新订单表订单状态为支付失败
				orderDetailsService.udpateOrderStatus("",PayState.section_PAID.getIndex() + "", mainOrderNum, orderDao);
				// 更新支付流水表状态为已支付
				shopsPaymentDao.udpateMainPaymentSerial(out_trade_no,PayState.section_PAID.getIndex() + "");
				resStr = "-1";
			}
		} catch (Exception e) {
			logger.error("同步回调出现异常：" + e.toString());
			e.printStackTrace();
			return "-1";
		}
		logger.info(mainOrderNum+"************ 支付宝同步回调结束 *******************resStr:"+resStr);
		return resStr;
	}

	private static boolean verifyZfbResult(String zfbStr) throws Exception {
		boolean checkResult = false;

		String[] str = zfbStr.split("</is_success>");

		if (str[0].indexOf("is_success") != -1 && str[0].indexOf("T") != -1) {
			checkResult = true;
		}

		return checkResult;
	}

	public boolean checkZfSuccess(String payNum) {
		boolean result = false;

		List<Map<String, String>> orderList = shopsPaymentDao.queryPaymentSerial(payNum, null, null, null);

		if (null != orderList && orderList.size() > 0) {
			logger.info("queryOrderMap=" + orderList);
			int statu = Integer.valueOf(orderList.get(0).get("payState"));

			if (statu == 1) {
				return true;
			} else {
				return false;
			}

		}
		return result;
	}

	// 保存支付流水记录
	@Override
	@WriteTransactional
	public String savePaymentSerial(String mainOrderNum, String body, BigDecimal orderTotalPrice,String payType) {
		String payNum = new MakeOrderNumber().savePayNum();
		shopsPaymentDao.deletePaymentSerial(mainOrderNum);
		OrderPaymentSerialDto dto = new OrderPaymentSerialDto();
		dto.setPayNum(payNum);// 支付流水单号/交易单号（保证唯一性）
		dto.setMainOrderNum(mainOrderNum);// 主订单号码
											// t_order_main_list的main_order_num
		dto.setGoodsDesc(body);// 商品描述(简要描述 可空)
		dto.setPayAccountName("用户");// 支付方
		dto.setPayAccountNum("用户账号");// 支付账号
		
		if("1".equals(payType)){
			dto.setPayTypeCode("1");// 支付方式代号（t_base_payment_list的支付代号）
			dto.setPayTypeName("支付宝");// 支付方式名称（冗余字段，请带入）
			dto.setPayeeAccountNum("DX@dxwriter.com");// 收款方账号
			dto.setPayeeAccountName("宇宙文明（深圳）科技有限公司");// 收款方户名
		}else{
			dto.setPayTypeCode("2");// 支付方式代号（t_base_payment_list的支付代号）
			dto.setPayTypeName("微信");// 支付方式名称（冗余字段，请带入）
			dto.setPayeeAccountNum("微信账户");// 收款方账号
			dto.setPayeeAccountName("微信账户有限公司");// 收款方户名
		}
		
		
		dto.setPayAmount(orderTotalPrice);// 付款金额
		dto.setFactorage(new BigDecimal(0));// 手续费
		shopsPaymentDao.savePaymentSerial(dto);
		return payNum;
	}
}
