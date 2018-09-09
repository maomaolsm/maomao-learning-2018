package com.maomao.springaop.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/8/13.
 */

// 既可以在类上用（ElementType.TYPE），又可以在方法上用（ElementType.METHOD）
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaoRequestMapping {
    String value() default "";
}
