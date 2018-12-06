package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.GuaranteeBean;
import com.lqmrSys.bean.dto.FinancingMatchingContractDto;
import com.lqmrSys.bean.pagination.FinancingMatchingContractDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingContractReq;
import com.lqmrSys.dao.FinancingMatchingContractDao;
import com.lqmrSys.dao.GuaranteeDao;
import com.lqmrSys.service.ContractService;
@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private FinancingMatchingContractDao financingMatchingContractDao;
	@Autowired
	private GuaranteeDao guaranteeDao;

	@Override
	public Res<Integer> addFinancingMatchingContract(FinancingMatchingContractReq req) {
		int contractNum = financingMatchingContractDao.financingMatchingContractAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String contractCode = "33062101"+sdf.format(new Date()).substring(2, 4);
		if(contractNum<=9){
			contractCode = contractCode + "0000" + contractNum;
		}else if(contractNum<=99){
			contractCode = contractCode + "000" + contractNum;
		}else if(contractNum<=999){
			contractCode = contractCode + "00" + contractNum;
		}else if(contractNum<=9999){
			contractCode = contractCode + "0" + contractNum;
		}else if(contractNum<=99999){
			contractCode = contractCode + contractNum;
		}
		req.setContractCode(contractCode);
		int result = financingMatchingContractDao.addFinancingMatchingContract(req);
		if(result!=0){
			List<GuaranteeBean> guaranteeList = req.getGuaranteeList();
			if(guaranteeList!=null && guaranteeList.size()>0){
				for (GuaranteeBean guaranteeBean : guaranteeList) {
					guaranteeBean.setContractId(result);
					guaranteeDao.addGuarantee2(guaranteeBean);
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyFinancingMatchingContract(FinancingMatchingContractReq req) {
		int result = financingMatchingContractDao.modifyFinancingMatchingContract(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<FinancingMatchingContractDto> financingMatchingContractDetail(int contractId) {
		FinancingMatchingContractDto dto = financingMatchingContractDao.financingMatchingContractDetailById(contractId);
		if(dto!=null){
			return new Res<FinancingMatchingContractDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<FinancingMatchingContractDto>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), dto);
	}

	@Override
	public List<FinancingMatchingContractDto> financingMatchingContractList(FinancingMatchingContractDataTablesReq req) {
		return financingMatchingContractDao.financingMatchingContractList(req);
	}

	@Override
	public int financingMatchingContractCount(FinancingMatchingContractDataTablesReq req) {
		return financingMatchingContractDao.financingMatchingContractCount(req);
	}

}
