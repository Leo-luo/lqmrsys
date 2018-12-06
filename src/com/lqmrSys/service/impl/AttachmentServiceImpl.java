package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.AttachmentDto;
import com.lqmrSys.bean.pagination.AttachmentDataTablesReq;
import com.lqmrSys.bean.req.AttachmentReq;
import com.lqmrSys.dao.AttachmentDao;
import com.lqmrSys.service.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Res<Integer> addAttachment(AttachmentReq req) {
		int result = attachmentDao.addAttachment(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyAttachment(AttachmentReq req) {
		int result = attachmentDao.modifyAttachment(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delAttachment(int attachmentId, int operatorId, String operatorName) {
		int result = attachmentDao.delAttachment(attachmentId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<AttachmentDto> attachmentDetailById(int attachmentId) {
		AttachmentDto dto = attachmentDao.attachmentDetailById(attachmentId);
		if(dto!=null){
			return new Res<AttachmentDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<AttachmentDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<AttachmentDto> attachmentList(AttachmentDataTablesReq req) {
		return attachmentDao.attachmentList(req);
	}

	@Override
	public int attachmentCount(AttachmentDataTablesReq req) {
		return attachmentDao.attachmentCount(req);
	}

}
