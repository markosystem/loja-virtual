package com.capitani.brasilprev.lojavirtual;

import com.capitani.brasilprev.lojavirtual.factory.ProductFactory;
import com.capitani.brasilprev.lojavirtual.factory.UserFactory;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.service.ProductService;
import com.capitani.brasilprev.lojavirtual.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

@Component
class initializeData implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(initializeData.class);
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Registrando Usuário Teste");
        User userRegistered = userService.save(UserFactory.initialize("Usuário Teste", "user", new BCryptPasswordEncoder().encode("123456"), "userteste@gmail.com"));
        logger.info("---------------------------------");
    }
}
