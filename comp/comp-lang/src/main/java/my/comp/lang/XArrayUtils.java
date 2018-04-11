package my.comp.lang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

public class XArrayUtils extends ArrayUtils {
	/**
	 * 将String数组装换成Long数组
	 * 
	 * @param array
	 * @return
	 */
	public static Long[] toLongArray(String[] array) {
		int size = array.length;
		Long[] longArray = new Long[size];
		for (int i = 0; i < size; i++) {
			longArray[i] = Long.parseLong(array[i]);
		}
		return longArray;
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	public static <T> List<T> array2List(T[] array) {
		return Arrays.asList(array);
	}

	public static Map<String, Object> array2Map(Object[][] array) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object[] d : array) {
			map.put(d[0].toString(), d[1]);
		}
		return map;
	}

}
