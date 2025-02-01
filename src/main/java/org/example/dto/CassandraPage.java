package org.example.dto;

import org.example.utils.PageUtils;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record CassandraPage<R> (
        List<R> content,
        int count,
        String pagingState,
        boolean hasNext
) {

    public <T> CassandraPage(Slice<T> slice, Function<T, R> mapper) {
        this(slice.getContent().stream().map(mapper).toList(), slice.getContent().size(), getPagingState(slice), slice.hasNext());
    }

    @Nullable
    private static String getPagingState(final Slice<?> slice) {
        if (slice.hasNext()) {
            var bb = ((CassandraPageRequest)slice.nextPageable()).getPagingState();
            return bb != null ? PageUtils.to(bb) : null;
        } else {
            return null;
        }
    }

    public Optional<String> getPagingState() {
        return Optional.ofNullable(pagingState);
    }
}
