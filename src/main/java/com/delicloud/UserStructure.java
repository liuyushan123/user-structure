package com.delicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author liuyushan
 * Date: 2021/12/20
 */
@SpringBootApplication
@EntityScan("com.delicloud")
@EnableJpaRepositories(basePackages = "com.delicloud")
@ComponentScan(basePackages = "com.delicloud")
public class UserStructure {

    public static void main(String[] args) {
        SpringApplication.run(UserStructure.class, args);
    }
}
