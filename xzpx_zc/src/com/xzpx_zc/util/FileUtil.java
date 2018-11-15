package com.xzpx_zc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
/**
 * 文件工具类
* @projectName zc2017
* @ClassName: FileUtil 
* @Description: TODO
* @author zhangchao
* @date 2017年8月18日 下午3:57:26 
*
 */
public class FileUtil {
	public static List<File> fileList = new LinkedList<>();
	/**
	 * 删除文件夹或者文件
	* @Title: deleteFile
	* @Description: TODO 
	* @param @param file   
	* @return void   
	* @throws
	 */
	public static void deleteFile(File file) {
		File[] files = file.listFiles();
		if(files != null) {
			for (File file2 : files) {
				if(file2.isFile())
					file2.delete();
				else
					deleteFile(file2);
			}
		}
		file.delete();
	}
	/**
	 * 复制文件
	* @Title: copyFile
	* @Description: TODO 
	* @param @param oldFile
	* @param @param newFile
	* @param @throws IOException   
	* @return void   
	* @throws
	 */
	public static void copyFile(File oldFile, File newFile) throws IOException {
		FileInputStream fis = new FileInputStream(oldFile);
		FileOutputStream fos = new FileOutputStream(newFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
	}
	/**
	 * 复制文件夹
	* @Title: copyFolder
	* @Description: TODO 
	* @param @param oldFile
	* @param @param newFile
	* @param @throws Exception   
	* @return void   
	* @throws
	 */
	public static void copyFolder(File oldFile, File newFile) throws Exception {
	    newFile.mkdirs();
	    String[] file = oldFile.list();
	    File temp = null;
	    for (int i = 0; i < file.length; i++) {
	      if (oldFile.getName().endsWith(File.separator))
	        temp = new File(oldFile + file[i]);
	      else
	        temp = new File(oldFile + File.separator + file[i]);
	      if (temp.isFile()) {
	        FileInputStream input = new FileInputStream(temp);
	        FileOutputStream output = new FileOutputStream(newFile + "/" + (temp.getName()).toString());
	        byte[] b = new byte[1024 * 5];
	        int len;
	        while ((len = input.read(b)) != -1)
	          output.write(b, 0, len);
	        output.flush();
	        output.close();
	        input.close();
	      }
	      if (temp.isDirectory())
	        copyFolder(new File(oldFile + "/" + file[i]), new File(newFile + "/" + file[i]));
	    }
	}  
	/**
	 * 遍历文件夹中所有某种格式的文件
	* @Title: getFileList
	* @Description: TODO 
	* @param @param file
	* @param @param endWith
	* @param @return   
	* @return List<File>   
	* @throws
	 */
	public static List<File> getFileList(File file, String endWith) {
		File[] files = file.listFiles();
		if(files != null) {
			for(int i=0; i<files.length; i++) {
				File subFile = files[i];
				if(subFile.isDirectory()) {
					getFileList(subFile, endWith);
				} else if(subFile.getName().endsWith(endWith)) {
					fileList.add(subFile);
				}
			}
		}
		return fileList;
	}
	public static void printFileList(File file, int node) {
		node ++;
		File[] files = file.listFiles();
		if(files != null) {
			for (File f : files) {
				for(int i=0; i<node; i++) {
					if(i == (node - 1)) {
						System.out.print(" |-");
					} else {
						System.out.print("  ");
					}
				}
				System.out.println(f.getName());
				printFileList(f, node);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		printFileList(new File("F:\\JAVA\\jquery测试"), 0);
	}
}
