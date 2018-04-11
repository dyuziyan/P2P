package my.comp.event;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import my.comp.event.handler.EventHandler;

public class DefaultEventPublisher implements EventPublisher {

	private Set<EventHandler> handlers = new HashSet<EventHandler>();
	
	public void regist(EventHandler handler) {
		handlers.add(handler);
	}

	public void handle(Serializable event) {

		for (EventHandler handler : handlers) {
			if (!handler.canHandle(event)) continue;
				
			handler.handle(event);
		}

	}

	public void publish(Serializable event) {
		handle(event);
	}
}
