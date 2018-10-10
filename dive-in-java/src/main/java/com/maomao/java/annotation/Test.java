package com.maomao.java.annotation;

import static com.maomao.java.annotation.TableCreator.createTableSql;

/**
 * annotation 的原理
 *
 * @author senmao.li
 * @since 2018/8/21 14:01
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(createTableSql("com.maomao.java.annotation.Member"));
    }
}
