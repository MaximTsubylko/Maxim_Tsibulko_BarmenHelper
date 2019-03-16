package com.tsibulko.finaltask.service;


import com.tsibulko.finaltask.dao.DaoException;

public class ServiceException extends Exception {

    int errCode;

    public ServiceException(String error) {
    }

    public ServiceException(int code) {
        this.errCode = code;

    }

    public ServiceException(Exception e,int code){
        super();
        this.errCode = code;
    }
    public ServiceException(Exception e) {
    }

    public ServiceException(Exception e, String msg) {
    }

    public ServiceException(String msg, DaoException e) {

    }

    public int getCode() {
        return errCode;
    }
}

