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
import com.lqmrSys.bean.dto.GuaranteeDto;
import com.lqmrSys.bean.pagination.GuaranteeDataTablesReq;
import com.lqmrSys.bean.req.GuaranteeReq;
import com.lqmrSys.service.GuaranteeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/guarantee")
@Api(value = "担保", description = "担保相关接口")
public class GuaranteeController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private GuaranteeService guaranteeService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:")
	public Res<Integer> addGuarantee(@RequestBody GuaranteeReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentguaranteeId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return guaranteeService.addGuarantee(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:担保ID")
	public Res<Integer> modifyGuarantee(@RequestBody GuaranteeReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getGuaranteeId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return guaranteeService.modifyGuarantee(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delGuarantee(
			@ApiParam(required = true, value = "担保ID")@RequestParam int guaranteeId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(guaranteeId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return guaranteeService.delGuarantee(guaranteeId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:担保ID")
	public Res<GuaranteeDto> GuaranteeDetail(@ApiParam(required = true, value = "担保ID")@RequestParam int guaranteeId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(guaranteeId+"")){
			return new Res<GuaranteeDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return guaranteeService.guaranteeDetailById(guaranteeId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<GuaranteeDto> GuaranteeList(@ModelAttribute GuaranteeDataTablesReq req){
		List<GuaranteeDto> dto = guaranteeService.guaranteeList(req);
		JSONResponse<GuaranteeDto> jsonResponse = new JSONResponse<GuaranteeDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<GuaranteeDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = guaranteeService.guaranteeCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
