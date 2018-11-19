package com.primeton.maoxinjie.demo.dao;

import java.util.List;

import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.util.PageModelUtil;


/**
 * 组织机构管理的数据访问接口
 * @author MAIBENBEN
 *
 */
public interface IOrganizationDao {

	//添加组织机构
	int insertOrganization(OrganizationModel organizationModel) throws Exception;
	//通过id删除组织机构
	int deleteOrganizationById(int id) throws Exception;
	//批量删除组织机构
	int deleteOrganization(int[] ids) throws Exception;
	//修改组织机构
	int updateOrganization(OrganizationModel organizationModel) throws Exception;
	//通过id获取组织机构详情
	OrganizationModel getOrganizationByID(int id) throws Exception;
	//通过名称查询组织机构
	OrganizationModel getOrganizationByOrgName(String orgName) throws Exception;
	//分页+搜索查询
	List<OrganizationModel> queryOrganizationByPage(OrganizationModel organizationModel) throws Exception;
	//搜索查询用户的数量
	int getPersonCount(OrganizationModel organizationModel) throws Exception;
}
