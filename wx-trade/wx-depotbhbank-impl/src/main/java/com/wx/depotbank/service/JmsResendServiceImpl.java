package com.wx.depotbank.service;

import my.comp.jms.JmsReSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.service.Result;
import com.wx.service.Results;

@Service
public class JmsResendServiceImpl implements JmsResendService {

	@Autowired(required = false)
	private JmsReSender jmsReSender;

	public Result<Void> resend(long logId) {
		try {
			jmsReSender.send(logId);
		} catch (Exception e) {
			e.printStackTrace();
			return Results.byExc(e);
		}
		return Results.success();
	}

}
