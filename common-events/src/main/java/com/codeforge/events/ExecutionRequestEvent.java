package com.codeforge.events;

import java.time.Instant;
import java.util.UUID;

public record ExecutionRequestEvent(
        UUID eventId,
        String executionId,
        int schemaVersion,
        Instant occurredAt,

        String userId,
        Language language,
        String fileName,
        String sourceCode,
        String preloadedInput,
        int timeoutSeconds,
        int memoryLimitMb
) implements ExecutionEvent {

    public static ExecutionRequestEvent of(
            String executionId,
            String userId,
            Language language,
            String fileName,
            String sourceCode,
            String preloadedInput,
            int timeoutSeconds,
            int memoryLimitMb) {

        return new ExecutionRequestEvent(
                UUID.randomUUID(),
                executionId,
                1,
                Instant.now(),
                userId,
                language,
                fileName,
                sourceCode,
                preloadedInput,
                timeoutSeconds,
                memoryLimitMb
        );
    }
}