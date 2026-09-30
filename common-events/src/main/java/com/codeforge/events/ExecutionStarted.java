package com.codeforge.events;

import java.time.Instant;
import java.util.UUID;

public record ExecutionStarted(
        UUID eventId,
        String executionId,
        int schemaVersion,
        Instant occurredAt,

        String workerId,
        String containerId,
        Instant startedAt
) implements ExecutionEvent {

    public static ExecutionStarted of(
            String executionId,
            String workerId,
            String containerId) {

        Instant now = Instant.now();

        return new ExecutionStarted(
                UUID.randomUUID(),
                executionId,
                1,
                now,
                workerId,
                containerId,
                now
        );
    }
}