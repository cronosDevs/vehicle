package com.tech.vehicle.util.exception.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends RuntimeException {
    private String errorCode;

    public BaseException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public BaseException(String ex, Throwable cause, String errorCode) {
        super(ex, cause);
        this.errorCode = errorCode;
    }
}
