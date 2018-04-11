package my.comp.jms;

import java.io.Serializable;

public interface JmsSender {

	void send(Serializable message) throws JmsException;

	void send(Serializable message, boolean withLogger) throws JmsException;

	void send(String destination, Serializable message) throws JmsException;

	void send(String destination, Serializable message, boolean withLogger) throws JmsException;

}
