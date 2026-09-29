package com.codeforge.events;

import java.time.Instant;

public record ExecutionRequestEvent(
        String executionId,
        String userId,
        String language,
        String sourceCode,
        String stdin,
        long timeoutMs,
        Instant submittedAt
) {
    public static ExecutionRequestEvent of(String executionId, String userId, String language,
                                            String sourceCode, String stdin, long timeoutMs) {
        return new ExecutionRequestEvent(executionId, userId, language, sourceCode, stdin,
                timeoutMs, Instant.now());
    }
}
