package com.maomao.third;

import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.*;

/**
 * Add some description about this class.
 *
 * @author senmao.li
 * @since 2019/7/9 18:01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CacheImportSelector.class)
public @interface EnableDefineService {

    Class<?>[] exclude() default {};
}
