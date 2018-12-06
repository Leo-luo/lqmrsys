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
import com.lqmrSys.bean.dto.AttachmentDto;
import com.lqmrSys.bean.pagination.AttachmentDataTablesReq;
import com.lqmrSys.bean.req.AttachmentReq;
import com.lqmrSys.service.AttachmentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/attachment")
@Api(value = "附件", description = "附件相关接口")
public class AttachmentController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private AttachmentService attachmentService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "全必填")
	public Res<Integer> addAttachment(@RequestBody AttachmentReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDataId()+"", req.getDataFrom(), req.getOperatorId()+"", req.getOperatorName())){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return attachmentService.addAttachment(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "全必填")
	public Res<Integer> modifyAttachment(@RequestBody AttachmentReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDataId()+"", req.getDataFrom(), req.getFilePath(), req.getOperatorId()+"", req.getOperatorName())){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return attachmentService.modifyAttachment(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delAttachment(
			@ApiParam(required = true, value = "附件ID")@RequestParam int attachmentId,
			@ApiParam(required = true, value = "操作人员ID")@RequestParam int operatorId,
			@ApiParam(required = true, value = "操作人员名称")@RequestParam String operatorName){
		/**必填参数非空检验*/
		if(!publicService.checkParam(attachmentId+"", operatorId+"", operatorName)){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return attachmentService.delAttachment(attachmentId,operatorId,operatorName);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:附件ID")
	public Res<AttachmentDto> attachmentDetail(@ApiParam(required = true, value = "附件ID")@RequestParam int attachmentId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(attachmentId+"")){
			return new Res<AttachmentDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return attachmentService.attachmentDetailById(attachmentId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<AttachmentDto> attachmentList(@ModelAttribute AttachmentDataTablesReq req){
		List<AttachmentDto> dto = attachmentService.attachmentList(req);
		JSONResponse<AttachmentDto> jsonResponse = new JSONResponse<AttachmentDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<AttachmentDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = attachmentService.attachmentCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
}
