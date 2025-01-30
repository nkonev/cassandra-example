package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloCassandra {
    final static Logger logger = LoggerFactory.getLogger(HelloCassandra.class);

    public static void main(final String[] args) throws SQLException {
        // Used driver: com.ing.data.cassandra.cassandra.jdbc.CassandraDriver
        // https://stackoverflow.com/questions/58279546/nonodeavailableexception-no-node-was-available-to-execute-the-query/58588661#58588661
        final String url = "jdbc:cassandra://localhost:9042/store?localdatacenter=datacenter1";
        try (var connection = DriverManager.getConnection(url)) {
            try (var statement = connection.createStatement()) {
                try (var resultSet = statement.executeQuery("SELECT * FROM store.shopping_cart;")) {
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
