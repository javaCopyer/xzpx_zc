package com.xzpx_zc.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Producer;
import com.xzpx_zc.pojo.ZcNav;
import com.xzpx_zc.util.ExcelUtil;
@Controller
public class CommonController extends BaseComtroller{
	@Resource
	private Producer kaptchaProducer;


	public Producer getKaptchaProducer() {
		return kaptchaProducer;
	}


	public void setKaptchaProducer(Producer kaptchaProducer) {
		this.kaptchaProducer = kaptchaProducer;
	}

	@RequestMapping(value="/testDownload1", method={RequestMethod.GET, RequestMethod.POST})
	public void testDowmLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.reset();
		response.setContentType("APPLICATION/OCTET-STREAM");  
		response.setHeader("Content-Disposition", "attachment; filename="  + URLEncoder.encode("张超.txt", "utf-8"));  
		String result = "张超aaa\r\n\t哈哈";
		ServletOutputStream os = response.getOutputStream();
		os.write(result.getBytes());
		os.close();
	}
	@RequestMapping(value="/testDownload2", method={RequestMethod.GET, RequestMethod.POST})
	  public ResponseEntity<byte[]> export() throws IOException {  
	        HttpHeaders headers = new HttpHeaders();    
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
	        headers.setContentDispositionFormData("attachment", "ass.txt");    
	        return new ResponseEntity<byte[]>("张超".getBytes(), headers, HttpStatus.CREATED);    
	    }  
	@RequestMapping(value="/getImageCode", method={RequestMethod.GET, RequestMethod.POST})
	public void getImageCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		response.reset();
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		String capText = kaptchaProducer.createText();
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		session.setAttribute("imageCode", capText);
		session.setAttribute("imageCodeDate", new Date());
		out.close();
	}
	
	
	@RequestMapping(value="/getExcel", method={RequestMethod.GET, RequestMethod.POST})
	public void getExcel(HttpServletResponse response) throws IOException  {
		response.reset(); 
		response.setContentType("APPLICATION/OCTET-STREAM");  
		response.setHeader("Content-Disposition", "attachment; filename="  + URLEncoder.encode("张超.xls", "UTF-8"));  
		ExcelUtil util = new ExcelUtil();
		String[] head = {"姓名", "年龄"};
		LinkedHashMap map = new LinkedHashMap();
		map.put("姓名", "张三");
		map.put("年龄", "13");
		List<LinkedHashMap<String, Object>> list = new LinkedList<LinkedHashMap<String, Object>>();
		list.add(map);
		util.setSheetName("张超").setList(list).setHead(head).setOs(response.getOutputStream()).exportDate();
	}
	@ModelAttribute
	public Model returnModel(Model model) {
System.out.println("returnModel");		
		ZcNav nav = new ZcNav();
		nav.setNavName("新下1");
		model.addAttribute("nav1", nav);
		return model;
	}
	@RequestMapping(value="testModel")
	public Model testModelAttribute(Model model) {
		ZcNav nav = new ZcNav();
		nav.setNavName("新下2");
		model.addAttribute("nav2", nav);
		return model;
	}
}
