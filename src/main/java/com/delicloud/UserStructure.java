package com.delicloud;

import com.delicloud.platform.v2.common.data.config.CustomJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author liuyushan
 * Date: 2021/12/20
 */
@SpringBootApplication
public class UserStructure {

    public static void main(String[] args) {
        SpringApplication.run(UserStructure.class, args);
    }
}
