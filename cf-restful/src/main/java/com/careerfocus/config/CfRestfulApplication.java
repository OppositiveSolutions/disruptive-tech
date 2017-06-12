package com.careerfocus.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({"com.careerfocus"})
@EnableJpaRepositories("com.careerfocus.repository")
@EntityScan("com.careerfocus.entity")
@EnableAsync
public class CfRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfRestfulApplication.class, args);
    }
}
