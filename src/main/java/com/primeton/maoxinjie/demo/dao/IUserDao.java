package com.primeton.maoxinjie.demo.dao;


import java.util.List;

import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.PageModelUtil;



/**
 * 用户管理的数据访问接口
 * @author MAIBENBEN
 *
 */
public interface IUserDao {

	//添加用户信息
	int insertUser(UserModel userModel) throws Exception;
	//通过ID删除用户
	int deleteUserById(int id) throws Exception;
	//批量删除用户信息
	int deleteUserForBatch(int[] ids) throws Exception;
	//修改用户信息
	int updateUser(UserModel userModel) throws Exception;
	//通过账号和密码获取用户
	UserModel getUserByAccountAndPwd(UserModel userModel) throws Exception;
	//通过id获取用户信息
	UserModel getUserByID(int id) throws Exception;
	//通过用户名称查询用户
	UserModel getUserByName(String userName) throws Exception;
	//通过orgId获取用户信息
	List<UserModel> queryUserByOID(int orgId) throws Exception;
	//分页+搜索查询
	List<UserModel> queryUserByPage(PageModelUtil<UserModel> page) throws Exception;
	//搜索查询用户的数量
	int queryPersonCount(UserModel userModel) throws Exception;
	
	
}
