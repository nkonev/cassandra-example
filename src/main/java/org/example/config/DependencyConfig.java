package org.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.CassandraRepositoriesRegistrar;

@Configuration
public class DependencyConfig {

    // declares that CassandraRepositoriesRegistrar depends on SpringLiquibase
    @Configuration(proxyBeanMethods = false)
    public static class CassandraRepositoriesRegistrarDependsOnBeanFactoryPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {

        protected CassandraRepositoriesRegistrarDependsOnBeanFactoryPostProcessor() {
            super(CassandraRepositoriesRegistrar.class, SpringLiquibase.class);
        }
    }

}
