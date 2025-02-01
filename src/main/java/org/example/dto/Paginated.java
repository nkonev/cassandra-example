package org.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Optional;

public record Paginated (
        @Min(1)
        @Max(1000)
        Integer limit,
        String pagingState
) {
    public static final Integer DEFAULT_LIMIT = 10;

    public Optional<String> getPagingState() {
        return Optional.ofNullable(pagingState);
    }

    public int getLimit() {
        return limit != null ? limit : DEFAULT_LIMIT;
    }
}
