package com.wx.trade.service.balanceOfAccount;

import java.util.List;
import java.util.concurrent.Callable;

import com.wx.trade.dao.BalanceOfAccountDao;
import com.wx.trade.domain.ToCompareDomain;
import com.wx.trade.enums.CompareTypeEnum;

public class ToBeCompareTask implements Callable<List<ToCompareDomain>>{
	
	 private BalanceOfAccountDao BalanceOfAccountDao;
	 private CompareTypeEnum compareType;  
	 private int modNum;  
     private int modPage;  
       
     public ToBeCompareTask(CompareTypeEnum compareType,int modNum,int modPage,BalanceOfAccountDao balanceOfAccountDao){  
    	 this.compareType=compareType;
         this.modNum = modNum;  
         this.modPage = modPage;  
         this.BalanceOfAccountDao=balanceOfAccountDao;
     }  
     public ToBeCompareTask(){
    	 
     }
     @Override  
     public List<ToCompareDomain> call() throws Exception {  
    	List<ToCompareDomain> resultList=null;
    	if(CompareTypeEnum.RECHARGECOMPARE.equals(compareType)){
    		resultList=BalanceOfAccountDao.getRechargeCompareList(modNum,modPage);
 		}else if(CompareTypeEnum.REDPACKETCOMPARE.equals(compareType)){
 			resultList=BalanceOfAccountDao.getRedPacketCompareList(modNum,modPage);
 		}else if(CompareTypeEnum.WITHDRAWCOMPARE.equals(compareType)){
 			resultList=BalanceOfAccountDao.getWithdrawCompareList(modNum,modPage);
 		}else if(CompareTypeEnum.FUNDTRANSFERCOMPARE.equals(compareType)){
 			resultList=BalanceOfAccountDao.getFundTransferCompareList(modNum,modPage);
 		}else if (CompareTypeEnum.INVESTCOMPARE.equals(compareType)) {
 			resultList = BalanceOfAccountDao.getInvestCompareList(modNum, modPage);
		}else {
			return resultList;
		}
    	 
        return resultList;  
     }  
}
