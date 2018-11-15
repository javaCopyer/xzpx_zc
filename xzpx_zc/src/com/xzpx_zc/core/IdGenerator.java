package com.xzpx_zc.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IdGenerator {
	private static long id;
	public static synchronized long getNextId() throws IOException {
		String path = RootPath.getPath() + "id.txt";
		System.out.println(path);
		if(id == 0) {
			BufferedReader br = new BufferedReader(new FileReader(path));
			id = Long.parseLong(br.readLine());
			br.close();
		}
		PrintWriter pw = new PrintWriter(new FileWriter(path));
		pw.print(++id);
		pw.close();
		return id;
	}
	
	
	
}
