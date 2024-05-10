package com.tech.vehicle.util.exception.domain;

public class NotFoundException extends BaseException {
    public NotFoundException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public NotFoundException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
