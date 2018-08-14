package com.maomao.imoocdiveinspringboot.configyration;

import org.springframework.context.annotation.Bean;

/**
 * HelloWorld 配置
 *
 * @author senmao.li
 * @since 2018/8/14 18:02
 */
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld() {
        return "Hello, World 2018";
    }
}
