package com.maomao;

import org.springframework.context.annotation.Bean;

public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "------------------------------- maomao";
    }

}
