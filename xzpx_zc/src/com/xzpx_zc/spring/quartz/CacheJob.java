package com.xzpx_zc.spring.quartz;

import java.util.Calendar;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xzpx_zc.spring.cache.CacheRefreshHpler;
import com.xzpx_zc.spring.cache.CachedInvocation;
/**
 *定时刷新缓存的人任务类
* @ClassName: CacheJob 
* @Description: TODO
* @author zhangchao
* @date 2017年10月13日 上午10:51:50 
*
 */
public class CacheJob extends QuartzJobBean {
	Logger logger = Logger.getLogger(CacheJob.class);
	CacheManager cacheManager;
	private static final ConcurrentMap<Long, ConcurrentMap<String, CachedInvocation>> maps = new ConcurrentHashMap<Long, ConcurrentMap<String, CachedInvocation>>();
	// {刷新缓存时间戳  | 缓存}
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
System.out.println("刷新缓存开始。。。。");
	for(Map.Entry<Long, ConcurrentMap<String, CachedInvocation>> entrys : maps.entrySet()) {
		long endTime = entrys.getKey();
System.out.println("endTime:" + endTime);		
		ConcurrentMap<String, CachedInvocation> caches = entrys.getValue();
		long nowTime = System.currentTimeMillis();
System.out.println("nowTime:" + nowTime);			
		if(endTime <= nowTime) {
			for(Map.Entry<String, CachedInvocation> cacheEntry : caches.entrySet()) {
				String key = cacheEntry.getKey();
				CachedInvocation cachedInvocation = cacheEntry.getValue();
				String[] cahceNames = cachedInvocation.getCacheNames();
				try {
					Object result = cachedInvocation.invoke();  //反射执行缓存的方法获取新的数据
					Cache cache = cacheManager.getCache(cahceNames[0]);  //获取缓存
					//从新设置缓存
					if(cache != null) {
						cache.evict(key);  //删除key的缓存
System.out.println("evit");
						cache.put(key, result);
System.out.println("put");
						//将CachedInvocation的new属性设置为true， 这样可以重新设置刷新的时间戳加进maps中
						for(ConcurrentMap<String, CachedInvocation> cachedInvocations : CacheRefreshHpler.getTimercaches().values()) {
							CachedInvocation invocation = cachedInvocations.get(key);
							if(invocation != null) 
								invocation.setNew(true);
						}
					}
						
					} catch(Exception e) {
						e.printStackTrace();
				}
			}
			maps.remove(endTime);
		}
		
	}

System.out.println("遍历缓存集合开始。。。。");	
		//遍历缓存集合 ， 将刷新时间转换为时间戳加进maps中
		ConcurrentMap<Integer, ConcurrentMap<String, CachedInvocation>> timerCaches = CacheRefreshHpler.getTimercaches(); //缓存集合
		for(Map.Entry<Integer, ConcurrentMap<String, CachedInvocation>> entrys : timerCaches.entrySet()) {
			int interval = entrys.getKey();  //刷新间隔时间
			ConcurrentMap<String, CachedInvocation> caches = entrys.getValue(); 
			//遍历缓存的key， 检查这个换成是否已经存入maps
			for(Map.Entry<String, CachedInvocation> cachedInvocations : caches.entrySet()) {
				String key = cachedInvocations.getKey();
System.out.println("cacheJobKey" + key);				
				CachedInvocation cachedInvocation = cachedInvocations.getValue();
System.out.println("isNew前：" + cachedInvocation.isNew());				
				if(cachedInvocation.isNew()) {
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.SECOND, interval);
					long endTime = calendar.getTimeInMillis();
					cachedInvocation.setNew(false);
System.out.println("isNew后：" + cachedInvocation.isNew());
					ConcurrentMap<String, CachedInvocation> map = maps.get(endTime);
					if(map == null) {
						map =  new ConcurrentHashMap<String, CachedInvocation>();
						maps.put(endTime, map);
					}
					map.put(key, cachedInvocation);	
				}
			}
		}
	}

	
	
	
	
	
	
	
}
