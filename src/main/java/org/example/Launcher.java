package org.example;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.CassandraRepositoriesRegistrar;

@SpringBootApplication
public class Launcher {

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

}
