package com.maomao.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/12/30.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    String value() default "";
}
