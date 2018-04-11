package com.wx.trade.service.balanceOfAccount;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wx.trade.domain.BalanceOfAccount;
import com.wx.trade.domain.ToCompareDomain;
import com.wx.trade.enums.BalanceOfAccountStateEnum;
import com.wx.trade.enums.CompareStateEnum;

public class CompareDataTask extends RecursiveTask<HashMap<String,HashMap>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompareDataTask.class);

	private static final long serialVersionUID = 4229454694710009450L;
	
	private long threadNum=10;
	
	private List<BalanceOfAccount> balanceOfAccountList;					// 对比表LIST
	private HashMap<String, BalanceOfAccount> balanceOfAccountMap; 	// 对比表MAP
	private HashMap<String, ToCompareDomain> compareMap;				// 非一致数据
	
	public CompareDataTask(List<BalanceOfAccount> balanceOfAccountList,HashMap<String,BalanceOfAccount> balanceOfAccountMap,HashMap<String,ToCompareDomain> compareMap) {
		this.balanceOfAccountList = balanceOfAccountList;
		this.balanceOfAccountMap = balanceOfAccountMap;
		this.compareMap = compareMap;
	}
	@Override
	protected HashMap<String, HashMap> compute() {
		//对比数据少于500则不做切割处理
		if (balanceOfAccountList.size() < threadNum) {
			LOGGER.debug("CompareDataTask:本线程处理数据量为："+balanceOfAccountList.size());
			compareData(balanceOfAccountList);
		} else {
			int size = balanceOfAccountList.size();
			int mid = size / 2;
			LOGGER.debug("CompareDataTask--待分割总数据："+size);
			CompareDataTask task1 = new CompareDataTask(balanceOfAccountList.subList(0, mid),balanceOfAccountMap,compareMap);
			CompareDataTask task2 = new CompareDataTask(balanceOfAccountList.subList(mid, size),balanceOfAccountMap,compareMap);
			//invokeALL比fork高效
			invokeAll(task1, task2);
		}
		HashMap<String, HashMap> resultMap=new HashMap<String, HashMap>();
		resultMap.put("balanceOfAccountMap", balanceOfAccountMap);
		resultMap.put("compareMap", compareMap);
		return resultMap;
	}

	// 对比数据，得出所有不一致的数据集
	protected void compareData(List<BalanceOfAccount> balanceOfAccountList) {
		// 如果对账表数据集为空，则表示银行对账文件没有生成导致，不做处理
		if (null == balanceOfAccountList || balanceOfAccountList.isEmpty()) {
			LOGGER.debug("CompareDataTask-----存管对账文件没有生成-----");
			return;
		}
		
		for (BalanceOfAccount balanceOfAccount : balanceOfAccountList) {
			ToCompareDomain toCompareDomain = compareMap.get(balanceOfAccount.getMerBillNo());
			// 对账表有数据，非对账表无数据，代表丢单,compareMap增加
			if (null == toCompareDomain) {
				LOGGER.debug("CompareDataTask-----丢单数据-----"+balanceOfAccount.getMerBillNo());
				balanceOfAccountMap.get(balanceOfAccount.getMerBillNo()).setComparestate(BalanceOfAccountStateEnum.NEEDCORRECT.getKey());
				ToCompareDomain addCompareDomain = new ToCompareDomain();
				addCompareDomain.setMerBillNo(balanceOfAccount.getMerBillNo());
				addCompareDomain.setCreDt(balanceOfAccount.getCreDt().toString());
				addCompareDomain.setTransAmt(balanceOfAccount.getTransAmt());
				addCompareDomain.setType(balanceOfAccount.getType());
				addCompareDomain.setCompareState(CompareStateEnum.LOSING.getKey());
				compareMap.put(balanceOfAccount.getMerBillNo(), addCompareDomain);
				continue;
			}

			// 2边都存在的数据，对比数据参数，一致则compareMap进行移除
			if (toCompareDomain.getTransAmt().compareTo(balanceOfAccount.getTransAmt()) == 0
					&& toCompareDomain.getCreDt().equals(balanceOfAccount.getCreDt())) {
				LOGGER.debug("CompareDataTask-----数据一致-----"+balanceOfAccount.getMerBillNo());
				balanceOfAccountMap.get(balanceOfAccount.getMerBillNo()).setComparestate(BalanceOfAccountStateEnum.COMPARED.getKey());
				compareMap.remove(balanceOfAccount.getMerBillNo());
			} else {
				LOGGER.debug("CompareDataTask-----数据不一致-----"+balanceOfAccount.getMerBillNo());
				balanceOfAccountMap.get(balanceOfAccount.getMerBillNo()).setComparestate(BalanceOfAccountStateEnum.NEEDCORRECT.getKey());
				compareMap.get(balanceOfAccount.getMerBillNo()).setCompareState(CompareStateEnum.DATAASYMMETRY.getKey());
			}
			// 如此处理，原被对账表遗留数据即为银行对账为空，自平台存在数据的情况，该情况已未对账处理，实体默认未对账
		}
	}
}
