package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.DictDto;
import com.lqmrSys.bean.dto.MenuTreeDto;
import com.lqmrSys.bean.pagination.DictDataTablesReq;
import com.lqmrSys.bean.req.DictReq;

public interface DictService {
	//新增数据字典
	public Res<Integer> addDict(DictReq req);
	//修改数据字典
	public Res<Integer> modifyDict(DictReq req);
	//逻辑删除数据字典
	public Res<Integer> delDict(int dictId);
	//数据字典信息
	public Res<DictDto> dictDetailById(int dictId);
	//数据字典列表
	public List<DictDto> dictList(DictDataTablesReq req);
	//数据字典列表长度
	public int dictCount(DictDataTablesReq req);
	//数据字典树列表
	public List<List<MenuTreeDto>> dictMenuTreeList(String dictCodes);
}
