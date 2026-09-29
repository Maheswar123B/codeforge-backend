package com.codeforge.events;

import java.time.Instant;

public record ExecutionResultEvent(
        String executionId,
        String stdout,
        String stderr,
        Integer exitCode,
        boolean timedOut,
        long executionTimeMs,
        Instant completedAt
) {
}
