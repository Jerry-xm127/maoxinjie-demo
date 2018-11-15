package com.primeton.maoxinjie.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.primeton.maoxinjie.demo.dao.IOrganizationDao;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;

/**
 * 组织机构服务
 * @author MAIBENBEN
 *
 */
@Service
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private IOrganizationDao organizationDao;

	@Override
	public int createOrganization(OrganizationModel organizationModel) throws Exception {
		return organizationDao.insertOrganization(organizationModel);
	}

	@Override
	public int removeOrganization(int[] ids) throws Exception {
		return organizationDao.deleteOrganization(ids);
	}

	@Override
	public int modifyOrganization(OrganizationModel organizationModel) throws Exception {
		return organizationDao.updateOrganization(organizationModel);
	}

	@Override
	public OrganizationModel getOrganizationByID(int id) throws Exception {
		return organizationDao.getOrganizationByID(id);
	}

	@Override
	public int removeOrganizationById(int id) throws Exception {
		return organizationDao.deleteOrganizationById(id);
	}

}
