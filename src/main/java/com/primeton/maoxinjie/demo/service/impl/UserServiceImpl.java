package com.primeton.maoxinjie.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.exception.BusyException;
import com.primeton.maoxinjie.demo.exception.ResultCodeEnum;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;
import com.primeton.maoxinjie.demo.util.StrUtil;

/**
 * 用户管理service接口实现
 * @author MAIBENBEN
 *
 */
@Service
@JsonIgnoreProperties(ignoreUnknown=true)
@Transactional(rollbackFor=Exception.class)
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
	public ResponseResultUtil createUser(UserModel userModel) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		//判断必要的属性为空
		if(StrUtil.isNull(userModel.getUserAccount()) || StrUtil.isNull(userModel.getUserPwd()) || StrUtil.isNull(userModel.getUserName())) {
			throw new BusyException(ResultCodeEnum.ACCOUNT_PWD_USERNAME_ERROR.getCode(),ResultCodeEnum.ACCOUNT_PWD_USERNAME_ERROR.getMessage());
		}
		//对于创建用户来说先判断该用户是否已经存在
		if(userDao.getUserByUserAccount(userModel.getUserAccount()) != null) {
			throw new BusyException(ResultCodeEnum.USER_EXIST_ERROR.getCode(),ResultCodeEnum.USER_EXIST_ERROR.getMessage());
		}
		if(userDao.insertUser(userModel) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
	}
	
	/**
	 * 通过id单个删除用户信息
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil removeUserById(int id) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		UserModel record = userDao.getUserByID(id);
		if(record == null) {
			throw new BusyException(ResultCodeEnum.ID_USER_EXIST_ERROR.getCode(),ResultCodeEnum.ID_USER_EXIST_ERROR.getMessage());
		}
		if (userDao.deleteUserById(id) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 通过id修改用户信息</p> 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil modifyUser(UserModel userModel) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		if(userModel == null) {
			throw new BusyException(ResultCodeEnum.USER_NULL_ERROR.getCode(),ResultCodeEnum.USER_NULL_ERROR.getMessage());
		}
		UserModel record = userDao.getUserByID(userModel.getUserId());
		if(record == null ) {
			throw new BusyException(ResultCodeEnum.USER_NULL_ERROR.getCode(),ResultCodeEnum.USER_NULL_ERROR.getMessage());
		}else if(record.getUserName().equals(userModel.getUserName())){
			throw new BusyException(ResultCodeEnum.USER_EXIST_ERROR.getCode(),ResultCodeEnum.USER_EXIST_ERROR.getMessage());
		}
		if (userDao.updateUser(userModel) > 0) {
			responseResult = ResponseResultUtil.success();
		}else {
			responseResult = ResponseResultUtil.error();
		}
		return responseResult;
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
	public ResponseResultUtil getUserByAccountAndPwd(UserModel userModel,HttpSession httpSession) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		UserModel record = userDao.getUserByAccountAndPwd(userModel);
		if (null != record) {
			responseResult = ResponseResultUtil.success();
			responseResult.put("data", record);
			httpSession.setAttribute("user", record);
		}else {
			responseResult = ResponseResultUtil.error(ResultCodeEnum.ACCOUNT_PWD_ERROR.getCode(), ResultCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 通过id查询用户信息</p> 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResultUtil getUserByID(int id) throws Exception {
		ResponseResultUtil responseResult = new ResponseResultUtil();
		UserModel record = userDao.getUserByID(id);
		if (null != record) {
			responseResult = ResponseResultUtil.success();
			responseResult.put("data", record);
		}else {
			responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
		}
		return responseResult;
	}

	/**
	 * 
	 * <p>Description: 获取用户的数量</p> 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Override
	public int countUser(UserModel userModel) throws Exception {
		return userDao.countUser(userModel);
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
	public ResponseResultUtil queryUserByPage(int pageNo, int pageSize, String userName, String sex) throws Exception {
			ResponseResultUtil responseResult = new ResponseResultUtil();
			UserModel searchUser = new UserModel();
			if(StrUtil.isNotNull(userName)) {
				searchUser.setUserName(userName);
			}
			if(StrUtil.isNotNull(sex)) {
				searchUser.setUserSex(sex);
			}
			//对于官方文档所说PageHelper方法调用后紧跟 MyBatis 查询方法，这就是安全的
			PageHelper.startPage(pageNo, pageSize);
			List<UserModel> userList = userDao.queryUserByPage(searchUser);
			PageInfo<UserModel> pageInfo = new PageInfo<>(userList);
			if (pageInfo.getList().size() > 0) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", pageInfo);
			}else {
				responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			}
		return responseResult;
	}
}
