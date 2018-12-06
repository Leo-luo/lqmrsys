package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.GuaranteeDto;
import com.lqmrSys.bean.pagination.GuaranteeDataTablesReq;
import com.lqmrSys.bean.req.GuaranteeReq;

public interface GuaranteeService {
	//新增担保
	public Res<Integer> addGuarantee(GuaranteeReq req);
	//修改担保
	public Res<Integer> modifyGuarantee(GuaranteeReq req);
	//逻辑删除担保
	public Res<Integer> delGuarantee(int guaranteeId, int operatorId, String operatorName);
	//担保信息
	public Res<GuaranteeDto> guaranteeDetailById(int guaranteeId);
	//担保列表
	public List<GuaranteeDto> guaranteeList(GuaranteeDataTablesReq req);
	//担保列表长度
	public int guaranteeCount(GuaranteeDataTablesReq req);
}
