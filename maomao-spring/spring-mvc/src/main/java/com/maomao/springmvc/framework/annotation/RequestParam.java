package com.maomao.springmvc.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/8/13.
 */

// 主要放在参数上边
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
