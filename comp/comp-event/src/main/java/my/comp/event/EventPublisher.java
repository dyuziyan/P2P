package my.comp.event;

import java.io.Serializable;

public interface EventPublisher {
	
	void publish(Serializable event);
}
