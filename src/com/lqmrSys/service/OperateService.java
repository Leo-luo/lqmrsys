package com.lqmrSys.service;

import java.util.List;

import com.lqmrSys.bean.OperateBean;
import com.lqmrSys.bean.dto.OperateDto;
import com.lqmrSys.bean.pagination.OperateDataTablesReq;

public interface OperateService {
	//新增操作日志
	public int addOperate(OperateBean req);
	//操作日志列表
	public List<OperateDto> operateList(OperateDataTablesReq req);
	//操作日志列表长度
	public int operateCount(OperateDataTablesReq req);
}
