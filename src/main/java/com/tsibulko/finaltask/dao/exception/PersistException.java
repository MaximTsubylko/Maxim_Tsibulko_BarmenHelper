package com.tsibulko.finaltask.dao.exception;


public class PersistException extends Exception {

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Exception e) {

    }

}

