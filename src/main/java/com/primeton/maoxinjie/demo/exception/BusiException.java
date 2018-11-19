package com.primeton.maoxinjie.demo.exception;


public class BusiException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    

    public BusiException(Integer code, String message) {
        super(message);
    }

}
