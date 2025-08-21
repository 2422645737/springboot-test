package com.shiyueoe.springdemo.export.mapper;

import com.shiyueoe.springdemo.export.entity.DemoData;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DemoMapper {

    public List<DemoData> get(@Param("limit") int limit, @Param("offset") int offset,@Param("id") Long id);

    public int countAll();
}