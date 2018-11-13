package com.maomao.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * interrupt interrupted
 * <p>
 * Created by maomao on 2018/10/14.
 */
public class ThreadInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    System.out.println("before : " + in);

                    // 设置复位
                    Thread.interrupted();
                    System.out.println("after : " + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        // 中断
        thread.interrupt();
    }
}
