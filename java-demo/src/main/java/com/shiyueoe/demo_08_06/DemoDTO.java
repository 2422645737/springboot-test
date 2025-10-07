package com.shiyueoe.demo_08_06;

import lombok.Data;

@Data
public class DemoDTO {

    private String userName;
    private String age;

    public DemoDTO(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }
}