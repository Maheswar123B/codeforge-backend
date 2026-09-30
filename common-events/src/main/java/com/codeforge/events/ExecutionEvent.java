package com.codeforge.events;

import java.time.Instant;
import java.util.UUID;

public interface ExecutionEvent {

    UUID eventId();

    String executionId();

    int schemaVersion();

    Instant occurredAt();
}