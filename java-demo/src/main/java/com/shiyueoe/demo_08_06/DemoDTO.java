package com.shiyueoe.demo_08_06;

import lombok.Data;

@Data
public class DemoDTO {

    @LengthBetween(min = 5, max = 15, message = "用户名长度必须在5 ~ 15字符之间")
    private String userName;
}
