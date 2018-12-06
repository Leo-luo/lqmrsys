package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.CustomerDto;
import com.lqmrSys.bean.pagination.CustomerDataTablesReq;
import com.lqmrSys.bean.req.CustomerReq;

public interface CustomerService {
	//新增客户
	public Res<Integer> addCustomer(CustomerReq req);
	//修改客户
	public Res<Integer> modifyCustomer(CustomerReq req);
	//逻辑删除客户
	public Res<Integer> delCustomer(int customerId, int operatorId, String operatorName);
	//客户信息
	public Res<CustomerDto> customerDetailById(int customerId);
	//客户列表
	public List<CustomerDto> customerList(CustomerDataTablesReq req);
	//客户列表长度
	public int customerCount(CustomerDataTablesReq req);
}
