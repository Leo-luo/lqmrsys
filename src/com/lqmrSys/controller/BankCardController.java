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
import com.lqmrSys.bean.dto.BankCardDto;
import com.lqmrSys.bean.pagination.BankCardDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;
import com.lqmrSys.service.BankCardService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/bankCard")
@Api(value = "银行卡", description = "银行卡相关接口")
public class BankCardController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private BankCardService bankCardService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:卡号,所属银行,银行卡类型,关联ID")
	public Res<Integer> addBankCard(@RequestBody BankCardReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getCardNum(), req.getBankName(), req.getCardType(), req.getRelationId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return bankCardService.addBankCard(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:银行卡ID")
	public Res<Integer> modifyBankCard(@RequestBody BankCardReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getCardId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return bankCardService.modifyBankCard(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delBankCard(@ApiParam(required = true, value = "银行卡ID")@RequestParam int cardId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(cardId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return bankCardService.delBankCard(cardId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:银行卡ID")
	public Res<BankCardDto> BankCardDetail(@ApiParam(required = true, value = "银行卡ID")@RequestParam int cardId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(cardId+"")){
			return new Res<BankCardDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return bankCardService.bankCardDetailById(cardId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<BankCardDto> BankCardList(@ModelAttribute BankCardDataTablesReq req){
		List<BankCardDto> dto = bankCardService.bankCardList(req);
		JSONResponse<BankCardDto> jsonResponse = new JSONResponse<BankCardDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<BankCardDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = bankCardService.bankCardCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
