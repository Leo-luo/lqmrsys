package com.lqmrSys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.JSONResponse;
import com.common.PublicService;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.RepaymentBean;
import com.lqmrSys.bean.req.RepaymentReq;
import com.lqmrSys.service.RepaymentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/repayment")
@Api(value = "还款计划", description = "还款计划相关接口")
public class RepaymentController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private RepaymentService repaymentService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifyRepayment(@RequestBody RepaymentReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getRepaymentId()+"", req.getActualInterestAmount()+"", req.getActualPrincipalAmount()+"", req.getActualServiceAmount()+"", req.getOperatorId()+"", req.getOperatorName())){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return repaymentService.modifyRepayment(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<RepaymentBean> repaymentList(
			@ApiParam(required = true, value = "借据表ID")@RequestParam int loanReceiptId,
			@ApiParam(required = true, value = "还款类型:本息/服务费")@RequestParam String repaymentType){
		List<RepaymentBean> dto = repaymentService.repaymentList(loanReceiptId, repaymentType);
		JSONResponse<RepaymentBean> jsonResponse = new JSONResponse<RepaymentBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<RepaymentBean>(), 0, 0);
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
