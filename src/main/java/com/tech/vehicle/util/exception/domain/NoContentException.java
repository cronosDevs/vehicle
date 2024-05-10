package com.tech.vehicle.util.exception.domain;

public class NoContentException extends BaseException {
    public NoContentException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public NoContentException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
