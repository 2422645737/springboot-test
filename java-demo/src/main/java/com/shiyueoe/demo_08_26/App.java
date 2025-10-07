package com.shiyueoe.demo_08_26;

public class App {
    public static final ThreadLocal<String> user = new ThreadLocal<>();
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            user.set("user1");
            System.out.println(Thread.currentThread().getName() + ":" + user.get());

            user.remove();
        },"线程1");
        Thread t2 = new Thread(() -> {
            user.set("user2");
            System.out.println(Thread.currentThread().getName() + ":" + user.get());

            user.remove();
        },"线程2");

        t1.start();
        t2.start();
    }

}