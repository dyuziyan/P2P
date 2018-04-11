package com.wx.carloadtrade.enums;
	public enum PayType {  
		/**
		 * 微信支付
		 */
		pay_wx("微信支付",1),
		/**
		 * 支付宝
		 */
		pay_zfb("支付宝",2),
		/**
		 * 到店支付
		 */
		pay_ddzf("到店支付",3),
		/**
		 * 银联支付
		 */
		pay_ylzf("银联支付",4),
		;
	    // 成员变量  
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private PayType(String name, int index) {  
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
	        for (PayType c : PayType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	}  


