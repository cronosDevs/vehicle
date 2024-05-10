package com.tech.vehicle.util.exception.domain;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public UnauthorizedException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
