package com.maomao.imoocdiveinspringboot.configyration;

import com.maomao.imoocdiveinspringboot.annotation.EnableHelloWorld;
import com.maomao.imoocdiveinspringboot.condition.ConditionOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld 自动装配
 *
 * @author senmao.li
 * @since 2018/8/14 17:25
 */
@Configuration // spring 模式注解装配
// @EnableHelloWorld -> 会加载 HelloWorldImportSelector -> 会加载 HelloWorldConfiguration
// 最终转生成 helloWorld Bean
@EnableHelloWorld // spring @Enable 模块装配
@ConditionOnSystemProperty(name = "user.name", value = "lx") // 条件装配
public class HelloWorldAutoConfiguration {
}
