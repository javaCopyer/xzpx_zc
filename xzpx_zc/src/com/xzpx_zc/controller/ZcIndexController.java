package com.xzpx_zc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzpx_zc.pojo.ZcNav;
import com.xzpx_zc.pojo.ZcUser;
import com.xzpx_zc.result.Result;
import com.xzpx_zc.service.ZcNavService;

@Controller
public class ZcIndexController {
	@Resource
	private ZcNavService zcNavService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/index", method={RequestMethod.GET, RequestMethod.POST})
	public Result goIndex(ZcNav zcNav) {
		System.out.println(zcNav.getNavId());
		List<ZcNav> navList = new ArrayList<ZcNav>();
		ZcNav n1 = new ZcNav();
		n1.setNavId(1);
		ZcNav n2 = new ZcNav();
		n1.setNavId(2);
		navList.add(n1);
		navList.add(n2);
		Result result = new Result(1, "查詢成功", navList);
		return result;
	}
	
	@RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST})
	public ZcNav test(String id) {
		ZcNav nav = new ZcNav();
		nav.setNavName("测试呢");
		return nav;
	}
}
