package com.wx.depotbank.service;

import my.comp.rmi.annotation.RemoteService;

import com.wx.service.Result;


/**
 * 待抽走，暂时先写这里
 */
@RemoteService("/jmsResendService")
public interface JmsResendService {

	Result<Void> resend(long logId);

}
