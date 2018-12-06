package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqmrSys.bean.OperateBean;
import com.lqmrSys.bean.dto.OperateDto;
import com.lqmrSys.bean.pagination.OperateDataTablesReq;
import com.lqmrSys.dao.OperateDao;
import com.lqmrSys.service.OperateService;
@Service
public class OperateServiceImpl implements OperateService {
	@Autowired
	private OperateDao operateDao;
	@Override
	public int addOperate(OperateBean req) {
		return operateDao.addOperate(req);
	}

	@Override
	public List<OperateDto> operateList(OperateDataTablesReq req) {
		return operateDao.operateList(req);
	}

	@Override
	public int operateCount(OperateDataTablesReq req) {
		// TODO Auto-generated method stub
		return operateDao.operateCount(req);
	}

}
