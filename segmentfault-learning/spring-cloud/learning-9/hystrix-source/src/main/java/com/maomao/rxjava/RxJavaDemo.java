package com.maomao.rxjava;

import rx.Single;
import rx.schedulers.Schedulers;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2018/12/10 17:08
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        Single.just("maomao") // 仅能发布单个数据
                .subscribeOn(Schedulers.io()) // 在 I/O 线程上执行
                .subscribe(RxJavaDemo::println); // 订阅并且消费数据
        System.out.println("线程：" + Thread.currentThread().getName());
    }

    private static void println(Object o) {
        System.out.println("线程：" + Thread.currentThread().getName() + "，数据：" + o);
    }

}
