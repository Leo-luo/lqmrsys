package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.RepaymentBean;
import com.lqmrSys.bean.req.RepaymentReq;

public interface RepaymentService {
	//修改还款计划
	public Res<Integer> modifyRepayment(RepaymentReq req);
	//还款计划列表
	public List<RepaymentBean> repaymentList(int loanReceiptId, String repaymentType);
}
