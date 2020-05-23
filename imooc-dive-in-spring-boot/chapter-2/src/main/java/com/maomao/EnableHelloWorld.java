package com.maomao;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
