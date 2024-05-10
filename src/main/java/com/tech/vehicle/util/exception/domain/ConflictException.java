package com.tech.vehicle.util.exception.domain;

public class ConflictException extends BaseException {
    public ConflictException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public ConflictException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
