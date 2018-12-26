package com.maomao.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by maomao on 2018/12/26.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

}
