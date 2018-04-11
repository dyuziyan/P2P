package my.comp.jms.core;

import my.comp.jms.DestType;

public class JmsQueueSender extends JmsAbstractSender {
	public JmsQueueSender() {
		this.setDestType(DestType.queue);
	}
}