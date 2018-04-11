package com.wx.trade.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wx.account.service.AccountService;
import com.wx.service.BaseService;
import com.wx.service.Result;
import com.wx.service.Results;
import com.wx.trade.dao.BalanceOfAccountDao;
import com.wx.trade.domain.BalanceOfAccount;
import com.wx.trade.domain.ToCompareDomain;
import com.wx.trade.enums.BalanceOfAccountStateEnum;
import com.wx.trade.enums.CompareStateEnum;
import com.wx.trade.enums.CompareTypeEnum;
import com.wx.trade.service.BalanceOfAccountService;
import com.wx.trade.service.balanceOfAccount.CompareDataTask;
import com.wx.trade.service.balanceOfAccount.CompareTask;
import com.wx.trade.service.balanceOfAccount.ToBeCompareTask;

import my.comp.transaction.WriteTransactional;

/**
 * 对账接口
 */
@Deprecated
@Service
public class BalanceOfAccountServiceImpl extends BaseService implements BalanceOfAccountService {

	@Resource
	private BalanceOfAccountDao BalanceOfAccountDao;
	@Resource
	private AccountService accountService;

	@Override
	@WriteTransactional
	public Result<String> balanceOfAccount(CompareTypeEnum compareType) {
		List<BalanceOfAccount> balanceOfAccountList = new ArrayList<BalanceOfAccount>(); // 对比表LIST
		List<ToCompareDomain> tobeCompareList = new ArrayList<ToCompareDomain>(); // 被对比表LIST
		// 获取数据
		balanceOfAccountList=getCompareList(compareType,balanceOfAccountList);
		logger.debug(compareType+"存管对账数据处理中---对账数据共--" + balanceOfAccountList.size());
		if (balanceOfAccountList.isEmpty()) {
			return Results.error("没有可对账数据");
		}
		tobeCompareList=getTobeCompareList(compareType,tobeCompareList);
		logger.debug(compareType+"存管对账数据处理中---待对比数据共--" + tobeCompareList.size());
		if (tobeCompareList.isEmpty()) {
			return Results.error("没有可对账数据");
		}

		HashMap<String, BalanceOfAccount> balanceOfAccountMap = new HashMap<String, BalanceOfAccount>(); // 对比表MAP
		HashMap<String, ToCompareDomain> compareMap = new HashMap<String, ToCompareDomain>(); // 非一致数据
		// 将要比对数据放入Map
		for (ToCompareDomain toCompareDomain : tobeCompareList) {
			compareMap.put(toCompareDomain.getMerBillNo(), toCompareDomain);
		}
		for (BalanceOfAccount balanceOfAccount : balanceOfAccountList) {
			balanceOfAccountMap.put(balanceOfAccount.getMerBillNo(), balanceOfAccount);
		}
		// 比较数据 使用 Fork/Join 进行切分处理
		ForkJoinPool pool = new ForkJoinPool();
		CompareDataTask task = new CompareDataTask(balanceOfAccountList, balanceOfAccountMap, compareMap);
		pool.execute(task);
		try {
			HashMap<String, HashMap> mapResult = task.get();
			pool.shutdown();
			HashMap<String, BalanceOfAccount> balanceOfAccountHashMap = mapResult.get("balanceOfAccountMap");
			HashMap<String, ToCompareDomain> compareHashMap = mapResult.get("compareMap");

			List<BalanceOfAccount> BalanceOfAccountResultList = new ArrayList<BalanceOfAccount>(
					balanceOfAccountHashMap.values());
			List<ToCompareDomain> ToCompareDomainResultList = new ArrayList<ToCompareDomain>(compareHashMap.values());
			
			logger.debug(compareType+"存管对账数据处理中---未对账数据剔除前：" + BalanceOfAccountResultList.size());
			Iterator<BalanceOfAccount> BalanceOfAccountIter = BalanceOfAccountResultList.iterator();
			while (BalanceOfAccountIter.hasNext()) {
				BalanceOfAccount balanceOfAccount = BalanceOfAccountIter.next();
				if (BalanceOfAccountStateEnum.UNCOMPARE.getKey() == balanceOfAccount.getComparestate()) {
					BalanceOfAccountIter.remove();
				}
			}
			logger.debug(compareType+"存管对账数据处理中---对账数据剔除后：" + BalanceOfAccountResultList.size());
			
			
			if(!BalanceOfAccountResultList.isEmpty()){
				// ------对账完成正常数据入库处理-------------
				if(CompareTypeEnum.INVESTCOMPARE.equals(compareType)){
					BalanceOfAccountDao.batchUpdateInvestBalanceOfAccount(BalanceOfAccountResultList);
				}else{
					BalanceOfAccountDao.batchUpdateBalanceOfAccount(BalanceOfAccountResultList);
				}
				
				if (CompareTypeEnum.RECHARGECOMPARE.equals(compareType)) {
					BalanceOfAccountDao.batchUpdateRecharge(BalanceOfAccountResultList);
				} else if (CompareTypeEnum.REDPACKETCOMPARE.equals(compareType)) {
					BalanceOfAccountDao.batchUpdateRedPacket(BalanceOfAccountResultList);
				} else if (CompareTypeEnum.WITHDRAWCOMPARE.equals(compareType)) {
					//TODO 此处得重构
					for (BalanceOfAccount balanceOfAccount : BalanceOfAccountResultList) {
						// 更改提现为最终状态的用户资金，添加资金流水，直接往站内信表添加信息
						accountService.withdrawStateFinal(balanceOfAccount.getPlaCustId(), balanceOfAccount.getChargeCorg(),
								balanceOfAccount.getTransAmt(), balanceOfAccount.getMerBillNo());
					}

					BalanceOfAccountDao.batchUpdateWithdraw(BalanceOfAccountResultList);
				} else if (CompareTypeEnum.FUNDTRANSFERCOMPARE.equals(compareType)) {
					BalanceOfAccountDao.batchUpdateFundTransfer(BalanceOfAccountResultList);
				} else if (CompareTypeEnum.INVESTCOMPARE.equals(compareType)) {
					BalanceOfAccountDao.batchUpdateInvest(BalanceOfAccountResultList);
				} else {
					return Results.error("入参异常");
				}
			}
			
			logger.debug(compareType+"存管对账数据处理中---正常数据入库处理完成");
			// ------不一致 丢单数据 录入对账异常表-------------
			// compareState=0的数据不录入对账异常表，作为未对账处理
			logger.debug(compareType+"存管被对账数据处理中---未对账数据剔除前：" + ToCompareDomainResultList.size());
			//取出对账异常表包含的订单列表--不再重复入库
			HashMap<String, ToCompareDomain> compareOldDateMap = new HashMap<String, ToCompareDomain>(); 
			if(!ToCompareDomainResultList.isEmpty()){
				List<ToCompareDomain> compareOldDateList=BalanceOfAccountDao.getCompareList(ToCompareDomainResultList);
				for (ToCompareDomain compareOldDate : compareOldDateList) {
					compareOldDateMap.put(compareOldDate.getMerBillNo(), compareOldDate);
				}
			}
			Iterator<ToCompareDomain> iter = ToCompareDomainResultList.iterator();
			while (iter.hasNext()) {
				ToCompareDomain toCompareDomain = iter.next();
				//不再重复入库，剔除未对账数据
				if (CompareStateEnum.NOTCOMPARE.getKey() == toCompareDomain.getCompareState()||
						compareOldDateMap.get(toCompareDomain.getMerBillNo())!=null) {
					iter.remove();
				}
			}
			
			logger.debug(compareType+"存管被对账数据处理中---未对账数据剔除后：" + ToCompareDomainResultList.size());
			if(!ToCompareDomainResultList.isEmpty()){
				BalanceOfAccountDao.batchUpdateToBeCompare(ToCompareDomainResultList);
			}
			logger.debug(compareType+"存管被对账数据处理中---异常数据入库处理完成");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return Results.success();
	}

	// 使用FutureTask进行多线程数据读取，减少读取时间
	public List<BalanceOfAccount> getCompareList(CompareTypeEnum compareType,List<BalanceOfAccount> balanceOfAccountList) {
		int count=0;
		if(CompareTypeEnum.INVESTCOMPARE.equals(compareType)){
			count = BalanceOfAccountDao.getCountForInvestBalanceOfAccount();
		}else{
			count = BalanceOfAccountDao.getCountForBalanceOfAccount(compareType.getKey().toString());
		}
		int threadNum = getThreadNum(count);
		// 均分到不同线程去获取
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		List<FutureTask<List<BalanceOfAccount>>> FuntureTaskList = new ArrayList<>();
		for (int i = 0; i < threadNum; i++) {
			CompareTask task = new CompareTask(compareType.getKey().toString(), threadNum, i,BalanceOfAccountDao);
			FutureTask<List<BalanceOfAccount>> future = new FutureTask<>(task);
			executor.submit(future);
			FuntureTaskList.add(future);
		}
		executor.shutdown();

		checkThreadDone(executor);
		for (FutureTask<List<BalanceOfAccount>> balanceOfAccount : FuntureTaskList) {
			try {
				List<BalanceOfAccount> listToCompare = balanceOfAccount.get();
				if (null != listToCompare && !listToCompare.isEmpty())
					balanceOfAccountList.addAll(listToCompare);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return balanceOfAccountList;
	}

	public List<ToCompareDomain> getTobeCompareList(CompareTypeEnum compareType,List<ToCompareDomain> tobeCompareList) {

		int count = 0;
		if (CompareTypeEnum.RECHARGECOMPARE.equals(compareType)) {
			count = BalanceOfAccountDao.getCountForRecharge();
		} else if (CompareTypeEnum.REDPACKETCOMPARE.equals(compareType)) {
			count = BalanceOfAccountDao.getCountForRedPacket();
		} else if (CompareTypeEnum.WITHDRAWCOMPARE.equals(compareType)) {
			count = BalanceOfAccountDao.getCountForWithdraw();
		}else if (CompareTypeEnum.FUNDTRANSFERCOMPARE.equals(compareType)) {
				count = BalanceOfAccountDao.getCountForFundTransfer();
		} else if (CompareTypeEnum.INVESTCOMPARE.equals(compareType)) {
			count = BalanceOfAccountDao.getCountForInvest();
		} else {
			return tobeCompareList;
		}

		int threadNum = getThreadNum(count);
		// 均分到不同线程去获取
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);

		List<FutureTask<List<ToCompareDomain>>> FutureCompareList = new ArrayList<>();
		for (int i = 0; i < threadNum; i++) {
			ToBeCompareTask task = new ToBeCompareTask(compareType, threadNum, i,BalanceOfAccountDao);
			FutureTask<List<ToCompareDomain>> future = new FutureTask<>(task);
			executor.submit(future);
			FutureCompareList.add(future);
		}
		executor.shutdown();

		checkThreadDone(executor);
		for (FutureTask<List<ToCompareDomain>> ToCompareDomain : FutureCompareList) {
			try {
				List<ToCompareDomain> ToCompareDomainList = ToCompareDomain.get();
				if (null != ToCompareDomainList && !ToCompareDomainList.isEmpty())
					tobeCompareList.addAll(ToCompareDomainList);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return tobeCompareList;
	}

	private int getThreadNum(int count) {
		int threadNum = 0;
		if (count > 100000) {
			threadNum = 10;
		} else if (count > 50000 && count <= 100000) {
			threadNum = 7;
		} else if (count > 30000 && count <= 50000) {
			threadNum = 3;
		} else {
			threadNum = 1;
		}
		return threadNum;
	}

	private void checkThreadDone(ExecutorService executor) {
		// 判断是否完成
		while (true) {
			if (executor.isTerminated()) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
