package com.lqmrSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.BankCardDto;
import com.lqmrSys.bean.pagination.BankCardDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;
import com.lqmrSys.dao.BankCardDao;
import com.lqmrSys.service.BankCardService;
@Service
public class BankCardServiceImpl implements BankCardService {
	
	@Autowired
	private BankCardDao bankCardDao;

	@Override
	public Res<Integer> addBankCard(BankCardReq req) {
		int result = bankCardDao.addBankCard(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyBankCard(BankCardReq req) {
		int result = bankCardDao.modifyBankCard(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delBankCard(int cardId) {
		int result = bankCardDao.delBankCard(cardId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<BankCardDto> bankCardDetailById(int cardId) {
		BankCardDto dto = bankCardDao.bankCardDetailById(cardId);
		if(dto!=null){
			return new Res<BankCardDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<BankCardDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<BankCardDto> bankCardList(BankCardDataTablesReq req) {
		if(req.getRelationId()!=0){
			return bankCardDao.bankCardList(req);
		}
		return new ArrayList<BankCardDto>();
	}

	@Override
	public int bankCardCount(BankCardDataTablesReq req) {
		if(req.getRelationId()!=0){
			return bankCardDao.bankCardCount(req);
		}
		return 0;
	}

}
