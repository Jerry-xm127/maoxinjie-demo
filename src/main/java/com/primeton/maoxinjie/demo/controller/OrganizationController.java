package com.primeton.maoxinjie.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;
import com.primeton.maoxinjie.demo.util.ResponseResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组织机构管理模块api
 * @author MAIBENBEN
 *
 */
@Api(value="组织机构管理的相关api",tags="组织机构管理模块")
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
	
	
	@Autowired
	private IOrganizationService organizationService;
	
	/**
	 * 
	 * <p>Description: 创建组织机构</p>
	 * @parameter
	 * @return 
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="添加组织机构",notes="添加组织机构信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResultUtil createOrganization(@RequestBody OrganizationModel organizationModel) throws Exception {
		return organizationService.createOrganization(organizationModel);
	}
	
	/**
	 * 	
	 * <p>Description: 通过id删除机构</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="通过id删除组织机构",notes="删除组织机构信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResultUtil removeOrganizationById(@PathVariable("id") int id) throws Exception {
		return organizationService.removeOrganizationById(id);
	}
	
	/**
	 * 
	 * <p>Description: 通过id修改机构信息</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @param organizationModel
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="通过id修改",notes="通过id修改组织机构",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseResultUtil modifyOrganization(@PathVariable("id") int id, @RequestBody OrganizationModel organizationModel) throws Exception {
		return organizationService.modifyOrganization(organizationModel);
	}
	
	/**
	 * 
	 * <p>Description: 通过id获取机构信息</p>
	 * @parameter
	 * @return 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="通过id获取组织机构详细信息",notes="通过id获取组织机构信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseResultUtil getOrganizationById(@PathVariable("id") int id) throws Exception {
		return organizationService.getOrganizationByID(id);
	}

	/**
	 * 
	 * <p>Description: 分页+搜索查询机构信息</p>
	 * @parameter
	 * @return 
	 * @param pageNo
	 * @param pageSize
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "分页+搜索获取组织机构信息", notes="模糊查询及分页显示",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseResultUtil queryOrganization(@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
									@RequestParam(value="pageSize",defaultValue="5") int pageSize,
									@RequestParam(name="orgName",required=false) String orgName) throws Exception {
		return organizationService.queryOrgByPage(pageNo, pageSize, orgName);
	}
}
