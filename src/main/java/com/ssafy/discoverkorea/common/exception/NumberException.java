package com.ssafy.discoverkorea.common.exception;

public class NumberException extends IllegalArgumentException {

    public NumberException() {
    }

    public NumberException(String s) {
        super(s);
    }

    public NumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberException(Throwable cause) {
        super(cause);
    }
}
