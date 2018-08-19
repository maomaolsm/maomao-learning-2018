package com.maomao.java.annotation;

import com.maomao.java.annotation.Constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by maomao on 2018/8/19.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {

    String name() default "";

    // 列类型分配长度
    int value() default 0;

    // 嵌套注解
    Constraints constraint() default @Constraints;

}
