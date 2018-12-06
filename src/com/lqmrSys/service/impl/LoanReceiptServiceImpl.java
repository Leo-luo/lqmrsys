package com.lqmrSys.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constants;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.LoanReceiptBean;
import com.lqmrSys.bean.RepaymentBean;
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.dto.LoanReceiptDto;
import com.lqmrSys.bean.dto.LoanReceiptDtob;
import com.lqmrSys.bean.pagination.LoanReceiptDataTablesReq;
import com.lqmrSys.dao.DemandDao;
import com.lqmrSys.dao.FinancingMatchingDao;
import com.lqmrSys.dao.LoanReceiptDao;
import com.lqmrSys.dao.RepaymentDao;
import com.lqmrSys.service.LoanReceiptService;
@Service
public class LoanReceiptServiceImpl implements LoanReceiptService {
	@Autowired
	private LoanReceiptDao loadReceiptDao;
	@Autowired
	private FinancingMatchingDao financingMatchingDao;
	@Autowired
	private RepaymentDao repaymentDao;
	@Autowired
	private DemandDao demandDao;

	@Override
	public Res<Integer> addLoanReceipt(LoanReceiptBean req) {
		int loanReceiptNum = loadReceiptDao.loanReceiptAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String loanReceiptCode = "120"+sdf.format(new Date()).substring(2, 8);
		if(loanReceiptNum<=9){
			loanReceiptCode = loanReceiptCode + "0000" + loanReceiptNum;
		}else if(loanReceiptNum<=99){
			loanReceiptCode = loanReceiptCode + "000" + loanReceiptNum;
		}else if(loanReceiptNum<=999){
			loanReceiptCode = loanReceiptCode + "00" + loanReceiptNum;
		}else if(loanReceiptNum<=9999){
			loanReceiptCode = loanReceiptCode + "0" + loanReceiptNum;
		}else if(loanReceiptNum<=99999){
			loanReceiptCode = loanReceiptCode + loanReceiptNum;
		}
		req.setLoanReceiptCode(loanReceiptCode);
		int result = loadReceiptDao.addLoanReceipt(req);
		if(result!=0){
			//根据还款方式-服务费支付方式，生成还款计划数据
			FinancingMatchingDto dto = financingMatchingDao.financingMatchingDetailById(req.getFinancingMatchingId());
			if(dto!=null){
				boolean resultTrue = false;
				double financingAmount = dto.getFinancingAmount();//融资金额
				double supplierMonthRate = dto.getSupplierMonthRate();//月利率(供应方资金)
				double supplierServiceRate = dto.getSupplierServiceRate();//服务费用利率(供应方资金)
				String planStartTime = dto.getStartTime();//开始日期
				String planEndTime = dto.getEndTime();//截止日期
				int days = 0;
				BigDecimal thousand = new BigDecimal(1000);
				BigDecimal monthDays = new BigDecimal(30);
				BigDecimal decimal1 = new BigDecimal(Double.toString(financingAmount));
				BigDecimal decimal2 = new BigDecimal(Double.toString(supplierMonthRate));
				BigDecimal decimal3 = new BigDecimal(Double.toString(supplierServiceRate));
				BigDecimal decimal4 = new BigDecimal(Integer.toString(days));
				//根据还款方式，生成本息还款计划
				if("利息前置".equals(dto.getRepaymentMode().substring(0, 4))){//利息前置,到期还本
					try {
						days = (int)((format.parse(planEndTime).getTime()-format.parse(planStartTime).getTime())/(1000*3600*24));
						Double planInterestAmount = decimal1.multiply(decimal2).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//						Double planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						//付息预置7天还款日
						Calendar calendar = Calendar.getInstance(); 
						calendar.setTime(format.parse(planStartTime));
						calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
						//插入数据
						RepaymentBean bean1 = new RepaymentBean();
						bean1.setFinancingMatchingId(req.getFinancingMatchingId());
						bean1.setLoanReceiptId(result);
						bean1.setPlanStartTime(planStartTime);
						bean1.setPlanEndTime(sdf.format(calendar.getTime()));
						bean1.setPlanPrincipalAmount(0);
						bean1.setPlanInterestAmount(planInterestAmount);
//						bean1.setPlanServiceAmount(planServiceAmount);
						bean1.setPlanServiceAmount(0);
						bean1.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean1.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean1.setActualPrincipalAmount(0);
						bean1.setActualInterestAmount(0);
						bean1.setActualServiceAmount(0);
						bean1.setPayStatus(Constants.DATA_STATUS_NORMAL);
						bean1.setOperatorId(req.getOperatorId());
						bean1.setOperatorName(req.getOperatorName());
						bean1.setRepaymentType("本息");
						int result1 = repaymentDao.addRepayment(bean1);
						//本金在截止日前一天还款
						Calendar calendar2 = Calendar.getInstance(); 
						calendar2.setTime(format.parse(planEndTime));
						calendar2.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
						RepaymentBean bean2 = new RepaymentBean();
						bean2.setFinancingMatchingId(req.getFinancingMatchingId());
						bean2.setLoanReceiptId(result);
						bean2.setPlanStartTime(sdf.format(calendar2.getTime()));
						bean2.setPlanEndTime(planEndTime);
						bean2.setPlanPrincipalAmount(decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean2.setPlanInterestAmount(0);
						bean2.setPlanServiceAmount(0);
						bean2.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean2.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean2.setActualPrincipalAmount(0);
						bean2.setActualInterestAmount(0);
						bean2.setActualServiceAmount(0);
						bean2.setPayStatus(Constants.DATA_STATUS_NORMAL);
						bean2.setOperatorId(req.getOperatorId());
						bean2.setOperatorName(req.getOperatorName());
						bean2.setRepaymentType("本息");
						int result2 = repaymentDao.addRepayment(bean2);
						if(result1!=0 && result2!=0){
							resultTrue = true;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("利随本清".equals(dto.getRepaymentMode().substring(0, 4))){//利随本清
					try {
						days = (int)((format.parse(planEndTime).getTime()-format.parse(planStartTime).getTime())/(1000*3600*24));
						Double planInterestAmount = decimal1.multiply(decimal2).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//						Double planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						//还款日
						Calendar calendar = Calendar.getInstance(); 
						calendar.setTime(format.parse(planStartTime));
						calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
						//插入数据
						RepaymentBean bean1 = new RepaymentBean();
						bean1.setFinancingMatchingId(req.getFinancingMatchingId());
						bean1.setLoanReceiptId(result);
						bean1.setPlanStartTime(sdf.format(calendar.getTime()));
						bean1.setPlanEndTime(planEndTime);
						bean1.setPlanPrincipalAmount(decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean1.setPlanInterestAmount(planInterestAmount);
						bean1.setPlanServiceAmount(0);
						bean1.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean1.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean1.setActualPrincipalAmount(0);
						bean1.setActualInterestAmount(0);
						bean1.setActualServiceAmount(0);
						bean1.setPayStatus(Constants.DATA_STATUS_NORMAL);
						bean1.setOperatorId(req.getOperatorId());
						bean1.setOperatorName(req.getOperatorName());
						bean1.setRepaymentType("本息");
						int result1 = repaymentDao.addRepayment(bean1);
						if(result1!=0){
							resultTrue = true;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("按期付息".equals(dto.getRepaymentMode().substring(0, 4))){//按期付息,到期还本
					try {
						//计算金额
						BigDecimal dayPlanInterestAmount = decimal1.multiply(decimal2).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
//						BigDecimal dayPlanServiceAmount = decimal1.multiply(decimal3).divide(thousand).divide(monthDays).setScale(2, BigDecimal.ROUND_HALF_UP);
						//计算日期，循环插入数据
						ArrayList<String> dayList = new ArrayList<String>();
				    	Date d1 = sdf.parse(planStartTime);//定义起始日期
				    	Date d2 = sdf.parse(planEndTime);//定义结束日期
				    	Calendar dd = Calendar.getInstance();//定义日期实例
				    	dd.setTime(d1);//设置日期起始时间
				    	while(dd.getTime().before(d2)){//判断是否到结束日期
				    		dd.add(Calendar.DAY_OF_MONTH, -1);//进行当前日期月份天数减1
					    	String str = sdf.format(dd.getTime());
					    	dayList.add(str);
					    	dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份天数加1
					    	String str2 = sdf.format(dd.getTime());
					    	dayList.add(str2);
					    	dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
				    	}
				    	if("01".equals(planStartTime.substring(8, 10))){//判断是否为月初，去除数组中不可使用对象
				    		dayList.remove(0);
						}
				    	dayList.add(sdf.format(d2.getTime()));//截止日期加入数组中
				    	for (int i = 0; i < dayList.size(); i++) {
							int next = i+1;
							Date startDay = sdf.parse(dayList.get(i));
				    		Date endDay = sdf.parse(dayList.get(next));
				    		days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
				    		decimal4 = new BigDecimal(Integer.toString(days));
				    		Double planInterestAmount = dayPlanInterestAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//				    		Double planServiceAmount = dayPlanServiceAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    		//插入数据
							RepaymentBean bean = new RepaymentBean();
							bean.setFinancingMatchingId(req.getFinancingMatchingId());
							bean.setLoanReceiptId(result);
							bean.setPlanStartTime(dayList.get(i));
							bean.setPlanEndTime(dayList.get(next));
							if(next==(dayList.size()-1)){
								bean.setPlanPrincipalAmount(decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							}else{
								bean.setPlanPrincipalAmount(0);
							}
							bean.setPlanInterestAmount(planInterestAmount);
//							bean.setPlanServiceAmount(planServiceAmount);
							bean.setPlanServiceAmount(0);
							bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setActualPrincipalAmount(0);
							bean.setActualInterestAmount(0);
							bean.setActualServiceAmount(0);
							bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean.setOperatorId(req.getOperatorId());
							bean.setOperatorName(req.getOperatorName());
							bean.setRepaymentType("本息");
							int result1 = repaymentDao.addRepayment(bean);
							if(result1!=0){
								resultTrue = true;
							}else{
								return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
							}
				    		i++;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				//根据服务费支付方式，生成服务费还款计划
				if("一次性".equals(dto.getServicePayMode())){//按期付息,到期还本
					try {
						//计算金额
						days = (int)((format.parse(planEndTime).getTime()-format.parse(planStartTime).getTime())/(1000*3600*24));
						BigDecimal planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
						//插入数据
						RepaymentBean bean = new RepaymentBean();
						bean.setFinancingMatchingId(req.getFinancingMatchingId());
						bean.setLoanReceiptId(result);
						bean.setPlanStartTime(planStartTime);
						bean.setPlanEndTime(planEndTime);
						bean.setPlanPrincipalAmount(0);
						bean.setPlanInterestAmount(0);
						bean.setPlanServiceAmount(planServiceAmount.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
						bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						bean.setActualPrincipalAmount(0);
						bean.setActualInterestAmount(0);
						bean.setActualServiceAmount(0);
						bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
						bean.setOperatorId(req.getOperatorId());
						bean.setOperatorName(req.getOperatorName());
						bean.setRepaymentType("服务费");
						int result1 = repaymentDao.addRepayment(bean);
						if(result1!=0){
							resultTrue = true;
						}else{
							return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("按月平均支付".equals(dto.getServicePayMode())){
					try {
						//计算金额
						BigDecimal dayPlanServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
						//计算日期，循环插入数据
						ArrayList<String> dayList = new ArrayList<String>();
				    	Date d1 = sdf.parse(planStartTime);//定义起始日期
				    	Date d2 = sdf.parse(planEndTime);//定义结束日期
				    	Calendar dd = Calendar.getInstance();//定义日期实例
				    	dd.setTime(d1);//设置日期起始时间
				    	while(dd.getTime().before(d2)){//判断是否到结束日期
				    		dd.add(Calendar.DAY_OF_MONTH, -1);//进行当前日期月份天数减1
					    	String str = sdf.format(dd.getTime());
					    	dayList.add(str);
					    	dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份天数加1
					    	String str2 = sdf.format(dd.getTime());
					    	dayList.add(str2);
					    	dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
				    	}
				    	if("01".equals(planStartTime.substring(8, 10))){//判断是否为月初，去除数组中不可使用对象
				    		dayList.remove(0);
						}
				    	dayList.add(sdf.format(d2.getTime()));//截止日期加入数组中
				    	for (int i = 0; i < dayList.size(); i++) {
							int next = i+1;
							Date startDay = sdf.parse(dayList.get(i));
				    		Date endDay = sdf.parse(dayList.get(next));
				    		days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
				    		decimal4 = new BigDecimal(Integer.toString(days));
				    		Double planServiceAmount = dayPlanServiceAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    		//插入数据
							RepaymentBean bean = new RepaymentBean();
							bean.setFinancingMatchingId(req.getFinancingMatchingId());
							bean.setLoanReceiptId(result);
							bean.setPlanStartTime(dayList.get(i));
							bean.setPlanEndTime(dayList.get(next));
							bean.setPlanPrincipalAmount(0);
							bean.setPlanInterestAmount(0);
							bean.setPlanServiceAmount(planServiceAmount);
							bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setActualPrincipalAmount(0);
							bean.setActualInterestAmount(0);
							bean.setActualServiceAmount(0);
							bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean.setOperatorId(req.getOperatorId());
							bean.setOperatorName(req.getOperatorName());
							bean.setRepaymentType("服务费");
							int result1 = repaymentDao.addRepayment(bean);
							if(result1!=0){
								resultTrue = true;
							}else{
								return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
							}
				    		i++;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("按季平均支付".equals(dto.getServicePayMode())){
					try {
						//计算金额
						BigDecimal dayPlanServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
						//计算日期，循环插入数据
						ArrayList<String> dayList = new ArrayList<String>();
				    	Date d1 = sdf.parse(planStartTime);//定义起始日期
				    	Date d2 = sdf.parse(planEndTime);//定义结束日期
				    	Calendar dd = Calendar.getInstance();//定义日期实例
				    	dd.setTime(d1);//设置日期起始时间
				    	while(dd.getTime().before(d2)){//判断是否到结束日期
				    		dd.add(Calendar.DAY_OF_MONTH, -1);//进行当前日期月份天数减1
					    	String str = sdf.format(dd.getTime());
					    	dayList.add(str);
					    	dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份天数加1
					    	String str2 = sdf.format(dd.getTime());
					    	dayList.add(str2);
					    	dd.add(Calendar.MONTH, 4);//进行当前日期月份加1
				    	}
				    	if("01".equals(planStartTime.substring(8, 10))){//判断是否为月初，去除数组中不可使用对象
				    		dayList.remove(0);
						}
				    	dayList.add(sdf.format(d2.getTime()));//截止日期加入数组中
				    	for (int i = 0; i < dayList.size(); i++) {
							int next = i+1;
							Date startDay = sdf.parse(dayList.get(i));
				    		Date endDay = sdf.parse(dayList.get(next));
				    		days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
				    		decimal4 = new BigDecimal(Integer.toString(days));
				    		Double planServiceAmount = dayPlanServiceAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    		//插入数据
							RepaymentBean bean = new RepaymentBean();
							bean.setFinancingMatchingId(req.getFinancingMatchingId());
							bean.setLoanReceiptId(result);
							bean.setPlanStartTime(dayList.get(i));
							bean.setPlanEndTime(dayList.get(next));
							bean.setPlanPrincipalAmount(0);
							bean.setPlanInterestAmount(0);
							bean.setPlanServiceAmount(planServiceAmount);
							bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setActualPrincipalAmount(0);
							bean.setActualInterestAmount(0);
							bean.setActualServiceAmount(0);
							bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean.setOperatorId(req.getOperatorId());
							bean.setOperatorName(req.getOperatorName());
							bean.setRepaymentType("服务费");
							int result1 = repaymentDao.addRepayment(bean);
							if(result1!=0){
								resultTrue = true;
							}else{
								return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
							}
				    		i++;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("按半平均支付".equals(dto.getServicePayMode())){
					try {
						//计算金额
						BigDecimal dayPlanServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
						//计算日期，循环插入数据
						ArrayList<String> dayList = new ArrayList<String>();
				    	Date d1 = sdf.parse(planStartTime);//定义起始日期
				    	Date d2 = sdf.parse(planEndTime);//定义结束日期
				    	Calendar dd = Calendar.getInstance();//定义日期实例
				    	dd.setTime(d1);//设置日期起始时间
				    	while(dd.getTime().before(d2)){//判断是否到结束日期
				    		dd.add(Calendar.DAY_OF_MONTH, -1);//进行当前日期月份天数减1
					    	String str = sdf.format(dd.getTime());
					    	dayList.add(str);
					    	dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份天数加1
					    	String str2 = sdf.format(dd.getTime());
					    	dayList.add(str2);
					    	dd.add(Calendar.MONTH, 4);//进行当前日期月份加1
				    	}
				    	if("01".equals(planStartTime.substring(8, 10))){//判断是否为月初，去除数组中不可使用对象
				    		dayList.remove(0);
						}
				    	dayList.add(sdf.format(d2.getTime()));//截止日期加入数组中
				    	for (int i = 0; i < dayList.size(); i++) {
							int next = i+1;
							Date startDay = sdf.parse(dayList.get(i));
				    		Date endDay = sdf.parse(dayList.get(next));
				    		days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
				    		decimal4 = new BigDecimal(Integer.toString(days));
				    		Double planServiceAmount = dayPlanServiceAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				    		//插入数据
							RepaymentBean bean = new RepaymentBean();
							bean.setFinancingMatchingId(req.getFinancingMatchingId());
							bean.setLoanReceiptId(result);
							bean.setPlanStartTime(dayList.get(i));
							bean.setPlanEndTime(dayList.get(next));
							bean.setPlanPrincipalAmount(0);
							bean.setPlanInterestAmount(0);
							bean.setPlanServiceAmount(planServiceAmount);
							bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean.setActualPrincipalAmount(0);
							bean.setActualInterestAmount(0);
							bean.setActualServiceAmount(0);
							bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean.setOperatorId(req.getOperatorId());
							bean.setOperatorName(req.getOperatorName());
							bean.setRepaymentType("服务费");
							int result1 = repaymentDao.addRepayment(bean);
							if(result1!=0){
								resultTrue = true;
							}else{
								return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
							}
				    		i++;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("按还款方式支付".equals(dto.getServicePayMode())){
					if("利息前置".equals(dto.getRepaymentMode().substring(0, 4))){//利息前置,到期还本
						try {
							days = (int)((format.parse(planEndTime).getTime()-format.parse(planStartTime).getTime())/(1000*3600*24));
							Double planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							//付息预置7天还款日
							Calendar calendar = Calendar.getInstance(); 
							calendar.setTime(format.parse(planStartTime));
							calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
							//插入数据
							RepaymentBean bean1 = new RepaymentBean();
							bean1.setFinancingMatchingId(req.getFinancingMatchingId());
							bean1.setLoanReceiptId(result);
							bean1.setPlanStartTime(planStartTime);
							bean1.setPlanEndTime(sdf.format(calendar.getTime()));
							bean1.setPlanPrincipalAmount(0);
							bean1.setPlanInterestAmount(0);
							bean1.setPlanServiceAmount(planServiceAmount);
							bean1.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean1.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean1.setActualPrincipalAmount(0);
							bean1.setActualInterestAmount(0);
							bean1.setActualServiceAmount(0);
							bean1.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean1.setOperatorId(req.getOperatorId());
							bean1.setOperatorName(req.getOperatorName());
							bean1.setRepaymentType("服务费");
							int result1 = repaymentDao.addRepayment(bean1);
							if(result1!=0){
								resultTrue = true;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}else if("利随本清".equals(dto.getRepaymentMode().substring(0, 4))){//利随本清
						try {
							days = (int)((format.parse(planEndTime).getTime()-format.parse(planStartTime).getTime())/(1000*3600*24));
							Double planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							//还款日
							Calendar calendar = Calendar.getInstance(); 
							calendar.setTime(format.parse(planStartTime));
							calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
							//插入数据
							RepaymentBean bean1 = new RepaymentBean();
							bean1.setFinancingMatchingId(req.getFinancingMatchingId());
							bean1.setLoanReceiptId(result);
							bean1.setPlanStartTime(sdf.format(calendar.getTime()));
							bean1.setPlanEndTime(planEndTime);
							bean1.setPlanPrincipalAmount(0);
							bean1.setPlanInterestAmount(0);
							bean1.setPlanServiceAmount(planServiceAmount);
							bean1.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean1.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							bean1.setActualPrincipalAmount(0);
							bean1.setActualInterestAmount(0);
							bean1.setActualServiceAmount(0);
							bean1.setPayStatus(Constants.DATA_STATUS_NORMAL);
							bean1.setOperatorId(req.getOperatorId());
							bean1.setOperatorName(req.getOperatorName());
							bean1.setRepaymentType("服务费");
							int result1 = repaymentDao.addRepayment(bean1);
							if(result1!=0){
								resultTrue = true;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}else if("按期付息".equals(dto.getRepaymentMode().substring(0, 4))){//按期付息,到期还本
						try {
							//计算金额
							BigDecimal dayPlanServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
							//计算日期，循环插入数据
							ArrayList<String> dayList = new ArrayList<String>();
					    	Date d1 = sdf.parse(planStartTime);//定义起始日期
					    	Date d2 = sdf.parse(planEndTime);//定义结束日期
					    	Calendar dd = Calendar.getInstance();//定义日期实例
					    	dd.setTime(d1);//设置日期起始时间
					    	while(dd.getTime().before(d2)){//判断是否到结束日期
					    		dd.add(Calendar.DAY_OF_MONTH, -1);//进行当前日期月份天数减1
						    	String str = sdf.format(dd.getTime());
						    	dayList.add(str);
						    	dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份天数加1
						    	String str2 = sdf.format(dd.getTime());
						    	dayList.add(str2);
						    	dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
					    	}
					    	if("01".equals(planStartTime.substring(8, 10))){//判断是否为月初，去除数组中不可使用对象
					    		dayList.remove(0);
							}
					    	dayList.add(sdf.format(d2.getTime()));//截止日期加入数组中
					    	for (int i = 0; i < dayList.size(); i++) {
								int next = i+1;
								Date startDay = sdf.parse(dayList.get(i));
					    		Date endDay = sdf.parse(dayList.get(next));
					    		days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
					    		decimal4 = new BigDecimal(Integer.toString(days));
					    		Double planServiceAmount = dayPlanServiceAmount.multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					    		//插入数据
								RepaymentBean bean = new RepaymentBean();
								bean.setFinancingMatchingId(req.getFinancingMatchingId());
								bean.setLoanReceiptId(result);
								bean.setPlanStartTime(dayList.get(i));
								bean.setPlanEndTime(dayList.get(next));
								bean.setPlanPrincipalAmount(0);
								bean.setPlanInterestAmount(0);
								bean.setPlanServiceAmount(planServiceAmount);
								bean.setInterestRate(decimal2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
								bean.setServiceRate(decimal3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
								bean.setActualPrincipalAmount(0);
								bean.setActualInterestAmount(0);
								bean.setActualServiceAmount(0);
								bean.setPayStatus(Constants.DATA_STATUS_NORMAL);
								bean.setOperatorId(req.getOperatorId());
								bean.setOperatorName(req.getOperatorName());
								bean.setRepaymentType("服务费");
								int result1 = repaymentDao.addRepayment(bean);
								if(result1!=0){
									resultTrue = true;
								}else{
									return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
								}
					    		i++;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
				//修改需求方状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废
				demandDao.modifyDemandStatus(dto.getDemandId(), 3);
				if(resultTrue){
					return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
				}else{
					return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(),"计划还款数据生成未成功", result);
				}
			}
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public List<LoanReceiptDto> loanReceiptList(LoanReceiptDataTablesReq req) {
		return loadReceiptDao.loanReceiptList(req);
	}

	@Override
	public int loanReceiptCount(LoanReceiptDataTablesReq req) {
		return loadReceiptDao.loanReceiptCount(req);
	}

	@Override
	public List<LoanReceiptDtob> loanReceiptList2(LoanReceiptDataTablesReq req) {
		return loadReceiptDao.loanReceiptList2(req);
	}

	@Override
	public int loanReceiptCount2(LoanReceiptDataTablesReq req) {
		return loadReceiptDao.loanReceiptCount2(req);
	}
}
