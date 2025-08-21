package com.shiyueoe.springdemo.valid.controller;

import com.shiyueoe.springdemo.valid.entity.DemoDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Timer;

@RestController
@RequestMapping("/test")
public class ValidController {


    @PostMapping("demo")
    public String demo(@RequestBody Map<String,String> map){
        System.out.println(map);
        return "hello world";
    }
}