package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constants;
import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.CustomerDto;
import com.lqmrSys.bean.pagination.CustomerDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;
import com.lqmrSys.bean.req.CustomerReq;
import com.lqmrSys.dao.BankCardDao;
import com.lqmrSys.dao.CustomerDao;
import com.lqmrSys.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BankCardDao bankCardDao;

	@Override
	public Res<Integer> addCustomer(CustomerReq req) {
		int customerNum = customerDao.customerAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String customerCode = "z"+sdf.format(new Date());
		if(customerNum<=9){
			customerCode = customerCode + "0000" + customerNum;
		}else if(customerNum<=99){
			customerCode = customerCode + "000" + customerNum;
		}else if(customerNum<=999){
			customerCode = customerCode + "00" + customerNum;
		}else if(customerNum<=9999){
			customerCode = customerCode + "0" + customerNum;
		}else if(customerNum<=99999){
			customerCode = customerCode + customerNum;
		}
		req.setCustomerCode(customerCode);
		//规避时间格式字段空值无法存入的问题
		if("".equals(req.getCredentialsEffectiveTime())){
			req.setCredentialsEffectiveTime(null);
		}
		if("".equals(req.getBirthday())){
			req.setBirthday(null);
		}
		int result = customerDao.addCustomer(req);
		if(result!=0){
			List<BankCardReq> cardList = req.getCardList();
			if(cardList!=null && cardList.size()>0){
				for (BankCardReq bankCardReq : cardList) {
					bankCardReq.setRelationId(result);
					bankCardReq.setCardType(Constants.SYS_CUSTOMER);
					bankCardDao.addBankCard(bankCardReq);
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyCustomer(CustomerReq req) {
		int result = customerDao.modifyCustomer(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delCustomer(int customerId, int operatorId, String operatorName) {
		int result = customerDao.delCustomer(customerId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<CustomerDto> customerDetailById(int customerId) {
		CustomerDto dto = customerDao.customerDetailById(customerId);
		if(dto!=null){
			return new Res<CustomerDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<CustomerDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<CustomerDto> customerList(CustomerDataTablesReq req) {
		return customerDao.customerList(req);
	}

	@Override
	public int customerCount(CustomerDataTablesReq req) {
		return customerDao.customerCount(req);
	}

}
