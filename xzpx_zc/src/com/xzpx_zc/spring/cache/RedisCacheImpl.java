package com.xzpx_zc.spring.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
@SuppressWarnings("rawtypes")
/**
 * 基于spring注解的自定义缓存 （redis缓存）
* @projectName sptingDemo
* @ClassName: RedisCacheImpl 
* @Description: TODO
* @author zhangchao
* @date 2017年9月28日 上午10:03:24 
*
 */
public class RedisCacheImpl implements Cache{
	String name;
	RedisTemplate redisTemplate;

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Object getNativeCache() {
		return this;
	}

	public ValueWrapper get(Object key) {
System.out.println(key + "wo de key");
		ValueWrapper valueWrapper = null;
		Object value = redisTemplate.opsForValue().get(key);
		if(value != null)
			valueWrapper = new SimpleValueWrapper(value);
		return valueWrapper;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
		return (T) redisTemplate.opsForValue().get(key);
	}

	@SuppressWarnings("unchecked")
	public void put(Object key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void evict(Object key) {
System.out.println("evict");
		redisTemplate.delete(key);
	}

	@SuppressWarnings("unchecked")
	public void clear() {
System.out.println("clear");
		redisTemplate.delete("*");;
	}

}
