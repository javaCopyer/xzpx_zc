package com.xzpx_zc.log;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class Log implements Serializable{
	private static final long serialVersionUID = 8955992824006024295L;
	private long time; //请求时间
	private String ip; //请求用户ip
	private HttpServletRequest request; //用户请求
	private String targetClassName; //请求的目标类名称 
	private String targetMethoodName; //请求的目标方法名称
	private String targetMethodDesc;   //请求的方法描述
	private Exception exception;    //异常
	private Object result;     //返回值
	
	
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
		
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getTargetClassName() {
		return targetClassName;
	}
	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
	public String getTargetMethoodName() {
		return targetMethoodName;
	}
	public void setTargetMethoodName(String targetMethoodName) {
		this.targetMethoodName = targetMethoodName;
	}
	public String getTargetMethodDesc() {
		return targetMethodDesc;
	}
	public void setTargetMethodDesc(String targetMethodDesc) {
		this.targetMethodDesc = targetMethodDesc;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String getReqeustParamAndValue() {
		Enumeration<String> names = this.getRequest().getParameterNames();
		StringBuffer stringBuffer = new StringBuffer();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String[] value = this.getRequest().getParameterValues(name);
			stringBuffer.append(name);
			stringBuffer.append(":");
			stringBuffer.append(Arrays.toString(value));
			if(names.hasMoreElements())  
				stringBuffer.append(System.getProperty("line.separator"));
		}
		return stringBuffer.toString();
	}
	private String getExceptionStacktrace() {
		if (this.exception == null){
	       return "无异常";
		}
//		StackTraceElement[] stackTraceElements = this.exception.getStackTrace();
//		StringBuffer buffer = new StringBuffer();
//		for (StackTraceElement stackTraceElement : stackTraceElements) {
//			buffer.append(stackTraceElement);
//			buffer.append("\n");
//		}
//		return buffer.toString();
		StringWriter stringWriter = new StringWriter();
	    this.exception.printStackTrace(new PrintWriter(stringWriter));
	    return stringWriter.toString();
	}
	@Override
	public String toString() {
		String line = System.getProperty("line.separator");
		return "[请求时间]：" + this.getTime() + line +
				"[请求地址]：" + this.getRequest().getRequestURI() + line +
				"[请求方式]:" + this.getRequest().getMethod() + line +
				"[Controller]：" + targetClassName + line +
			   "[methood]：" + targetMethoodName + line +
			   "[方法描述]：" + targetMethodDesc + line +
			   "[请求]：" + this.getReqeustParamAndValue() + line +
			   "[返回值]：" + this.getResult() + line + 
			   "[异常]:" + this.getExceptionStacktrace() + line;
		
	}
}
