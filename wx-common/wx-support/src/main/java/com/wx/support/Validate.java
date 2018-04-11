package com.wx.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.wx.service.Results;
@Aspect
public class Validate {
	//hibernate实现
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Around("execution(com.wx.service.Result com.wx..*Service.*(..))")
	public Object validate(ProceedingJoinPoint point) throws Throwable {

		Object[] os = point.getArgs();

		Set<ConstraintViolation<Object>> violations = new HashSet<ConstraintViolation<Object>>();

		for (Object obj : os) {

			if (obj == null) continue;

			violations.addAll(validator.validate(obj, Default.class));
		}

		if (violations.size() > 0) {

			List<ConstraintViolation<Object>> msgs = new ArrayList<ConstraintViolation<Object>>();

			msgs.addAll(violations);

			return Results.error(msgs.get(0).getMessage());
		} else {
			return point.proceed();
		}
	}
}
