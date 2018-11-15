package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.PageModel;

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

	@Override
	public int createUser(UserModel userModel) throws Exception {
		
		return userDao.insertUser(userModel);
	}

	@Override
	public int removeUserForBatch(int[] ids) throws Exception {
		return userDao.deleteUserForBatch(ids);
	}

	@Override
	public int modifyUser(UserModel userModel) throws Exception {
		return userDao.updateUser(userModel);
	}

	@Override
	public UserModel getUserByAccountAndPwd(String account, String pwd) throws Exception {
		UserModel userModel = new UserModel();
		userModel.setuAccount(account);
		userModel.setuPwd(pwd);
		return userDao.getUserByAccountAndPwd(userModel);
	}

	@Override
	public UserModel getUserByID(int id) throws Exception {
		return userDao.getUserByID(id);
	}

	@Override
	public int removeUserById(int id) throws Exception {
		return userDao.deleteUserById(id);
	}

	@Override
	public List<UserModel> queryUserByPage(PageModel<UserModel> page) throws Exception {
		return userDao.queryUserByPage(page);
	}

	@Override
	public int getPersonCount(UserModel userModel) throws Exception {
		return userDao.queryPersonCount(userModel);
	}



}
