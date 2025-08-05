package com.shiyueoe.springdemo.valid.entity;

import com.shiyueoe.springdemo.valid.validator.LengthBetween;
import lombok.Data;

@Data
public class DemoDTO {

    @LengthBetween(min = 5, max = 15, message = "用户名长度必须在5 ~ 15字符之间")
    private String userName;
}
