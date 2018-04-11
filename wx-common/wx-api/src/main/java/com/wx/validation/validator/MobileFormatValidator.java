package com.wx.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wx.validation.MobileFormat;

public class MobileFormatValidator implements ConstraintValidator<MobileFormat, String>{
	
	private boolean isMobile;  
	
	/** 
     * 中国电信号码格式验证
     * 手机段： 133,153,180,181,189,177,1700,173 
     **/  
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";  
    /** 
     * 中国联通号码格式验证
     * 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,175 
     **/  
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";  
    /** 
     * 中国移动号码格式验证 
     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705 
     **/  
    private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)";   
	
    /** 
     * 匹配函数 
     * @param regex 
     * @param input 
     */  
    private static boolean match(String regex, String input) {  
        return Pattern.matches(regex, input);  
    }  
    
	@Override
	public void initialize(MobileFormat constraintAnnotation) {
		isMobile = constraintAnnotation.isMobile();  
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean mobileState=false;
		boolean unicomState=false;
		boolean telecomState=false;
		if(!isMobile){
			mobileState=match(CHINA_MOBILE_PATTERN, value);		
			unicomState=match(CHINA_UNICOM_PATTERN, value); 
			telecomState=match(CHINA_TELECOM_PATTERN, value);  
		}
		if(mobileState||unicomState||telecomState){
			return true;
		}
		return false;
	}

}
