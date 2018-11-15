package com.xzpx_zc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzpx_zc.mapper.ZcNavMapperSelf;
import com.xzpx_zc.pojo.ZcNav;
import com.xzpx_zc.service.ZcNavService;
@Service
public class ZcNavServiceImpl implements ZcNavService {
	@Resource
	private ZcNavMapperSelf zcNavMapperSelf;
	@Override
	public List<ZcNav> selectNav(ZcNav zcNav) {
		return zcNavMapperSelf.selectNav(zcNav);
	}


}
