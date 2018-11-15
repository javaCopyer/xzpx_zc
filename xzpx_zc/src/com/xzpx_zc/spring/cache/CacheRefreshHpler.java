package com.xzpx_zc.spring.cache;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.concurrent.ConcurrentMapCache;

/**
 * 缓存刷新助手类
* @projectName sptingDemo
* @ClassName: CacheRefreshHpler 
* @Description: TODO
* @author zhangchao
* @date 2017年9月13日 下午2:26:16 
*
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CacheRefreshHpler {
	/*
	 * 存储缓存调用的集合，供定时刷新缓存job使用
	 * ConcurrentMap<time, ConcurrentMap<key, CachedInvocation>>
	 */

	private static final ConcurrentMap<Integer, ConcurrentMap<String, CachedInvocation>> timerCaches =  new ConcurrentHashMap();

	
	public static ConcurrentMap<Integer, ConcurrentMap<String, CachedInvocation>> getTimercaches() {
		return timerCaches;
	}

	/**
	 * 根据时间间隔获取到时间段内的缓存集合
	* @Title: getCacheMapByTime
	* @Description: TODO 
	* @param @param timerInterval
	* @param @return   
	* @return ConcurrentMap<String,CachedInvocation>   
	* @throws
	 */
	public static ConcurrentMap<String, CachedInvocation> getCacheMapByTime(Integer timerInterval) {
		return timerCaches.get(timerInterval);
	}
	
/**
 * 	添加唯一key缓存调用到缓存集合中		
* @Title: putCachedInvocation
* @Description: TODO 
* @param @param timerInterval 时间间隔
* @param @param key			   缓存唯一key
* @param @param cachedInvocation  缓存调用  
* @return void   
* @throws
 */
	public static void putCachedInvocation(int timerInterval, String key, CachedInvocation cachedInvocation) {
		ConcurrentMap map = getCacheMapByTime(timerInterval);
		if(map == null) {
			map = new ConcurrentHashMap();
			timerCaches.put(timerInterval, map);
		}
		map.put(key, cachedInvocation);
		
	}
}
