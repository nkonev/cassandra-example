package org.example.dto;

import com.datastax.oss.driver.api.core.cql.PagingState;
import org.example.utils.PageUtils;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.nio.ByteBuffer;

public record CassandraPage<T> (
        List<T> content,
        Integer count,
        String pagingState,
        boolean hasNext
) {

    public CassandraPage(List<T> content, Slice<?> slice) {
        this(content, content.size(), getPagingState(slice), slice.hasNext());
    }

    @Nullable
    private static String getPagingState(final Slice<?> slice) {
        if (slice.hasNext()) {
            var bb = ((CassandraPageRequest)slice.nextPageable()).getPagingState();
            if (bb == null) {
                return null;
            } else {
                return PageUtils.to(bb);
            }
        } else {
            return null;
        }
    }

    public Optional<String> getPagingState() {
        return Optional.ofNullable(pagingState);
    }
}
