package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.SupplierAdvanceDto;
import com.lqmrSys.bean.pagination.SupplierAdvanceDataTablesReq;
import com.lqmrSys.bean.req.SupplierAdvanceReq;

public interface SupplierAdvanceService {
	//新增供应方预登记
	public Res<Integer> addSupplierAdvance(SupplierAdvanceReq req);
	//修改供应方预登记
	public Res<Integer> modifySupplierAdvance(SupplierAdvanceReq req);
	//逻辑删除供应方预登记
	public Res<Integer> delSupplierAdvance(int advanceId, int operatorId, String operatorName);
	//供应方预登记信息
	public Res<SupplierAdvanceDto> supplierAdvanceDetailById(int advanceId);
	//供应方预登记列表
	public List<SupplierAdvanceDto> supplierAdvanceList(SupplierAdvanceDataTablesReq req);
	//供应方预登记列表长度
	public int supplierAdvanceCount(SupplierAdvanceDataTablesReq req);
}
