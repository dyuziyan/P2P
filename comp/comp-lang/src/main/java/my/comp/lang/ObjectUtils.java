package my.comp.lang;

import org.apache.commons.lang.StringUtils;

public class ObjectUtils extends org.apache.commons.lang.ObjectUtils{
	public static boolean isEmpty(Object o) {
		if (o instanceof String) {
			return StringUtils.isEmpty((String) o);
		} else {
			return o == null;
		}
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	
}
