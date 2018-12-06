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
import com.lqmrSys.bean.dto.DictDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DictDataTablesReq;
import com.lqmrSys.bean.req.DictReq;
import com.lqmrSys.service.DictService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/dict")
@Api(value = "数据字典", description = "数据字典相关接口")
public class DictController {
	@Autowired
	private PublicService publicService;
	@Autowired
	private DictService dictService;

	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ApiOperation(value = "新增", notes = "必填:字典类型编码、字典内容、父字典ID")
	public Res<Integer> addDict(@RequestBody DictReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getTypeCode(), req.getContent(), req.getParentDictId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return dictService.addDict(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    @ApiOperation(value = "修改", notes = "必填:字典ID、字典类型编码、字典内容、父字典ID")
	public Res<Integer> modifyDict(@RequestBody DictReq req){
		/**必填参数非空检验*/
		if(!publicService.checkParam(req.getDictId()+"", req.getTypeCode(), req.getContent(), req.getParentDictId()+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return dictService.modifyDict(req);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/del")
    @ApiOperation(value = "删除", notes = "单个,全必填")
	public Res<Integer> delDict(@ApiParam(required = true, value = "字典ID")@RequestParam int dictId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(dictId+"")){
			return new Res<Integer>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return dictService.delDict(dictId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    @ApiOperation(value = "详情", notes = "必填:字典ID")
	public Res<DictDto> dictDetail(@ApiParam(required = true, value = "字典ID")@RequestParam int dictId){
		/**必填参数非空检验*/
		if(!publicService.checkParam(dictId+"")){
			return new Res<DictDto>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return dictService.dictDetailById(dictId);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/select_pagination")
    @ApiOperation(value = "列表", notes = "分页")
	public JSONResponse<DictDto> dictList(@ModelAttribute DictDataTablesReq req){
		List<DictDto> dto = dictService.dictList(req);
		JSONResponse<DictDto> jsonResponse = new JSONResponse<DictDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), 0, 0, new ArrayList<DictDto>(), req.getStart(), req.getLength());
        if(dto!=null){
        	int count = dto.size();
        	int totalCount = dictService.dictCount(req);
        	jsonResponse.setRecords(count);
            jsonResponse.setTotalRecords(totalCount);
            jsonResponse.setDataList(dto);
        }
		return jsonResponse;
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/menuTreeList")
    @ApiOperation(value = "字典树列表", notes = "字典树列表")
	public Res<List<List<MenuTreeDto>>> getMenuTree(@ApiParam(required = true, value = "字典编码串,','逗号隔开")@RequestParam String dictCodes){
		/**必填参数非空检验*/
		if(!publicService.checkParam(dictCodes+"")){
			return new Res<List<List<MenuTreeDto>>>(ResultCodeEnum.PARAMETER_NULL.getCode(), ResultCodeEnum.PARAMETER_NULL.getMessage(), null);
		}
		return new Res<List<List<MenuTreeDto>>>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dictService.dictMenuTreeList(dictCodes));
	}
}
