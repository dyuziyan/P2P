package my.comp.jms.core;

import java.io.Serializable;

import my.comp.jms.DestType;
import my.comp.jms.JmsException;
import my.comp.jms.JmsMessage;
import my.comp.jms.JmsSender;
import my.comp.jms.logger.JmsLogService;
import my.comp.sn.SerialNumberable;

import org.springframework.jms.core.JmsTemplate;

public abstract class JmsAbstractSender implements JmsSender {

	private JmsTemplate jmsTemplate;

	private JmsLogService jmsLogService;

	private DestType destType;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public JmsLogService getJmsLogService() {
		return jmsLogService;
	}

	public void setJmsLogService(JmsLogService jmsLogService) {
		this.jmsLogService = jmsLogService;
	}

	public DestType getDestType() {
		return destType;
	}

	public void setDestType(DestType destType) {
		this.destType = destType;
	}
	
	public void send(Serializable message) throws JmsException {
		send(message, true);
	}

	public void send(Serializable message, boolean withLogger) throws JmsException {
		JmsMessage msg = message.getClass().getAnnotation(JmsMessage.class);
		if (msg == null) throw new IllegalArgumentException("Annotation JmsMessage could not be found.");
		// 发送消息前记录日志
		send(msg.destination(), message, withLogger);
	}
	
	public void send(String destination, Serializable message) throws JmsException {
		send(destination, message, true);
	}

	public void send(String destination, Serializable message, boolean withLogger) throws JmsException {

		// 发送消息前记录日志
		if (jmsLogService != null && message instanceof SerialNumberable && withLogger) {
			SerialNumberable logmsg = (SerialNumberable) message;
			jmsLogService.logSendBefore(getDestType(), destination, logmsg);
		}
		try {
			jmsTemplate.convertAndSend(destination, message);
			// 发送消息后修改状态
			if (jmsLogService != null && message instanceof SerialNumberable) {
				SerialNumberable logmsg = (SerialNumberable) message;
				jmsLogService.logSendAfter(logmsg);
			}
		} catch (org.springframework.jms.JmsException e) {
			// ignore jms exception
			e.printStackTrace();
		}

	}

}