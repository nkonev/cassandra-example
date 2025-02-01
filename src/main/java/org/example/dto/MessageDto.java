package org.example.dto;
import java.time.LocalDateTime;

public record MessageDto(
        long id,
        LocalDateTime createDateTime,
        String body,
        long ownerId
) { }
