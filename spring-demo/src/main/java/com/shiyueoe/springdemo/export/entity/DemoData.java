package com.shiyueoe.springdemo.export.entity;


import lombok.Data;

import java.util.Date;

@Data
public class DemoData {
    private Long id;
    private String name;
    private String category;
    private Date createdAt;
    private Date updatedAt;
}
