package com.primeton.maoxinjie.demo.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.primeton.maoxinjie.demo.model.UserModel;



/**
 * 用户管理的数据访问接口
 * @author MAIBENBEN
 *
 */
public interface IUserDao {

	/**
	 * 
	 * <p>Description: 添加用户信息</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	int insertUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过ID删除用户</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteUserById(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 修改用户信息</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	int updateUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过账号和密码获取用户</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	UserModel getUserByAccountAndPwd(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过id获取用户信息</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserModel getUserByID(int id) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过账号查询用户</p>
	 * @parameter
	 * @return 
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	UserModel getUserByUserAccount(String userAccount) throws Exception;
	
	/**
	 * 
	 * <p>Description: 通过orgId获取用户信息</p>
	 * @parameter
	 * @return 
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<UserModel> queryUserByOID(int orgId) throws Exception;
	
	/**
	 * 
	 * <p>Description: 分页+搜索查询</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	List<UserModel> queryUserByPage(UserModel userModel) throws Exception;
	
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
