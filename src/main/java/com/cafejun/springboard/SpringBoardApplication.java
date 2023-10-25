package com.cafejun.springboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
@ConfigurationPropertiesScan // 이게 없으면 custom propert가 스캔이 안됨
@SpringBootApplication
public class SpringBoardApplication {
	// CloudType으로 배포 세팅
	public static void main(String[] args) {
		SpringApplication.run(SpringBoardApplication.class, args);
	}

}
