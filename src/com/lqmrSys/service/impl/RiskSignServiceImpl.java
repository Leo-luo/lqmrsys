package com.lqmrSys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.req.RiskSignReq;
import com.lqmrSys.dao.RiskSignDao;
import com.lqmrSys.service.RiskSignService;
@Service
public class RiskSignServiceImpl implements RiskSignService {
	@Autowired
	private RiskSignDao riskSignDao;

	@Override
	public Res<Integer> addRiskSign(RiskSignReq req) {
		int result = riskSignDao.addRiskSign(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delRiskSign(int signId, int operatorId,String operatorName) {
		int result = riskSignDao.delRiskSign(signId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}
}
