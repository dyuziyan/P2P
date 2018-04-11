package my.comp.event;

import java.lang.reflect.Method;

import my.comp.event.annotation.EventListener;
import my.comp.event.handler.AsyncEventHandlerFactory;
import my.comp.event.handler.EventHandler;
import my.comp.event.handler.SpringEventHandler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class EventPublisherConfigurer implements BeanPostProcessor, BeanFactoryAware, InitializingBean {

	private BeanFactory beanFactory;
	private DefaultEventPublisher publisher;
	private AsyncEventHandlerFactory asyncEventHandlerFactory;

	public void setStandardPublisher(DefaultEventPublisher standardPublisher) {
		this.publisher = standardPublisher;
	}

	public void setAsyncEventHandlerFactory(AsyncEventHandlerFactory asyncEventHandlerFactory) {
		this.asyncEventHandlerFactory = asyncEventHandlerFactory;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		publisher = beanFactory.getBean(DefaultEventPublisher.class);
		asyncEventHandlerFactory = beanFactory.getBean(AsyncEventHandlerFactory.class);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		for (Method method : bean.getClass().getMethods()) {
			EventListener listenerAnnotation = method.getAnnotation(EventListener.class);

			if (listenerAnnotation == null) {
				continue;
			}

			Class<?> eventType = method.getParameterTypes()[0];

			if (listenerAnnotation.async()) {
				EventHandler handler = asyncEventHandlerFactory.create(eventType, beanName, method, beanFactory);
				publisher.regist(handler);
			} else {
				EventHandler handler = new SpringEventHandler(eventType, beanName, method, beanFactory);
				publisher.regist(handler);
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
