package com.shiyueoe.springdemo.lock.entity;

import lombok.Data;

@Data
public class GrabResponse {
    private boolean success;

    /**
     * 抢到的钱
     */
    private double amount;

    private String message;

    private Long userId;
}