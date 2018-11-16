package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusiException;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.PageModelUtil;
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
			throw new BusiException("用户名或密码或姓名不能为空!");
		}
		//对于创建用户来说先判断该用户是否已经存在
		if(userDao.getUserByName(userModel.getuName()) != null) {
			throw new BusiException("该用户已存在!");
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
			throw new BusiException("修改的用户信息为空!");
		}
		UserModel record = userDao.getUserByID(userModel.getId());
		if(record == null || record.getuName().equals(userModel.getuName())) {
			throw new BusiException("用户名已存在不能进行修改");
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
		return userDao.deleteUserById(id);
	}

	@Override
	public List<UserModel> queryUserByPage(PageModelUtil<UserModel> page) throws Exception {
		return userDao.queryUserByPage(page);
	}

	@Override
	public int getPersonCount(UserModel userModel) throws Exception {
		return userDao.queryPersonCount(userModel);
	}



}
