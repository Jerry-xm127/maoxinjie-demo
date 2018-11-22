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
	
	@Autowired
	private OrganizationController orgController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	
	
	@Test
	public void testOrg() throws Exception{
		OrganizationModel org = this.buildOrg();
		this.testCreateOrg(org);
		this.testUpdateOrg(org);
		this.testGetOrgById(org);
		this.testQueryOrg();
		this.testDeleteOrg(org);
	}
	
	public OrganizationModel buildOrg() {
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setOrgName("云计算部门");
		organizationModel.setOrgAddress("上海浦东新区");
		organizationModel.setOrgStatus("1");
		return organizationModel;
	}
	
	public void testCreateOrg(OrganizationModel organizationModel) throws Exception{
		ResponseResultUtil responseResult = orgController.createOrganization(organizationModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
	
	public void testGetOrgById(OrganizationModel organizationModel) throws Exception {
		ResponseResultUtil responseResult = orgController.getOrganizationById(organizationModel.getOrgId());
		Assert.assertNotNull(responseResult.get("data"));
	}
	
	public void testQueryOrg() throws Exception {
		ResponseResultUtil responseResult = orgController.queryOrganization(1, 5, null);
		Assert.assertNotNull(responseResult.get("data"));
	}
	
	public void testUpdateOrg(OrganizationModel organizationModel) throws Exception {
		organizationModel.setOrgName("大数据服务");
		organizationModel.setOrgAddress("北京");
		ResponseResultUtil responseResult = orgController.modifyOrganization(organizationModel.getOrgId(), organizationModel);
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}

	public void testDeleteOrg(OrganizationModel organizationModel) throws Exception{
		ResponseResultUtil responseResult = orgController.removeOrganizationById(organizationModel.getOrgId());
		Assert.assertEquals("操作成功", responseResult.get("msg"));
	}
}
