package my.comp.jms.logger;

import javax.annotation.Resource;

import my.comp.jms.JmsException;
import my.comp.sn.SerialNumberable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JmsLoggerAspect {

	@Resource
	private JmsLogService JmsLogService;

	@Pointcut("within(com.wx..*) && @annotation(my.comp.jms.logger.JmsLogger)")
	public void pointcut() {

	}

	@Around("pointcut()")
	public Object aroundCousume(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;

		Object[] args = joinPoint.getArgs();
		SerialNumberable loggable = getArg(args, SerialNumberable.class);

		try {
			JmsLogService.logConsumeBefore(loggable);
		} catch (JmsException e) {
			e.printStackTrace();
			return null;
		}

		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return result;
		}
		JmsLogService.logConsumeAfter(loggable);
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> T getArg(Object[] args, Class<T> argClass) {
		for (Object arg : args) {
			if (argClass.isAssignableFrom(arg.getClass())) { return (T) arg; }
		}
		return null;
	}

}
