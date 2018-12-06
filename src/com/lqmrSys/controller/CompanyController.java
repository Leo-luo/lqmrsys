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
import com.lqmrSys.bean.dto.CompanyDto;
import com.lqmrSys.bean.pagination.CompanyDataTablesReq;
import com.lqmrSys.bean.req.CompanyReq;
import com.lqmrSys.service.CompanyService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/company")
@Api(value = "企业", description = "企业相关接口")
public class CompanyController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private CompanyService companyService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:")
	public Res<Integer> addCompany(@RequestBody CompanyReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentcompanyId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return companyService.addCompany(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:企业ID")
	public Res<Integer> modifyCompany(@RequestBody CompanyReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getCompanyId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return companyService.modifyCompany(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delCompany(
			@ApiParam(required = true, value = "企业ID")@RequestParam int companyId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(companyId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return companyService.delCompany(companyId, operatorId, operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:企业ID")
	public Res<CompanyDto> CompanyDetail(@ApiParam(required = true, value = "企业ID")@RequestParam int companyId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(companyId+"")){
			return new Res<CompanyDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return companyService.companyDetailById(companyId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<CompanyDto> CompanyList(@ModelAttribute CompanyDataTablesReq req){
		List<CompanyDto> dto = companyService.companyList(req);
		JSONResponse<CompanyDto> jsonResponse = new JSONResponse<CompanyDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<CompanyDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = companyService.companyCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
