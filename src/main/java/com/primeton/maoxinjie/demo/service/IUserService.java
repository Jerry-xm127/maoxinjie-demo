package com.primeton.maoxinjie.demo.service;

import java.util.List;

import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.PageModel;

public interface IUserService {
	
	//创建一个用户
	int createUser(UserModel userModel) throws Exception;
	//通过ID删除用户
	int removeUserById(int id) throws Exception;
	//删除用户信息
	int removeUserForBatch(int[] ids) throws Exception;
	//修改用户信息
	int modifyUser(UserModel userModel) throws Exception;
	//通过账号和密码获取用户
	UserModel getUserByAccountAndPwd(String account, String pwd) throws Exception;
	//通过id获取用户信息
	UserModel getUserByID(int id) throws Exception;
	//分页+搜索查询
	List<UserModel> queryUserByPage(PageModel<UserModel> page) throws Exception;
	//搜索查询用户的数量
	int getPersonCount(UserModel userModel) throws Exception;
}
