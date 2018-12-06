package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constants;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.CompanyDto;
import com.lqmrSys.bean.pagination.CompanyDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;
import com.lqmrSys.bean.req.CompanyReq;
import com.lqmrSys.dao.BankCardDao;
import com.lqmrSys.dao.CompanyDao;
import com.lqmrSys.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private BankCardDao bankCardDao;

	@Override
	public Res<Integer> addCompany(CompanyReq req) {
		int companyNum = companyDao.companyAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String companyCode = "q"+sdf.format(new Date());
		if(companyNum<=9){
			companyCode = companyCode + "0000" + companyNum;
		}else if(companyNum<=99){
			companyCode = companyCode + "000" + companyNum;
		}else if(companyNum<=999){
			companyCode = companyCode + "00" + companyNum;
		}else if(companyNum<=9999){
			companyCode = companyCode + "0" + companyNum;
		}else if(companyNum<=99999){
			companyCode = companyCode + companyNum;
		}
		//规避时间格式字段空值无法存入的问题
		if("".equals(req.getRegisterTime())){
			req.setRegisterTime(null);
		}
		req.setCompanyCode(companyCode);
		int result = companyDao.addCompany(req);
		if(result!=0){
			List<BankCardReq> cardList = req.getCardList();
			if(cardList!=null && cardList.size()>0){
				for (BankCardReq bankCardReq : cardList) {
					bankCardReq.setRelationId(result);
					bankCardReq.setCardType(Constants.SYS_COMPANY);
					bankCardDao.addBankCard(bankCardReq);
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyCompany(CompanyReq req) {
		int result = companyDao.modifyCompany(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delCompany(int companyId, int operatorId, String operatorName) {
		int result = companyDao.delCompany(companyId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<CompanyDto> companyDetailById(int companyId) {
		CompanyDto dto = companyDao.companyDetailById(companyId);
		if(dto!=null){
			return new Res<CompanyDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<CompanyDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<CompanyDto> companyList(CompanyDataTablesReq req) {
		return companyDao.companyList(req);
	}

	@Override
	public int companyCount(CompanyDataTablesReq req) {
		return companyDao.companyCount(req);
	}

}
