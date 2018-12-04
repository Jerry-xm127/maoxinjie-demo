package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.dao.IOrganizationDao;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusyException;
import com.primeton.maoxinjie.demo.exception.ResultCodeEnum;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;
import com.primeton.maoxinjie.demo.util.StrUtil;

/**
 * 
 * <p>Title: OrganizationServiceImpl</p>
 * <p>Description: 组织机构管理service接口实现</p>  
 * @author Jerry
 * @date 2018年11月16日 
 *
 */
@Service
@JsonIgnoreProperties(ignoreUnknown=true)
@Transactional(rollbackFor=Exception.class)
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
	public ResponseResultUtil createOrganization(OrganizationModel organizationModel) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		//判断创建的组织机构信息是否为空
		if(organizationModel == null) {
			throw new BusyException(ResultCodeEnum.ORG_NULL_ERROR.getCode(),ResultCodeEnum.ORG_NULL_ERROR.getMessage());
		}
		//判断添加的组织机构名称是否存在
		if(organizationDao.getOrganizationByOrgName(organizationModel.getOrgName()) != null) {
			throw new BusyException(ResultCodeEnum.ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ORG_EXIST_ERROR.getMessage());
		}
		if(organizationModel.getOrgPid() == null) {
			organizationModel.setOrgPid(0);
		}
		if(organizationDao.insertOrganization(organizationModel) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
	}
	
	/**
	 * 
	 * <p>Description: 通过id单个删除组织机构</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil removeOrganizationById(int id) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		//首先判断id对应的信息是否存在
		OrganizationModel record = organizationDao.getOrganizationByID(id);
		if(record == null) {
			throw new BusyException(ResultCodeEnum.ID_ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ID_ORG_EXIST_ERROR.getMessage());
		}
		//在判断该组织机构对应的有没有用户
		List<UserModel> userList = userDao.queryUserByOID(id);
		if(userList.size() > 0) {
			throw new BusyException(ResultCodeEnum.ORG_USER_NOT_NULL_ERROR.getCode(), ResultCodeEnum.ORG_USER_NOT_NULL_ERROR.getMessage());
		}
		if (organizationDao.deleteOrganizationById(id) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 修改组织机构信息</p> 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil modifyOrganization(OrganizationModel organizationModel) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		if(organizationModel == null) {
			throw new BusyException(ResultCodeEnum.ORG_NULL_ERROR.getCode(),ResultCodeEnum.ORG_NULL_ERROR.getMessage());
		}
		OrganizationModel record = organizationDao.getOrganizationByID(organizationModel.getOrgId());
		if(record == null) {
			throw new BusyException(ResultCodeEnum.ID_ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ID_ORG_EXIST_ERROR.getMessage());
		}else if (record.getOrgName().equals(organizationModel.getOrgName())) {
			throw new BusyException(ResultCodeEnum.ORG_EXIST_ERROR.getCode(),ResultCodeEnum.ORG_EXIST_ERROR.getMessage());
		}
		if (organizationDao.updateOrganization(organizationModel) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 通过id查询组织机构</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil getOrganizationByID(int id) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		if (null != organizationDao.getOrganizationByID(id)) {
			responseResult = ResponseResultUtil.success();
			responseResult.put("data", organizationDao.getOrganizationByID(id));
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
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
	public ResponseResultUtil queryOrgsByPage(int pageNo, int pageSize, String orgName)
			throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		OrganizationModel searchOrg = new OrganizationModel();
		if(StrUtil.isNotNull(orgName)) {
			searchOrg.setOrgName(orgName);
		}
		//对于官方文档所说PageHelper方法调用后紧跟 MyBatis 查询方法，这就是安全的
		PageHelper.startPage(pageNo, pageSize);
		List<OrganizationModel> orgList = organizationDao.queryOrganizationByPage(searchOrg);;
		PageInfo<OrganizationModel> pageInfo = new PageInfo<>(orgList);
		if (pageInfo.getList().size() > 0) {
			responseResult = ResponseResultUtil.success();
			responseResult.put("data", pageInfo);
		}else {
			responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			responseResult.put("data", pageInfo.getList());
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 根据pid查询机构下边的子数据</p> 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil queryOrgsByPid(Integer pid) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
				List<OrganizationModel> orgList = organizationDao.queryOrganizationsByPid(pid);
				if (orgList.size() > 0) {
					responseResult = ResponseResultUtil.success();
					responseResult.put("data", orgList);
				}else {
					responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
					responseResult.put("data", orgList);
				}
				return responseResult;
	}

}
