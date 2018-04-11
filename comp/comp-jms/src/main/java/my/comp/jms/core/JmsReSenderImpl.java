package my.comp.jms.core;

import my.comp.jms.DestType;
import my.comp.jms.JmsException;
import my.comp.jms.JmsReSender;
import my.comp.jms.logger.JmsLog;
import my.comp.jms.logger.JmsLogService;
import my.comp.sn.SerialNumberable;

import org.springframework.jms.core.JmsTemplate;

import com.alibaba.fastjson.JSONObject;

public class JmsReSenderImpl implements JmsReSender {

	private JmsTemplate jmsQueueTemplate;

	private JmsTemplate jmsTopicTemplate;

	private JmsLogService jmsLogService;

	public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
		this.jmsQueueTemplate = jmsQueueTemplate;
	}

	public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
		this.jmsTopicTemplate = jmsTopicTemplate;
	}

	public void setJmsLogService(JmsLogService jmsLogService) {
		this.jmsLogService = jmsLogService;
	}

	@Override
	public void send(long jmsLogId) throws JmsException {

		JmsLog jmsLog = jmsLogService.logReSend(jmsLogId);

		SerialNumberable loggable = fromJmsLog(jmsLog);

		getJmsTemplate(jmsLog.getDestType()).convertAndSend(jmsLog.getDestName(), loggable);

	}

	private SerialNumberable fromJmsLog(JmsLog jmsLog) throws JmsException {
		try {
			Object object = JSONObject.parseObject(jmsLog.getMsgData(), Class.forName(jmsLog.getMsgClass()));
			return (SerialNumberable) object;
		} catch (ClassNotFoundException e) {
			throw new JmsException(e);
		}
	}

	private JmsTemplate getJmsTemplate(DestType destType) throws JmsException {
		if (DestType.queue.equals(destType)) return jmsQueueTemplate;
		else if (DestType.topic.equals(destType)) return jmsTopicTemplate;
		else throw new JmsException("找不到对应的JmsTemplate：" + destType);
	}

}
