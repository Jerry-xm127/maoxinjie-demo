package com.primeton.maoxinjie.demo.dao;

import java.util.List;

import com.primeton.maoxinjie.demo.model.OrganizationModel;


/**
 * 组织机构管理的数据访问接口
 * @author MAIBENBEN
 *
 */
public interface IOrganizationDao {

	/**
	 * 
	 * <p>Description: 添加组织机构</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	int insertOrganization(OrganizationModel organizationModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id删除组织机构</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteOrganizationById(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 修改组织机构</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	int updateOrganization(OrganizationModel organizationModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id获取组织机构详情</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OrganizationModel getOrganizationByID(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过名称查询组织机构</p>
	 * @parameter
	 * @return 
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	OrganizationModel getOrganizationByOrgName(String orgName) throws Exception;
	
	/**
	 * 
	 * <p>Description: 分页+搜索查询</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	List<OrganizationModel> queryOrganizationByPage(OrganizationModel organizationModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 搜索组织机构的数量</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	int countOrganization(OrganizationModel organizationModel) throws Exception;
}
