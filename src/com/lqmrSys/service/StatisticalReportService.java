package com.lqmrSys.service;

import java.util.List;

import com.lqmrSys.bean.SupplyExpireRemindBean;
import com.lqmrSys.bean.dto.LoanExpireRemindDto;

public interface StatisticalReportService {
	//供应方到期提醒列表
	public List<SupplyExpireRemindBean> supplyExpireRemindList();
	//借款到期提醒列表-逾期借款提醒列表
	public List<LoanExpireRemindDto> loanExpireRemindList(String listType);
	
}
