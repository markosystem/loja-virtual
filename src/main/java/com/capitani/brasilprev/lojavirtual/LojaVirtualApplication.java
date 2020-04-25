package com.capitani.brasilprev.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Collections;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class LojaVirtualApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LojaVirtualApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8181"));
        app.run(args);
    }
}
