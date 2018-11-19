package com.primeton.maoxinjie.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

/**
 * 统一异常处理类
 * 可以根据不同的异常类型进行处理
 * @author MAIBENBEN
 *
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
    /**
     * 处理的异常类型
     */
    @ExceptionHandler(value = BusiException.class)
    public ResponseResultUtil defaultErrorHandler(HttpServletRequest req, Exception e) {
    	 log.error("业务异常:", e);
         return ResponseResultUtil.error("业务异常:" + e.getMessage());
    }
    
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResultUtil notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResponseResultUtil.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResultUtil handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseResultUtil.error("服务器错误，请联系管理员");
    }
} 