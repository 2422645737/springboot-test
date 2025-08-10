package com.shiyueoe.springdemo.export.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiyueoe.springdemo.export.entity.DemoData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DemoMapper extends BaseMapper<DemoData> {

    public List<DemoData> get(@Param("limit") int limit, @Param("offset") int offset,@Param("id") Long id);

    public int countAll();
}
