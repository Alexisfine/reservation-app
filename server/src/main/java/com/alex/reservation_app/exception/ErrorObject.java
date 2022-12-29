package com.alex.reservation_app.exception;

import java.time.ZonedDateTime;

public class ErrorObject {
    private Integer httpStatus;
    private String message;
    private ZonedDateTime timestamp;

    public ErrorObject(Integer httpStatus, String message, ZonedDateTime timestamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErrorObject{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
