package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.RefuseDto;
import com.lqmrSys.bean.pagination.RefuseDataTablesReq;
import com.lqmrSys.bean.req.RefuseReq;
import com.lqmrSys.dao.RefuseDao;
import com.lqmrSys.service.RefuseService;
@Service
public class RefuseServiceImpl implements RefuseService {
	
	@Autowired
	private RefuseDao refuseDao;

	@Override
	public Res<Integer> addRefuse(RefuseReq req) {
		int result = refuseDao.addRefuse(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyRefuse(RefuseReq req) {
		int result = refuseDao.modifyRefuse(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delRefuse(int refuseId, int operatorId, String operatorName) {
		int result = refuseDao.delRefuse(refuseId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public List<RefuseDto> refuseList(RefuseDataTablesReq req) {
		return refuseDao.refuseList(req);
	}

	@Override
	public int refuseCount(RefuseDataTablesReq req) {
		return refuseDao.refuseCount(req);
	}

	@Override
	public Res<Integer> hasRefuse(int dataId, String dataFrom) {
		return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), refuseDao.hasRefuse(dataId, dataFrom));
	}

}
