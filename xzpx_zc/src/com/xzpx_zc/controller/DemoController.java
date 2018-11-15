package com.xzpx_zc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {

	@RequestMapping(value="/getUsers", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getUsers(HttpServletResponse response, String name) throws IOException {
//		long id = IdGenerator.getNextId();
//		PrintWriter out = response.getWriter();
//		out.println("id:" + id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "张超");
		map.put("age", "11");
		ModelAndView mv = new ModelAndView("demo", map);
		return mv;
	}
	@RequestMapping(value="/getUsers2", method={RequestMethod.POST, RequestMethod.GET})
	public void getUsers2(HttpServletResponse response, HttpSession session, String name) throws IOException {
		String sessionId = session.getId();
		PrintWriter out = response.getWriter();
		out.print("sessionId:" + sessionId + " name: " + session.getAttribute("name"));	
		
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("name", "张超");
		map.put("age", "11");
		ModelAndView mv = new ModelAndView("demo", map);
		return mv;*/
	}
}
