package com.maomao.springaop.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/8/13.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaoService {
    String value() default "";
}
