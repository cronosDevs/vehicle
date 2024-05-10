package com.tech.vehicle.util.exception.domain;

public class InternalServerException extends BaseException {
    public InternalServerException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public InternalServerException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
