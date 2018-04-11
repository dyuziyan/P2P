package com.wx.support;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class DtoUtils {

	public final static void copyProperties(Object dest, Object src) {
		try {
			BeanUtils.copyProperties(src, dest,getNullPropertyNames(src));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}

	public final static <T> T clone(Class<T> returnClass, Object src) {
		try {
			T dest = returnClass.newInstance();
			BeanUtils.copyProperties(src, dest,getNullPropertyNames(src));
			return dest;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public final static boolean checkNull(Object bean) throws Exception {

		if (bean == null) return true;

		Class<?> clazz = bean.getClass();
		
		checkClazzNull(clazz,bean);
		
		checkSuperNull(clazz,bean);
		
		return false;
	}
	
	private static void checkClazzNull(Class<?> clazz,Object bean){
		for (Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(bean) == null) {
					throw new RuntimeException("属性不能为空："+f.getName());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static void checkSuperNull(Class<?> clazz,Object bean){
		Class<?> superClazz = clazz.getSuperclass();
		
		checkClazzNull(superClazz,bean);
		
		Class<?> superClazz2 = superClazz.getSuperclass();
		
		if(superClazz2 instanceof Object) return;
		checkSuperNull(superClazz2, bean);
	}

	public static Long getOrderId(String orderNo){
		String num = orderNo!=null && orderNo.indexOf("_")>=0 ? orderNo.substring(orderNo.lastIndexOf("_")+1):orderNo;
		if(!NumberUtils.isNumber(num)) throw new RuntimeException("解析业务id异常");
		return NumberUtils.createLong(num);
	}
}
