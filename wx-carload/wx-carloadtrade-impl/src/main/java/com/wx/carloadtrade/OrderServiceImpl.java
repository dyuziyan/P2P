package com.wx.carloadtrade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadtrade.dao.OrderDao;
import com.wx.carloadtrade.dao.ShopsPaymentDao;
import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.carloadtrade.enums.MakeOrderNumber;
import com.wx.carloadtrade.enums.OrderState;
import com.wx.carloadtrade.enums.PayState;
import com.wx.carloadtrade.service.OrderDetailsService;
import com.wx.carloadtrade.service.OrderService;
import com.wx.service.Result;
import com.wx.service.Results;

import my.comp.lang.DateUtils;

@Component
@Service
public class OrderServiceImpl implements OrderService{
	
	@Resource
	private OrderDetailsService orderDetailsService;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private ShopsPaymentDao shopsPaymentDao;

	@Override
	public Result<Map<String,Object>> saveOrder(OrderMainListDto dto) {
		try {
			dto.setOrderState(OrderState.TO_BE_PAID.getIndex());
			dto.setMainOrderNum(new MakeOrderNumber().saveMainOrderNum());
			dto.setPayState(PayState.TO_BE_PAID.getIndex());
			//生成订单
			BigDecimal orderTotalPrice = orderDetailsService.saveOrder(dto,orderDao);
			//获取平台支付方式
			List<Map> paymentMap = shopsPaymentDao.queryPaymentList();
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("mainOrderNum", dto.getMainOrderNum());
			resultMap.put("paymentMap", paymentMap);
			resultMap.put("orderTotalPrice", orderTotalPrice);
			return Results.success(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
		
		
	/*	String busiShopId = parameterMap.get("busiShopId") == null ? "" :((String[])parameterMap.get("busiShopId"))[0];//商家编号
		String busiShopName = parameterMap.get("busiShopName") == null ? "" :((String[])parameterMap.get("busiShopName"))[0];//商家编号
		String receiverName = parameterMap.get("receiverName") == null ? "" :((String[])parameterMap.get("receiverName"))[0];//联系人
		String receiverSex = parameterMap.get("receiverSex") == null ? "" :((String[])parameterMap.get("receiverSex"))[0];//联系人性别 1：男 2：女
		String receiverPhone = parameterMap.get("receiverPhone") == null ? "" :((String[])parameterMap.get("receiverPhone"))[0];//联系人电话
		String isDoorService = parameterMap.get("isDoorService") == null ? "" :((String[])parameterMap.get("isDoorService"))[0];//是否上门取送 1：是 0：否
		String userCarId = parameterMap.get("userCarId") == null ? "" :((String[])parameterMap.get("userCarId"))[0];//用户车辆ID
		String carNumber = parameterMap.get("carNumber") == null ? "" :((String[])parameterMap.get("carNumber"))[0];//用户车牌号码
		String carDeliverAddress = parameterMap.get("carDeliverAddress") == null ? "" :((String[])parameterMap.get("carDeliverAddress"))[0];//送车地址
		String carFetchAddress = parameterMap.get("carFetchAddress") == null ? "" :((String[])parameterMap.get("carFetchAddress"))[0];//取车地址
		String carFetchTime = parameterMap.get("carFetchTime") == null ? "" :((String[])parameterMap.get("carFetchTime"))[0];//取车时间
		String appointmentTime = parameterMap.get("appointmentTime") == null ? "" :((String[])parameterMap.get("appointmentTime"))[0];//预约时间
		String orderTotalPrice = parameterMap.get("orderTotalPrice") == null ? "" :((String[])parameterMap.get("orderTotalPrice"))[0];//订单总价
		String doorServicePrice = parameterMap.get("doorServicePrice") == null ? "" :((String[])parameterMap.get("doorServicePrice"))[0];//上门服务费
		String discountPrice = parameterMap.get("discountPrice") == null ? "" :((String[])parameterMap.get("discountPrice"))[0];//优惠价格
		String manHourFee = parameterMap.get("manHourFee") == null ? "" :((String[])parameterMap.get("	"))[0];//工时费
		String maintains = parameterMap.get("maintains") == null ? "" :((String[])parameterMap.get("maintains"))[0];//保养服务id集合
		String cares = parameterMap.get("cares") == null ? "" :((String[])parameterMap.get("cares"))[0];//养护服务id集合
		String repairs = parameterMap.get("repairs") == null ? "" :((String[])parameterMap.get("repairs"))[0];//维修服务id集合
		
		OrderMainListDto dto = new OrderMainListDto();
		
		dto.setOrderState(OrderState.TO_BE_PAID.getIndex());
		dto.setMainOrderNum(new MakeOrderNumber().makeOrderNum("M"));
		dto.setPayState(PayState.TO_BE_PAID.getIndex());
		orderDetailsService.saveOrder(dto);
		
		dto.setBusiShopId(Long.valueOf(busiShopId));
		
		dto.setBusiShopName(busiShopName);
		dto.setReceiverName(receiverName);
		
		dto.setReceiverSex(Integer.valueOf(receiverSex));
		
		dto.setReceiverPhone(receiverPhone);
		
		dto.setIsDoorService(Integer.valueOf(isDoorService));
		dto.setUserCarId(Long.valueOf(userCarId));
		
		dto.setCarNumber(carNumber);
		dto.setCarDeliverAddress(carDeliverAddress);
		dto.setCarFetchAddress(carFetchAddress);
		
		dto.setCarFetchTime(DateUtils.parseDate(carFetchTime));
		dto.setAppointmentTime(DateUtils.parseDate(appointmentTime));
		dto.setOrderTotalPrice(new BigDecimal(orderTotalPrice));
		dto.setDoorServicePrice(new BigDecimal(doorServicePrice));
		dto.setDiscountPrice(new BigDecimal(discountPrice));
		dto.setManHourFee(new BigDecimal(manHourFee));
		*/
	}

	@Override
	public  Result<List<Map<String, String>>>  queryOrderMainList(String mainOrderNum, String orderState, String userId,Integer startRow,Integer rows) {
		List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
		List<OrderMainListDto> orderMainList = orderDao.queryOrderMainList(mainOrderNum, orderState, userId, startRow, rows);
		Map<String, String> tempMap = null;
		for (OrderMainListDto dto : orderMainList) {
			tempMap = new HashMap<String, String>();
			tempMap.put("orderType", dto.getOrderType());  //订单服务类型
			tempMap.put("orderState", dto.getOrderState().toString());//订单状态
			tempMap.put("busiShopName", dto.getBusiShopName());//商家名称
			tempMap.put("appointmentTime","");
			if(null != dto.getAppointmentTime()){
				tempMap.put("appointmentTime", DateUtils.formatDate(dto.getAppointmentTime(),DateUtils.DATE_FULL));//预约时间
			}
			tempMap.put("orderTotalPrice", String.valueOf(dto.getOrderTotalPrice())); //订单总价
			tempMap.put("mainOrderNum", dto.getMainOrderNum()); //主订单号码
			tempMap.put("carNumber", dto.getCarNumber());//车牌号码
			tempMap.put("orderStateName", dto.getOrderStateName()); //订单状态名称
			tempMap.put("orderTypeName", dto.getOrderTypeName());//订单类型名称
			resultMap.add(tempMap);
		}
		if(null == resultMap || resultMap.size() < 1){
			return Results.error();
		}
		return Results.success(resultMap);
	}

	@Override
	public Result<Map<String, Object>> queryOrderDetails(String mainOrderNum,String userId) {
		try {
			List<OrderMainListDto> mainList = orderDao.queryOrderMainList(mainOrderNum, null, userId,null,null);
			if(null == mainList || mainList.size() < 1){
				return Results.error();
			}
			
			List<Map<String, String>> careMap = orderDao.queryOrderCareDetails(mainOrderNum, userId);//查询养护订单详情
			List<Map<String, String>> maintainMap = orderDao.queryOrderMaintainDetails(mainOrderNum, userId);//查询保养订单详情
			List<Map<String, String>> repairMap = orderDao.queryOrderRepairDetails(mainOrderNum, userId);//查询维修订单详情
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("orderType", mainList.get(0).getOrderType());//订单服务类型
			resultMap.put("orderState", mainList.get(0).getOrderState());// 订单状态
			resultMap.put("busiShopName", mainList.get(0).getBusiShopName());//商家名称
			resultMap.put("busiShopAddress", mainList.get(0).getBusiShopAddress());//商家地址
			resultMap.put("carBrandName", mainList.get(0).getCarBrandName());//车辆品牌
			resultMap.put("orderTotalPrice", mainList.get(0).getOrderTotalPrice());//订单总价
			resultMap.put("carFetchAddress", mainList.get(0).getCarFetchAddress());//取车地址
			resultMap.put("carDeliverAddress", mainList.get(0).getCarDeliverAddress());//送车地址
			resultMap.put("isDoorService", mainList.get(0).getIsDoorService());//是否上门取送 1：是 0：否
			if(null != mainList.get(0).getAppointmentTime()){
				resultMap.put("appointmentTime", DateUtils.formatDate(mainList.get(0).getAppointmentTime(),DateUtils.DATE_FULL));//预约时间
			}else{
				resultMap.put("appointmentTime","");
			}
			resultMap.put("mainOrderNum", mainList.get(0).getMainOrderNum());//主订单号码
			resultMap.put("receiverName", mainList.get(0).getReceiverName());//联系人
			resultMap.put("receiverPhone", mainList.get(0).getReceiverPhone());//联系人电话
			resultMap.put("carNumber", mainList.get(0).getCarNumber());//车牌号码
			resultMap.put("manHourFee", mainList.get(0).getManHourFee() );//工时费
			resultMap.put("discountPrice", mainList.get(0).getDiscountPrice());//优惠价格
			resultMap.put("doorServicePrice", mainList.get(0).getDoorServicePrice());//上门服务费
			resultMap.put("carinfo", mainList.get(0).getCarinfo());//车辆信息
			resultMap.put("careMap", careMap);//保养订单详情
			resultMap.put("maintainMap", maintainMap);//养护订单详情
			resultMap.put("repairMap", repairMap);//维修订单详情
			resultMap.put("orderStateName", mainList.get(0).getOrderStateName()); //订单状态名称
			resultMap.put("playUrl",  mainList.get(0).getPlayUrl());//播放地址
			resultMap.put("liveState", mainList.get(0).getLiveState());//直播状态
			return Results.success(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
	}

/*	@Override
	public Result<String> udpateOrderStatus(String orderState, String payState, String mainOrderNum) {
		try {
			orderDao.udpateMainOrderStatus(orderState, payState, mainOrderNum);
			orderDao.udpateCareOrderStatus(orderState, payState, mainOrderNum);
			orderDao.udpateMaintainOrderStatus( orderState, payState, mainOrderNum);
			orderDao.udpateRepairOrderStatus( orderState, payState, mainOrderNum);
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
		return Results.success();
	}*/

	@Override
	public Result<String> cancelOrder(String userId, String mainOrderNum) {
		// 修改订单状态（取消）
		Result<String> result = orderDetailsService.udpateOrderStatus(OrderState.ALREADY_CANCEL.getIndex() + "", null,
				mainOrderNum, orderDao);
		// 生成子訂單日誌
		orderDetailsService.saveOrderLog(mainOrderNum, OrderState.ALREADY_CANCEL.getIndex(), "用户取消订单", orderDao);
		return result;
	}

	@Override
	public Result<String> paySuccess(String userId, String mainOrderNum,String operateDesc) {
		// 修改订单状态（取消）
		Result<String> result = orderDetailsService.udpateOrderStatus(OrderState.IN_SERVICE.getIndex() + "", PayState.ALREADY_PAID.getIndex()+"",
				mainOrderNum, orderDao);
		// 生成子訂單日誌
		orderDetailsService.saveOrderLog(mainOrderNum, OrderState.IN_SERVICE.getIndex(), operateDesc, orderDao);
		return result;
	}

	@Override
	public Result<String> orderRefundApply(String userId, String mainOrderNum, String refundReason, String refundDesc) {
		List<OrderMainListDto> mainList = orderDao.queryOrderMainList(mainOrderNum, null, userId,null,null);
		if(null == mainList || mainList.size() != 1 ){
			return Results.byMessage("0", "申请失败!");
		}
		Map param = new HashMap<String,String>();
		param.put("refundNum", new MakeOrderNumber().saveTKOrderNum());
		param.put("userId", userId);
		param.put("orderNum", mainOrderNum);
		param.put("refundReason", refundReason);
		param.put("refundDesc", refundDesc);
		param.put("refundAmount", mainList.get(0).getOrderTotalPrice());
		boolean result = orderDetailsService.orderRefundApply(param,orderDao);
		if(result){
			return Results.byMessage("1", "退款申请成功!");
		}
		return Results.byMessage("0", "退款申请失败,请稍后再试!");
	}

    @Override
    public Result<String> udpateOrderStatus(String orderState, String payState, String mainOrderNum)
    {
        Result<String> result = orderDetailsService.udpateOrderStatus(orderState, payState, mainOrderNum, orderDao);
        return result;
    }

	
}
