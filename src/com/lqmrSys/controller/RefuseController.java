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
import com.lqmrSys.bean.dto.RefuseDto;
import com.lqmrSys.bean.pagination.RefuseDataTablesReq;
import com.lqmrSys.bean.req.RefuseReq;
import com.lqmrSys.service.RefuseService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/refuse")
@Api(value = "拒贷", description = "拒贷相关接口")
public class RefuseController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private RefuseService refuseService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:")
	public Res<Integer> addRefuse(@RequestBody RefuseReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDataId()+"", req.getDataFrom())){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return refuseService.addRefuse(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:拒贷ID")
	public Res<Integer> modifyRefuse(@RequestBody RefuseReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getRefuseId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return refuseService.modifyRefuse(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delRefuse(
			@ApiParam(required = true, value = "拒贷ID")@RequestParam int refuseId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(refuseId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return refuseService.delRefuse(refuseId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<RefuseDto> RefuseList(@ModelAttribute RefuseDataTablesReq req){
		List<RefuseDto> dto = refuseService.refuseList(req);
		JSONResponse<RefuseDto> jsonResponse = new JSONResponse<RefuseDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<RefuseDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = refuseService.refuseCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/isRefuse")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> hasRefuse(
			@ApiParam(required = true, value = "客户/企业ID")@RequestParam int dataId,
			@ApiParam(required = true, value = "来源：公司(sys_company),个人(sys_customer)")@RequestParam String dataFrom){
		/**必填参数非空检验*/
		if(!publicService.checkParam(dataId+"", dataFrom)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return refuseService.hasRefuse(dataId, dataFrom);
	}
	
}
