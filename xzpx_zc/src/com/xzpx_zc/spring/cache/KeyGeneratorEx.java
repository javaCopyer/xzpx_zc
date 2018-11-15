package com.xzpx_zc.spring.cache;
/**
 * 创建人：zhangchao
 * 类说明：spring cache自定义key的唯一值
 */
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.annotation.Cacheable;

@SuppressWarnings("deprecation")
public class KeyGeneratorEx extends org.springframework.cache.interceptor.DefaultKeyGenerator{
	/**
	 * 方法描述：生成唯一key
	 */
	public Object generate(Object target, Method method, Object... params) {
		//调用父类方法根据入参获取缓存的key
		Object key = super.generate(target, method, params);
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(target.hashCode() + "." + method.hashCode());
		keyBuilder.append("["+ key +"]");
		String uniqueKey = keyBuilder.toString();   //key : classHashCode.methodHashCode[super.key]
System.out.println(uniqueKey);
		Method methodImpl = null;
		try {
			//入参的method为声明的接口方法， 这里获取实现类target的method对象，
			methodImpl = target.getClass().getMethod(method.getName(), method.getParameterTypes());
			CacheTimer cacheTimer = methodImpl.getAnnotation(CacheTimer.class);
			//如果存在缓存注解
			if(cacheTimer != null) {
				int interval = cacheTimer.interval();
				ConcurrentMap<String, CachedInvocation> cachedInvocations = CacheRefreshHpler.getCacheMapByTime(interval);
				//如果指定时间间隔内的缓存Map为空， 或者缓存Map中没有该缓存Key，  则将方法的缓存放进去
				if(cachedInvocations == null || !cachedInvocations.containsKey(uniqueKey)) {
					Cacheable cacheable = methodImpl.getAnnotation(Cacheable.class);
					String[] cacheNames = null;
					if(cacheable != null)
						cacheNames = cacheable.value();
System.out.println(cacheNames);					
					CachedInvocation cachedInvocation = new CachedInvocation(target, methodImpl, params, cacheNames);
System.out.println("isNew:" + cachedInvocation.isNew());					
					CacheRefreshHpler.putCachedInvocation(interval, uniqueKey, cachedInvocation);
System.out.println(interval);				
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		
//		Cacheable cacheable = method.getAnnotation(Cacheable.class);
//		System.out.println(Arrays.toString(cacheable.value()));;
//		System.out.println(key);
//		System.out.println(uniqueKey);
		return uniqueKey;
	}



}
