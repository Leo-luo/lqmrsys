package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.TrialDto;
import com.lqmrSys.bean.pagination.TrialDataTablesReq;
import com.lqmrSys.bean.req.TrialReq;
import com.lqmrSys.service.TrialService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/trial")
@Api(value = "审核-审批", description = "审核-审批相关接口")
public class TrialController {
//	@Autowired
//	private PublicService publicService;
	@Autowired
	private TrialService trialService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/addList")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addTrialList(@RequestBody List<TrialReq> list){
		return trialService.addTrialList(list);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modifyTrialStatus")
    @ApiOperation(value = "修改审核-审批状态", notes = "全必填")
	public Res<Integer> modifyTrialStatus(@RequestBody TrialReq req){
		return trialService.modifyTrialStatus(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "不分页")
	public JSONResponse<TrialDto> trialList(@ModelAttribute TrialDataTablesReq req){
		List<TrialDto> dto = trialService.trialList(req);
		JSONResponse<TrialDto> jsonResponse = new JSONResponse<TrialDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<TrialDto>(), 0, 0);
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
