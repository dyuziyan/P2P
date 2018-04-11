package com.wx.carloadtrade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.wx.carloadtrade.dao.OrderDao;
import com.wx.carloadtrade.domain.CarServicePart;
import com.wx.carloadtrade.dto.OrderDetailsDto;
import com.wx.carloadtrade.dto.OrderMainListDto;
import com.wx.carloadtrade.enums.MakeOrderNumber;
import com.wx.carloadtrade.enums.OrderState;
import com.wx.carloadtrade.service.OrderDetailsService;
import com.wx.service.Result;
import com.wx.service.Results;

import my.comp.transaction.WriteTransactional;

@Component
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Override
	@WriteTransactional
	public BigDecimal saveOrder(OrderMainListDto dto,OrderDao orderDao) {

		List<CarServicePart> maintainList = null;// 保养服务
		List<CarServicePart> careList = null;// 养护服务
		List<CarServicePart> repairList = null;// 维修服务

		BigDecimal marketTotalPriceCount = new BigDecimal(0);// 总市场价/原价
		BigDecimal goodsTotalPriceCount = new BigDecimal(0);// 商家总价/
		BigDecimal manHourFeeCount = new BigDecimal(0);// 总工时费

		Long unitCount = 1L;
		//计算价格
		if (StringUtils.isNotBlank(dto.getMaintains())) {
			maintainList = orderDao.queryCarServicePart(dto.getMaintains(),null);// 保养服务
			for (CarServicePart ml : maintainList) {
				if(null != ml.getUnitCount()){
					unitCount = ml.getUnitCount();
				}else{
					unitCount = 1L;
				}
				if(null != ml.getServiceProMarketPrice()){
					marketTotalPriceCount = marketTotalPriceCount.add(ml.getServiceProMarketPrice().multiply(new BigDecimal(unitCount)));
				}
				if(null != ml.getServiceProBusiPrice()){
					// 计算的商品总价应该乘以数量;备注:应目前版本数量始终为1,所以不用相乘
					goodsTotalPriceCount = goodsTotalPriceCount.add(ml.getServiceProBusiPrice().multiply(new BigDecimal(unitCount)));
				}
				manHourFeeCount = manHourFeeCount.add(ml.getManHourFee());
			}
		}

		if (StringUtils.isNotBlank(dto.getCares())) {
			careList = orderDao.queryCarServicePart(dto.getCares(),null);// 养护服务
			for (CarServicePart cl : careList) {
				if(null != cl.getUnitCount()){
					unitCount = cl.getUnitCount();
				}else{
					unitCount = 1L;
				}
				if(null != cl.getServiceProMarketPrice()){
					marketTotalPriceCount = marketTotalPriceCount.add(cl.getServiceProMarketPrice().multiply(new BigDecimal(unitCount)));
				}
				if(null != cl.getServiceProBusiPrice()){
					// 计算的商品总价应该乘以数量;备注:应目前版本数量始终为1,所以不用相乘
					goodsTotalPriceCount = goodsTotalPriceCount.add(cl.getServiceProBusiPrice().multiply(new BigDecimal(unitCount)));
				}
				manHourFeeCount = manHourFeeCount.add(cl.getManHourFee());
			}
		}

		if (StringUtils.isNotBlank(dto.getRepairs())) {
			repairList = orderDao.queryCarServicePart(dto.getRepairs(),null);// 维修服务
			for (CarServicePart rl : repairList) {
				if(null != rl.getUnitCount()){
					unitCount = rl.getUnitCount();
				}else{
					unitCount = 1L;
				}
				if(null != rl.getServiceProMarketPrice()){
					marketTotalPriceCount = marketTotalPriceCount.add(rl.getServiceProMarketPrice().multiply(new BigDecimal(unitCount)));
				}
				if(null != rl.getServiceProBusiPrice()){
					// 计算的商品总价应该乘以数量;备注:应目前版本数量始终为1,所以不用相乘
					goodsTotalPriceCount = goodsTotalPriceCount.add(rl.getServiceProBusiPrice().multiply(new BigDecimal(unitCount)));
				}
				manHourFeeCount = manHourFeeCount.add(rl.getManHourFee());
			}
		}
/*
		if (goodsTotalPriceCount.compareTo(dto.getGoodsTotalPrice()) != 0) {
			return 0;
		}

		if (manHourFeeCount.compareTo(dto.getManHourFee()) != 0) {
			return 0;
		}*/
		//获取时间段优惠金额 
		Map<String, String> discountMap = orderDao.getAppointTimeDiscount(dto.getAppointTimeId());
		BigDecimal discount = new BigDecimal("1");//优惠折扣率
		BigDecimal floatingAmount = new BigDecimal("0");//浮动金额
		if(null != discountMap && !discountMap.isEmpty()){
			if(null != discountMap.get("discountRate")){
				discount = new BigDecimal(String.valueOf(discountMap.get("discountRate")));
			}
			if(null != discountMap.get("floatingAmount")){
				floatingAmount = new BigDecimal(String.valueOf(discountMap.get("floatingAmount")));
			}
		}
		dto.setGoodsTotalPrice(goodsTotalPriceCount);//商家折扣价
		dto.setManHourFee(manHourFeeCount);//工时费
		dto.setMarketTotalPrice(marketTotalPriceCount.add(manHourFeeCount));// 市场价
		
		//商家总价 = 商家折扣价+工时费
		BigDecimal businessTotal = dto.getGoodsTotalPrice().add(dto.getManHourFee());
		
		//优惠价格= (商品原价-商家折扣价)-(商品实际价格-商家总价)
//		dto.setDiscountPrice(dto.getGoodsTotalPrice().subtract(marketTotalPriceCount.subtract(businessActual).subtract(businessTotal)));
		
		//优惠价格= 市场价-(商家总价x折扣率/100+商家总价)
		dto.setDiscountPrice(dto.getMarketTotalPrice().subtract(businessTotal.multiply(discount).divide(new BigDecimal("100")).add(businessTotal)));
		
		// 订单总价=商家总价+商家总价x时间段优惠折扣率+上门服务费
		dto.setOrderTotalPrice(businessTotal.multiply(discount));
		//有折扣率的时候除以100
		if(discount.compareTo(new BigDecimal("1")) != 0){
			dto.setOrderTotalPrice(dto.getOrderTotalPrice().divide(new BigDecimal("100")));
		}
		dto.setOrderTotalPrice(dto.getOrderTotalPrice().add(businessTotal));
		
		//+上门服务费
		if(null != dto.getDoorServicePrice()){
			dto.setOrderTotalPrice(dto.getOrderTotalPrice().add(dto.getDoorServicePrice()));
		}
		//订单总金额向上取整
		dto.setOrderTotalPrice(dto.getOrderTotalPrice().setScale(0,BigDecimal.ROUND_UP));
		//优惠价格向上取整
		dto.setDiscountPrice(dto.getDiscountPrice().setScale(0,BigDecimal.ROUND_UP));
		
		
		// 新增主订单数据
		orderDao.saveOrderMainList(dto);

		// 存在保养订单
		if (null != maintainList && maintainList.size() > 0) {
			// 新增保养订单
			String BYorderNum = new MakeOrderNumber().saveBYOrderNum();// 生成保养订单号
			dto.setOrderNum(BYorderNum);
			orderDao.saveOrderMaintainList(dto);
			// 添加保养服务详情
			this.saveOrderDetails(maintainList, dto.getUserId(), dto.getMainOrderNum(), BYorderNum, 1,orderDao);
		}

		// 存在维修订单
		if (null != repairList && repairList.size() > 0) {
			// 新增维修订单
			String WXorderNum = new MakeOrderNumber().saveWXOrderNum();// 生成维修订单号
			dto.setOrderNum(WXorderNum);
			orderDao.saveOrderRepairList(dto);
			// 添加维修服务详情
			this.saveOrderDetails(repairList, dto.getUserId(), dto.getMainOrderNum(), WXorderNum, 2,orderDao);
		}

		// 存在养护订单
		if (null != careList && careList.size() > 0) {
			// 新增养护订单
			String YHorderNum = new MakeOrderNumber().saveYHOrderNum();// 生成养护订单号
			dto.setOrderNum(YHorderNum);
			orderDao.saveOrderCareList(dto);
			// 添加养护服务详情
			this.saveOrderDetails(careList, dto.getUserId(), dto.getMainOrderNum(), YHorderNum, 3,orderDao);
		}
		return dto.getOrderTotalPrice();
	}

	@Override
	public List<CarServicePart> queryCarServicePart(String ids,OrderDao orderDao) {
		return orderDao.queryCarServicePart(ids,null);
	}

	/**
	 * 添加保养/维修/养护服务详情
	 * @param maintains 保养服务项目ids
	 * @param userId 用户编号
	 * @param mainOrderNum 主订单编号
	 * @param orderNum 订单编号
	 * @param orderType 1：保养订单，2：维修订单，3：养护订单
	 */
	public void saveOrderDetails(List<CarServicePart> cspList, Long userId, String mainOrderNum, String orderNum,
			int orderType,OrderDao orderDao) {
		List<OrderDetailsDto> omdList = new ArrayList<OrderDetailsDto>();
		BigDecimal big = new BigDecimal("0");
		for (CarServicePart csp : cspList) {
			OrderDetailsDto odmDto = new OrderDetailsDto();
			if(null == csp.getServiceProBusiPrice()){
				odmDto.setBusiPrice(big);// 商家价格
				odmDto.setSettlePrice(big);// 结算价
			}else{
				odmDto.setBusiPrice(csp.getServiceProBusiPrice());// 商家价格
				odmDto.setSettlePrice(csp.getServiceProBusiPrice());// 结算价
			}
			// 数量
			if(null == csp.getUnitCount()){
				odmDto.setBuyCount(0);
			}else{
				odmDto.setBuyCount(csp.getUnitCount().intValue());
			}
			odmDto.setGoodsId(csp.getId());// 商品ID （更换零件时关联的零件ID）
			odmDto.setGoodsInfo(csp.getServiceProName());// 商品信息
			// odmDto.setIsAdditional(isAdditional);//是否新加标识位 0：是 1：否
			odmDto.setMainOrderNum(mainOrderNum);// 主订单号码 t_order_main_list 的main_order_num
			// 市场价
			if(null == csp.getServiceProMarketPrice()){
				odmDto.setMarketPrice(big);
			}else{
				odmDto.setMarketPrice(csp.getServiceProMarketPrice());
			}
			odmDto.setOrderNum(orderNum);// 订单号码 t_order_repair_list 的 order_num
			odmDto.setServiceProNum(csp.getServiceProNum());// 服务项目编号
			odmDto.setUnitPrice(csp.getServiceProBusiPrice());// 单价
			odmDto.setUserId(userId);// 用户ID
			odmDto.setManHourFee(csp.getManHourFee());
			omdList.add(odmDto);
		}
		if (orderType == 1) {
			orderDao.saveOrderMaintainDetails(omdList);
		} else if (orderType == 2) {
			orderDao.saveOrderRepairDetails(omdList);
		} else {
			orderDao.saveOrderCareDetails(omdList);
		}
	}

	@Override
	@WriteTransactional
	public Result<String> udpateOrderStatus(String orderState, String payState, String mainOrderNum,OrderDao orderDao) {
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
	}
	
	/**
	 * 生成子订单操作日志
	 * @param mainOrderNum 主订单号
	 * @param handleType 操作类型  1待支付，2已取消（包含用户自动取消和订单超时取消），3服务中，4退款中（用户发起退款），5退款已取消（用户取消退款），6退款已拒绝，7已退款，8已完成
	 * @param remark  备注
	 */
	@Override
	@WriteTransactional
	public Result<String> saveOrderLog(String mainOrderNum, Integer handleType, String remark, OrderDao orderDao) {
		try {
			List<OrderMainListDto> repairList = orderDao.queryOrderRepair(mainOrderNum);
			if (null != repairList && repairList.size() > 0) {
				orderDao.saveRepairOrderLog(mainOrderNum, repairList.get(0).getOrderNum(), handleType, remark);
			}

			List<OrderMainListDto> careList = orderDao.queryOrderCare(mainOrderNum);
			if (null != careList && careList.size() > 0) {
				orderDao.saveCareOrderLog(mainOrderNum, repairList.get(0).getOrderNum(), handleType, remark);
			}

			List<OrderMainListDto> maintainList = orderDao.queryOrderMaintain(mainOrderNum);
			if (null != maintainList && maintainList.size() > 0) {
				orderDao.saveMaintainOrderLog(mainOrderNum, repairList.get(0).getOrderNum(), handleType, remark);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Results.error();
		}
		return Results.success();
	}

	@Override
	@WriteTransactional
	public boolean orderRefundApply(Map<String,String> map,OrderDao orderDao) {
		try {
			String mainOrderNum = map.get("orderNum");
			String orderState = OrderState.IN_REFUNDS.getIndex()+"";
			int result = orderDao.saveOrderRefundList(map);
			orderDao.udpateMainOrderStatus(orderState, null, mainOrderNum);
			orderDao.udpateCareOrderStatus(orderState, null, mainOrderNum);
			orderDao.udpateMaintainOrderStatus(orderState, null, mainOrderNum);
			orderDao.udpateRepairOrderStatus( orderState, null, mainOrderNum);
			if(result > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
