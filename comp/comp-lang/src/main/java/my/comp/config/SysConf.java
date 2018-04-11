package my.comp.config;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取配置信息
 */
public class SysConf {
	private static final String PATH = "SysConf.properties";
	private static final String PATH_EXT = "cfg-wx.properties";
	private static PropertiesConfiguration properties = null;
	private static final Logger logger = LoggerFactory.getLogger(SysConf.class);

	static {
		try {
			properties = new PropertiesConfiguration();
			properties.setEncoding("UTF-8");
			load(PATH);
			load(PATH_EXT);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static void load(String path) throws ConfigurationException {
		String base = Thread.currentThread().getContextClassLoader().getResource("").toString();
		URL url = ConfigurationUtils.locate(base, path);
		logger.debug("加载配置文件{}：{}", path, url);
		if (url != null) properties.load(url);
	}

	protected static void load(String... files) {
		try {
			properties = new PropertiesConfiguration();
			properties.setEncoding("UTF-8");
			for (String file : files) {
				properties.load(file);
			}
			logger.debug("配置文件编码：" + properties.getEncoding());

		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 得到一个整数属性
	 * 
	 * @param key
	 *            String
	 * @return Integer
	 * */
	public static Integer getInt(String key) {
		return properties.getInt(key);
	}

	/**
	 * <p>
	 * 得到一个整数属性
	 * 
	 * @param key
	 *            String
	 * @param defaultVal
	 * 
	 * @return Integer
	 * */
	public static Integer getInt(String key, Integer defaultVal) {
		return properties.getInt(key, defaultVal);
	}

	/**
	 * <p>
	 * 得到一个长整数属性
	 * 
	 * @param key
	 *            String
	 * @return Integer
	 * */
	public static Long getLong(String key) {
		return properties.getLong(key);
	}

	/**
	 * <p>
	 * 从property中得到一个属性
	 * 
	 * @param key
	 *            String
	 * @return String
	 */
	public static String get(String key) {
		return properties.getString(key);
	}

	/**
	 * <p>
	 * 从property中得到一个属性
	 * 
	 * @param key
	 *            String
	 * @return String[]
	 */
	public static String[] getArray(String key) {
		return properties.getStringArray(key);
	}

	/**
	 * <p>
	 * 从property中得到一个属性
	 * 
	 * @param param
	 *            String
	 * @param String
	 *            default value
	 * @return String
	 */
	public String get(String key, String defaultValue) {
		return properties.getString(key, defaultValue);
	}

}
