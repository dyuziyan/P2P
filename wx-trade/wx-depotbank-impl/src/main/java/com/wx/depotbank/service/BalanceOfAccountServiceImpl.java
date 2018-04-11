package com.wx.depotbank.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.vfs2.FileSystemException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.wx.depotbank.common.BankConstant;
import com.wx.depotbank.dto.BalanceOfAccountDTO;
import com.wx.depotbank.exception.BankException;
import com.wx.depotbank.service.balanceOfAccount.CompareTask;
import com.wx.support.ChartSet;

import my.comp.vfs.FileUtils;
import my.comp.vfs.SFTPUtils;


@Service
public class BalanceOfAccountServiceImpl implements BalanceOfAccountService
{
	private static Logger logger = LoggerFactory.getLogger(BalanceOfAccountServiceImpl.class);
	
	@Override
	@Test
	public void balanceOfAccount() throws BankException {
		//sftp连接
		SFTPUtils sftputils=connentSFTP();
		if(sftputils==null){
			logger.error("连接银行sftp服务器失败");
			return;
		}
		//下载文件到本地
		String remotePath="";
		String remoteFileName="";
		String localFilePath="";
		String localFileName="C:\\Users\\dyuziyan\\Desktop\\ALEVE000210-20171012";
		boolean fileDownloadState=SFTPDownloadFile(sftputils,remotePath,remoteFileName,localFilePath,localFileName);
		if(!fileDownloadState){
			logger.error("银行sftp不存在对账文件"+remoteFileName);
			return;
		}
		//读取文件
		List<String> balanceOfAccountListfile=getFileList(localFilePath,localFileName);
		if(balanceOfAccountListfile==null){
			logger.error("从"+localFilePath+"读取文件"+localFileName+"失败");
			return;
		}
		//组装待对比数据，切割字符串
		List<BalanceOfAccountDTO> balanceOfAccountList=getCompareList(balanceOfAccountListfile);
		//从数据库流水表取出 对账文件当天的数据
		
		//2者对比，文件存在流水表不存在---丢单，文件不存在流水表存在---请求未提交到银行，2者皆存在数据不一致---数据异常，2者皆存在数据一直----校验通过
		
	}
	
	//并没有考虑订单号相同导致key值相同造成的并发问题，该方法默认认同流水文件取出来的数据不会存在订单号相同的情况
	//使用FutureTask进行多线程处理数据
	public List<BalanceOfAccountDTO> getCompareList(List<String> balanceOfAccountListfile) {
		int count=0;
		int length = balanceOfAccountListfile.size();
//		int threadNum = getThreadNum(length);
		int threadNum = 3;
	    int listExecutorSize = length % threadNum == 0 ? length / threadNum : (length/ threadNum + 1);
		// 均分到不同线程去获取
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		List<FutureTask<List<BalanceOfAccountDTO>>> FuntureTaskList = new ArrayList<>();
		List<BalanceOfAccountDTO> BalanceOfAccountList=new ArrayList<BalanceOfAccountDTO>();
		int startIndex=0;
		int endIndex =0;
		logger.info("FutureTask多线程处理数据切割开始");
		for (int i = 0; i < threadNum; i++) {
			endIndex = (i + 1) * listExecutorSize;
			endIndex=endIndex > length ? length : endIndex;
			startIndex=i * listExecutorSize;
			CompareTask task = new CompareTask("FutureTask处理对账线程"+i,balanceOfAccountListfile,startIndex,endIndex);
			FutureTask<List<BalanceOfAccountDTO>> future = new FutureTask<>(task);
			executor.submit(future);
			FuntureTaskList.add(future);
		}
		executor.shutdown();

		checkThreadDone(executor);
		for (FutureTask<List<BalanceOfAccountDTO>> balanceOfAccount : FuntureTaskList) {
			try {
				List<BalanceOfAccountDTO> listToCompare = balanceOfAccount.get();
				if (null != listToCompare && !listToCompare.isEmpty())
					BalanceOfAccountList.addAll(listToCompare);
			} catch (InterruptedException |ExecutionException e) {
				e.printStackTrace();
			}
		}
		logger.info("FutureTask多线程处理数据切割结束");
		return BalanceOfAccountList;
	}
	
	private SFTPUtils connentSFTP(){
		SFTPUtils utils=null;
		try {
		    utils = new SFTPUtils(BankConstant.SFTP_NAME, BankConstant.SFTP_PWD, BankConstant.SFTP_IP,new Integer(BankConstant.SFTP_PORT));
			return utils;
		} catch (NumberFormatException | FileSystemException | JSchException e1) {
			logger.error("连接银行sftp服务器失败");
			e1.printStackTrace();
		}
		return utils;
	}
	
	private boolean SFTPDownloadFile(SFTPUtils sftputils,String remotePath,String remoteFileName,String localFilePath,String localFileName){
		boolean fileDownloadState=false;
		try {
			 fileDownloadState=sftputils.downFile(sftputils,remotePath,remoteFileName,localFilePath,localFileName,true);
		} catch (Exception e1) {
			logger.error("从银行sftp服务器下载对账文件到本地服务器失败"+remoteFileName);
			e1.printStackTrace();
		}
		return fileDownloadState;
	}
	
	private List<String> getFileList(String localFilePath,String localFileName){
		List<String> balanceOfAccountListfile=null;
		try {
			 balanceOfAccountListfile=FileUtils.readFileToList(localFileName, ChartSet.UTF8.getKey());
		} catch (IOException e) {
			logger.error("从"+localFilePath+"读取文件"+localFileName+"失败");
			e.printStackTrace();
		}
		return balanceOfAccountListfile;
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
	
	@Test
    public void test123(){
   	 String xxx="6666882002000016342"
   	 		+ "00000000005000000"
   	 		+ "156"
   	 		+ "D"
   	 		+ "20171012"
   	 		+ "20171012"
   	 		+ "000000"
   	 		+ "00000002"
   	 		+ "666007"
   	 		+ "null-0x37-1417101209204850033                     "
   	 		+ "00000000005000000"
   	 		+ "621661280026908683 "
   	 		+ "N"
   	 		+ ""
   	 		+ "                                                                                                    ";
	   	try {
			List<String> balanceOfAccountListfile=FileUtils.readFileToList("C:\\Users\\dyuziyan\\Desktop\\ALEVE000210-20171012", ChartSet.UTF8.getKey());
			List<BalanceOfAccountDTO> balanceOfAccountList=getCompareList(balanceOfAccountListfile);
			System.out.println(balanceOfAccountList);
		} catch (IOException e) {
			e.printStackTrace();
		}
   	 
    }
	
}
