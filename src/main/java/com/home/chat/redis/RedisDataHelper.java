package com.home.chat.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 读取redis中的数据或者操作redis
 * @author dell
 *
 */
@Slf4j
@Component
public class RedisDataHelper {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate<String,Object> redisTemplate;

	/**
	 *
	 * @param key
	 * @param map
	 * @return [key, map]
	 * @author zhangmj
	 * @date 2023/2/21 19:11
	 * @Description setKey 设置MAP
	 * @version 1.0
	 * @since JDK 1.8
	 **/
	public void setKey(String key, Map<String, Object> map) {
		this.redisTemplate.opsForHash().putAll(key, map);
	}

	/**
	 *
	 * @param key
	 * @return [key]
	 * @author zhangmj
	 * @date 2023/2/21 19:12
	 * @Description getMapValue 获取MAP对象
	 * @version 1.0
	 * @since JDK 1.8
	 **/
	public Map<Object, Object> getMapValue(String key) {
		return this.redisTemplate.opsForHash().entries(key);
	}

	/**
	 *
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return [key, timeout, unit]
	 * @author zhangmj
	 * @date 2023/2/21 19:13
	 * @Description expire 设置过期时间
	 * @version 1.0
	 * @since JDK 1.8
	 **/
	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 *
	 * @param key
	 * @param hashKey
	 * @return [key, hashKey]
	 * @author zhangmj
	 * @date 2023/2/21 19:13
	 * @Description getValue 获取map中的值
	 * @version 1.0
	 * @since JDK 1.8
	 **/
	public Object getValue(String key, String hashKey) {
		if (!hasKey(key)) {
			return null;
		}
		return this.redisTemplate.opsForHash().get(key, hashKey);
	}

	public <T> T getValue(String key, String hashKey, Class<T> clazz) {
		if (!hasKey(key)) {
			return null;
		}
		String mapJson = JSON.toJSONString(redisTemplate.opsForHash().get(key, hashKey));
		return JSON.parseObject(mapJson, clazz);
	}

	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 *
	 * @param keys
	 * @return [keys]
	 * @author zhangmj
	 * @date 2023/2/21 19:12
	 * @Description deleteData 批量删除KEY
	 * @version 1.0
	 * @since JDK 1.8
	 **/
	public void deleteData(List<String> keys) {
		// 执行批量删除操作时先序列化template
		redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.delete(keys);
	}

	/**
	 *  保存map值到指定key
	 */
	public void hput(String key, String field, Object object){
		redisTemplate.opsForHash().put(key, field, object);
	}

}
