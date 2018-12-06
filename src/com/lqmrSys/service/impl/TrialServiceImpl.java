package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.dto.LoanReceiptDtob;
import com.lqmrSys.bean.dto.TrialDto;
import com.lqmrSys.bean.pagination.TrialDataTablesReq;
import com.lqmrSys.bean.req.TrialReq;
import com.lqmrSys.dao.DemandDao;
import com.lqmrSys.dao.LoanReceiptDao;
import com.lqmrSys.dao.TrialDao;
import com.lqmrSys.service.TrialService;
@Service
public class TrialServiceImpl implements TrialService {
	@Autowired
	private TrialDao trialDao;
	@Autowired
	private DemandDao demandDao;
	@Autowired
	private LoanReceiptDao loadReceiptDao;

	@Override
	public Res<Integer> addTrialList(List<TrialReq> list) {
		TrialReq req = list.get(0);
		trialDao.delTrial(req.getTrialType(), req.getTrialDataId(), req.getOperatorId(), req.getOperatorName());
		boolean result = trialDao.addTrialList(list);
		if(result){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), list.size());
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), list.size());
	}

	@Override
	public Res<Integer> modifyTrialStatus(TrialReq req) {
		if(req.getOperatorId()!=req.getUserId()){
			return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), "非该节点审核/审批人员，请勿操作", 0);
		}
		TrialDto trial = trialDao.trialDetail(req.getTrialId());
		if(trial.getNode()!=trial.getTrialNode()){
			return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), "未到该节点审核/审批流程", 0);
		}
		int result = trialDao.modifyTrialStatus(req);
		if(result!=0){
			int trialStatus = req.getTrialStatus();
			int node = req.getNode();
			if(trialStatus==1){//审核-审批通过
				node = node+1;
			}else if(trialStatus==3){//审核-审批不通过
				if(node!=1){
					node = node-1;
				}
			}
			int result2 = trialDao.modifyNode(req.getTrialType(), req.getTrialDataId(), req.getOperatorId(), req.getOperatorName(), node);
			if(result2!=0){
				return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
			}
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public List<TrialDto> trialList(TrialDataTablesReq req) {
		List<TrialDto> trialList = trialDao.trialList(req);
		if(trialList!=null && trialList.size()>0){
			for (TrialDto trialDto : trialList) {
				if("sys_demand".equals(trialDto.getTrialType())){
					DemandDto dto = demandDao.demandDetailById(trialDto.getTrialDataId());
					if(dto!=null){
						trialDto.setDemandDetail(dto);
					}
				}else if("sys_loan_receipt".equals(trialDto.getTrialType())){
					LoanReceiptDtob dto = loadReceiptDao.loanReceiptDetail(trialDto.getTrialDataId());
					if(dto!=null){
						trialDto.setLoanReceiptDetail(dto);
					}
				}
			}
		}
		return trialList;
	}
}
