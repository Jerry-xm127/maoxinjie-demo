package com.primeton.maoxinjie.demo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

/**
 * 组织管理的实体类
 * @author MAIBENBEN
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@ApiModel(description="组织机构对象organization")
public class OrganizationModel implements Serializable{

	private static final long serialVersionUID = 8330685180188833372L;
	
	private Integer id;    			//主键
	private String oName;			    //组织机构名称
	private String oAddress;             //机构地址
	private Integer oPID; 				//机构的父级ID
	private String oStatus;            //可用状态          0：不可用    1可用
	private String oRemark;				//备注
	
	private List<UserModel> users;     //用户

	public OrganizationModel() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public Integer getoPID() {
		return oPID;
	}

	public void setoPID(Integer oPID) {
		this.oPID = oPID;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	public String getoRemark() {
		return oRemark;
	}

	public void setoRemark(String oRemark) {
		this.oRemark = oRemark;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "OrganizationModel [id=" + id + ", oName=" + oName + ", oAddress=" + oAddress + ", oPID=" + oPID
				+ ", oStatus=" + oStatus + ", oRemark=" + oRemark + ", users=" + users + "]";
	}


}
