package com.wx.carloadtrade.enums;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wx.dto.BaseObject;
public  class MakeOrderNumber  extends BaseObject{
	
	/** 
     * 锁对象，可以为任意对象 
     */  
    private static Object lockObj = "";  
    /** 
     * 订单号生成计数器 
     */  
    private static long orderNumCount = 0L;  
    /** 
     * 每毫秒生成订单号数量最大值 
     */  
    private int maxPerMSECSize=1000;  
    /** 
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展 
     */  
    
    /**
     * 生成主订单号
     * @return
     */
    public String saveMainOrderNum(){
    	return makeOrderNum("M");
    }
    /**
     * 生成保养订单号
     * @return
     */
    public String saveBYOrderNum(){
    	return makeOrderNum("BY");
    }
    
    /**
     * 生成维修订单号
     * @return
     */
    public String saveWXOrderNum(){
    	return makeOrderNum("WX");
    }
    
    /**
     * 生成养护订单号
     * @return
     */
    public String saveYHOrderNum(){
    	return makeOrderNum("YH");
    }
    
    /**
     * 生成退款单号
     * @return
     */
    public String saveTKOrderNum(){
    	return makeOrderNum("TK");
    }
    
    /**
     * 生成支付流水单号/交易单号
     * @return
     */
    public String savePayNum(){
    	return makeOrderNum("P");
    }
    
    public  String makeOrderNum(String tname) {  
    	// 最终生成的订单号  
    	String finOrderNum = "";  
        try {  
            synchronized (this) {  
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒  
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));  
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
                if (orderNumCount >= maxPerMSECSize) {  
                    orderNumCount = 0L;  
                }  
                //组装订单号  
                String countStr=maxPerMSECSize +orderNumCount+"";  
                finOrderNum=tname+nowLong+countStr.substring(1);  
                orderNumCount++;  
                System.out.println(finOrderNum + "--" + Thread.currentThread().getName() + "::" + tname );  
                // Thread.sleep(1000);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return finOrderNum;
    }  
  
    public static void main(String[] args) {  
        // 测试多线程调用订单号生成工具  
        try {  
            for (int i = 0; i < 200; i++) {  
                Thread t1 = new Thread(new Runnable() {  
                    public void run() {  
                    	MakeOrderNumber makeOrder = new MakeOrderNumber();  
                        makeOrder.makeOrderNum("a");  
                    }  
                }, "at" + i);  
                t1.start();  
  
                Thread t2 = new Thread(new Runnable() {  
                    public void run() {  
                    	MakeOrderNumber makeOrder = new MakeOrderNumber();  
                        makeOrder.makeOrderNum("b");  
                    }  
                }, "bt" + i);  
                t2.start();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
}
