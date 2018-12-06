package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.OperateDto;
import com.lqmrSys.bean.pagination.OperateDataTablesReq;
import com.lqmrSys.service.OperateService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/operate")
@Api(value = "操作日志", description = "操作日志相关接口")
public class OperateController {
	@Autowired
	private OperateService operateService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<OperateDto> operateList(@ModelAttribute OperateDataTablesReq req){
		List<OperateDto> dto = operateService.operateList(req);
		JSONResponse<OperateDto> jsonResponse = new JSONResponse<OperateDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<OperateDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = operateService.operateCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}

}
