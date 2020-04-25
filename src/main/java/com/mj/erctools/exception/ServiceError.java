package com.mj.erctools.exception;

import org.springframework.http.HttpStatus;

public enum ServiceError {
    ALREADY_USE_ID(HttpStatus.BAD_REQUEST, "이미 사용중인 ID 입니다.");

    private HttpStatus status;
    private String message;

    private ServiceError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
