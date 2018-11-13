package com.maomao.java.thread.demo2;

/**
 * ZooKeeper 思想借鉴
 *
 * Created by maomao on 2018/10/11.
 */
public class Test {
    PrintProcessor printProcessor;

    public Test() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();

    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("maomao");
        new Test().doTest(request);
    }

    public void doTest(Request request) {
        printProcessor.processorRequest(request);
    }
}
