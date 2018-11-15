package com.primeton.maoxinjie.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value="com.primeton.maoxinjie.demo.dao")
public class MaoxinjieDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaoxinjieDemoApplication.class, args);
	}
}
