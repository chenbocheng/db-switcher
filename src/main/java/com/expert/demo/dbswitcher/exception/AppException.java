package com.expert.demo.dbswitcher.exception;

public class AppException extends RuntimeException {
    public AppException(String exMessage) {
        super(exMessage);
    }

    public AppException(String exMessage, Throwable cause) {
        super(exMessage, cause);
    }
}
