package com.xzpx_zc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xzpx_zc.pojo.ZcNav;
import com.xzpx_zc.pojo.ZcNavExample;

public interface ZcNavMapper {
    long countByExample(ZcNavExample example);

    int deleteByExample(ZcNavExample example);

    int deleteByPrimaryKey(Integer navId);

    int insert(ZcNav record);

    int insertSelective(ZcNav record);

    List<ZcNav> selectByExample(ZcNavExample example);

    ZcNav selectByPrimaryKey(Integer navId);

    int updateByExampleSelective(@Param("record") ZcNav record, @Param("example") ZcNavExample example);

    int updateByExample(@Param("record") ZcNav record, @Param("example") ZcNavExample example);

    int updateByPrimaryKeySelective(ZcNav record);

    int updateByPrimaryKey(ZcNav record);
}