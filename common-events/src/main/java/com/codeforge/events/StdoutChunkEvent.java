package com.codeforge.events;

import java.time.Instant;

public record StdoutChunkEvent(
        String executionId,
        String data,
        long sequenceNumber,
        Instant timestamp
) {
}
