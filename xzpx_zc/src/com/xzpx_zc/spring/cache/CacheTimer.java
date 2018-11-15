package com.xzpx_zc.spring.cache;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
/**
 * 缓存刷新时间的注解， 必须指定一个int值， 单位分钟
* @projectName sptingDemo
* @ClassName: CacheTimer 
* @Description: TODO
* @author zhangchao
* @date 2017年10月20日 下午4:39:48 
*
 */
public @interface CacheTimer {
	public int interval() default 0;
}
