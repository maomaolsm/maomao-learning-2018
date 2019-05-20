package com.maomao.demo;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/2/21 17:45
 */
public class IdentityHashDemo {
    public static void main(String[] args) {
        Integer var  = new Integer(1);
        Integer var2  = new Integer(1);

        // hashCode 就是其 value
        System.out.println(var.hashCode());
        System.out.println(var2.hashCode());

        System.out.println(System.identityHashCode(var));
        System.out.println(System.identityHashCode(var2));
    }
}
