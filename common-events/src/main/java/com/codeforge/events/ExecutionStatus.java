package com.codeforge.events;

public enum ExecutionStatus {

    QUEUED,
    RUNNING,

    COMPLETED,
    RUNTIME_ERROR,
    COMPILE_ERROR,
    TIMEOUT,
    MEMORY_EXCEEDED,
    OUTPUT_LIMIT_EXCEEDED,
    INTERNAL_ERROR,
    LOST;

    public boolean isTerminal() {
        return switch (this) {
            case COMPLETED,
                 RUNTIME_ERROR,
                 COMPILE_ERROR,
                 TIMEOUT,
                 MEMORY_EXCEEDED,
                 OUTPUT_LIMIT_EXCEEDED,
                 INTERNAL_ERROR,
                 LOST -> true;

            case QUEUED, RUNNING -> false;
        };
    }
}