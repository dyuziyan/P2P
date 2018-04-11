package com.wx.carloadtrade.enums;
	public enum OrderState {  
		/**
		 * 待支付
		 */
		TO_BE_PAID("待支付",1),
		/**
		 * 已取消
		 */
		ALREADY_CANCEL("已取消",2),
		/**
		 * 服务中
		 */
		IN_SERVICE("服务中",3),
		/**
		 * 退款中
		 */
		IN_REFUNDS("退款中",4),
		/**
		 * 退款已取消
		 */
		REFUNDS_CANCEL("退款已取消",5),
		/**
		 * 退款已拒绝
		 */
		REFUNDS_REJECT("退款已拒绝",6),
		/**
		 * 已退款
		 */
		ALREADY_REFUNDED("已退款",7),
		/**
		 * 已完成
		 */
		COMPLETE("已完成",8);
		
	    // 成员变量  
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private OrderState(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    
	    public static String getName(int index) {  
	        for (OrderState c : OrderState.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	}  


