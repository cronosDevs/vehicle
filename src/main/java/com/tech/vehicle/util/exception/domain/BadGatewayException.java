package com.tech.vehicle.util.exception.domain;

public class BadGatewayException extends BaseException {

    public BadGatewayException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public BadGatewayException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
