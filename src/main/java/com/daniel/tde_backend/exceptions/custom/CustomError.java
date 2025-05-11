package com.daniel.tde_backend.exceptions.custom;

import java.time.Instant;

public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public CustomError(String error, Instant timestamp, String path, Integer status) {
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public Integer getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
