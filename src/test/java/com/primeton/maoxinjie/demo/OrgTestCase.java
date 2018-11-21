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

import com.primeton.maoxinjie.demo.controller.OrganizationController;
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	private Logger log = LoggerFactory.getLogger(OrgTestCase.class);
	
	@Autowired
	private OrganizationController orgController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	
	
	@Test
	public void testOrg() throws Exception{
//		this.testCreateOrg();
//		this.testUpdateOrg();
//		this.testGetOrgById();
//		this.testQueryOrg();
//		this.testDeleteOrg();
	}
	
	public void testCreateOrg() throws Exception{
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setOrgName("北方金融事业部");
		organizationModel.setOrgAddress("上海浦东新区");
		organizationModel.setOrgStatus("1");
		ResponseResultUtil responseResult = orgController.createOrganization(organizationModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testDeleteOrg() throws Exception{
		int id = 2;
		ResponseResultUtil responseResult = orgController.removeOrganizationById(id);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testUpdateOrg() throws Exception {
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setOrgName("财务部");
		organizationModel.setOrgAddress("上海浦东");
		organizationModel.setOrgStatus("2");
		organizationModel.setOrgId(9);
		ResponseResultUtil responseResult = orgController.modifyOrganization(organizationModel.getOrgId(), organizationModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testGetOrgById() throws Exception {
		int id = 1;
		ResponseResultUtil responseResult = orgController.getOrganizationById(id);
		Assert.assertNotNull(responseResult.get("data"));
	}
	
	public void testQueryOrg() throws Exception {
		int pageNo = 1;
		int pageSize = 5;
		String orgName = "研发";
		ResponseResultUtil responseResult = orgController.queryOrganization(pageNo, pageSize, orgName);
		Assert.assertNotNull(responseResult.get("data"));
	}

}
