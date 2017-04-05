package com.careerfocus.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.careerfocus"})
@EnableJpaRepositories("com.careerfocus.dao")
@EntityScan("com.careerfocus.entity")
public class CfRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfRestfulApplication.class, args);
	}
}
