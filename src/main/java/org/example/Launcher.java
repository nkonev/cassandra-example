package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class Launcher implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    final static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    // TODO add a configuration, which requires to run Liquibase before repositories

    @Override
    public void run(String... args) throws Exception {
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

    }
}
