package com.primeton.maoxinjie.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理的后端入口
 * @author MAIBENBEN
 *
 */
@Api(value="用户管理的相关api",tags="用户管理模块")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public IUserService UserService;
	
	
	@ApiOperation(value="添加用户信息",notes="添加用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userModel",value="用户账号",required=true,paramType="form",dataTypeClass=UserModel.class),
	})
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResultUtil createUser(@RequestBody UserModel userModel) {
		
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			int num = UserService.createUser(userModel);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value="根据id删除用户信息",notes="删除用户信息")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResultUtil removeUser(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			int num = UserService.removeUserById(id);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取",notes="通过id获取")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseResultUtil getUser(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			UserModel user = UserService.getUserByID(id);
			if (null != user) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", user);
			}else {
				responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取",notes="通过id获取")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseResultUtil modifyUser(@PathVariable("id") int id, @RequestBody UserModel userModel) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		userModel.setId(id);
		try {
			int num = UserService.modifyUser(userModel);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value = "分页+搜索获取用户信息", notes="模糊查询及分页显示")
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseResultUtil queryUsers(@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
									@RequestParam(value="pageSize",defaultValue="5") int pageSize,
									@RequestParam("userName") String userName,
									@RequestParam("sex") String sex) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		UserModel serachUser = new UserModel();
		serachUser.setuName(userName);
		serachUser.setuSex(sex);
		try {
			PageInfo<UserModel> userList = UserService.queryUserByPage(pageNo, pageSize, serachUser);
			if (userList.getList().size() > 0) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", userList);
			}else {
				responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
}
