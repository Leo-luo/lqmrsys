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
import com.lqmrSys.bean.dto.FinancingMatchingContractDto;
import com.lqmrSys.bean.pagination.FinancingMatchingContractDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingContractReq;
import com.lqmrSys.service.ContractService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
@Controller
@RequestMapping(value = "/contract")
@Api(value = "合同", description = "合同相关接口")
public class ContractController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private ContractService contractService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addContract(@RequestBody FinancingMatchingContractReq req){
//		/**必填参数非空检验*/
//		if(!publicService.checkParam(req.getName(), req.getParentcontractId()+"")){
//			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
//		}
		return contractService.addFinancingMatchingContract(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifyContract(@RequestBody FinancingMatchingContractReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getContractId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return contractService.modifyFinancingMatchingContract(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:合同ID")
	public Res<FinancingMatchingContractDto> contractDetail(
			@ApiParam(required = true, value = "合同ID")@RequestParam int contractId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(contractId+"")){
			return new Res<FinancingMatchingContractDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return contractService.financingMatchingContractDetail(contractId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<FinancingMatchingContractDto> contractList(@ModelAttribute FinancingMatchingContractDataTablesReq req){
		List<FinancingMatchingContractDto> dto = contractService.financingMatchingContractList(req);
		JSONResponse<FinancingMatchingContractDto> jsonResponse = new JSONResponse<FinancingMatchingContractDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<FinancingMatchingContractDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = contractService.financingMatchingContractCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
