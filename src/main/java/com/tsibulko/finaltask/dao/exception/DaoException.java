package com.tsibulko.finaltask.dao.exception;


import java.sql.SQLException;

public class DaoException extends Exception {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception e) {

    }

    public DaoException(Exception e, String s) {
    }
}
