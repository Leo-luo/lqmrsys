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
import com.lqmrSys.bean.dto.FinancingMatchingAllDetailDto;
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.pagination.FinancingMatchingDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingReq;
import com.lqmrSys.service.FinancingMatchingService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/financingMatching")
@Api(value = "融资配对信息", description = "融资配对信息相关接口")
public class FinancingMatchingController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private FinancingMatchingService financingMatchingService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addFinancingMatching(@RequestBody FinancingMatchingReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentfinancingMatchingId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return financingMatchingService.addFinancingMatching(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifyFinancingMatching(@RequestBody FinancingMatchingReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getFinancingMatchingId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return financingMatchingService.modifyFinancingMatching(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delFinancingMatching(
			@ApiParam(required = true, value = "融资配对信息ID")@RequestParam int financingMatchingId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(financingMatchingId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return financingMatchingService.delFinancingMatching(financingMatchingId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:融资配对信息ID")
	public Res<FinancingMatchingDto> financingMatchingDetail(
			@ApiParam(required = true, value = "融资配对信息ID")@RequestParam int financingMatchingId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(financingMatchingId+"")){
			return new Res<FinancingMatchingDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return financingMatchingService.financingMatchingDetail(financingMatchingId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<FinancingMatchingDto> financingMatchingList(@ModelAttribute FinancingMatchingDataTablesReq req){
		List<FinancingMatchingDto> dto = financingMatchingService.financingMatchingList(req);
		JSONResponse<FinancingMatchingDto> jsonResponse = new JSONResponse<FinancingMatchingDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<FinancingMatchingDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = financingMatchingService.financingMatchingCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail_all")
    @ApiOperation(value = "所有详情", notes = "必填:配对登记编号")
	public Res<FinancingMatchingAllDetailDto> financingMatchingDetailAll(
			@ApiParam(required = true, value = "配对登记编号")@RequestParam String matchingCode){
		/**必填参数非空检验*/
		if(!publicService.checkParam(matchingCode)){
			return new Res<FinancingMatchingAllDetailDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return financingMatchingService.financingMatchingDetailAll(matchingCode);
	}
	
}
