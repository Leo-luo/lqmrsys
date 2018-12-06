package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.SupplierDto;
import com.lqmrSys.bean.pagination.SupplierDataTablesReq;
import com.lqmrSys.bean.req.SupplierReq;

public interface SupplierService {
	//新增供应方
	public Res<Integer> addSupplier(SupplierReq req);
	//修改供应方
	public Res<Integer> modifySupplier(SupplierReq req);
	//逻辑删除供应方
	public Res<Integer> delSupplier(int supplierId, int operatorId, String operatorName);
	//供应方信息
	public Res<SupplierDto> supplierDetailById(int supplierId);
	//供应方列表
	public List<SupplierDto> supplierList(SupplierDataTablesReq req);
	//供应方列表长度
	public int supplierCount(SupplierDataTablesReq req);
}
