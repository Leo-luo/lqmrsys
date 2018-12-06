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
import com.lqmrSys.bean.dto.RoleDto;
import com.lqmrSys.bean.pagination.RoleDataTablesReq;
import com.lqmrSys.bean.req.RoleReq;
import com.lqmrSys.service.RoleService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/role")
@Api(value = "角色", description = "角色相关接口")
public class RoleController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private RoleService roleService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:角色名称,模块ID串")
	public Res<Integer> addRole(@RequestBody RoleReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getName(), req.getModuleIds())){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return roleService.addRole(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:角色ID,父角色ID,模块ID串")
	public Res<Integer> modifyRole(@RequestBody RoleReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getRoleId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return roleService.modifyRole(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,必填:角色ID")
	public Res<Integer> delRole(@ApiParam(required = true, value = "字典ID")@RequestParam int RoleId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(RoleId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return roleService.delRole(RoleId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:角色ID")
	public Res<RoleDto> RoleDetail(@ApiParam(required = true, value = "字典ID")@RequestParam int RoleId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(RoleId+"")){
			return new Res<RoleDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return roleService.roleDetailById(RoleId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<RoleDto> RoleList(@ModelAttribute RoleDataTablesReq req){
		List<RoleDto> dto = roleService.roleList(req);
		JSONResponse<RoleDto> jsonResponse = new JSONResponse<RoleDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<RoleDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = roleService.roleCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
