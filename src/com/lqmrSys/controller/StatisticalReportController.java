package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.SupplyExpireRemindBean;
import com.lqmrSys.bean.dto.LoanExpireRemindDto;
import com.lqmrSys.service.StatisticalReportService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/remind")
@Api(value = "统计", description = "统计相关接口")
public class StatisticalReportController {
	@Autowired
	private StatisticalReportService statisticalReportService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list_supply")
    @ApiOperation(value = "出借到期列表", notes = "不分页")
	public JSONResponse<SupplyExpireRemindBean> supplyExpireRemindList(){
		List<SupplyExpireRemindBean> dto = statisticalReportService.supplyExpireRemindList();
		JSONResponse<SupplyExpireRemindBean> jsonResponse = new JSONResponse<SupplyExpireRemindBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<SupplyExpireRemindBean>(), 0, 0);
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = dto.size();
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list_loan")
    @ApiOperation(value = "借款到期、逾期列表", notes = "不分页")
	public JSONResponse<LoanExpireRemindDto> loanExpireRemindList(@ApiParam(required = true, value = "借款提醒类型(到期,逾期)")@RequestParam String listType){
		List<LoanExpireRemindDto> dto = statisticalReportService.loanExpireRemindList(listType);
		JSONResponse<LoanExpireRemindDto> jsonResponse = new JSONResponse<LoanExpireRemindDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<LoanExpireRemindDto>(), 0, 0);
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
