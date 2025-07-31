package com.shiyueoe.streamPattern;

public class LogEntry {
    String level; // "INFO", "WARN", "ERROR"
    String message;
    long timestamp;

    LogEntry(String level, String message, long timestamp) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "level='" + level + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
