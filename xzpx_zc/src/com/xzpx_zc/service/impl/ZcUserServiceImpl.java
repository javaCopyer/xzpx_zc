package com.xzpx_zc.service.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzpx_zc.mapper.ZcUserMapperSelf;
import com.xzpx_zc.pojo.ZcUser;
import com.xzpx_zc.service.ZcUserService;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ZcUserServiceImpl implements ZcUserService {
	@Resource
	private ZcUserMapperSelf zcUserMapperSelf;

	@Override
	public PageInfo selectUserPage(ZcUser zcUser, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ZcUser> list = zcUserMapperSelf.selectUser(zcUser);
		PageInfo pageInfo = new PageInfo(list);		
		return pageInfo;
	}

	@Override
	public List<ZcUser> selectUser(ZcUser zcUser) {
		return zcUserMapperSelf.selectUser(zcUser);
	}

}
