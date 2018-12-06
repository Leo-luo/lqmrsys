package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.RepaymentBean;
import com.lqmrSys.bean.req.RepaymentReq;
import com.lqmrSys.dao.RepaymentDao;
import com.lqmrSys.service.RepaymentService;
@Service
public class RepaymentServiceImpl implements RepaymentService {
	@Autowired
	private RepaymentDao repaymentDao;

	@Override
	public Res<Integer> modifyRepayment(RepaymentReq req) {
		int result = repaymentDao.modifyRepayment(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public List<RepaymentBean> repaymentList(int loanReceiptId, String repaymentType) {
		return repaymentDao.repaymentList(0, loanReceiptId, repaymentType);
	}
}
