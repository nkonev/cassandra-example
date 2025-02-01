package org.example.service;

import org.example.dto.CassandraPage;
import org.example.dto.Paginated;
import org.example.entity.cassandra.Message;
import org.example.repository.cassandra.MessageRepository;
import org.example.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public <T> CassandraPage<T> getPageOfMessages(long chatId, final Paginated paginated, Function<Message, T> mapper) {
        return getPageOfMessages(chatId, paginated.getLimit(), paginated.getPagingState().orElse(null), mapper);
    }

    public <T> CassandraPage<T> getPageOfMessages(long chatId, final int limit, final String pagingState, Function<Message, T> mapper) {
        final var pageRequest = createCassandraPageRequest(limit, pagingState);
        return getPageOfMessages(chatId, pageRequest, mapper);
    }

    public <T> CassandraPage<T> getPageOfMessages(long chatId, final CassandraPageRequest cassandraPageRequest, Function<Message, T> mapper) {
        final var userSlice = messageRepository.findByKeyChatId(chatId, cassandraPageRequest);
        return new CassandraPage<>(userSlice, mapper);
    }

    private CassandraPageRequest createCassandraPageRequest(final int limit, @Nullable final String pagingState) {
        final var pageRequest = PageRequest.of(0, limit);
        final var pageState = pagingState != null ? PageUtils.from(pagingState) : null ;
        return CassandraPageRequest.of(pageRequest, pageState);
    }
}
