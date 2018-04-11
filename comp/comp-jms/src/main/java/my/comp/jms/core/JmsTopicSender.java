package my.comp.jms.core;

import my.comp.jms.DestType;

public class JmsTopicSender extends JmsAbstractSender {
	public JmsTopicSender() {
		this.setDestType(DestType.topic);
	}
}