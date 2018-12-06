package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.FinancingMatchingContractDto;
import com.lqmrSys.bean.pagination.FinancingMatchingContractDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingContractReq;

public interface ContractService {
	//新增融资配对信息合同
	public Res<Integer> addFinancingMatchingContract(FinancingMatchingContractReq req);
	//修改融资配对信息合同
	public Res<Integer> modifyFinancingMatchingContract(FinancingMatchingContractReq req);
	//融资配对信息合同详情
	public Res<FinancingMatchingContractDto> financingMatchingContractDetail(int contractId);
	//融资配对信息合同列表
	public List<FinancingMatchingContractDto> financingMatchingContractList(FinancingMatchingContractDataTablesReq req);
	//融资配对信息合同列表长度
	public int financingMatchingContractCount(FinancingMatchingContractDataTablesReq req);
}
