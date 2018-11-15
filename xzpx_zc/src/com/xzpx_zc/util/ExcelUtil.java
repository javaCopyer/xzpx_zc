package com.xzpx_zc.util;

import java.io.ByteArrayInputStream;







import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * EXCEL锟斤拷锟斤拷锟斤拷
 * 说锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟绞癸拷玫锟斤拷锟斤拷锟絃ist<LinkedHashMap>锟斤拷锟斤拷萁峁�LinkedHashMap锟结按锟斤拷put锟斤拷顺锟斤拷锟斤拷锟斤拷锟斤拷锟�
 * @author zhangchao
 *
 */
public class ExcelUtil {
	private OutputStream os;
	private InputStream is;
	private String sheetName;
	private String [] head;
	private List<LinkedHashMap<String, Object>> list;
	public String getSheetName() {
		return sheetName;
	}
	public ExcelUtil setSheetName(String sheetName) {
		this.sheetName = sheetName;
		return this;
	}
	public String[] getHead() {
		return head;
	}
	public ExcelUtil setHead(String[] head) {
		this.head = head;
		return this;
	}
	public List<LinkedHashMap<String, Object>> getList() {
		return list;
	}
	public ExcelUtil setList(List<LinkedHashMap<String, Object>> list) {
		this.list = list;
		return this;
	}
	
	public OutputStream getOs() {
		return os;
	}
	public ExcelUtil setOs(OutputStream os) {
		this.os = os;
		return this;
	}
	
	public InputStream getIs() {
		return is;
	}
	public ExcelUtil setIs(InputStream is) {
		this.is = is;
		return this;
	}
	public String[] getMapKeys() {
		String [] keys = null;
		String allKeys = "";
		if(list != null && list.size() > 0) {
			Map<String, Object> map = this.getList().get(0);
			Iterator<String> iterator = map.keySet().iterator();
			while(iterator.hasNext()) {
				if(iterator.hasNext())
					allKeys += iterator.next() + ",";
				else
					allKeys += iterator.next();
			}
			keys = allKeys.split(",");
		}
		else {
			keys = new String[0];
		}
		
		return keys;
	}
	public void test() {
		for(String s : this.getMapKeys()) {
			System.out.println(s);
		}
	}
	
	//锟斤拷excel锟斤拷锟斤拷锟斤拷转锟斤拷为List锟斤拷锟斤拷
	public List<List<String>> importDate() throws IOException {
		List<List<String>> list = new LinkedList<List<String>>();
	    Workbook workbook = null;
	    byte[] data = new byte[this.is.available()];
	    this.is.read(data);
	    this.is.close();
	    try {
	      workbook = new HSSFWorkbook(new ByteArrayInputStream(data));   //xls
	    }
	    catch (OfficeXmlFileException e) {
	      workbook = new XSSFWorkbook(new ByteArrayInputStream(data)); //xlsx
	    }
	    //锟斤拷锟斤拷锟斤拷锟叫碉拷sheet
	    for (int i = 0, len1 = workbook.getNumberOfSheets(); i < len1; i++) {
	      Sheet sheet = workbook.getSheetAt(i);
	      if (sheet == null)
	        continue;
	    //锟斤拷锟斤拷锟斤拷锟叫碉拷锟斤拷
	      for (int j = 0, len2 = sheet.getLastRowNum(); j <= len2; j++) {
	        List<String> subList = new LinkedList<String>();
	        Row row = sheet.getRow(j);
	        if (row == null)
	          continue;
	        for (int k = 0, len3 = row.getLastCellNum(); k < len3; k++) {
	          Cell cell = row.getCell(k);
	          if (cell == null)
	            continue;
	          String value = cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? Double.toString(cell.getNumericCellValue()) : cell.getStringCellValue();
	          subList.add(value.trim());
	        }
	        list.add(subList);
	      }
	    }
	    return list;
	}
	
	//锟斤拷莸锟斤拷锟轿猠xcel
	public InputStream exportDate() throws IOException {
		String[] keys = this.getMapKeys();
		for(String s : keys) {
			System.out.println(s);
		}
		Workbook workBook = new HSSFWorkbook();
		Sheet sheet = workBook.createSheet(sheetName);
		Row row = sheet.createRow(0);
		if(head != null) {
			for(int i=0; i<head.length; i++) {
				row.createCell(i).setCellValue(head[i]);
			}
		} else {
			for(int i=0; i<keys.length; i++) {
				row.createCell(i).setCellValue(keys[i]);
			}
		}
		//锟斤拷锟斤拷锟斤拷锟�
		for(int i=0; i<this.getList().size(); i++) {
			row = sheet.createRow(i+1);
			for(int j=0; j<keys.length; j++) {
				//System.out.println(keys[j]);
				row.createCell(j).setCellValue(this.getList().get(i).get(keys[j]).toString());
			}
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workBook.write(os);
		workBook.write(bos);
		byte[] bytes = bos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return bis;
		//this.os.close();
		//return this.getList().size();
	}
}
 