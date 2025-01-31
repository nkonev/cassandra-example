package org.example;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table("shopping_cart")
public record ShoppingCart(
        @PrimaryKey
        ShoppingCart.ShoppingCartKey key,
        @Column("last_update_timestamp")
        LocalDateTime lastUpdateTimestamp
) {
        @PrimaryKeyClass
        public record ShoppingCartKey (
                @PrimaryKeyColumn(name = "userid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
                String userid,
                @PrimaryKeyColumn(name = "item_count", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
                int itemCount
        ) implements Serializable { }
}
