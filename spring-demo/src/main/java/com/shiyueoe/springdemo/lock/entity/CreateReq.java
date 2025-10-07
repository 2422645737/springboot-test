package com.shiyueoe.springdemo.lock.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateReq {
    /**
     * 红包总金额
     */
    private double total;

    /**
     * 红包划分个数
     */
    private Integer partCount;
}