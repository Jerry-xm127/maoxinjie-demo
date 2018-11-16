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

import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.controller.UserController;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.util.ResponseResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	/**
	 * 
	 * <p>Description: 测试用户模块</p>
	 * @parameter
	 * @return
	 */
	@Test
	public void testUser() {
		//this.testCreateUser();
		//this.testDeleteUser();
		//this.testUpdateUser();
//		this.testGetUserById();
		this.testQueryUser();
		
	}

	public void testCreateUser() {
		UserModel userModel = new UserModel();
		userModel.setuAccount("ceshiten");
		userModel.setuName("测试者10");;
		userModel.setuPwd("123456");
		userModel.setuAge(15);;
		userModel.setuSex("1");;
		ResponseResult result = userController.createUser(userModel);
		Assert.assertEquals("操作成功", result.success().get("msg"));
	}

	public void testDeleteUser() {
		int id = 89989;
		ResponseResult result = userController.removeUser(id);
		Assert.assertEquals("操作成功", result.success().get("msg"));
	}
	
	public void testUpdateUser() {
		UserModel userModel = new UserModel();
		userModel.setId(16);
		userModel.setuAccount("ceshisix");
		userModel.setuName("测试者16");;
		userModel.setuPwd("123456");
		userModel.setuAge(16);;
		userModel.setuSex("1");;
		ResponseResult result = userController.modifyUser(userModel);
		Assert.assertEquals("操作成功", result.success().get("msg"));
	}
	
	public void testGetUserById() {
		int id = 99;
		ResponseResult result = userController.getUser(id);
		Assert.assertNotNull(result.get("data"));
	}
	
	public void testQueryUser() {
		int pageNo = 1;
		int pageSize = 5;
		String userName = "";
		String sex = "";
		ResponseResult pages = userController.queryUsers(pageNo, pageSize, userName, sex);
		UserModel record = (UserModel) pages.get("data");
		Assert.assertNotNull("分页查询异常", record);
	}
}
