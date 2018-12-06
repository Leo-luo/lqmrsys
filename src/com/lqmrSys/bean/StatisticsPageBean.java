package com.lqmrSys.bean;

import java.util.List;

public class StatisticsPageBean {
	//供应总额
	private double totalSupplyAmount;
	//贷款总额
	private double totalLoanAmount;
	//可配对金额
	private double canMatchingSupplyAmount;
	//人员数量
	private int userNum;
	//历年供应金额列表
	private List<StatisticsBean> yearSupplyAmountList;
	//月供应金额列表
	private List<StatisticsBean> monthSupplyAmountList;
	//历年贷款金额列表
	private List<StatisticsBean> yearLoanAmountList;
	//月贷款金额列表
	private List<StatisticsBean> monthLoanAmountList;
	//客户经理出借金额列表
	private List<StatisticsBean> managerSupplyAmountList;
	//客户经理贷款金额列表
	private List<StatisticsBean> managerLoanAmountList;
	//客户经理出借-贷款统计列表
	private List<StatisticsUserBean> managerSupplayAndLoanList;
	public List<StatisticsUserBean> getManagerSupplayAndLoanList() {
		return managerSupplayAndLoanList;
	}
	public void setManagerSupplayAndLoanList(
			List<StatisticsUserBean> managerSupplayAndLoanList) {
		this.managerSupplayAndLoanList = managerSupplayAndLoanList;
	}
	public double getTotalSupplyAmount() {
		return totalSupplyAmount;
	}
	public void setTotalSupplyAmount(double totalSupplyAmount) {
		this.totalSupplyAmount = totalSupplyAmount;
	}
	public double getTotalLoanAmount() {
		return totalLoanAmount;
	}
	public void setTotalLoanAmount(double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}
	public double getCanMatchingSupplyAmount() {
		return canMatchingSupplyAmount;
	}
	public void setCanMatchingSupplyAmount(double canMatchingSupplyAmount) {
		this.canMatchingSupplyAmount = canMatchingSupplyAmount;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public List<StatisticsBean> getYearSupplyAmountList() {
		return yearSupplyAmountList;
	}
	public void setYearSupplyAmountList(List<StatisticsBean> yearSupplyAmountList) {
		this.yearSupplyAmountList = yearSupplyAmountList;
	}
	public List<StatisticsBean> getMonthSupplyAmountList() {
		return monthSupplyAmountList;
	}
	public void setMonthSupplyAmountList(List<StatisticsBean> monthSupplyAmountList) {
		this.monthSupplyAmountList = monthSupplyAmountList;
	}
	public List<StatisticsBean> getYearLoanAmountList() {
		return yearLoanAmountList;
	}
	public void setYearLoanAmountList(List<StatisticsBean> yearLoanAmountList) {
		this.yearLoanAmountList = yearLoanAmountList;
	}
	public List<StatisticsBean> getMonthLoanAmountList() {
		return monthLoanAmountList;
	}
	public void setMonthLoanAmountList(List<StatisticsBean> monthLoanAmountList) {
		this.monthLoanAmountList = monthLoanAmountList;
	}
	public List<StatisticsBean> getManagerSupplyAmountList() {
		return managerSupplyAmountList;
	}
	public void setManagerSupplyAmountList(
			List<StatisticsBean> managerSupplyAmountList) {
		this.managerSupplyAmountList = managerSupplyAmountList;
	}
	public List<StatisticsBean> getManagerLoanAmountList() {
		return managerLoanAmountList;
	}
	public void setManagerLoanAmountList(List<StatisticsBean> managerLoanAmountList) {
		this.managerLoanAmountList = managerLoanAmountList;
	}
}
