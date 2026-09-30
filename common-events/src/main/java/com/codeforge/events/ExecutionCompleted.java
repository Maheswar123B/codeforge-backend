package com.codeforge.events;

import java.time.Instant;
import java.util.UUID;

public record ExecutionCompleted(
        UUID eventId,
        String executionId,
        int schemaVersion,
        Instant occurredAt,

        ExecutionStatus status,
        Integer exitCode,
        long wallTimeMs,
        long lastSeq,
        boolean truncated,
        String message
) implements ExecutionEvent {

    public static ExecutionCompleted of(
            String executionId,
            ExecutionStatus status,
            Integer exitCode,
            long wallTimeMs,
            long lastSeq,
            boolean truncated,
            String message) {

        return new ExecutionCompleted(
                UUID.randomUUID(),
                executionId,
                1,
                Instant.now(),
                status,
                exitCode,
                wallTimeMs,
                lastSeq,
                truncated,
                message
        );
    }
}