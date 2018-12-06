package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.FinancingMatchingAllDetailDto;
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.pagination.FinancingMatchingDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingReq;

public interface FinancingMatchingService {
	//新增融资配对信息
	public Res<Integer> addFinancingMatching(FinancingMatchingReq req);
	//修改融资配对信息
	public Res<Integer> modifyFinancingMatching(FinancingMatchingReq req);
	//删除融资配对信息
	public Res<Integer> delFinancingMatching(int financingMatchingId, int operatorId, String operatorName);
	//融资配对信息列表
	public List<FinancingMatchingDto> financingMatchingList(FinancingMatchingDataTablesReq req);
	//融资配对信息长度
	public int financingMatchingCount(FinancingMatchingDataTablesReq req);
	//融资配对信息详情
	public Res<FinancingMatchingDto> financingMatchingDetail(int financingMatchingId);
	//融资配对信息详情
	public Res<FinancingMatchingAllDetailDto> financingMatchingDetailAll(String matchingCode);
}
