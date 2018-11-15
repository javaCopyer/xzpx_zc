package com.xzpx_zc.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请求拦截器
* @projectName xzpx_zc
* @ClassName: RequestInterceptor 
* @Description: TODO
* @author zhangchao
* @date 2017年11月2日 下午4:15:42 
*
 */
public class RequestInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse resopnse, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		Object user = session.getAttribute("user");
//		if(user == null)
//			return false;
		return true;
	}

}
