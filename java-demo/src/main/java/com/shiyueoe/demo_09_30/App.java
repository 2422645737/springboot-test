package com.shiyueoe.demo_09_30;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        executor.execute(() -> {
            System.out.println("hello world");
        });
    }
}