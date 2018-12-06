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
import com.lqmrSys.bean.dto.RelationshipDto;
import com.lqmrSys.bean.pagination.RelationshipDataTablesReq;
import com.lqmrSys.bean.req.RelationshipReq;
import com.lqmrSys.service.RelationshipService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
@Controller
@RequestMapping(value = "/relationship")
@Api(value = "关系图", description = "关系图相关接口")
public class RelationshipController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private RelationshipService relationshipService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:")
	public Res<Integer> addRelationship(@RequestBody RelationshipReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentrelationshipId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return relationshipService.addRelationship(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:关系图ID")
	public Res<Integer> modifyRelationship(@RequestBody RelationshipReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getRelationshipId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return relationshipService.modifyRelationship(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delRelationship(
			@ApiParam(required = true, value = "关系图ID")@RequestParam int relationshipId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(relationshipId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return relationshipService.delRelationship(relationshipId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:关系图ID")
	public Res<RelationshipDto> RelationshipDetail(@ApiParam(required = true, value = "关系图ID")@RequestParam int relationshipId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(relationshipId+"")){
			return new Res<RelationshipDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return relationshipService.relationshipDetailById(relationshipId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<RelationshipDto> RelationshipList(@ModelAttribute RelationshipDataTablesReq req){
		List<RelationshipDto> dto = relationshipService.relationshipList(req);
		JSONResponse<RelationshipDto> jsonResponse = new JSONResponse<RelationshipDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<RelationshipDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = relationshipService.relationshipCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
