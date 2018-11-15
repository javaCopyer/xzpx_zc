package com.xzpx_zc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xzpx_zc.pojo.ZcUser;
import com.xzpx_zc.pojo.ZcUserExample;

public interface ZcUserMapper {
   
	long countByExample(ZcUserExample example);

    int deleteByExample(ZcUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(ZcUser record);

    int insertSelective(ZcUser record);

    List<ZcUser> selectByExample(ZcUserExample example);

    ZcUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") ZcUser record, @Param("example") ZcUserExample example);

    int updateByExample(@Param("record") ZcUser record, @Param("example") ZcUserExample example);

    int updateByPrimaryKeySelective(ZcUser record);

    int updateByPrimaryKey(ZcUser record);
}