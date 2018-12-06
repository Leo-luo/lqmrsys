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
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.pagination.DemandDataTablesReq;
import com.lqmrSys.bean.req.DemandReq;
import com.lqmrSys.service.DemandService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/demand")
@Api(value = "需求方", description = "需求方相关接口")
public class DemandController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private DemandService demandService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addDemand(@RequestBody DemandReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentdemandId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return demandService.addDemand(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifyDemand(@RequestBody DemandReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDemandId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return demandService.modifyDemand(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delDemand(
			@ApiParam(required = true, value = "需求方ID")@RequestParam int demandId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(demandId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return demandService.delDemand(demandId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:需求方ID")
	public Res<DemandDto> demandDetail(@ApiParam(required = true, value = "需求方ID")@RequestParam int demandId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(demandId+"")){
			return new Res<DemandDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return demandService.demandDetailById(demandId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<DemandDto> demandList(@ModelAttribute DemandDataTablesReq req){
		List<DemandDto> dto = demandService.demandList(req);
		JSONResponse<DemandDto> jsonResponse = new JSONResponse<DemandDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<DemandDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = demandService.demandCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
