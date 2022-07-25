package org.cs.log.assignment.domain;

public class LogEvent {
    private String id;
    private long timestamp;
    private String type;
    private String host;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
