package com.xzpx_zc.util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtil {
	public static String toXML(Object obj) {
		XStream xtream = new XStream();
		return xtream.toXML(obj);
	}
	public static Object fromXML(String xml) {
		XStream xstream = new XStream(new DomDriver());  
		return xstream.fromXML(xml);
	}
	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setAge(1);
		User user1 = new User();
		user1.setAge(2);
		list.add(user);
		list.add(user1);
		System.out.println(toXML(list));
		List<User> a = (List<User>) fromXML(toXML(list));
		for (User user2 : a) {
			System.out.println(user2.getAge());
		}
		
	}
}
