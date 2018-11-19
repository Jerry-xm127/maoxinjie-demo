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
import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgTestCase {
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	private Logger log = LoggerFactory.getLogger(OrgTestCase.class);
	
	@Autowired
	private IOrganizationService orgService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build(); // 构造MockMvc
	}
	
	
	@Test
	public void testOrg() {
		//this.testCreateOrg();
		//this.testDeleteOrg();
		//this.testUpdateOrg();
		//this.testGetOrgById();
		this.testQueryOrg();
	}
	
	public void testCreateOrg(){
		int num = 0;
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setoName("北方金融事业部");
		organizationModel.setoAddress("上海浦东");
		organizationModel.setoStatus("1");
		try {
			num = orgService.createOrganization(organizationModel);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, num);
	}
	
	public void testDeleteOrg() {
		int id = 2;
		int result = 0;
		try {
			result = orgService.removeOrganizationById(id);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, result);
	}
	
	public void testUpdateOrg() {
		OrganizationModel organizationModel = new OrganizationModel();
		organizationModel.setoName("金融事业部");
		organizationModel.setoAddress("上海浦东");
		organizationModel.setoStatus("2");
		organizationModel.setId(2);
		int result = 0;
		try {
			result = orgService.modifyOrganization(organizationModel);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotEquals(0, result);
	}
	
	public void testGetOrgById() {
		int id = 1;
		OrganizationModel result = null;
		try {
			result = orgService.getOrganizationByID(id);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotNull("通过id查询异常", result);;
	}
	
	public void testQueryOrg() {
		int pageNo = 1;
		int pageSize = 5;
		OrganizationModel searchOrg = new OrganizationModel();
		String orgName = "金融";
		searchOrg.setoName(orgName);
		OrganizationModel record = null;
		try {
			PageInfo<OrganizationModel> pages = orgService.queryOrgByPage(pageNo, pageSize, searchOrg);
			System.out.println(pages);
			record = pages.getList().get(0);
		} catch (Exception e) {
			log.error("数据库操作异常",e);
		}
		Assert.assertNotNull("分页查询异常", record);
	}

}
