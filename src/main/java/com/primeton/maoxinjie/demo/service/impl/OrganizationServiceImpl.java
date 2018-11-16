package com.primeton.maoxinjie.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.primeton.maoxinjie.demo.dao.IOrganizationDao;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusiException;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;

/**
 * 
 * <p>Title: OrganizationServiceImpl</p>
 * <p>Description: </p>  
 * @author Jerry
 * @date 2018年11月16日 
 *
 */
@Service
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private IOrganizationDao organizationDao;
	@Autowired
	private IUserDao userDao;

	/**
	 * 
	 * <p>Description: 创建组织机构</p> 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int createOrganization(OrganizationModel organizationModel) throws Exception {
		int num = 0;
		if(organizationModel == null) {
			throw new BusiException("所创建的组织机构信息为空!");
		}
		if(organizationDao.getOrganizationByOrgName(organizationModel.getoName()) != null) {
			throw new BusiException("该组织机构已存在!");
		}
		num = organizationDao.insertOrganization(organizationModel);
		return num;
	}

	/**
	 * 
	 * <p>Description: 批量删除组织机构数据</p> 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Override
	public int removeOrganization(int[] ids) throws Exception {
		return organizationDao.deleteOrganization(ids);
	}

	/**
	 * 
	 * <p>Description: 修改组织机构信息</p> 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int modifyOrganization(OrganizationModel organizationModel) throws Exception {
		int num = 0;
		if(organizationModel == null) {
			throw new BusiException("修改的组织机构信息为空!");
		}
		OrganizationModel record = organizationDao.getOrganizationByID(organizationModel.getId());
		if(record == null || record.getoName().equals(organizationModel.getoName())) {
			throw new BusiException("用户名已存在不能进行修改");
		}
			num = organizationDao.updateOrganization(organizationModel);
		return num;
	}

	/**
	 * 
	 * <p>Description: 通过id查询组织机构</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrganizationModel getOrganizationByID(int id) throws Exception {
		
		return organizationDao.getOrganizationByID(id);
	}

	/**
	 * 
	 * <p>Description: 通过id单个删除组织机构</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int removeOrganizationById(int id) throws Exception {
		return organizationDao.deleteOrganizationById(id);
	}

}
