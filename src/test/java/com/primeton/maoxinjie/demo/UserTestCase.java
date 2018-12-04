package com.primeton.maoxinjie.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.primeton.maoxinjie.demo.controller.UserController;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MaoxinjieDemoApplication.class)
public class UserTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	
	
	@Test
	public void testUser() throws Exception {
		UserModel userModel = this.buildUserModel();
		this.testCreateUser(userModel);
//		this.testUpdateUser(userModel);
//		this.testGetUserById(userModel);
//		this.testQueryUser();
//		this.testDeleteUser(userModel);
	}

	public UserModel buildUserModel(){
		UserModel userModel = new UserModel();
		userModel.setUserId(50);
		userModel.setUserAccount("ceshitest");
		userModel.setUserName("测试Junit");
		userModel.setUserPwd("123456");
		userModel.setUserAge(25);
		userModel.setUserSex("1");
		userModel.setUserPhone("18888888888");
		OrganizationModel org = new OrganizationModel();
		org.setOrgId(8);
		userModel.setOrg(org);
		userModel.setUserStatus("1");
		return userModel;
	}
	
	public void testCreateUser(UserModel userModel) throws Exception{
		ResponseResultUtil responseResult = userController.createUser(userModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}

	public void testGetUserById(UserModel userModel) throws Exception {
		ResponseResultUtil responseResult = userController.getUser(userModel.getUserId());
		Assert.assertNotNull(responseResult.get("data"));;
	}

	
	public void testUpdateUser(UserModel userModel) throws Exception{
		userModel.setUserName("修改Junit");
		userModel.setUserAge(29);;
		userModel.setUserSex("0");
		userModel.setUserStatus("1");
		ResponseResultUtil responseResult = userController.modifyUser(userModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testQueryUser() throws Exception{
		ResponseResultUtil responseResult = userController.queryUsers(1, 5, null, null, 0);
		Assert.assertNotNull(responseResult.get("data"));
	}
	
	public void testDeleteUser(UserModel userModel) throws Exception{
		ResponseResultUtil responseResult = userController.removeUser(userModel.getUserId());
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
}
