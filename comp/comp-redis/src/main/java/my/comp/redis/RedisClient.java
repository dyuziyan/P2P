package my.comp.redis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class RedisClient {
	private static Log logger = LogFactory.getLog(RedisClient.class);

	/**
	 * 最大连接数
	 */
	private static final int MAX_TOTAL = 300;
	/**
	 * 最大空闲连接数
	 */
	private static final int MAX_IDLE = 1;
	/**
	 * 连接时的最大等待毫秒数
	 */
	private static final int MAX_WAIT = 3000;
	/**
	 * IP地址
	 */
	private String IP = "192.168.1.148";
	/**
	 * 端口
	 */
	private int PORT = 6379;

	/**
	 * 密码
	 */
	private String PASSWORD = "";// adzs@#161010

	public RedisClient(String ip, int port) {
		IP = ip;
		PORT = port;
		init();
	}

	public RedisClient(String ip, int port, String password) {
		IP = ip;
		PORT = port;
		PASSWORD = password;
		init();
	}

	/**
	 * 切片链接池
	 */
	private static ShardedJedisPool shardedJedisPool;

	public void init() {
		// 创建jedis池配置实例
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置池配置项值
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT);
		// 根据配置实例化jedis池
		List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
		JedisShardInfo jedisShardInfo = new JedisShardInfo(IP, PORT);
		if (null != PASSWORD && !"".equals(PASSWORD)) {
			jedisShardInfo.setPassword(PASSWORD);
		}
		jdsInfoList.add(jedisShardInfo);
		shardedJedisPool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
				Sharded.DEFAULT_KEY_TAG_PATTERN);
	}

	/**
	 * 保存对象
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:56:12
	 * @param key
	 * @param value
	 * @param timeout
	 *            超时时间，单位为秒
	 */
	public void setObject(String key, Object value, int timeout) {
		ShardedJedis jedis = null;
		if (key == null || value == null) {
			return;
		}
		byte[] val = SerializeUtil.serialize(value);
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			jedis.set(key.getBytes(), val);
			jedis.expire(key, timeout);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【异常提示信息】" + e, e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 保存对象
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:56:53
	 * @param key
	 * @param value
	 */
	public <T> void setObject(String key, T value) {
		ShardedJedis jedis = null;
		if (key == null || value == null) {
			return;
		}
		byte[] val = SerializeUtil.serialize(value);
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			jedis.set(key.getBytes(), val);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e, e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 根据Key获取对象
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:57:03
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getObject(String key) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			// 获取jedis实例后可以对redis服务进行一系列的操作
			byte[] value = jedis.get(key.getBytes());
			Object data = SerializeUtil.unSerialize(value);
			return (T) data;
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 设置String值
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:57:14
	 * @param key
	 * @param value
	 * @param timeout
	 *            超时时间，单位为秒
	 */
	public void setString(String key, String value, int timeout) {
		ShardedJedis jedis = null;
		if (key == null || value == null) {
			return;
		}
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
			jedis.expire(key, timeout);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【异常提示信息】" + e, e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置String值
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:57:37
	 * @param key
	 * @param value
	 */
	public void setString(String key, String value) {
		ShardedJedis jedis = null;
		if (key == null || value == null) {
			return;
		}
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("【异常提示信息】" + e, e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 获取String值
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:57:47
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @author zhouxiaojun
	 * @date 2017年5月5日下午5:57:55
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 
	 * 描述：保存map对象
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:12:04
	 * @param key
	 * @param map
	 * @return
	 */
	public String setMap(String key, Map<String, String> map) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.hmset(key, map);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * 描述：获取map对象
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:11:50
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * 描述：想list头部添加值
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:22:13
	 * @param key
	 * @param str
	 * @return
	 */
	public long setList(String key, String... str) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.rpush(key, str);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1l;
	}

	/**
	 * 
	 * 描述：获取list的数据
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:23:08
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> getList(String key, int start, int end) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * 描述：删除list
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:32:14
	 * @param key
	 * @return
	 */
	public long delList(String key) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1l;
	}

	/**
	 * 
	 * 描述：获取jedis实例
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月27日下午4:34:11
	 * @return
	 */
	public ShardedJedis getShardedJedis() {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		}
		return jedis;
	}

	/**
	 * 
	 * 描述：向有序集合添加一个或多个成员，或者更新已存在成员的分数
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月28日下午3:19:09
	 * @param key
	 * @param score
	 * @param value
	 */
	public <T> long setZAddSet(String key, double score, T value) {
		ShardedJedis jedis = null;
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			byte[] val = SerializeUtil.serialize(value);
			return jedis.zadd(key.getBytes(), score, val);
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1l;
	}

	/**
	 * 
	 * 描述：通过分数返回有序集合指定区间内的成员
	 * 
	 * @author xiaojun.zhou
	 * @date 2017年6月28日下午3:17:44
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Set<T> getSetByScore(String key, double min, double max) {

		ShardedJedis jedis = null;
		Set<T> datas = new HashSet<T>();
		try {
			// 从jedis池中获取一个jedis实例
			jedis = shardedJedisPool.getResource();
			Set<byte[]> set1 = jedis.zrangeByScore(key.getBytes(), min, max);
			for (byte[] by : set1) {
				Object data = SerializeUtil.unSerialize(by);
				datas.add((T) data);
			}
		} catch (Exception e) {
			logger.error("【异常提示信息】" + e);
		} finally {
			// 释放对象池，即获取jedis实例使用后要将对象还回去
			if (jedis != null) {
				jedis.close();
			}
		}
		return datas;
	}

	public static void main(String[] args) throws ParseException {
		RedisClient client = new RedisClient("192.168.1.149", 6379);
		// client.setString("a", "ad你好");
		// System.out.println(client.getString("a"));
		//
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("aaa", "aaa");
		// map.put("bbb", "bbb");
		// map.put("ccc", "ccc");
		//
		// System.out.println(client.setMap("testmpa", map));
		// System.out.println(client.getMap("testmpa"));
		//
		// client.setObject("abc", map);
		// Map<String, String> map1 = client.getObject("abc");
		// System.out.println(map1);
		//
		// long num = client.setList("bbb", "a", "b", "c");
		// System.out.println("num -- >" + num);
		//
		// System.out.println(client.getList("bbb", 0, 100));

		DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = YYYY_MM_DD_MM_HH_SS.parse("2017-06-28 15:12:12");
		Date date2 = YYYY_MM_DD_MM_HH_SS.parse("2017-06-29 15:12:12");
		Date date3 = YYYY_MM_DD_MM_HH_SS.parse("2017-06-25 15:12:12");
		Date date4 = YYYY_MM_DD_MM_HH_SS.parse("2017-06-24 15:12:12");
		Date date5 = YYYY_MM_DD_MM_HH_SS.parse("2017-06-28 15:12:13");

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("time1", date1.getTime());
		map1.put("ip", "192.168.1.121");
		map1.put("name", "小米1");
		client.setZAddSet("testsort7", date1.getTime(), map1);

		map1.put("time1", date2.getTime());
		map1.put("ip", "192.168.1.122");
		map1.put("name", "小米2");
		client.setZAddSet("testsort7", date2.getTime(), map1);

		map1.put("time1", date3.getTime());
		map1.put("ip", "192.168.1.123");
		map1.put("name", "小米3");
		client.setZAddSet("testsort7", date3.getTime(), map1);

		map1.put("time1", date4.getTime());
		map1.put("ip", "192.168.1.124");
		map1.put("name", "小米4");
		System.out.println(client.setZAddSet("testsort7", date4.getTime(), map1));

		map1.put("time1", date5.getTime());
		map1.put("ip", "192.168.1.124");
		map1.put("name", "小米5");
		System.out.println(client.setZAddSet("testsort7", date5.getTime(), map1));

		Set<Map<String, Object>> datas = client.getSetByScore("testsort7", date4.getTime(), date2.getTime());
		for (Map<String, Object> data : datas) {
			System.out.println(data);
		}

		System.out.println(client.getShardedJedis().zcard("testsort7"));
	}
}
