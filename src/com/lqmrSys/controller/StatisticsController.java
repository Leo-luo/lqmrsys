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
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.StatisticsBean;
import com.lqmrSys.bean.StatisticsPageBean;
import com.lqmrSys.bean.StatisticsUserBean;
import com.lqmrSys.service.StatisticsService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/statistics")
@Api(value = "统计", description = "统计相关接口")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/statisticsPage")
    @ApiOperation(value = "统计页面数据", notes = "统计页面数据")
	public Res<StatisticsPageBean> statisticsPageData(){
		return statisticsService.pageData();
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/yearSupplyAmountList")
    @ApiOperation(value = "历年供应金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> yearSupplyAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.yearSupplyAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/monthSupplyAmountList")
    @ApiOperation(value = "月供应金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> monthSupplyAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy-MM)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy-MM)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.monthSupplyAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/yearLoanAmountList")
    @ApiOperation(value = "历年贷款金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> yearLoanAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.yearLoanAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/monthLoanAmountList")
    @ApiOperation(value = "月贷款金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> monthLoanAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy-MM)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy-MM)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.monthLoanAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/managerSupplyAmountList")
    @ApiOperation(value = "客户经理出借金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> managerSupplyAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy-MM-dd)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy-MM-dd)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.managerSupplyAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/managerLoanAmountList")
    @ApiOperation(value = "客户经理贷款金额列表", notes = "不分页")
	public JSONResponse<StatisticsBean> managerLoanAmountList(
			@ApiParam(required = true, value = "开始时间(yyyy-MM-dd)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy-MM-dd)")@RequestParam String endTime){
		List<StatisticsBean> dto = statisticsService.managerLoanAmountList(startTime, endTime);
		JSONResponse<StatisticsBean> jsonResponse = new JSONResponse<StatisticsBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsBean>(), 0, 0);
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
    @RequestMapping(method = RequestMethod.GET, value = "/managerSupplayAndLoanList")
    @ApiOperation(value = "客户经理出借-贷款统计列表", notes = "不分页")
	public JSONResponse<StatisticsUserBean> managerSupplayAndLoanList(
			@ApiParam(required = true, value = "开始时间(yyyy-MM-dd)")@RequestParam String startTime,
			@ApiParam(required = true, value = "截止时间(yyyy-MM-dd)")@RequestParam String endTime){
		List<StatisticsUserBean> dto = statisticsService.managerSupplayAndLoanList(startTime, endTime);
		JSONResponse<StatisticsUserBean> jsonResponse = new JSONResponse<StatisticsUserBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<StatisticsUserBean>(), 0, 0);
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
