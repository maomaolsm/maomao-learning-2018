package com.maomao.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * 可见性问题
 * <p>
 * Created by maomao on 2018/10/14.
 */
public class VisibleDemo {
    // 若不 volatile 则线程不能停止，即对 stop 的改变线程读不到
    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
