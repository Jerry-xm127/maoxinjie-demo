package com.primeton.maoxinjie.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.ResponseResult;

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
	public IUserService UserService;
	
	
	@ApiOperation(value="添加用户信息",notes="添加用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userModel",value="用户账号",required=true,paramType="form",dataTypeClass=UserModel.class),
	})
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResult createUser(UserModel userModel) {
		
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = UserService.createUser(userModel);
			if (num > 0) {
				responseResult = ResponseResult.success();
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResult.sysError();
			e.printStackTrace();
		}
		return responseResult;
	}
	
	@ApiOperation(value="根据id删除用户信息",notes="删除用户信息")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResult removeUser(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = UserService.removeUserById(id);
			if (num > 0) {
				responseResult = ResponseResult.success();
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResult.sysError();
			e.printStackTrace();
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取",notes="通过id获取")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseResult getUser(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			UserModel user = UserService.getUserByID(id);
			if (null != user) {
				responseResult = ResponseResult.success();
				responseResult.put("data", user);
			}else {
				responseResult = ResponseResult.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			}
		} catch (Exception e) {
			responseResult = ResponseResult.sysError();
			e.printStackTrace();
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取",notes="通过id获取")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<ResponseResult> modifyUser(@PathVariable("id") int id,UserModel userModel) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = UserService.modifyUser(userModel);
			if (num > 0) {
				responseResult = ResponseResult.success();
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResult.sysError();
			e.printStackTrace();
		}
		return ResponseEntity.ok(responseResult);
	}
}
