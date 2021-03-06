package com.maomao.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/12/26.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
