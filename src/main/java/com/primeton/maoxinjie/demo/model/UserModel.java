package com.primeton.maoxinjie.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

/**
 * 用户实体类
 * @author MAIBENBEN
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="用户对象user")
public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 8920689769707837446L;
	
	private Integer userId;  			//主键
	private String userName;  			//用户姓名
	private String userAccount;		//用户账号
	private String userPwd;			//密码
	private Integer userAge;			//年龄
	private String userSex;			//性别   0：女	1：男
	private String userPhone;			//联系电话
	private String userRemark;         //备注
	private String userStatus;			//用户状态   0：不可用   1：可用
	
	private OrganizationModel org;				//用户与组织机构关系

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public OrganizationModel getOrg() {
		return org;
	}

	public void setOrg(OrganizationModel org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName + ", userAccount=" + userAccount + ", userPwd="
				+ userPwd + ", userAge=" + userAge + ", userSex=" + userSex + ", userPhone=" + userPhone
				+ ", userRemark=" + userRemark + ", userStatus=" + userStatus + ", org=" + org + "]";
	}

	
}
