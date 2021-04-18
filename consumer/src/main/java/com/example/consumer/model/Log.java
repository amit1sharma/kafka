package com.example.consumer.model;

import javax.persistence.*;

@Entity
@Table(name = "log_table")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long time;
    private String appName;
    private String contextId;
    private String message;
    private String logLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public Log() {
    }

    public Log(long time, String appName, String contextId, String message, String logLevel) {
        this.time = time;
        this.appName = appName;
        this.contextId = contextId;
        this.message = message;
        this.logLevel = logLevel;
    }
}