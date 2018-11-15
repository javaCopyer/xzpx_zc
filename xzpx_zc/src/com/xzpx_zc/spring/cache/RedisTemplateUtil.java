package com.xzpx_zc.spring.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

@SuppressWarnings({"rawtypes","unchecked"})
public class RedisTemplateUtil {
	private RedisTemplate redisTemplate;
	
	
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}


	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	/**
	 *删除指定key的缓存 
	 */
	public void delete(String ... keys) throws Exception{
		if(keys != null && keys.length == 1)
			redisTemplate.delete(keys[0]);
		if(keys != null && keys.length > 1) 
			redisTemplate.delete(CollectionUtils.arrayToList(keys));
	}
	/**
	 *添加缓存
	 *key键
	 *value值 
	 */
	public void setValue(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * timeout : 缓存过期时间 
	 * timeOutType : 过期时间单位
	 */
	public void setValue(String key, Object value, Long timeout, TimeUnit unit) {
		if(timeout == null)
			setValue(key, value);
		else {
			setValue(key, value);
			redisTemplate.expire(key, timeout, unit);
		}
	}
	/**
	 *获取缓存 
	 */
	public Object getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	/**
	 *获取缓存 
	 */
	public <T> T getValue(String key, Class<T> clazz) {
		return (T) redisTemplate.opsForValue().get(key);
	}
	/**
	 * 递增操作 
	 */
	public double increment(String key, double delta) {
		return redisTemplate.opsForValue().increment(key, delta);
		
	}
	
	/**
	 * 递减操作 
	 */
	public double decrement(String key, double delta) {
		return redisTemplate.opsForValue().increment(key, -delta);
	}
	
	/**
	 *存入hash（map）
	 */
	public void hsetAll(String key, Map map) {
		redisTemplate.opsForHash().putAll(key, map);
	}
	/**
	 * 存入hash
	* @Title: hset
	* @Description: TODO 
	* @param @param key    缓存key
	* @param @param field  hash key
	* @param @param value  hash value 
	* @return void   
	* @throws
	 */
	public void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}
	/**
	 * 获取hash （map） 
	 */
	public Map hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}
	/**
	 * 添加队列（正序）
	 */
	public void lpush(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}
	/**
	 * 添加队列
	 */
	public void rpush(String key, Object ... values) {
		if(values != null && values.length == 1)
			redisTemplate.opsForList().leftPush(key, values[0]);
		if(values != null && values.length > 1)
			redisTemplate.opsForList().leftPushAll(key, values);
	}
	/**
	 * 正序获取队列中的value
	 */
	public Object lpop(String key) {
		return redisTemplate.opsForList().leftPop(key);
		
	}
	/**
	 * 反序获取队列中的value 
	 */
	public Object rpop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}
	
}
