package com.primeton.maoxinjie.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * 
	 * <p>Description: 创建用户</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="添加用户信息",notes="添加用户信息",response=ResponseResultUtil.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="userModel",value="用户信息"),
	})
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResultUtil createUser(@RequestBody UserModel userModel) throws Exception {
		return userService.createUser(userModel);
	}
	
	/**
	 * 
	 * <p>Description: 根据id删除用户</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="根据id删除用户信息",notes="删除用户信息",response=ResponseResultUtil.class)
	@ApiImplicitParam(value="用户id", name="id", dataType = "Integer", required = true)
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResultUtil removeUser(@PathVariable("id") Integer id) throws Exception {
		return userService.removeUserById(id);
	}
	
	/**
	 * 
	 * <p>Description: 根据id修改用户</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="通过id修改",notes="通过id修改用户信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public ResponseResultUtil modifyUser(@RequestBody UserModel userModel) throws Exception {
		return userService.modifyUser(userModel);
	}
	
	/**
	 * 
	 * <p>Description: 根据id获取单个用户信息</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="通过id获取",notes="通过id获取",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseResultUtil getUser(@PathVariable("id") int id) throws Exception {
		return userService.getUserByID(id);
	}

	/**
	 * 
	 * <p>Description: 分页+搜索查询获取用户</p>
	 * @parameter
	 * @return 
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param sex
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "分页+搜索获取用户信息", notes="模糊查询及分页显示",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseResultUtil queryUsers(@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
									@RequestParam(value="pageSize",defaultValue="5") int pageSize,
									@RequestParam(name="userName",required=false) String userName,
									@RequestParam(name="sex",required=false) String sex,
									@RequestParam(name="orgId",required=false) int orgId) throws Exception {
		return userService.queryUserByPage(pageNo, pageSize, userName, sex, orgId);
	}
	
	/**
	 * 
	 * <p>Description: 用户登录验证</p>
	 * @parameter
	 * @return 
	 * @param userModel
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="根据账号密码登录",notes="验证登录是否正确",response=ResponseResultUtil.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="userModel",value="用户信息")})
	@RequestMapping(value="/login", method=RequestMethod.POST)	
	public ResponseResultUtil login(@RequestBody UserModel userModel,HttpSession httpSession) throws Exception {
		return userService.getUserByAccountAndPwd(userModel,httpSession);
	}
	
	/**
	 * 
	 * <p>Description: 用户退出清除session信息</p>
	 * @parameter
	 * @return 
	 * @param session
	 */
	@ApiOperation(value = "用户退出", response = ResponseResultUtil.class)
    @RequestMapping(value = "/out", method = RequestMethod.DELETE)
    public void signOut(HttpSession session) {
        session.removeAttribute("user");
    }
}
