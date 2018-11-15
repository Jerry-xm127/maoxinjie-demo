package com.primeton.maoxinjie.demo.constant;

/**
 * 存放异常码的常量
 * @author MAIBENBEN
 *
 */
public enum ResultCodeEnum {

	SUCCESS(0000,"操作成功"),
	ERROR(0001,"操作失败"),
	SYSTEM_ERROR(1000,"系统错误"),
	ACCOUNT_PWD_ERROR(2000,"用户名或密码错误"),
	NO_FIND_DATA(2001,"未查询到相关数据"),
	UNAUTHORIZED_ERROR(0002,"未授权错误"),
	NOT_LOGIN(0003,"未登录错误");
	
	private Integer code;		//状态码
	private String message;		//消息提示

	
	private ResultCodeEnum(Integer code, String message) {	
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
