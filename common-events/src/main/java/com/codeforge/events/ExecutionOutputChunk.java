package com.codeforge.events;

import java.time.Instant;
import java.util.UUID;

public record ExecutionOutputChunk(
        UUID eventId,
        String executionId,
        int schemaVersion,
        Instant occurredAt,

        StreamType stream,
        String data,
        long sequenceNumber
) implements ExecutionEvent {

    public static ExecutionOutputChunk of(
            String executionId,
            StreamType stream,
            String data,
            long sequenceNumber) {

        return new ExecutionOutputChunk(
                UUID.randomUUID(),
                executionId,
                1,
                Instant.now(),
                stream,
                data,
                sequenceNumber
        );
    }
}