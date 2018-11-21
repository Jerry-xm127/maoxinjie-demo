package com.primeton.maoxinjie.demo.controller;

import javax.servlet.http.HttpSession;

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
import com.primeton.maoxinjie.demo.exception.BusyException;
import com.primeton.maoxinjie.demo.exception.ResultCodeEnum;
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
	

	@Autowired
	public IUserService userService;
	
	
	@ApiOperation(value="添加用户信息",notes="添加用户信息",response=ResponseResultUtil.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userModel",value="用户信息"),
	})
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResultUtil createUser(@RequestBody UserModel userModel) throws Exception {
		return userService.createUser(userModel);
	}
	
	@ApiOperation(value="根据id删除用户信息",notes="删除用户信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResultUtil removeUser(@PathVariable("id") int id) throws Exception {
		return userService.removeUserById(id);
	}
	
	@ApiOperation(value="通过id修改",notes="通过id修改用户信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseResultUtil modifyUser(@PathVariable("id") int id, @RequestBody UserModel userModel) throws Exception {
		return userService.modifyUser(userModel);
	}
	
	@ApiOperation(value="通过id获取",notes="通过id获取",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseResultUtil getUser(@PathVariable("id") int id) throws Exception {
		return userService.getUserByID(id);
	}
	

	
	@ApiOperation(value = "分页+搜索获取用户信息", notes="模糊查询及分页显示",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseResultUtil queryUsers(@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
									@RequestParam(value="pageSize",defaultValue="5") int pageSize,
									@RequestParam(name="userName",required=false) String userName,
									@RequestParam(name="sex",required=false) String sex) throws Exception {
		UserModel serachUser = new UserModel();
		serachUser.setUserName(userName);
		serachUser.setUserSex(sex);
		return userService.queryUserByPage(pageNo, pageSize, serachUser);
	}
	
	@ApiOperation(value="根据账号密码登录",notes="验证登录是否正确",response=ResponseResultUtil.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="userModel",value="用户信息")})
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseResultUtil login(@RequestBody UserModel userModel,HttpSession httpSession) throws Exception {
		return userService.getUserByAccountAndPwd(userModel,httpSession);
	}
}
