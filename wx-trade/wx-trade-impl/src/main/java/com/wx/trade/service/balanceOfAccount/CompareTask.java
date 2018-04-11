package com.wx.trade.service.balanceOfAccount;

import java.util.List;
import java.util.concurrent.Callable;


import com.wx.trade.dao.BalanceOfAccountDao;
import com.wx.trade.domain.BalanceOfAccount;
import com.wx.trade.enums.CompareTypeEnum;

public class CompareTask implements Callable<List<BalanceOfAccount>>{
	
	 private BalanceOfAccountDao BalanceOfAccountDao;
	 
	 private String type;  
	 private int modNum;  
     private int modPage;  
       
     public CompareTask(String type,int modNum,int modPage,BalanceOfAccountDao balanceOfAccountDao){  
    	 this.type=type;
         this.modNum = modNum;  
         this.modPage = modPage;  
         this.BalanceOfAccountDao=balanceOfAccountDao;
     }  
     public CompareTask(){ 
    	 
     }
    		 
     @Override  
     public List<BalanceOfAccount> call() throws Exception {  
    	 List<BalanceOfAccount> resultList=null;
    	 if(type.equals(CompareTypeEnum.INVESTCOMPARE.getKey().toString())){
 			resultList=BalanceOfAccountDao.getInvestBalanceOfAccountList(modNum, modPage);
 		}else{
 			resultList=BalanceOfAccountDao.getBalanceOfAccountList(type,modNum,modPage);
 		}
         return resultList;  
     }  
}
