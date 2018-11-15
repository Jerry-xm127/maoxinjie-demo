package com.primeton.maoxinjie.demo.service;

import com.primeton.maoxinjie.demo.model.OrganizationModel;

public interface IOrganizationService {

	//创建一个组织机构信息
	int createOrganization(OrganizationModel organizationModel) throws Exception;
	//批量删除组织机构
	int removeOrganization(int[] ids) throws Exception;
	//通过id删除组织机构
	int removeOrganizationById(int id) throws Exception;
	//修改组织机构
	int modifyOrganization(OrganizationModel organizationModel) throws Exception;
	//通过id获取组织机构详情
	OrganizationModel getOrganizationByID(int id) throws Exception;
}
