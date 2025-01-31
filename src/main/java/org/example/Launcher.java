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

import javax.sql.DataSource;

@SpringBootApplication
public class Launcher implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

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
        logger.info("Using SQL connection");
        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                try (var resultSet = statement.executeQuery("SELECT * FROM shopping_cart;")) {
                    while (resultSet.next()) {
                        var uid = resultSet.getString("userid");
                        var ic = resultSet.getInt("item_count");
                        var ts = resultSet.getTimestamp("last_update_timestamp");
                        logger.info("Row: {}, {}, {}", uid, ic, ts);
                    }
                }
            }
        }

        logger.info("Using repository");
        shoppingCartRepository.findAll().forEach(shoppingCart -> {
            logger.info("Row: {}, {}, {}", shoppingCart.userid(), shoppingCart.itemCount(), shoppingCart.lastUpdateTimestamp());
        });
    }
}
