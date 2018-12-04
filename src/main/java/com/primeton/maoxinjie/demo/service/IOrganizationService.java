package com.primeton.maoxinjie.demo.service;

import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

/**
 * 
 * <p>Title: IOrganizationService</p>
 * <p>Description: 组织机构service接口定义</p>  
 * @author Jerry
 * @date 2018年11月21日 
 *
 */
public interface IOrganizationService {

	/**
	 * 
	 * <p>Description: 创建一个组织机构信息</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil createOrganization(OrganizationModel organizationModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id删除组织机构</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil removeOrganizationById(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 修改组织机构</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil modifyOrganization(OrganizationModel organizationModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id获取组织机构详情</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil getOrganizationByID(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 分页+搜索查询</p>
	 * @parameter
	 * @return 
	 * @param pageNo
	 * @param pageSize
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil queryOrgsByPage(int pageNo, int pageSize, String orgName) throws Exception;
	
	/**
	 * 
	 * <p>Description: 根据pid查询机构下边的子数据</p>
	 * @parameter
	 * @return 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil queryOrgsByPid(Integer pid) throws Exception;
}
