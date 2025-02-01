package org.example;

import liquibase.integration.spring.SpringLiquibase;
import org.example.entity.cassandra.Message;
import org.example.repository.cassandra.MessageRepository;
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
    private MessageRepository messageRepository;

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

        for (int i = 0; i < 1000; ++i) {
            messageRepository.save(new Message(new Message.MessageKey(1, i), LocalDateTime.now(), "hello"+i, 1));
        }

        messageRepository.findByKeyChatId(1, Sort.by(Sort.Order.desc("key.id"))).forEach(message -> logger.info("Row: {}", message));
    }
}
