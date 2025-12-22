package com.tejaupvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tejaupvc.repository")
@EntityScan(basePackages = "com.tejaupvc.model")
public class DistribuidoraTejaUpvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistribuidoraTejaUpvcApplication.class, args);
    }
}