package com.maomao.java.thread.demo2;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by maomao on 2018/10/11.
 */
public class PrintProcessor extends Thread implements RequestProcessor {

    // 阻塞队列
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue();

    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = linkedBlockingQueue.take();
                System.out.println("print data : " + request);
                nextProcessor.processorRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processorRequest(Request request) {
        linkedBlockingQueue.add(request);
    }
}
