package com.shiyueoe.demo_08_06;

import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        List<DemoDTO> list = new ArrayList<>();
        list.add(new DemoDTO("wanghui","123"));
        list.add(new DemoDTO("lisi","123"));
        list.add(new DemoDTO("wanghui","123"));

        Map<String, List<DemoDTO>> collect =
                list.stream().collect(Collectors.groupingBy(a -> a.getUserName() + a.getAge()));

        int a =10;

    }

}