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
import com.lqmrSys.bean.dto.LoginDto;
import com.lqmrSys.bean.dto.UserDto;
import com.lqmrSys.bean.pagination.UserDataTablesReq;
import com.lqmrSys.bean.req.UserReq;
import com.lqmrSys.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/user")
@Api(value = "人员", description = "人员相关接口")
public class UserController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private UserService userService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    @ApiOperation(value = "登录", notes = "全必填")
	public Res<LoginDto> userLogin(
			@ApiParam(required = true, value = "账号")@RequestParam String account,
			@ApiParam(required = true, value = "密码")@RequestParam String pwd){
		/**必填参数非空检验*/
		if(!publicService.checkParam(account, pwd)){
			return new Res<LoginDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.userLogin(account, pwd);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:姓名,部门ID,角色ID")
	public Res<Integer> addUser(@RequestBody UserReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getName(), req.getDeptId()+"", req.getRoleId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.addUser(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:人员ID")
	public Res<Integer> modifyUser(@RequestBody UserReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getUserId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.modifyUser(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,必填:人员ID")
	public Res<Integer> delUser(@ApiParam(required = true, value = "人员ID")@RequestParam int userId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.delUser(userId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:人员ID")
	public Res<UserDto> userDetail(@ApiParam(required = true, value = "人员ID")@RequestParam int userId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"")){
			return new Res<UserDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.userDetail(userId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<UserDto> UserList(@ModelAttribute UserDataTablesReq req){
		List<UserDto> dto = userService.userList(req);
		JSONResponse<UserDto> jsonResponse = new JSONResponse<UserDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<UserDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = userService.userCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/userMods")
    @ApiOperation(value = "获取人员权限串", notes = "必填:人员ID")
	public Res<String> userMods(@ApiParam(required = true, value = "人员ID")@RequestParam int userId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"")){
			return new Res<String>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.userMods(userId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/resetPwd")
    @ApiOperation(value = "重置密码", notes = "单个,必填:人员ID")
	public Res<Integer> resetPwd(@ApiParam(required = true, value = "人员ID")@RequestParam int userId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.resetUserPwd(userId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/modifyPwd")
    @ApiOperation(value = "修改密码", notes = "单个,必填:人员ID")
	public Res<Integer> modifyPwd(
			@ApiParam(required = true, value = "人员ID")@RequestParam int userId,
			@ApiParam(required = true, value = "原密码")@RequestParam String oldPwd,
			@ApiParam(required = true, value = "新密码")@RequestParam String pwd){
		/**必填参数非空检验*/
		if(!publicService.checkParam(userId+"", oldPwd, pwd)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return userService.modifyUserPwd(userId, pwd, oldPwd);
	}

}
