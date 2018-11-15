package com.xzpx_zc.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Jsontil<T> {
	public static JsonGenerator JSONGENERATOR = null;
	public static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	static {
		try {
			JSONGENERATOR = OBJECTMAPPER.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出json字符串处的流，
	 */
	private OutputStream os;
	
	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public static void toJSON(Object data) throws Exception, IOException {
//		User user = new User();
//		user.setAge(1);
//		user.setName("张三");
		JSONGENERATOR.writeObject(data);
		OBJECTMAPPER.writeValue(System.out, data);
	}
	public static Object fromJson(String json, Object targetClass) throws Exception {
		Object obj = null;
		obj = OBJECTMAPPER.readValue(json, targetClass.getClass());
		return obj;
	}
	public static void main(String[] args) throws IOException, Exception {
		User user = new User();
//		user.setAge(1);
//		user.setName("张三");
//		Map map = new HashMap();
//		map.put("name", "我");
//		map.put("key", "12345");
//		List list = new ArrayList();
//		list.add(user);
//		toJSON(list);
		String json = "{\"name\":\"zc\", \"age\": \"1\"}";
		user = (User) fromJson(json, user);
		System.out.println(user.getAge());
		System.out.println(user.getName());
	}
	
}
