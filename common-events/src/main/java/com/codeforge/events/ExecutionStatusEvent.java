package com.codeforge.events;

import java.time.Instant;

public record ExecutionStatusEvent(
        String executionId,
        ExecutionStatus status,
        Instant timestamp
) {
    public static ExecutionStatusEvent of(String executionId, ExecutionStatus status) {
        return new ExecutionStatusEvent(executionId, status, Instant.now());
    }
}
