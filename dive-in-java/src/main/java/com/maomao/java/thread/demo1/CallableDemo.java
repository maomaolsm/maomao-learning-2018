package com.maomao.java.thread.demo1;

import java.util.concurrent.*;

/**
 * Created by maomao on 2018/10/11.
 */
public class CallableDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableDemo callableDemo = new CallableDemo();

        Future<String> future = executorService.submit(callableDemo);

        // 在这里阻塞
        String rs = future.get();
        System.out.println(rs);
        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        return "haha";
    }
}
