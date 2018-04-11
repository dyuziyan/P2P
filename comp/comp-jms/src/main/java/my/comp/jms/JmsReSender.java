package my.comp.jms;

/**
 * 消息重发接口
 */
public interface JmsReSender {

	void send(long jmsLogId) throws JmsException;
}
