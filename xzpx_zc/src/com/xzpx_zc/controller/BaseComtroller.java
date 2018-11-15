package com.xzpx_zc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * 基础controller
* @projectName xzpx_zc
* @ClassName: BaseComtroller 
* @Description: TODO
* @author zhangchao
* @date 2017年12月11日 下午7:21:44 
*
 */
@Controller
public class BaseComtroller {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
System.out.println("baseController");
		this.request = request;
		this.response = response;
	}
}
