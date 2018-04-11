package com.wx.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wx.validation.validator.MobileFormatValidator;

@NotNull(message="手机号不能为空")
@NotEmpty(message="手机号不能为空")
@Length(max=11,min=11,message="手机号格式不正确")
//约束注解应用的目标元素类型(METHOD, FIELD, TYPE, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER等)
@Target({ElementType.FIELD, ElementType.METHOD})
//约束注解应用的时机
@Retention(RetentionPolicy.RUNTIME)
//与约束注解关联的验证器
@Constraint(validatedBy=MobileFormatValidator.class)
public @interface MobileFormat {
	
	boolean isMobile() default false; 
	
	//约束注解验证时的输出消息  
    String message() default"手机号格式不正确";
    //约束注解在验证时所属的组别  
    Class<?>[] groups() default {};
    //约束注解的有效负载  
    Class<? extends Payload>[] payload() default {};
}
