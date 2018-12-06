package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.pagination.DemandDataTablesReq;
import com.lqmrSys.bean.req.DemandReq;

public interface DemandService {
	//新增需求方
	public Res<Integer> addDemand(DemandReq req);
	//修改需求方
	public Res<Integer> modifyDemand(DemandReq req);
	//逻辑删除需求方
	public Res<Integer> delDemand(int demandId, int operatorId, String operatorName);
	//需求方信息
	public Res<DemandDto> demandDetailById(int demandId);
	//需求方列表
	public List<DemandDto> demandList(DemandDataTablesReq req);
	//需求方列表长度
	public int demandCount(DemandDataTablesReq req);
}
