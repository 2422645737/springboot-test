package com.shiyueoe.demo_08_01.deadlock;

public class App {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread-1 拿到 LOCK_A，准备获取 LOCK_B");
                sleep(1000);
                synchronized (LOCK_B) {
                    System.out.println("Thread-1 拿到 LOCK_B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK_B) {
                System.out.println("Thread-2 拿到 LOCK_B，准备获取 LOCK_A");
                sleep(1000);
                synchronized (LOCK_A) {
                    System.out.println("Thread-2 拿到 LOCK_A");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {

        }
    }
}