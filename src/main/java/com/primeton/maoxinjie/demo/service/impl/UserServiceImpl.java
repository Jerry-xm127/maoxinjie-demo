package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusiException;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.StrUtil;

/**
 * 用户管理服务
 * @author MAIBENBEN
 *
 */
@Service
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	/**
	 * 
	 * <p>Description: 创建添加用户信息</p> 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int createUser(UserModel userModel) throws Exception {
		int num = 0;
		//判断必要的属性为空
		if(StrUtil.isNull(userModel.getuAccount()) || StrUtil.isNull(userModel.getuPwd()) || StrUtil.isNull(userModel.getuName())) {
			throw new BusiException(ResultCodeEnum.ACCOUNT_PWD_USERNAME_ERROR.getCode(),ResultCodeEnum.ACCOUNT_PWD_USERNAME_ERROR.getMessage());
		}
		//对于创建用户来说先判断该用户是否已经存在
		if(userDao.getUserByName(userModel.getuName()) != null) {
			throw new BusiException(ResultCodeEnum.USER_EXIST_ERROR.getCode(),ResultCodeEnum.USER_EXIST_ERROR.getMessage());
		}
		num = userDao.insertUser(userModel);
		return num;
	}

	/**
	 * 
	 * <p>Description: 通过id批量删除用户</p> 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Override
	public int removeUserForBatch(int[] ids) throws Exception {
		return userDao.deleteUserForBatch(ids);
	}

	/**
	 * 
	 * <p>Description: 通过id修改用户信息</p> 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int modifyUser(UserModel userModel) throws Exception {
		int num = 0;
		if( userModel == null) {
			throw new BusiException(ResultCodeEnum.USER_NULL_ERROR.getCode(),ResultCodeEnum.USER_NULL_ERROR.getMessage());
		}
		UserModel record = userDao.getUserByID(userModel.getId());
		if(record == null || record.getuName().equals(userModel.getuName())) {
			throw new BusiException(ResultCodeEnum.USER_EXIST_ERROR.getCode(),ResultCodeEnum.USER_EXIST_ERROR.getMessage());
		}
			num = userDao.updateUser(userModel);
		return num;
	}

	/**
	 * 
	 * <p>Description: 验证登录</p> 
	 * @param account
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserModel getUserByAccountAndPwd(String account, String pwd) throws Exception {
		UserModel userModel = new UserModel();
		userModel.setuAccount(account);
		userModel.setuPwd(pwd);
		return userDao.getUserByAccountAndPwd(userModel);
	}

	/**
	 * 
	 * <p>Description: 通过id查询用户信息</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserModel getUserByID(int id) throws Exception {
		return userDao.getUserByID(id);
	}

	/**
	 * 通过id单个删除用户信息
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int removeUserById(int id) throws Exception {
		int num = 0;
		UserModel record = userDao.getUserByID(id);
		if(record == null) {
			throw new BusiException(ResultCodeEnum.ID_USER_EXIST_ERROR.getCode(),ResultCodeEnum.ID_USER_EXIST_ERROR.getMessage());
		}
			num = userDao.deleteUserById(id);
		return num;
	}

	/**
	 * 
	 * <p>Description: 获取用户的数量</p> 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getPersonCount(UserModel userModel) throws Exception {
		return userDao.queryPersonCount(userModel);
	}

	/**
	 * 
	 * <p>Description: 分页+搜索查询用户信息</p> 
	 * @param pageNo
	 * @param pageSize
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<UserModel> queryUserByPage(int pageNo, int pageSize, UserModel userModel) throws Exception {
		//对于官方文档所说PageHelper方法调用后紧跟 MyBatis 查询方法，这就是安全的
			PageHelper.startPage(pageNo, pageSize);
			List<UserModel> userList = userDao.queryUserByPage(userModel);
		return new PageInfo<>(userList);
	}



}
