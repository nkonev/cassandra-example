package org.example;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table("shopping_cart")
public record ShoppingCart(
        @PrimaryKey
        String userid,
        @Column("item_count")
        int itemCount,
        @Column("last_update_timestamp")
        LocalDateTime lastUpdateTimestamp
) { }
