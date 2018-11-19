package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
import com.primeton.maoxinjie.demo.dao.IOrganizationDao;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusiException;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.model.UserModel;
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
@Transactional
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
			throw new BusiException(ResultCodeEnum.ORG_NULL_ERROR.getCode(),ResultCodeEnum.ORG_NULL_ERROR.getMessage());
		}
		if(organizationDao.getOrganizationByOrgName(organizationModel.getoName()) != null) {
			throw new BusiException(ResultCodeEnum.ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ORG_EXIST_ERROR.getMessage());
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
			throw new BusiException(ResultCodeEnum.ORG_NULL_ERROR.getCode(),ResultCodeEnum.ORG_NULL_ERROR.getMessage());
		}
		OrganizationModel record = organizationDao.getOrganizationByID(organizationModel.getId());
		if(record == null || record.getoName().equals(organizationModel.getoName())) {
			throw new BusiException(ResultCodeEnum.ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ORG_EXIST_ERROR.getMessage());
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
		int num = 0;
		//首先判断id对应的信息是否存在
		OrganizationModel record = organizationDao.getOrganizationByID(id);
		if(record == null) {
			throw new BusiException(ResultCodeEnum.ID_ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ID_ORG_EXIST_ERROR.getMessage());
		}
		//在判断该组织机构对应的有没有用户
		List<UserModel> userList = userDao.queryUserByOID(id);
		if(userList.size() > 0) {
			throw new BusiException(ResultCodeEnum.ORG_USER_NOT_NULL_ERROR.getCode(), ResultCodeEnum.ORG_USER_NOT_NULL_ERROR.getMessage());
		}
			num = organizationDao.deleteOrganizationById(id);
		return num;
	}

	/**
	 * 
	 * <p>Description: 组织机构的分页+搜索查询</p> 
	 * @param pageNo
	 * @param pageSize
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<OrganizationModel> queryOrgByPage(int pageNo, int pageSize, OrganizationModel organizationModel)
			throws Exception {
		//对于官方文档所说PageHelper方法调用后紧跟 MyBatis 查询方法，这就是安全的
		PageHelper.startPage(pageNo, pageSize);
		List<OrganizationModel> orgList = organizationDao.queryOrganizationByPage(organizationModel);
	return new PageInfo<>(orgList);
	}

}
