package com.lqmrSys.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.StatisticsBean;
import com.lqmrSys.bean.StatisticsPageBean;
import com.lqmrSys.bean.StatisticsUserBean;
import com.lqmrSys.dao.ImportDataDao;
import com.lqmrSys.service.StatisticsService;
@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private ImportDataDao importDataDao;

	@Override
	public Res<StatisticsPageBean> pageData() {
		BigDecimal basic = new BigDecimal(10000); 
		
		StatisticsPageBean bean = new StatisticsPageBean();
		double totalSupplyAmount = importDataDao.totalSupplyAmount();//供应总额
		double totalLoanAmount = importDataDao.totalLoanAmount();////贷款总额
		double canMatchingSupplyAmount = importDataDao.canMatchingAmount();////可配对金额
		List<StatisticsBean> yearSupplyAmountList = importDataDao.yearSupplyAmountList("", "");//历年供应金额列表
		List<StatisticsBean> monthSupplyAmountList = importDataDao.monthSupplyAmountList("", "");//月供应金额列表
		List<StatisticsBean> yearLoanAmountList = importDataDao.yearLoanAmountList("", "");//历年贷款金额列表
		List<StatisticsBean> monthLoanAmountList = importDataDao.monthLoanAmountList("", "");//月贷款金额列表
		List<StatisticsBean> managerSupplyAmountList = importDataDao.managerSupplyAmountList("", "");//客户经理出借金额列表
		List<StatisticsBean> managerLoanAmountList = importDataDao.managerLoanAmountList("", "");//客户经理贷款金额列表
		List<StatisticsUserBean> managerSupplayAndLoanList = importDataDao.managerSupplayAndLoanList("", "");//客户经理出借-贷款统计列表
		if(totalSupplyAmount!=0){
			BigDecimal b1 = new BigDecimal(Double.toString(totalSupplyAmount)); 
			bean.setTotalSupplyAmount(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if(totalLoanAmount!=0){
			BigDecimal b1 = new BigDecimal(Double.toString(totalLoanAmount)); 
			bean.setTotalLoanAmount(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		if(canMatchingSupplyAmount!=0){
			BigDecimal b1 = new BigDecimal(Double.toString(canMatchingSupplyAmount)); 
			bean.setCanMatchingSupplyAmount(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		bean.setUserNum(39);
		if(yearSupplyAmountList!=null && yearSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : yearSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setYearSupplyAmountList(yearSupplyAmountList);
		}
		if(monthSupplyAmountList!=null && monthSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : monthSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setMonthSupplyAmountList(monthSupplyAmountList);
		}
		if(yearLoanAmountList!=null && yearLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : yearLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setYearLoanAmountList(yearLoanAmountList);
		}
		if(monthLoanAmountList!=null && monthLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : monthLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setMonthLoanAmountList(monthLoanAmountList);
		}
		if(managerSupplyAmountList!=null && managerSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : managerSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setManagerSupplyAmountList(managerSupplyAmountList);
		}
		if(managerLoanAmountList!=null && managerLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : managerLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setManagerLoanAmountList(managerLoanAmountList);
		}
		if(managerSupplayAndLoanList!=null && managerSupplayAndLoanList.size()>0){
			for (StatisticsUserBean statisticsUserBean : managerSupplayAndLoanList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsUserBean.getSupplyAmount())); 
				BigDecimal b2 = new BigDecimal(Double.toString(statisticsUserBean.getTotalSupplyAmount())); 
				BigDecimal b3 = new BigDecimal(Double.toString(statisticsUserBean.getLoanAmount())); 
				BigDecimal b4 = new BigDecimal(Double.toString(statisticsUserBean.getTotalLoanAmount())); 
				statisticsUserBean.setSupplyAmount(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setTotalSupplyAmount(b2.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setLoanAmount(b3.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setTotalLoanAmount(b4.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			bean.setManagerSupplayAndLoanList(managerSupplayAndLoanList);
		}
		return new Res<StatisticsPageBean>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), bean);
	}

	@Override
	public List<StatisticsBean> yearSupplyAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> yearSupplyAmountList = importDataDao.yearSupplyAmountList(startTime, endTime);//历年供应金额列表
		if(yearSupplyAmountList!=null && yearSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : yearSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return yearSupplyAmountList;
	}

	@Override
	public List<StatisticsBean> monthSupplyAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> monthSupplyAmountList = importDataDao.monthSupplyAmountList(startTime, endTime);//历年供应金额列表
		if(monthSupplyAmountList!=null && monthSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : monthSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return monthSupplyAmountList;
	}

	@Override
	public List<StatisticsBean> yearLoanAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> yearLoanAmountList = importDataDao.yearLoanAmountList(startTime, endTime);//历年供应金额列表
		if(yearLoanAmountList!=null && yearLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : yearLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return yearLoanAmountList;
	}

	@Override
	public List<StatisticsBean> monthLoanAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> monthLoanAmountList = importDataDao.monthLoanAmountList(startTime, endTime);//历年供应金额列表
		if(monthLoanAmountList!=null && monthLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : monthLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return monthLoanAmountList;
	}

	@Override
	public List<StatisticsBean> managerSupplyAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> managerSupplyAmountList = importDataDao.managerSupplyAmountList(startTime, endTime);//历年供应金额列表
		if(managerSupplyAmountList!=null && managerSupplyAmountList.size()>0){
			for (StatisticsBean statisticsBean : managerSupplyAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return managerSupplyAmountList;
	}

	@Override
	public List<StatisticsBean> managerLoanAmountList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsBean> managerLoanAmountList = importDataDao.managerLoanAmountList(startTime, endTime);//历年供应金额列表
		if(managerLoanAmountList!=null && managerLoanAmountList.size()>0){
			for (StatisticsBean statisticsBean : managerLoanAmountList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsBean.getDataValue())); 
				statisticsBean.setDataValue(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return managerLoanAmountList;
	}

	@Override
	public List<StatisticsUserBean> managerSupplayAndLoanList(String startTime, String endTime) {
		BigDecimal basic = new BigDecimal(10000); 
		List<StatisticsUserBean> managerSupplayAndLoanList = importDataDao.managerSupplayAndLoanList(startTime, endTime);
		if(managerSupplayAndLoanList!=null && managerSupplayAndLoanList.size()>0){
			for (StatisticsUserBean statisticsUserBean : managerSupplayAndLoanList) {
				BigDecimal b1 = new BigDecimal(Double.toString(statisticsUserBean.getSupplyAmount())); 
				BigDecimal b2 = new BigDecimal(Double.toString(statisticsUserBean.getTotalSupplyAmount())); 
				BigDecimal b3 = new BigDecimal(Double.toString(statisticsUserBean.getLoanAmount())); 
				BigDecimal b4 = new BigDecimal(Double.toString(statisticsUserBean.getTotalLoanAmount())); 
				statisticsUserBean.setSupplyAmount(b1.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setTotalSupplyAmount(b2.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setLoanAmount(b3.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
				statisticsUserBean.setTotalLoanAmount(b4.divide(basic, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		return managerSupplayAndLoanList;
	}
}
