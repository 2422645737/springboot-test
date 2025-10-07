package com.shiyueoe.springdemo.lock.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.util.List;
import java.util.Queue;

@Data
public class RedPacket {
    /**
     * 红包id
     */
    private Long id;

    /**
     * 这一份红包金额
     */
    private int totalCents;


    private Queue<Integer> parts;
}