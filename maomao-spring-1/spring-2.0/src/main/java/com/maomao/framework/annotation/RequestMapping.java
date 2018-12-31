package com.maomao.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/12/30.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
