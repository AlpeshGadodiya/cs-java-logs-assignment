package org.cs.log.assignment.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    private String id;
    private String host;
    private String type;
    private long duration;
    private boolean alert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", host=" + host + ", type=" + type + ", duration=" + duration + ", alert=" + alert
                + "]";
    }
}

