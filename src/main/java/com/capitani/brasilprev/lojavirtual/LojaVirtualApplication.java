package com.capitani.brasilprev.lojavirtual;

import com.capitani.brasilprev.lojavirtual.factory.ClientFactory;
import com.capitani.brasilprev.lojavirtual.factory.ProductFactory;
import com.capitani.brasilprev.lojavirtual.factory.PurchaseOrderFactory;
import com.capitani.brasilprev.lojavirtual.factory.UserFactory;
import com.capitani.brasilprev.lojavirtual.model.Client;
import com.capitani.brasilprev.lojavirtual.model.Product;
import com.capitani.brasilprev.lojavirtual.model.PurchaseOrder;
import com.capitani.brasilprev.lojavirtual.model.User;
import com.capitani.brasilprev.lojavirtual.model.enums.Status;
import com.capitani.brasilprev.lojavirtual.service.ClientService;
import com.capitani.brasilprev.lojavirtual.service.ProductService;
import com.capitani.brasilprev.lojavirtual.service.PurchaseOrderService;
import com.capitani.brasilprev.lojavirtual.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class LojaVirtualApplication {
    @Value("${lojavirtual.server.port}")
    private static String port;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LojaVirtualApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port == null ? "8080" : port));
        app.run(args);
    }
}

@Component
class initializeData implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(initializeData.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Registro de Usuário do Sistema Automático");
        User userRegistered = userService.save(UserFactory.initialize("Usuário Teste", "user", new BCryptPasswordEncoder().encode("123456"), "userteste@gmail.com"));
        logger.info("---------------------------------");

        logger.info("Registro Automático de Produtos");
        List<Product> productList = new ArrayList<>();
        for (int i = 1; i <= 6; i++)
            productList.add(productService.save(ProductFactory.initialize("Produto " + i, new BigDecimal(10 + i), userRegistered, null)));
        logger.info("---------------------------------");

        logger.info("Registro Automático de Clientes");
        List<Client> clientList = new ArrayList<>();
        for (int i = 1; i <= 2; i++)
            clientList.add(clientService.save(ClientFactory.initialize("Cliente " + i, i + "email@gmail.com", "0000000" + i, userRegistered, null)));
        logger.info("---------------------------------");

        logger.info("Registro Automático de Pedidos");
        List<Product> productList1 = Arrays.asList(productList.get(0), productList.get(1), productList.get(2));
        List<Product> productList2 = Arrays.asList(productList.get(3), productList.get(4), productList.get(5));
        PurchaseOrder order1 = PurchaseOrderFactory.initialize(Status.OPEN, clientList.get(0), productList1, 20191);
        PurchaseOrder order2 = PurchaseOrderFactory.initialize(Status.COMPLETED, clientList.get(1), productList2, 20192);
        order1.setUserCreated(userRegistered);
        order2.setUserCreated(userRegistered);
        purchaseOrderService.save(order1);
        purchaseOrderService.save(order2);
        logger.info("---------------------------------");
    }
}
