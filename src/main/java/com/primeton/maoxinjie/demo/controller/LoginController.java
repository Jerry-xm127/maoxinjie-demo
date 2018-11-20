package com.primeton.maoxinjie.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户登录管理操作入口
 * @author MAIBENBEN
 *
 */
@Api(value="登录管理的相关api",tags="登录管理模块")
@RestController
@RequestMapping("/api/actions/")
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="根据账号密码登录",notes="验证登录是否正确",response=ResponseResultUtil.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="account",value="用户账号",required=true,paramType="query"),
			@ApiImplicitParam(name="pwd",value="密码",required=true,paramType="query")})
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseResultUtil getUserByAccountAndPwd(@RequestParam String account, @RequestParam String pwd,HttpSession httpSession) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			UserModel user = userService.getUserByAccountAndPwd(account,pwd);
			if (null != user) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", user);
				httpSession.setAttribute("user", user);
			}else {
				responseResult = ResponseResultUtil.error(ResultCodeEnum.ACCOUNT_PWD_ERROR.getCode(), ResultCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.error();
			e.printStackTrace();
		}
		return responseResult;
	}

}
