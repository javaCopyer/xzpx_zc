package com.xzpx_zc.result;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="result")
public class Result<T> implements Serializable{
	private static final long serialVersionUID = -5345307951708655984L;

	private int code;
	
	private String msg;
	
	private T data;

	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

}
