package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.StatisticsBean;
import com.lqmrSys.bean.StatisticsPageBean;
import com.lqmrSys.bean.StatisticsUserBean;

public interface StatisticsService {
	//统计页面数据
	public Res<StatisticsPageBean> pageData();
	//历年供应金额列表
	public List<StatisticsBean> yearSupplyAmountList(String startTime, String endTime);
	//月供应金额列表
	public List<StatisticsBean> monthSupplyAmountList(String startTime, String endTime);
	//历年贷款金额列表
	public List<StatisticsBean> yearLoanAmountList(String startTime, String endTime);
	//月贷款金额列表
	public List<StatisticsBean> monthLoanAmountList(String startTime, String endTime);
	//客户经理出借金额列表
	public List<StatisticsBean> managerSupplyAmountList(String startTime, String endTime);
	//客户经理贷款金额列表
	public List<StatisticsBean> managerLoanAmountList(String startTime, String endTime);
	//客户经理出借-贷款统计列表
	public List<StatisticsUserBean> managerSupplayAndLoanList(String startTime, String endTime);
		
}
