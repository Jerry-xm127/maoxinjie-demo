package com.primeton.maoxinjie.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.maoxinjie.demo.model.OrganizationModel;
import com.primeton.maoxinjie.demo.service.IOrganizationService;
import com.primeton.maoxinjie.demo.util.ResponseResult;

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
	
	@ApiOperation(value="添加组织机构",notes="添加组织机构信息")
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseResult createOrganization(OrganizationModel organizationModel) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = organizationService.createOrganization(organizationModel);
			if (num > 0) {
				responseResult = ResponseResult.success();
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult = ResponseResult.sysError();
			e.printStackTrace();
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id删除组织机构",notes="删除组织机构信息")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseResult removeOrganizationById(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = organizationService.removeOrganizationById(id);
			if (num > 0) {
				responseResult = ResponseResult.success();
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult.put("success", false);
			e.printStackTrace();
		}
		return responseResult;
	}
	
	@ApiOperation(value="通过id获取组织机构详细信息",notes="通过id获取组织机构信息")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ResponseResult> getOrganizationById(@PathVariable("id") int id) {
		//存放返回前台信息
		ResponseResult responseResult = new ResponseResult();
		try {
			OrganizationModel organizationModel = organizationService.getOrganizationByID(id);
			if (null != organizationModel) {
				responseResult = ResponseResult.success();
				responseResult.put("data", organizationModel);
			}else {
				responseResult = ResponseResult.error();
			}
		} catch (Exception e) {
			responseResult.put("success", false);
			e.printStackTrace();
		}
		return ResponseEntity.ok(responseResult);
	}

}
