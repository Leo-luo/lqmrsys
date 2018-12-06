package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.PublicService;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.req.ModuleReq;
import com.lqmrSys.service.ModuleService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/module")
@Api(value = "权限", description = "权限相关接口")
public class ModuleController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private ModuleService moduleService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/moduleTree")
    @ApiOperation(value = "权限树", notes = "权限树")
	public Res<List<MenuTreeDto>> getModuleTree(@ApiParam(required = true, value = "父权限ID")@RequestParam int parentModuleId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(parentModuleId+"")){
			return new Res<List<MenuTreeDto>>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return new Res<List<MenuTreeDto>>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), moduleService.buildModuleTree(parentModuleId));
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/roleModuleTree")
    @ApiOperation(value = "角色权限树", notes = "角色权限树")
	public Res<List<MenuTreeDto>> getRoleModuleTree(
			@ApiParam(required = true, value = "角色ID")@RequestParam int roleId,
			@ApiParam(required = true, value = "父权限ID")@RequestParam int parentModuleId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(roleId+"", parentModuleId+"")){
			return new Res<List<MenuTreeDto>>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return new Res<List<MenuTreeDto>>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), moduleService.buildRoleModuleTree(roleId, parentModuleId));
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modifyUserMod")
    @ApiOperation(value = "修改人员权限", notes = "必填:数据ID(即人员ID)")
	public Res<Integer> modifyUserMod(@RequestBody ModuleReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDataId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return moduleService.modifyUserMod(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/userModuleTree")
    @ApiOperation(value = "人员权限树", notes = "人员权限树")
	public Res<List<MenuTreeDto>> getUserModuleTree(
			@ApiParam(required = true, value = "人员ID")@RequestParam int userId,
			@ApiParam(required = true, value = "父权限ID")@RequestParam int parentModuleId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"", parentModuleId+"")){
			return new Res<List<MenuTreeDto>>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return new Res<List<MenuTreeDto>>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), moduleService.buildUserModuleTree(userId, parentModuleId));
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/userDataStr")
    @ApiOperation(value = "人员数据权限串", notes = "必填:人员ID")
	public Res<String> getUserDataStr(@ApiParam(required = true, value = "人员ID")@RequestParam int userId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"")){
			return new Res<String>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return moduleService.userDataStr(userId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modifyUserData")
    @ApiOperation(value = "修改人员数据权限", notes = "必填:数据ID(即人员ID)")
	public Res<Integer> modifyUserData(@RequestBody ModuleReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDataId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return moduleService.modifyUserData(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/moduleList")
    @ApiOperation(value = "列表", notes = "不分页")
	public JSONResponse<RelationModuleDto> moduleList(@ApiParam(required = true, value = "父权限ID")@RequestParam int parentModuleId){
		List<RelationModuleDto> dto = moduleService.moduleList(parentModuleId);
		JSONResponse<RelationModuleDto> jsonResponse = new JSONResponse<RelationModuleDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<RelationModuleDto>(), 0, 0);
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = dto.size();
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
}
