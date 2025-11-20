package com.lgdx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBoot1Application : 해당하는 클래스의 위치를 기준으로 하여, 하위에 있는 파일을 읽어서 Spring Boot 설정을 자동으로 진행하는 클래스
// @ComponentScan : @Controller, @RestController, @Service, @Repository

@SpringBootApplication
public class SpringBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot1Application.class, args);
	}

}
