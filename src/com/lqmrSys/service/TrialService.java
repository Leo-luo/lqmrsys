package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.TrialDto;
import com.lqmrSys.bean.pagination.TrialDataTablesReq;
import com.lqmrSys.bean.req.TrialReq;

public interface TrialService {
	//批量新增角色模块关联
	public Res<Integer> addTrialList(List<TrialReq> list);
	//修改审核-审批节点状态
	public Res<Integer> modifyTrialStatus(TrialReq req);
	//审核-审批节点列表
	public List<TrialDto> trialList(TrialDataTablesReq req);
}
