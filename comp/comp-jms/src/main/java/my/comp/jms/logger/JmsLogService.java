package my.comp.jms.logger;

import my.comp.jms.DestType;
import my.comp.jms.JmsException;
import my.comp.sn.SerialNumberable;

public interface JmsLogService {

	void logSendBefore(DestType destType, String destName, SerialNumberable loggableMsg) throws JmsException;

	void logSendAfter(SerialNumberable loggableMsg) throws JmsException;

	void logConsumeBefore(SerialNumberable loggableMsg) throws JmsException;

	void logConsumeAfter(SerialNumberable loggableMsg) throws JmsException;

	/**
	 * 记录重发消息日志
	 */
	JmsLog logReSend(long jmsLogId) throws JmsException;
}
