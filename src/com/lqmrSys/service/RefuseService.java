package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.RefuseDto;
import com.lqmrSys.bean.pagination.RefuseDataTablesReq;
import com.lqmrSys.bean.req.RefuseReq;

public interface RefuseService {
	//新增拒贷
	public Res<Integer> addRefuse(RefuseReq req);
	//修改拒贷
	public Res<Integer> modifyRefuse(RefuseReq req);
	//逻辑删除拒贷
	public Res<Integer> delRefuse(int refuseId, int operatorId, String operatorName);
	//拒贷列表
	public List<RefuseDto> refuseList(RefuseDataTablesReq req);
	//拒贷列表长度
	public int refuseCount(RefuseDataTablesReq req);
	//是否为拒贷客户
	public Res<Integer> hasRefuse(int dataId, String dataFrom);
}
