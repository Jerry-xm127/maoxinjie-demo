package com.primeton.maoxinjie.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.maoxinjie.demo.constant.ResultCodeEnum;
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
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IOrganizationService organizationService;
	
	@ApiOperation(value="添加组织机构",notes="添加组织机构信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResultUtil createOrganization(@RequestBody OrganizationModel organizationModel) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			int num = organizationService.createOrganization(organizationModel);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id删除组织机构",notes="删除组织机构信息",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResultUtil removeOrganizationById(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			int num = organizationService.removeOrganizationById(id);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult.put("success", false);
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取组织机构详细信息",notes="通过id获取组织机构信息",response=ResponseEntity.class)
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ResponseResultUtil> getOrganizationById(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		try {
			OrganizationModel organizationModel = organizationService.getOrganizationByID(id);
			if (null != organizationModel) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", organizationModel);
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult.put("success", false);
			log.error("数据库操作失败", e);
		}
		return ResponseEntity.ok(responseResult);
	}

	@ApiOperation(value="通过id修改",notes="通过id修改组织机构",response=ResponseResultUtil.class)
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseResultUtil modifyOrganization(@PathVariable("id") int id, @RequestBody OrganizationModel organizationModel) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		organizationModel.setId(id);
		try {
			int num = organizationService.modifyOrganization(organizationModel);
			if (num > 0) {
				responseResult = ResponseResultUtil.success();
			}else {
				responseResult = ResponseResultUtil.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
	
	@ApiOperation(value = "分页+搜索获取组织机构信息", notes="模糊查询及分页显示",response=ResponseResultUtil.class)
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseResultUtil queryOrganization(@RequestParam(value="pageNo",defaultValue="1") int pageNo, 
									@RequestParam(value="pageSize",defaultValue="5") int pageSize,
									@RequestParam("orgName") String orgName) {
		//存放返回前台信息
		ResponseResultUtil responseResult = new ResponseResultUtil();
		OrganizationModel serachOrg = new OrganizationModel();
		serachOrg.setoName(orgName);
		try {
			PageInfo<OrganizationModel> userList = organizationService.queryOrgByPage(pageNo, pageSize, serachOrg);
			if (userList.getList().size() > 0) {
				responseResult = ResponseResultUtil.success();
				responseResult.put("data", userList);
			}else {
				responseResult = ResponseResultUtil.error(ResultCodeEnum.NO_FIND_DATA.getCode(), ResultCodeEnum.NO_FIND_DATA.getMessage());
			}
		} catch (Exception e) {
			responseResult = ResponseResultUtil.sysError();
			log.error("数据库操作失败", e);
		}
		return responseResult;
	}
}
