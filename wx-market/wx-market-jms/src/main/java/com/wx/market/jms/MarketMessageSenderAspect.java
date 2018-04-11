package com.wx.market.jms;

import my.comp.jms.JmsException;
import my.comp.jms.core.JmsTopicSender;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import com.wx.market.dto.MarketMessage;

@Aspect
public class MarketMessageSenderAspect implements Ordered {

	@Autowired
	private JmsTopicSender jmsTopicSender;

	private static final Logger logger = LoggerFactory.getLogger(MarketMessageSenderAspect.class);

	@Pointcut("@annotation(com.wx.market.jms.MarketMessageSender)")
	public void pointcut() {

	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			logger.debug("After Advice: {}", joinPoint.getSignature().toLongString());
			logger.debug("拦截方法执行异常，不发送营销消息：{}", e.getMessage());
			throw e;
		}
		
		logger.debug("After Advice: {}", joinPoint.getSignature().toLongString());

		MarketMessageWrapper wrapper = MarketMessageHolder.remove();
		if (wrapper == null) {
			logger.debug("没有营销消息");
			return result;
		}

		for (MarketMessageEntry entry : wrapper.array()) {
			try {

				MarketMessage message = entry.getMessage();
				String destination = entry.getDestName();

				logger.debug("发送营销消息：{}", wrapper.toString());
				jmsTopicSender.send(destination, message, false);

			} catch (JmsException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected <T> T getArg(Object[] args, Class<T> argClass) {
		for (Object arg : args) {
			if (argClass.isAssignableFrom(arg.getClass())) { return (T) arg; }
		}
		return null;
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
