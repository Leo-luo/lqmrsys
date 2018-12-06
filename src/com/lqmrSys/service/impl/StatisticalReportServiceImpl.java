package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqmrSys.bean.SupplyExpireRemindBean;
import com.lqmrSys.bean.dto.DictDto;
import com.lqmrSys.bean.dto.LoanExpireRemindDto;
import com.lqmrSys.dao.DictDao;
import com.lqmrSys.dao.StatisticalReportDao;
import com.lqmrSys.service.StatisticalReportService;
@Service
public class StatisticalReportServiceImpl implements StatisticalReportService {
	@Autowired
	private StatisticalReportDao statisticalReportDao;
	@Autowired
	private DictDao dictDao;
	
	@Override
	public List<SupplyExpireRemindBean> supplyExpireRemindList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DictDto dict = dictDao.dictData("expire-remind-days");
		int remindDays = Integer.valueOf(dict.getContent());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, remindDays);
		String endTime = sdf.format(calendar.getTime());
		String startTime = sdf.format(new Date());
		return statisticalReportDao.supplyExpireRemindList(startTime, endTime);
	}
	
	@Override
	public List<LoanExpireRemindDto> loanExpireRemindList(String listType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DictDto dict = dictDao.dictData("expire-remind-days");
		int remindDays = Integer.valueOf(dict.getContent());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, remindDays);
		String endTime = sdf.format(calendar.getTime());
		String startTime = sdf.format(new Date());
		
		List<LoanExpireRemindDto> dtoList = new ArrayList<LoanExpireRemindDto>();
		if("到期".equals(listType)){
			dtoList = statisticalReportDao.loanExpireRemindList(startTime, endTime);
		}else if("逾期".equals(listType)){
			dtoList = statisticalReportDao.loanExpireRemindList("", startTime);
		}
		return dtoList;
	}
}
