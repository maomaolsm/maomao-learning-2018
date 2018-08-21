package com.maomao.java;

import static com.maomao.java.TableCreator.createTableSql;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/8/21 14:01
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(createTableSql("com.maomao.java.Member"));
    }
}
