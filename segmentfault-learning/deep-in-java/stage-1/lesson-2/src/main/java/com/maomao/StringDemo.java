package com.maomao;

import java.lang.reflect.Field;

/**
 * Created by maomao on 2019/2/17.
 */
public class StringDemo {
    public static void main(String[] args) throws Exception {

        String value = "hello";

        String value2 = new String("hello");

        System.out.println(value + " & " + value2);

        // string 到底是不是不变的，是可以改的

        // 从 java 1.5 开始对象的属性可以通过反射修改
        char[] chars = "world".toCharArray();

        // 获取 string 类中的 value 字段
        Field valueField = String.class.getDeclaredField("value");
        // 设置 private 字段可以修改
        valueField.setAccessible(true);
        // 把 chars 设置到 value 字段的内容
        // 第一个参数是自己的对象
        valueField.set(value2, chars);

        System.out.println(value + " & " + value2);

    }
}
