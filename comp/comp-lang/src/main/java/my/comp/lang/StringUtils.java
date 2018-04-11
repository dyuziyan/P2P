package my.comp.lang;

import java.text.MessageFormat;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static boolean isNull(String str) {
		if (null == str || str.equals("NULL") || str.equals("null") || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static String format(String pattern, Object... args) {
		Object[] arguments = null;
		if (args != null && args.length > 0) {
			arguments = new Object[args.length];
			for (int i = 0; i < args.length; i++) {
				if (args[i] != null && (args[i] instanceof Integer || args[i] instanceof Long)) {
					arguments[i] = String.valueOf(args[i]);
				} else {
					arguments[i] = args[i];
				}
			}
		}
		return MessageFormat.format(pattern, arguments);
	}

	/**
	 * 判断字符是否为空 ，空返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static String getRandom6Number() {
		int code = 0;
		while (code <= 0) {
			code = (int) (Math.random() * 1000000);
		}

		if (code < 10) {
			code = code * 100000;
		} else if (code < 100) {
			code = code * 10000;
		} else if (code < 1000) {
			code = code * 1000;
		} else if (code < 10000) {
			code = code * 100;
		} else if (code < 100000) {
			code = code * 10;
		}

		return String.valueOf(code);
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) return s;
		else return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 根据长度字符前补零
	 * 
	 * @param id
	 * @param length
	 * @return
	 */
	public static String fillZero(String preStr, String id, int length) {
		if (id == null) return null;
		StringBuilder str = new StringBuilder(id);
		if (id.length() < length) {
			for (int i = 0; i < length - id.length(); i++) {
				str.insert(0, "0");
			}
		}
		if (preStr != null) str.insert(0, preStr);
		return str.toString();
	}

}
