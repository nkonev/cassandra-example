package org.example.repository.cassandra;

import org.example.entity.cassandra.Message;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;

public interface MessageRepository extends CassandraRepository<Message, Message.MessageKey> {

    Slice<Message> findByKeyChatId(long chatId, CassandraPageRequest cassandraPageRequest);
}
