package com.lqmrSys.service;

import com.common.Res;
import com.lqmrSys.bean.req.RiskSignReq;

public interface RiskSignService {
	//新增风险标记
	public Res<Integer> addRiskSign(RiskSignReq req);
	//删除风险标记
	public Res<Integer> delRiskSign(int signId, int operatorId, String operatorName);
}
