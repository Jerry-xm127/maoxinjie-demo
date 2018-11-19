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
	ACCOUNT_PWD_USERNAME_ERROR(2001,"用户名或密码或姓名不能为空!"),
	USER_NULL_ERROR(2002,"修改的用户信息为空!"),
	USER_EXIST_ERROR(2003,"该用户已存在!"),
	ID_USER_EXIST_ERROR(2004,"该id对应的用户不存在"),
	NO_FIND_DATA(2005,"未查询到相关数据"),
	
	
	ORG_NULL_ERROR(3000,"组织机构信息为空!"),
	ORG_EXIST_ERROR(3001,"该组织机构已存在!"),
	ID_ORG_EXIST_ERROR(3002,"该id对应的组织机构不存在"),
	ORG_USER_NOT_NULL_ERROR(3003,"该组织机构对应的用户有数据不能删除"),
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
