package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.SupplierAdvanceDto;
import com.lqmrSys.bean.pagination.SupplierAdvanceDataTablesReq;
import com.lqmrSys.bean.req.SupplierAdvanceReq;
import com.lqmrSys.dao.SupplierAdvanceDao;
import com.lqmrSys.service.SupplierAdvanceService;
@Service
public class SupplierAdvanceServiceImpl implements SupplierAdvanceService {
	
	@Autowired
	private SupplierAdvanceDao supplierAdvanceDao;

	@Override
	public Res<Integer> addSupplierAdvance(SupplierAdvanceReq req) {
		int advanceNum = supplierAdvanceDao.supplierAdvanceAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String advanceCode = "3306219"+sdf.format(new Date());
		if(advanceNum<=9){
			advanceCode = advanceCode + "000" + advanceNum;
		}else if(advanceNum<=99){
			advanceCode = advanceCode + "00" + advanceNum;
		}else if(advanceNum<=999){
			advanceCode = advanceCode + "0" + advanceNum;
		}else if(advanceNum<=9999){
			advanceCode = advanceCode + advanceNum;
		}
		req.setAdvanceCode(advanceCode);
		int result = supplierAdvanceDao.addSupplierAdvance(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifySupplierAdvance(SupplierAdvanceReq req) {
		int result = supplierAdvanceDao.modifySupplierAdvance(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delSupplierAdvance(int advanceId, int operatorId, String operatorName) {
		int result = supplierAdvanceDao.delSupplierAdvance(advanceId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<SupplierAdvanceDto> supplierAdvanceDetailById(int advanceId) {
		SupplierAdvanceDto dto = supplierAdvanceDao.supplierAdvanceDetailById(advanceId);
		if(dto!=null){
			return new Res<SupplierAdvanceDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<SupplierAdvanceDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<SupplierAdvanceDto> supplierAdvanceList(SupplierAdvanceDataTablesReq req) {
		return supplierAdvanceDao.supplierAdvanceList(req);
	}

	@Override
	public int supplierAdvanceCount(SupplierAdvanceDataTablesReq req) {
		return supplierAdvanceDao.supplierAdvanceCount(req);
	}

}
