package com.primeton.maoxinjie.demo.util;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;


/**
 * 返回请求结果消息
 * @author MAIBENBEN
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseResult extends HashMap<String, Object>{

	private static final long serialVersionUID = 1826731312620400974L;

	/**
	 * 初始化消息对象
	 */
    public ResponseResult(){
    }
    
    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    @JsonIgnore
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    
    /**
     * 返回系统错误消息
     * 
     * @return 错误消息
     */
    public static ResponseResult sysError() {
        return error(ResultCodeEnum.SYSTEM_ERROR.getCode(),ResultCodeEnum.SYSTEM_ERROR.getMessage());
    }
    
    /**
     * 返回用户操作错误信息
     * @return 错误消息
     */
    public static ResponseResult error() {
        return error(ResultCodeEnum.ERROR.getCode(),ResultCodeEnum.ERROR.getMessage());
    }
    
    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static ResponseResult error(String msg) {
        return error(500, msg);
    }
    
    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static ResponseResult error(int code, String msg) {
        ResponseResult json = new ResponseResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success(ResultCodeEnum.SUCCESS.getCode(),ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static ResponseResult success(int code, String msg) {
        ResponseResult json = new ResponseResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

}
