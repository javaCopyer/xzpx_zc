package com.xzpx_zc.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xzpx_zc.pojo.ZcUser;
@SuppressWarnings("rawtypes")
public interface ZcUserService {
	
	public PageInfo selectUserPage(ZcUser zcUser, int pageNum, int pageSize);
	public List<ZcUser> selectUser(ZcUser zcUser);
	
}
