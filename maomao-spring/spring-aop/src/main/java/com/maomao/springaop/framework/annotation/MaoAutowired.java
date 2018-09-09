package com.maomao.springaop.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/8/13.
 */

// 主要放在字段上（ElementType.FIELD）
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaoAutowired {
    String value() default "";
}
