package org.example;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.CassandraRepositoriesRegistrar;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@SpringBootApplication
public class Launcher implements CommandLineRunner {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    final static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    // declares that CassandraRepositoriesRegistrar depends on SpringLiquibase
    @Configuration(proxyBeanMethods = false)
    public static class CassandraRepositoriesRegistrarDependsOnBeanFactoryPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {

        protected CassandraRepositoriesRegistrarDependsOnBeanFactoryPostProcessor() {
            super(CassandraRepositoriesRegistrar.class, SpringLiquibase.class);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Using repository");
        shoppingCartRepository.save(new ShoppingCart(new ShoppingCart.ShoppingCartKey("new1", 4), LocalDateTime.now()));
        shoppingCartRepository.save(new ShoppingCart(new ShoppingCart.ShoppingCartKey("new1", 6), LocalDateTime.now()));
        shoppingCartRepository.save(new ShoppingCart(new ShoppingCart.ShoppingCartKey("new1", 7), LocalDateTime.now()));

        shoppingCartRepository.findByKeyUserid("new1", Sort.by(Sort.Order.desc("key.itemCount"))).forEach(shoppingCart -> {
            logger.info("Row: {}, {}, {}", shoppingCart.key().userid(), shoppingCart.key().itemCount(), shoppingCart.lastUpdateTimestamp());
        });
    }
}
