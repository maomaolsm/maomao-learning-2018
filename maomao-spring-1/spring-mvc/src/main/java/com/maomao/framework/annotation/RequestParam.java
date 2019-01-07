package com.maomao.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2019/1/5.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
