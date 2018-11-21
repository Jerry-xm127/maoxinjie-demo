package com.primeton.maoxinjie.demo.service;

import javax.servlet.http.HttpSession;

import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

public interface IUserService {
	
	//创建一个用户
	ResponseResultUtil createUser(UserModel userModel) throws Exception;
	//通过ID删除用户
	ResponseResultUtil removeUserById(int id) throws Exception;
	//修改用户信息
	ResponseResultUtil modifyUser(UserModel userModel) throws Exception;
	//通过账号和密码获取用户
	ResponseResultUtil getUserByAccountAndPwd(UserModel userModel,HttpSession httpSession) throws Exception;
	//通过id获取用户信息
	ResponseResultUtil getUserByID(int id) throws Exception;
	//分页+搜索查询
	ResponseResultUtil queryUserByPage(int pageNo, int pageSize, UserModel userModel) throws Exception;
	//搜索查询用户的数量
	int countUser(UserModel userModel) throws Exception;
}
