package my.comp.event.handler;

public interface EventHandler {

	boolean canHandle(Object event);

	void handle(Object event);
}
