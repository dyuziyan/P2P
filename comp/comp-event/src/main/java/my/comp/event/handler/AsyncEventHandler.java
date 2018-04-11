package my.comp.event.handler;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.task.AsyncTaskExecutor;

public class AsyncEventHandler extends SpringEventHandler {
	
	private AsyncTaskExecutor taskExecutor;

	public AsyncEventHandler(AsyncTaskExecutor taskExecutor, Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
		super(eventType, beanName, method, beanFactory);
		this.taskExecutor = taskExecutor;
	}

	@Override
	public void handle(final Object event) {
		this.taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				doHandle(event);
			}
		});
	}
	
	private void doHandle(Object event) {
		super.handle(event);
	}

}
