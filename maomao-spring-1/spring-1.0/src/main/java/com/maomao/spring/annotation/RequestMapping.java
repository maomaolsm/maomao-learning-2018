package com.maomao.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/12/26.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
