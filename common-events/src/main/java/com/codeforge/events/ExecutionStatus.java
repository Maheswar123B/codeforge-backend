package com.codeforge.events;

/**
 * Lifecycle states of a single code execution, published on the
 * execution-status topic and streamed to the client via result-gateway.
 */
public enum ExecutionStatus {
    QUEUED,
    RUNNING,
    COMPLETED,
    FAILED,
    TIMED_OUT
}
