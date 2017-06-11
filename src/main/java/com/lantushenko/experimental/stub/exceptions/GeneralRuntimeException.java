package com.lantushenko.experimental.stub.exceptions;

public class GeneralRuntimeException extends RuntimeException {

    public GeneralRuntimeException(String pattern, Object... params) {
        super(String.format(pattern, params));
    }
}
