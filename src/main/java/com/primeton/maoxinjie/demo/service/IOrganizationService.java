package com.primeton.maoxinjie.demo.service;

import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

public interface IOrganizationService {

	//创建一个组织机构信息
	ResponseResultUtil createOrganization(OrganizationModel organizationModel) throws Exception;
	//通过id删除组织机构
	ResponseResultUtil removeOrganizationById(int id) throws Exception;
	//修改组织机构
	ResponseResultUtil modifyOrganization(OrganizationModel organizationModel) throws Exception;
	//通过id获取组织机构详情
	ResponseResultUtil getOrganizationByID(int id) throws Exception;
	//分页+搜索查询
	ResponseResultUtil queryOrgByPage(int pageNo, int pageSize, OrganizationModel organizationModel) throws Exception;
}
