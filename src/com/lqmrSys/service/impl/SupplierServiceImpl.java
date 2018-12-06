package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.SupplierDto;
import com.lqmrSys.bean.pagination.SupplierDataTablesReq;
import com.lqmrSys.bean.req.SupplierReq;
import com.lqmrSys.dao.SupplierDao;
import com.lqmrSys.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDao supplierDao;

	@Override
	public Res<Integer> addSupplier(SupplierReq req) {
		int supplierNum = supplierDao.supplierAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String supplierCode = "3306219"+sdf.format(new Date());
		if(supplierNum<=9){
			supplierCode = supplierCode + "0000" + supplierNum;
		}else if(supplierNum<=99){
			supplierCode = supplierCode + "000" + supplierNum;
		}else if(supplierNum<=999){
			supplierCode = supplierCode + "00" + supplierNum;
		}else if(supplierNum<=9999){
			supplierCode = supplierCode + "0" + supplierNum;
		}else if(supplierNum<=99999){
			supplierCode = supplierCode + supplierNum;
		}
		req.setSupplierCode(supplierCode);
		int result = supplierDao.addSupplier(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifySupplier(SupplierReq req) {
		int result = supplierDao.modifySupplier(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delSupplier(int supplierId, int operatorId, String operatorName) {
		int result = supplierDao.delSupplier(supplierId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<SupplierDto> supplierDetailById(int supplierId) {
		SupplierDto dto = supplierDao.supplierDetailById(supplierId);
		if(dto!=null){
			return new Res<SupplierDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<SupplierDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<SupplierDto> supplierList(SupplierDataTablesReq req) {
		return supplierDao.supplierList(req);
	}

	@Override
	public int supplierCount(SupplierDataTablesReq req) {
		return supplierDao.supplierCount(req);
	}

}
