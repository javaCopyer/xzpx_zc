package com.xzpx_zc.core;

public class RootPath {
	public static String getPath() {
		String path = "";
		path = RootPath.class.getClassLoader().getResource("").getPath();
		return path;
	}
}
