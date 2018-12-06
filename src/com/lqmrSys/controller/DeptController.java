package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.PublicService;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DeptDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DeptDataTablesReq;
import com.lqmrSys.bean.req.DeptReq;
import com.lqmrSys.service.DeptService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/dept")
@Api(value = "部门", description = "部门相关接口")
public class DeptController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private DeptService deptService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:部门名称,父部门ID")
	public Res<Integer> addDept(@RequestBody DeptReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getName(), req.getParentDeptId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return deptService.addDept(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:部门ID,父部门ID")
	public Res<Integer> modifyDept(@RequestBody DeptReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDeptId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return deptService.modifyDept(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delDept(@ApiParam(required = true, value = "部门ID")@RequestParam int deptId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(deptId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return deptService.delDept(deptId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:部门ID")
	public Res<DeptDto> DeptDetail(@ApiParam(required = true, value = "部门ID")@RequestParam int deptId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(deptId+"")){
			return new Res<DeptDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return deptService.deptDetailById(deptId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<DeptDto> DeptList(@ModelAttribute DeptDataTablesReq req){
		List<DeptDto> dto = deptService.deptList(req);
		JSONResponse<DeptDto> jsonResponse = new JSONResponse<DeptDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<DeptDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = deptService.deptCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/menuTree")
    @ApiOperation(value = "部门树", notes = "部门树")
	public Res<List<MenuTreeDto>> getMenuTree(@ApiParam(required = true, value = "父部门ID")@RequestParam int parentDeptId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(parentDeptId+"")){
			return new Res<List<MenuTreeDto>>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return new Res<List<MenuTreeDto>>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), deptService.buildDeptTree(parentDeptId));
	}
}
