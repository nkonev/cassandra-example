package org.example.entity.cassandra;

import org.example.dto.MessageDto;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table("message")
public record Message(
        @PrimaryKey
        MessageKey key,
        @Column("create_date_time")
        LocalDateTime createDateTime,
        @Column("body")
        String body,
        @Column("owner_id")
        long ownerId
) {
        @PrimaryKeyClass
        public record MessageKey(
                @PrimaryKeyColumn(name = "chat_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
                long chatId,
                @PrimaryKeyColumn(name = "id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
                long id
        ) implements Serializable { }

        public static MessageDto toDto(Message message) {
                return new MessageDto(
                        message.key().id(),
                        message.createDateTime(),
                        message.body(),
                        message.ownerId()
                );
        }
}
