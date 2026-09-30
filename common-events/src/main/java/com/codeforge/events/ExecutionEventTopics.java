package com.codeforge.events;

public final class ExecutionEventTopics {

    private ExecutionEventTopics() {}
    public static final String EXECUTION_REQUEST = "execution.request";

    public static final String EXECUTION_OUTPUT = "execution.output";

    public static final String EXECUTION_STARTED = "execution.started";

    public static final String EXECUTION_COMPLETED = "execution.completed";
}