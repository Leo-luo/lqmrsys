package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.CompanyDto;
import com.lqmrSys.bean.pagination.CompanyDataTablesReq;
import com.lqmrSys.bean.req.CompanyReq;

public interface CompanyService {
	//新增企业
	public Res<Integer> addCompany(CompanyReq req);
	//修改企业
	public Res<Integer> modifyCompany(CompanyReq req);
	//逻辑删除企业
	public Res<Integer> delCompany(int companyId, int operatorId, String operatorName);
	//企业信息
	public Res<CompanyDto> companyDetailById(int companyId);
	//企业列表
	public List<CompanyDto> companyList(CompanyDataTablesReq req);
	//企业列表长度
	public int companyCount(CompanyDataTablesReq req);
}
