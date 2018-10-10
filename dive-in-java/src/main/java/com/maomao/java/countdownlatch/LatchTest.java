package com.maomao.java.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Java 中模拟真正的同时并发请求
 *
 * @author senmao.li
 * @since 2018/10/9 14:43
 */
public class LatchTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable taskTemp = new Runnable() {

            // 此处非线程安全，留坑
            private int iCounter;

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    iCounter++;
                    System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        LatchTest latchTest = new LatchTest();
        latchTest.startTaskAllInOnce(5, taskTemp);
    }

    private long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减一，减到零时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");

        // 因为开启门只需要一个开关，所以立马就开启开始门
        startGate.countDown();

        // 等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed");
        return endTime - startTime;
    }
}
