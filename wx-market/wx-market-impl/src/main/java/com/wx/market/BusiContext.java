package com.wx.market ;

import com.wx.account.dto.AccountDto;
import com.wx.product.dto.ProductDto;
import com.wx.trade.dto.OrderDto;

public abstract class BusiContext {

	private static BusiContextImpl context;
	
	static void setContext(BusiContextImpl context) {
		BusiContext.context = context;
	}

	public static <T> T get(String key, Class<T> clazz) {
		return context.get(key, clazz);
	}
	
	/**
	 * 获取当前业务上下文中投资人基本信息
	 */
//	public static MemberDto getMember() {
//		return context.getBase();
//	}

	/**
	 * 获取当前业务上下文中投资人账户信息
	 * 
	 * @return 投资人账户信息
	 */
	public static AccountDto getAccount() {
		return context.getAccount();
	}

	/**
	 * 获取当前业务上下文中投资项目信息
	 */
	public static ProductDto getProduct() {
		return context.getProduct();
	}
	
	/**
	 * 获取投资订单
	 * @return
	 */
	public static OrderDto getOrder() {
		return context.getOrder();
		
	}

}
