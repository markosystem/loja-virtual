package com.capitani.brasilprev.lojavirtual.util;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class MessageResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String message;

    public MessageResponse(HttpStatus status, String message) {
        this.timestamp = new Date();
        this.status = status.value();
        this.message = message;
        switch (status) {
            case NOT_FOUND:
                this.error = "Not Found";
                break;
            case BAD_REQUEST:
                this.error = "Forbidden";
                break;
            case UNAUTHORIZED:
                this.error = "Unauthorized";
                break;
            default:
                this.error = "Ok";
                break;
        }

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
