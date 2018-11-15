package com.xzpx_zc.spring.aop;

import java.lang.reflect.Method;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xzpx_zc.log.Log;
import com.xzpx_zc.spring.annotation.ControllerAnnotation;

/**
 * 使用AOP拦截controller中的方法.打印日志
* @projectName sptingDemo
* @ClassName: ControllerAOP 
* @Description: TODO
* @author zhangchao
* @date 2017年9月1日 下午2:45:08 
*
 */
@SuppressWarnings("rawtypes")
@Aspect
public class ControllerAOP {
	private static Logger logger = Logger.getLogger("request2");
	@Pointcut("execution(* com.xzpx_zc.controller.*.*(..))")
	public void controllerAOP() {};
	
	/**
	 * 返回通知	在目标方法正常结束之后返回(目标方法执行出现异常时不再执行) 
 * 返回通知可以访问目标方法的执行结果 
	 * @param joinPoint
	 * @param result  返回值
	 */
	@AfterReturning(value="controllerAOP()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  //spring封装的request请求
		Class clazz = joinPoint.getTarget().getClass();  //目标类
		String targetMethodName = joinPoint.getSignature().getName(); //目标方法 
		String targetClassName = clazz.getName();   //目标类名称
		Method[] methods = clazz.getMethods();     //目标类内所有的方法
		String targetMethodDesc = "";			   //目标方法的注解描述			
		for (Method method : methods) {
			if(method.getName().equals(targetMethodName)) {
				ControllerAnnotation controllerAnnotation = method.getAnnotation(ControllerAnnotation.class);
				targetMethodDesc = controllerAnnotation == null ? "未添加" : controllerAnnotation.desc();
			}
		}
		//log4j记录日志到request2.log
		Log log = new Log();
		log.setTime(System.currentTimeMillis());
		log.setIp(getUserIP(request));
		log.setException(null);
		log.setRequest(request);
		log.setTargetClassName(targetClassName);
		log.setTargetMethoodName(targetMethodName);
		log.setTargetMethodDesc(targetMethodDesc);
		log.setResult(result);
		logger.debug(log.toString());
	}
	
	/**异常通知
	 * 声明该方法是一个异常通知:在目标方法出现异常时执行此方法 
 * 异常通知可以访问目标方法中的异常对象，且可以指定在出现特定异常时再执行通知代码 
	 * @param joinPoint
	 * @param e  异常
	 */
	@AfterThrowing(value="controllerAOP()",throwing="e")  
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  //spring封装的request请求
		Class clazz = joinPoint.getTarget().getClass();  //目标类
		String targetMethodName = joinPoint.getSignature().getName(); //目标方法 
		String targetClassName = clazz.getName();   //目标类名称
		Method[] methods = clazz.getMethods();     //目标类内所有的方法
		String targetMethodDesc = "";			   //目标方法的注解描述			
		for (Method method : methods) {
			if(method.getName().equals(targetMethodName)) {
				ControllerAnnotation controllerAnnotation = method.getAnnotation(ControllerAnnotation.class);
				targetMethodDesc = controllerAnnotation == null ? "未添加" : controllerAnnotation.desc();
			}
		}
		//log4j记录日志到request2.log
		Log log = new Log();
		log.setTime(System.currentTimeMillis());
		log.setIp(getUserIP(request));
		log.setException(e);
		log.setRequest(request);
		log.setTargetClassName(targetClassName);
		log.setTargetMethoodName(targetMethodName);
		log.setTargetMethodDesc(targetMethodDesc);
		log.setResult(null);
		logger.debug(log.toString());
	}
	
	private String getUserIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}
}
