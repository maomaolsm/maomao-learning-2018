package com.maomao.spring1.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/8/13.
 */

// 标识此注解用在类上面
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
