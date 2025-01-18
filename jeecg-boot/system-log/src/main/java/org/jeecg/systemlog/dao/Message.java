package org.jeecg.systemlog.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("@timestamp")
    private String timestamp;

    @JsonProperty("@version")
    private String version;

    private String message;

    @JsonProperty("logger_name")
    private String loggerName;

    @JsonProperty("thread_name")
    private String threadName;

    private String level;

    @JsonProperty("level_value")
    private long levelValue;

    @JsonProperty("stack_trace")
    private String stackTrace;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(long levelValue) {
        this.levelValue = levelValue;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
