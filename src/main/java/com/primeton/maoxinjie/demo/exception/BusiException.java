package com.primeton.maoxinjie.demo.exception;

/**
 * 
 * <p>Title: BusiException</p>
 * <p>Description: 自定义的异常</p>  
 * @author Jerry
 * @date 2018年11月20日 
 *
 */
public class BusiException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    

    public BusiException(Integer code, String message) {
        super(message);
    }

}
