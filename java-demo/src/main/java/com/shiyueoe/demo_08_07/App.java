package com.shiyueoe.demo_08_07;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();

        for(long i = 0; i < 1000; i++){
            list.add(i);
        }
        new Thread(() -> {
            synchronized (list){
                list.sort(Long::compareTo);
            }
        }).start();

        new Thread(() -> {
            synchronized (list){
                list.forEach(System.out::println);
            }
        }).start();
    }
}