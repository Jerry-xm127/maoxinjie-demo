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
import com.primeton.maoxinjie.demo.dao.IUserDao;
import com.primeton.maoxinjie.demo.model.UserModel;
import com.primeton.maoxinjie.demo.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	private Logger log = LoggerFactory.getLogger(UserTestCase.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserDao userDao;
	
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
		//this.testGetUserById();
		this.testQueryUser();
		
	}

	/**
	 * 
	 * <p>Description: 测试添加用户</p>
	 * @parameter
	 * @return
	 */
	public void testCreateUser(){
		UserModel userModel = new UserModel();
		userModel.setuAccount("ceshiten");
		userModel.setuName("测试者10");
		userModel.setuPwd("123456");
		userModel.setuAge(15);
		userModel.setuSex("1");
		int num = 0;
		try {
			num = userService.createUser(userModel);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, num);
	}

	/**
	 * 
	 * <p>Description: 测试删除用户</p>
	 * @parameter
	 * @return
	 */
	public void testDeleteUser() {
		int id = 16;
		int result = 0;
		try {
			result = userService.removeUserById(id);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, result);
	}
	
	/**
	 * 
	 * <p>Description: 测试修改用户</p>
	 * @parameter
	 * @return
	 */
	public void testUpdateUser() {
		UserModel userModel = new UserModel();
		userModel.setId(20);
		userModel.setuAccount("ceshiershi");
		userModel.setuName("测试者19");
		userModel.setuAge(16);;
		userModel.setuSex("1");
		userModel.setuStatus("1");
		int result = 0;
		try {
			result = userService.modifyUser(userModel);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, result);
	}
	
	/**
	 * 
	 * <p>Description: 测试根据id查询用户</p>
	 * @parameter
	 * @return
	 */
	public void testGetUserById() {
		int id = 223;
		UserModel result = new UserModel();
		try {
			result = userService.getUserByID(id);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotNull("查询失败", result);;
	}
	
	/**
	 * 
	 * <p>Description: 分页+搜索用户的测试</p>
	 * @parameter
	 * @return
	 */
	public void testQueryUser() {
		int pageNo = 1;
		int pageSize = 5;
		UserModel searchUser = new UserModel();
		String userName = "孙";
		String sex = "0";
		searchUser.setuName(userName);
		searchUser.setuSex(sex);
		PageInfo<UserModel> pages = null;
		UserModel record = new UserModel();
		try {
			pages = userService.queryUserByPage(pageNo, pageSize, searchUser);
			record = pages.getList().get(0);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotNull("分页查询异常", record);
	}
}
