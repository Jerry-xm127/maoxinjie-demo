package com.primeton.maoxinjie.demo.service;

import javax.servlet.http.HttpSession;

import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

/**
 * 
 * <p>Title: IUserService</p>
 * <p>Description: 用户管理service接口定义</p>  
 * @author Jerry
 * @date 2018年11月21日 
 *
 */
public interface IUserService {
	
	/**
	 * 
	 * <p>Description: 创建一个用户</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil createUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过ID删除用户</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil removeUserById(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 修改用户信息</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil modifyUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过账号和密码获取用户</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil getUserByAccountAndPwd(UserModel userModel,HttpSession httpSession) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id获取用户信息</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil getUserByID(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 分页+搜索查询</p>
	 * @parameter
	 * @return 
	 * @param pageNo
	 * @param pageSize
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	ResponseResultUtil queryUserByPage(int pageNo, int pageSize, String userName, String sex, int orgId) throws Exception;
	
	/**
	 * 
	 * <p>Description: 搜索查询用户的数量</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	int countUser(UserModel userModel) throws Exception;
}
