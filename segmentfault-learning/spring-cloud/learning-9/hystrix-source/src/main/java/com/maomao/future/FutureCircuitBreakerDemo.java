package com.maomao.future;

import java.util.Random;

/**
 * Created by maomao on 2018/12/10.
 */
public class FutureCircuitBreakerDemo {
    public static void main(String[] args) {

    }

    private static final Random RANDOM = new Random();

    /**
     * 随机时间执行命令
     */
    public class RandomCommand implements Command<String> {

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
