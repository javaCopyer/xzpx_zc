package com.xzpx_zc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xzpx_zc.pojo.ZcUser;
import com.xzpx_zc.result.Result;
import com.xzpx_zc.service.ZcUserService;

@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class ZcUserController {
	@Resource
	private ZcUserService zcUserService;
	@RequestMapping(value="/userLogin", method={RequestMethod.GET, RequestMethod.POST})
	public Result userLogin(ZcUser zcUser, HttpSession session) {
		List<ZcUser> list = zcUserService.selectUser(zcUser);
		if(list.size() == 0) 
			return new Result(0, "账号或密码错误", null);
		session.setAttribute("user", list);
		return new Result(1, "登陆成功", list);
	}
}
