package com.shiyueoe.demo_08_06;

import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class App {
    public static void main(String[] args) {
        DemoDTO demoDTO = new DemoDTO();

        demoDTO.setUserName("hhhhhhhhhhhhhhhhhhhhhhh");

        func(demoDTO);

    }

    public static void func(@Valid DemoDTO demoDTO){
        System.out.println(demoDTO);
    }
}
