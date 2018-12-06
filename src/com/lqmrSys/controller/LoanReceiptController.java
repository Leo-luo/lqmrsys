package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.PublicService;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.LoanReceiptBean;
import com.lqmrSys.bean.dto.LoanReceiptDto;
import com.lqmrSys.bean.dto.LoanReceiptDtob;
import com.lqmrSys.bean.pagination.LoanReceiptDataTablesReq;
import com.lqmrSys.service.LoanReceiptService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/loanReceipt")
@Api(value = "借据", description = "借据相关接口")
public class LoanReceiptController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private LoanReceiptService loadReceiptService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:")
	public Res<Integer> addLoanReceipt(
			@ApiParam(required = true, value = "融资配对信息ID")@RequestParam int financingMatchingId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(financingMatchingId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		LoanReceiptBean bean = new LoanReceiptBean();
		bean.setFinancingMatchingId(financingMatchingId);
		bean.setOperatorId(operatorId);
		bean.setOperatorName(operatorName);
		return loadReceiptService.addLoanReceipt(bean);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<LoanReceiptDto> loanReceiptList(@ModelAttribute LoanReceiptDataTablesReq req){
		List<LoanReceiptDto> dto = loadReceiptService.loanReceiptList(req);
		JSONResponse<LoanReceiptDto> jsonResponse = new JSONResponse<LoanReceiptDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<LoanReceiptDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = loadReceiptService.loanReceiptCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}

	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination_loan")
    @ApiOperation(value = "借款列表", notes = "分页")
	public JSONResponse<LoanReceiptDtob> loanReceiptList2(@ModelAttribute LoanReceiptDataTablesReq req){
		List<LoanReceiptDtob> dto = loadReceiptService.loanReceiptList2(req);
		JSONResponse<LoanReceiptDtob> jsonResponse = new JSONResponse<LoanReceiptDtob>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<LoanReceiptDtob>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = loadReceiptService.loanReceiptCount2(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
}
