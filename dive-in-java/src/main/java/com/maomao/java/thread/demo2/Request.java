package com.maomao.java.thread.demo2;

/**
 * Created by maomao on 2018/10/11.
 */
public class Request {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
            "name='" + name + '\'' +
            '}';
    }
}
