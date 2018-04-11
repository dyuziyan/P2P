package my.comp.event.handler;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.task.AsyncTaskExecutor;

public abstract class AsyncEventHandlerFactory {

	protected abstract AsyncTaskExecutor getExecutor();

	public AsyncEventHandler create(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
		return new AsyncEventHandler(getExecutor(), eventType, beanName, method, beanFactory);
	}
}
