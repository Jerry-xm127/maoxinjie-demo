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
	
	private Integer orgId;    			//主键
	private String orgName;			    //组织机构名称
	private String orgAddress;             //机构地址
	private Integer orgPid; 				//机构的父级ID
	private String orgStatus;            //可用状态          0：不可用    1可用
	private String orgRemark;				//备注
	
	private List<UserModel> users;     //用户

	public OrganizationModel() {
		super();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public Integer getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(Integer orgPid) {
		this.orgPid = orgPid;
	}

	public String getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getOrgRemark() {
		return orgRemark;
	}

	public void setOrgRemark(String orgRemark) {
		this.orgRemark = orgRemark;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "OrganizationModel [orgId=" + orgId + ", orgName=" + orgName + ", orgAddress=" + orgAddress + ", orgPid="
				+ orgPid + ", orgStatus=" + orgStatus + ", orgRemark=" + orgRemark + ", users=" + users + "]";
	}


}
