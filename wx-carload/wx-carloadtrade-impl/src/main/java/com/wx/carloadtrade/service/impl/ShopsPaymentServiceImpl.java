package com.wx.carloadtrade.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadtrade.dao.OrderDao;
import com.wx.carloadtrade.dao.OrderRefundDao;
import com.wx.carloadtrade.dao.ShopsPaymentDao;
import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.carloadtrade.dto.OrderPaymentSerialDto;
import com.wx.carloadtrade.dto.OrderRefundDto;
import com.wx.carloadtrade.dto.ShopsPaymentDto;
import com.wx.carloadtrade.enums.MakeOrderNumber;
import com.wx.carloadtrade.enums.OrderState;
import com.wx.carloadtrade.service.OrderDetailsService;
import com.wx.carloadtrade.service.ShopsPaymentService;
import com.wx.service.Result;
import com.wx.service.Results;

@Component
@Service
public class ShopsPaymentServiceImpl implements ShopsPaymentService{
	
	@Resource
	private ShopsPaymentDao shopsPaymentDao;
	
	@Resource
	private OrderRefundDao orderRefundDao;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private OrderDetailsService orderDetailsService;
	
	
	
	
	
	@Override
	public Result<ShopsPaymentDto> getShopsPayment(ShopsPaymentDto dto) {
		
		return null;
	}

	@Override
	public Result<Object> savePaymentSerial(String mainOrderNum,String payTypeName,String payTypeCode,String userId) {
		OrderPaymentSerialDto dto = new OrderPaymentSerialDto();
		try {
			List<OrderMainListDto> mainMap = orderDao.queryOrderMainList(mainOrderNum, null, userId, null, null);
			if(null == mainMap || mainMap.isEmpty()){
				return Results.error();
			}
			
			dto.setPayNum(new MakeOrderNumber().makeOrderNum("ZF"));//支付流水单号/交易单号（保证唯一性）
			dto.setMainOrderNum(mainOrderNum);//主订单号码 t_order_main_list 的main_order_num
			dto.setPayTypeCode(payTypeCode);//支付方式代号（t_base_payment_list的支付代号）
			dto.setPayTypeName(payTypeName);//支付方式名称（冗余字段，请带入）
			dto.setPayeeAccountNum("aaaaaaaaaaaa");//收款方账号
			dto.setPayeeAccountName("testName");//收款方户名
			dto.setPayAmount(mainMap.get(0).getOrderTotalPrice());//付款金额
			shopsPaymentDao.savePaymentSerial(dto);
		} catch (Exception e) {
			e.printStackTrace();
			Results.error();
		}
		return Results.success(dto.getPayNum());
	}
	
	@Override
	public Result<String> saveOrderRefundList(String mainOrderNum,String refundReason,String userId,String refundDesc) {
		
		List<OrderMainListDto> mainMap = orderDao.queryOrderMainList(mainOrderNum, null, userId, null, null);
		if(null == mainMap || mainMap.isEmpty()){
			return Results.error();
		}
		
		OrderRefundDto dto = new OrderRefundDto();
		dto.setRefund_num(new MakeOrderNumber().saveTKOrderNum());//退款编号
		dto.setOrder_num(mainOrderNum);//订单号码
		dto.setRefund_amount(mainMap.get(0).getOrderTotalPrice());//退款金额
		dto.setUser_id(Long.valueOf(userId));//客户ID
		dto.setRefund_reason(refundReason);//退款原因
		dto.setRefund_desc(refundDesc);//问题描述
		//生成退款单
		int result = orderRefundDao.saveOrderRefundList(dto);
		//修改订单状态为退款中
		String orderState = OrderState.IN_REFUNDS.getIndex()+"";
		orderDao.udpateMainOrderStatus(orderState, "", mainOrderNum);
		orderDao.udpateCareOrderStatus(orderState, "", mainOrderNum);
		orderDao.udpateMaintainOrderStatus( orderState, "", mainOrderNum);
		orderDao.udpateRepairOrderStatus( orderState, "", mainOrderNum);
		
		//生成退款单-操作日志
		orderRefundDao.saveOrderRefundlog(dto.getOrder_num(), userId, mainMap.get(0).getOrderTotalPrice(), refundReason);
		if(result > 0){
			Results.success();
		}
		return Results.error();
	}

	@Override
	public Result<String> saveOrderRefundlog(String refundNum, String handleUser, BigDecimal refundAmount, String refundReason) {
		int result = orderRefundDao.saveOrderRefundlog(refundNum, handleUser, refundAmount, refundReason);
		if(result > 0){
			Results.success();
		}
		return Results.error();
	}

	@Override
	public Result<String> saveOrderRefundPicture(String refundNum, String picUrlSrc, String serialNumber) {
		try {
			int result =  orderRefundDao.saveOrderRefundPicture(refundNum, picUrlSrc, serialNumber);
			if(result > 0){
				return Results.success();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}

	@Override
	public Result<List<Map>> queryPaymentList() {
		try {
			return Results.success(shopsPaymentDao.queryPaymentList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Results.error();
	}
	
	 @Override
	    public List<Map<String, String>> queryPaymentSerial(String payNum, String mainOrderNum, String payState, String payTypeCode)
	    {
	       
	        return shopsPaymentDao.queryPaymentSerial(payNum, mainOrderNum, payState, payTypeCode);
	    }

    @Override
    public int udpateMainPaymentSerial(String payNum, String payState)
    {
        // TODO Auto-generated method stub
        return shopsPaymentDao.udpateMainPaymentSerial(payNum, payState);
    }
	
}
