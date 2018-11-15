package com.xzpx_zc.spring.cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * spring cache缓存的调用的封装
* @projectName sptingDemo
* @ClassName: CachedInvocation 
* @Description: TODO
* @author zhangchao
* @date 2017年9月13日 上午12:42:14 
*
 */
public class CachedInvocation {
	private Object target;
	private Method method;
	private Object[] args;
	private String[] cacheNames;
	private boolean isNew;
	public CachedInvocation(Object target, Method method, Object[] args, String[] cacheNames) {
		super();
		this.target = target;
		this.method = method;
		this.args = args;
		this.cacheNames = cacheNames;
		isNew = true;
	}
	
	//get and setter
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public Object getTarget() {
		return target;
	}


	public void setTarget(Object target) {
		this.target = target;
	}


	public Method getMethod() {
		return method;
	}


	public void setMethod(Method method) {
		this.method = method;
	}


	public Object[] getArgs() {
		return args;
	}


	public void setArgs(Object[] args) {
		this.args = args;
	}


	public String[] getCacheNames() {
		return cacheNames;
	}


	public void setCacheNames(String[] cacheNames) {
		this.cacheNames = cacheNames;
	}


	/**
	 * 缓存方法的调用
	* @Title: invoke
	* @Description: TODO 
	* @param @return
	* @param @throws IllegalAccessException
	* @param @throws IllegalArgumentException
	* @param @throws InvocationTargetException   
	* @return Object   
	* @throws
	 */
	public Object invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return this.method.invoke(target, args);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return target.getClass().getName() + ":" + method.getName();
	}
}
