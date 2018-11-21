package com.primeton.maoxinjie.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.controller.UserController;
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	private Logger log = LoggerFactory.getLogger(UserTestCase.class);
	
	@Autowired
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	
	
	@Test
	public void testUser() throws Exception {
		this.testCreateUser();
		//this.testDeleteUser();
		//this.testUpdateUser();
		//this.testGetUserById();
		//this.testQueryUser();
		
	}

	public void testCreateUser() throws Exception{
		UserModel userModel = new UserModel();
		userModel.setUserAccount("ceshiseven");
		userModel.setUserName("测试者7");
		userModel.setUserPwd("123456");
		userModel.setUserAge(25);
		userModel.setUserSex("1");
		ResponseResultUtil responseResult = userController.createUser(userModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testDeleteUser() throws Exception{
		int id = 24;
		ResponseResultUtil responseResult = userController.removeUser(id);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testUpdateUser() throws Exception{
		UserModel userModel = new UserModel();
		userModel.setUserId(25);
		userModel.setUserAccount("ceshioneUpdate");
		userModel.setUserName("测试者1修改");
		userModel.setUserAge(29);;
		userModel.setUserSex("0");
		userModel.setUserStatus("1");
		ResponseResultUtil responseResult = userController.modifyUser(userModel.getUserId(), userModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testGetUserById() throws Exception {
		int id = 25;
		ResponseResultUtil responseResult = userController.getUser(id);
		Assert.assertNotNull(responseResult.get("data"));;
	}
	
	public void testQueryUser() throws Exception{
		int pageNo = 1;
		int pageSize = 5;
		String userName = null;
		String sex = null;
		ResponseResultUtil responseResult = userController.queryUsers(pageNo, pageSize, userName, sex);
		Assert.assertNotNull(responseResult.get("data"));
	}
}
