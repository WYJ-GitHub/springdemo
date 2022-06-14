package springdemo.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存工具类
 */
public class RedisCacheUtil {

	private static RedisTemplate<String, Object> redisTemplate;

	/**
	 * 两小时过期
	 */
	public static final long SESSION_EXPIRE_TIME = 60 * 60 * 2;

	public static void init(RedisTemplate<String, Object> rT) {
		Assert.notNull(rT, "redisTemplate 初始化参数不能为空");
		redisTemplate = rT;
	}

	/**
	 * 特定方法：指定通过token获取admin
	 *
	 * @param token
	 * @return
	 */
	public static <T> T getAdminByToken(String token, Class<T> clazz) {
		T admin = getToObject(token, clazz);
		if (Objects.isNull(admin)) {
			return null;
		}
		set(token, admin, SESSION_EXPIRE_TIME);
		return admin;
	}


	/**
	 * 特定方法：通过token添加账户--->默认60分钟有效期
	 *
	 * @param token
	 * @return
	 */
	public static void addAdminByToken(String token, Object admin) {
		if (null == admin) {
			return;
		}
		set(token, admin, SESSION_EXPIRE_TIME);
	}


	/**
	 * 普通缓存获取:可以转为指定对象
	 *
	 * @param key 键
	 * @return 值
	 */
	public static <T> T getToObject(String key, Class<T> clazz) {
		if (StringUtil.isBlank(key)) {
			return null;
		}

		Object o = redisTemplate.opsForValue().get(key);
		if (null != o) {
			try {
				return SingletonObject.OBJECT_MAPPER.readValue(StringUtil.toJson(o), clazz);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return null;
	}

	/**
	 * 指定缓存失效时间
	 *
	 * @param key  键
	 * @param time 时间(秒)
	 */
	public static void expire(String key, long time) {
		Assert.notNull(time, "时间不能为空");
		Assert.hasText(key, "key 不能为空");

		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key 获取过期时间
	 *
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public static long getExpire(String key) {
		Assert.hasText(key, "key 不能为空");

		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 *
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public static boolean hasKey(String key) {
		Assert.hasText(key, "key 不能为空");

		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除缓存
	 *
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public static void del(String... key) {
		Assert.notNull(key, "key 不能为空");

		if (key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	// ============================String=============================


	/**
	 * 普通缓存获取
	 *
	 * @param key 键
	 * @return 值
	 */
	public static Object get(String key) {
		Assert.hasText(key, "key 不能为空");

		return redisTemplate.opsForValue().get(key);
	}


	/**
	 * 普通缓存放入
	 *
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public static boolean set(String key, Object value) {
		if (null == key || null == value) {
			return false;
		}

		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public static boolean set(String key, Object value, long time) {
		if (null == key || null == value) {
			return false;
		}

		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 递增
	 *
	 * @param key   键
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public static long incr(String key, long delta) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(delta, "delta 不能为空");

		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 *
	 * @param key   键
	 * @param delta 要减少几(小于0)
	 * @return
	 */
	public static long decr(String key, long delta) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(delta, "delta 不能为空");

		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	// ================================Map=================================

	/**
	 * HashGet
	 *
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public static Object hget(String key, String item) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");

		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public static Map<Object, Object> hmget(String key) {
		Assert.hasText(key, "key 不能为空");

		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 *
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public static boolean hmset(String key, Map<String, Object> map) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(map, "map 不能为空");

		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * HashSet 并设置时间
	 *
	 * @param key  键
	 * @param map  对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
	 */
	public static boolean hmset(String key, Map<String, Object> map, long time) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(map, "map 不能为空");
		Assert.notNull(time, "time 不能为空");

		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @return true 成功 false失败
	 */
	public static boolean hset(String key, String item, Object value) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");
		Assert.notNull(value, "value 不能为空");

		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
	 */
	public static boolean hset(String key, String item, Object value, long time) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");
		Assert.notNull(value, "value 不能为空");
		Assert.notNull(time, "time 不能为空");

		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除hash表中的值
	 *
	 * @param key  键 不能为null
	 * @param item 项 可以使多个 不能为null
	 */
	public static void hdel(String key, Object... item) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(item, "item 不能为空");

		redisTemplate.opsForHash().delete(key, item);
	}

	/**
	 * 判断hash表中是否有该项的值
	 *
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return true 存在 false不存在
	 */
	public static boolean hHasKey(String key, String item) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");

		return redisTemplate.opsForHash().hasKey(key, item);
	}

	/**
	 * hash递增 如果不存在,就会创建一个 并把新增后的值返回
	 *
	 * @param key  键
	 * @param item 项
	 * @param num  要增加几(大于0)
	 * @return
	 */
	public static double hincr(String key, String item, double num) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");
		Assert.notNull(num, "num 不能为空");

		return redisTemplate.opsForHash().increment(key, item, num);
	}

	/**
	 * hash递减
	 *
	 * @param key  键
	 * @param item 项
	 * @param num  要减少记(小于0)
	 * @return
	 */
	public static double hdecr(String key, String item, double num) {
		Assert.hasText(key, "key 不能为空");
		Assert.hasText(item, "item 不能为空");
		Assert.notNull(num, "num 不能为空");

		return redisTemplate.opsForHash().increment(key, item, -num);
	}

	public static Set<String> keys(String s) {
		return redisTemplate.keys(s);
	}
	// ============================set=============================

	/**
	 * 根据key获取Set中的所有值
	 *
	 * @param key 键
	 * @return
	 */
	public Set<Object> sGet(String key) {
		Assert.hasText(key, "key 不能为空");

		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据value从一个set中查询,是否存在
	 *
	 * @param key   键
	 * @param value 值
	 * @return true 存在 false不存在
	 */
	public static boolean sHasKey(String key, Object value) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(value, "value 不能为空");

		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将数据放入set缓存
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public static long sSet(String key, Object... values) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(values, "values 不能为空");

		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 将set数据放入缓存
	 *
	 * @param key    键
	 * @param time   时间(秒)
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public static long sSetAndTime(String key, long time, Object... values) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(time, "time 不能为空");
		Assert.notNull(values, "values 不能为空");

		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0) {
				expire(key, time);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取set缓存的长度
	 *
	 * @param key 键
	 * @return
	 */
	public static long sGetSetSize(String key) {
		Assert.hasText(key, "key 不能为空");

		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 移除值为value的
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 移除的个数
	 */
	public static long setRemove(String key, Object... values) {
		Assert.hasText(key, "key 不能为空");
		Assert.notNull(values, "values 不能为空");

		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
