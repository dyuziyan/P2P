package my.comp.lang;

import java.math.BigDecimal;
import java.util.Map;

public class MapUtils extends org.apache.commons.collections.MapUtils {

	public static BigDecimal getBigValue(Map<?, ?> map, Object key, BigDecimal defaultValue) {
		Object obj = MapUtils.getObject(map, key, defaultValue);
		return (BigDecimal) obj;
	}
}
