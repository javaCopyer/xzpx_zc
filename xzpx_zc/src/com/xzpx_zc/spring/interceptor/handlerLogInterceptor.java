package com.xzpx_zc.spring.interceptor;

import java.io.PrintWriter;


import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xzpx_zc.log.Log;
import com.xzpx_zc.spring.annotation.ControllerAnnotation;

/**
 * 使用拦截器记录执行的日志
* @projectName sptingDemo
* @ClassName: handlerLogInterceptor 
* @Description: TODO
* @author zhangchao
* @date 2017年8月28日 下午10:53:51 
*
 */
public class handlerLogInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger("request1");
	/**
	 * 在进入controller方法之前执行的方法 
	 * return true 表示放行  return false表示不放行
	 * 只用这个方法return true才会执行下一个拦截器或者controller的方法
	 * 只要这个方法返回true时才会执行这个拦截器的下面两个方法
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}

	/**
	 * 这个方法是进入到controller方法之后，返回modelAndView之前执行，所以在这个方法中可以对返回的ModelAndView
	 * 进行操作
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	/**
	 * 这个方法是在视图渲染之后执行， 在这个方法中可以做一些释放资源 或者记录日志的操作
	 */
	@SuppressWarnings("rawtypes")
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Class clazz = handlerMethod.getBean().getClass();
		Method[] methods = clazz.getMethods();   //目标类的所有
		String targetClassName = clazz.getName();  //目标类的名称
		String targetMethodName = handlerMethod.getMethod().getName(); //目标方法
		String targetMethodDesc = "";  //目标方法描述
		for (Method method : methods) {
			if(method.getName().equals(targetMethodName)) {
				ControllerAnnotation controllerAnnotation = method.getAnnotation(ControllerAnnotation.class);
				targetMethodDesc = controllerAnnotation == null ? "未添加" : controllerAnnotation.desc();
			}
		}
		
		//记录日志到request1.log
		Log log = new Log();
		log.setTime(System.currentTimeMillis());
		log.setIp(getUserIP(request));
		log.setException(ex);
		log.setRequest(request);
		log.setTargetClassName(targetClassName);
		log.setTargetMethoodName(targetMethodName);
		log.setTargetMethodDesc(targetMethodDesc);
		logger.debug(log.toString());
	}
	
	@SuppressWarnings("unused")
	private String getExceptionStacktrace(Exception ex) {
		if (ex == null){
	       return "";
	    }
	    StringWriter stringWriter = new StringWriter();
	    ex.printStackTrace(new PrintWriter(stringWriter));
	    return stringWriter.toString();
	}
	public String getUserIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}
	
}
