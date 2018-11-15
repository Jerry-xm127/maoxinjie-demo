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
	
	private Integer id;  			//主键
	private String uName;  			//用户姓名
	private String uAccount;		//用户账号
	private String uPwd;			//密码
	private Integer uAge;			//年龄
	private String uSex;			//性别   0：女	1：男
	private String uPhone;			//联系电话
	private String uRemark;         //备注
	private String uStatus;			//用户状态   0：不可用   1：可用
	
	private OrganizationModel org;				//用户与组织机构关系
	
	public UserModel() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuAccount() {
		return uAccount;
	}

	public void setuAccount(String uAccount) {
		this.uAccount = uAccount;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public Integer getuAge() {
		return uAge;
	}

	public void setuAge(Integer uAge) {
		this.uAge = uAge;
	}

	public String getuSex() {
		return uSex;
	}

	public void setuSex(String uSex) {
		this.uSex = uSex;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuRemark() {
		return uRemark;
	}

	public void setuRemark(String uRemark) {
		this.uRemark = uRemark;
	}

	public String getuStatus() {
		return uStatus;
	}

	public void setuStatus(String uStatus) {
		this.uStatus = uStatus;
	}

	public OrganizationModel getOrg() {
		return org;
	}

	public void setOrg(OrganizationModel org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", uName=" + uName + ", uAccount=" + uAccount + ", uPwd=" + uPwd + ", uAge="
				+ uAge + ", uSex=" + uSex + ", uPhone=" + uPhone + ", uRemark=" + uRemark + ", uStatus=" + uStatus
				+ ", org=" + org + "]";
	}


	
	
}
