package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.LoanReceiptBean;
import com.lqmrSys.bean.dto.LoanReceiptDto;
import com.lqmrSys.bean.dto.LoanReceiptDtob;
import com.lqmrSys.bean.pagination.LoanReceiptDataTablesReq;

public interface LoanReceiptService {
	//新增借据
	public Res<Integer> addLoanReceipt(LoanReceiptBean req);
	//借据列表
	public List<LoanReceiptDto> loanReceiptList(LoanReceiptDataTablesReq req);
	//借据长度
	public int loanReceiptCount(LoanReceiptDataTablesReq req);
	//借款列表
	public List<LoanReceiptDtob> loanReceiptList2(LoanReceiptDataTablesReq req);
	//借款长度
	public int loanReceiptCount2(LoanReceiptDataTablesReq req);
}
