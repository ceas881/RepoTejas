package com.tejaupvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.tejaupvc.repository")  // ¡IMPORTANTE!
@EntityScan("com.tejaupvc.model")  // Si tienes entidades en otro paquete
public class DistribuidoraTejaUpvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistribuidoraTejaUpvcApplication.class, args);
    }
}