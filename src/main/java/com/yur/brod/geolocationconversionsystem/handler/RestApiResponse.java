package com.yur.brod.geolocationconversionsystem.handler;

import java.time.LocalTime;

public class RestApiResponse {
    private LocalTime timestamp;
    private String message;
    private String details;
    public RestApiResponse(LocalTime timestamp, String message, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
