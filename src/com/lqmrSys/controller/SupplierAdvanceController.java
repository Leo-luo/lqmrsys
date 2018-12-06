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
import com.lqmrSys.bean.dto.SupplierAdvanceDto;
import com.lqmrSys.bean.pagination.SupplierAdvanceDataTablesReq;
import com.lqmrSys.bean.req.SupplierAdvanceReq;
import com.lqmrSys.service.SupplierAdvanceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/supplierAdvance")
@Api(value = "供应方预登记", description = "供应方预登记相关接口")
public class SupplierAdvanceController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private SupplierAdvanceService supplierAdvanceService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addSupplierAdvance(@RequestBody SupplierAdvanceReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentsupplierAdvanceId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return supplierAdvanceService.addSupplierAdvance(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifySupplierAdvance(@RequestBody SupplierAdvanceReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getAdvanceId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return supplierAdvanceService.modifySupplierAdvance(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delSupplierAdvance(
			@ApiParam(required = true, value = "供应方预登记ID")@RequestParam int advanceId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(advanceId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return supplierAdvanceService.delSupplierAdvance(advanceId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:供应方预登记ID")
	public Res<SupplierAdvanceDto> supplierAdvanceDetail(@ApiParam(required = true, value = "供应方预登记ID")@RequestParam int advanceId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(advanceId+"")){
			return new Res<SupplierAdvanceDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return supplierAdvanceService.supplierAdvanceDetailById(advanceId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<SupplierAdvanceDto> supplierAdvanceList(@ModelAttribute SupplierAdvanceDataTablesReq req){
		List<SupplierAdvanceDto> dto = supplierAdvanceService.supplierAdvanceList(req);
		JSONResponse<SupplierAdvanceDto> jsonResponse = new JSONResponse<SupplierAdvanceDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<SupplierAdvanceDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = supplierAdvanceService.supplierAdvanceCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
