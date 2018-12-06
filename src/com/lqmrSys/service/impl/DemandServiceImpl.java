package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.pagination.DemandDataTablesReq;
import com.lqmrSys.bean.req.DemandReq;
import com.lqmrSys.dao.DemandDao;
import com.lqmrSys.service.DemandService;
@Service
public class DemandServiceImpl implements DemandService {
	
	@Autowired
	private DemandDao demandDao;

	@Override
	public Res<Integer> addDemand(DemandReq req) {
		int demandNum = demandDao.demandAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String demandCode = "rz"+sdf.format(new Date());
		if(demandNum<=9){
			demandCode = demandCode + "000" + demandNum;
		}else if(demandNum<=99){
			demandCode = demandCode + "00" + demandNum;
		}else if(demandNum<=999){
			demandCode = demandCode + "0" + demandNum;
		}else if(demandNum<=9999){
			demandCode = demandCode + demandNum;
		}
		req.setDemandCode(demandCode);
		int result = demandDao.addDemand(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyDemand(DemandReq req) {
		int result = demandDao.modifyDemand(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delDemand(int demandId, int operatorId, String operatorName) {
		int result = demandDao.delDemand(demandId, operatorId, operatorName);
		//修改需求方状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废
		int result1 = demandDao.modifyDemandStatus(demandId, 5);
		if(result!=0 && result1!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<DemandDto> demandDetailById(int demandId) {
		DemandDto dto = demandDao.demandDetailById(demandId);
		if(dto!=null){
			return new Res<DemandDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<DemandDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<DemandDto> demandList(DemandDataTablesReq req) {
		return demandDao.demandList(req);
	}

	@Override
	public int demandCount(DemandDataTablesReq req) {
		return demandDao.demandCount(req);
	}

}
