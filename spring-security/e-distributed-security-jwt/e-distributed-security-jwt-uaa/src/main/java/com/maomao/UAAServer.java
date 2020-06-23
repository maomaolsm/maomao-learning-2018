package com.maomao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = "com.maomao")
public class UAAServer {

	public static void main(String[] args) {
		SpringApplication.run(UAAServer.class, args);
	}

	// 获取code
	// http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://www.baidu.com
	// 拿到code，换token
	// http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=authorization_code&code=5PgfcD&redirect_uri=http://www.baidu.com

	// 简化模式获取token
	// http://localhost:53020/uaa/oauth/authorize?client_id=c1&response_type=token&scope=all&redirect_uri=http://www.baidu.com

	// 密码模式
	// http://localhost:53020/uaa/oauth/token?client_id=c1&client_secret=secret&grant_type=password&username=shangsan&password=123

	// 访问资源
	// headers 中添加 Authorization，值为：Bearer token值
	// http://127.0.0.1:53021/order/r1

}
