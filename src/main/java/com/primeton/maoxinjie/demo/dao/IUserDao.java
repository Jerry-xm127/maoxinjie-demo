package com.primeton.maoxinjie.demo.dao;


import java.util.List;

import com.primeton.maoxinjie.demo.model.UserModel;



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
	//修改用户信息
	int updateUser(UserModel userModel) throws Exception;
	//通过账号和密码获取用户
	UserModel getUserByAccountAndPwd(UserModel userModel) throws Exception;
	//通过id获取用户信息
	UserModel getUserByID(int id) throws Exception;
	//通过账号查询用户
	UserModel getUserByUserAccount(String userAccount) throws Exception;
	//通过orgId获取用户信息
	List<UserModel> queryUserByOID(int orgId) throws Exception;
	//分页+搜索查询
	List<UserModel> queryUserByPage(UserModel userModel) throws Exception;
	//搜索查询用户的数量
	int countUser(UserModel userModel) throws Exception;
	
	
}
