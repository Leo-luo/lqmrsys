package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.BankCardDto;
import com.lqmrSys.bean.pagination.BankCardDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;

public interface BankCardService {
	//新增银行卡
	public Res<Integer> addBankCard(BankCardReq req);
	//修改银行卡
	public Res<Integer> modifyBankCard(BankCardReq req);
	//逻辑删除银行卡
	public Res<Integer> delBankCard(int cardId);
	//银行卡信息
	public Res<BankCardDto> bankCardDetailById(int cardId);
	//银行卡列表
	public List<BankCardDto> bankCardList(BankCardDataTablesReq req);
	//银行卡列表长度
	public int bankCardCount(BankCardDataTablesReq req);
}
