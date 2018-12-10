package com.maomao.future;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by maomao on 2018/12/10.
 */
public class FutureCircuitBreakerDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        RandomCommand command = new RandomCommand();

        Future<String> future = executorService.submit(command::run);

        String result;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            result = command.fallback();
        }

        System.out.println(result);

        executorService.shutdown();

    }

    private static final Random RANDOM = new Random();

    /**
     * 随机时间执行命令
     */
    public static class RandomCommand implements Command<String> {

        @Override
        public String run() throws Exception {

            long executeTime = RANDOM.nextInt(200);

            System.out.println("sleep : " + executeTime + "ms");

            Thread.sleep(executeTime);

            return "do run";
        }

        @Override
        public String fallback() {
            return "fallback";
        }
    }

    public interface Command<T> {

        /**
         * 正常执行，并且返回结果
         *
         * @return
         */
        T run() throws Exception;

        /**
         * 错误时，返回容错结果
         *
         * @return
         */
        T fallback();

    }

}
