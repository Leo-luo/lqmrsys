package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.AttachmentDto;
import com.lqmrSys.bean.pagination.AttachmentDataTablesReq;
import com.lqmrSys.bean.req.AttachmentReq;

public interface AttachmentService {
	//新增附件
	public Res<Integer> addAttachment(AttachmentReq req);
	//修改附件
	public Res<Integer> modifyAttachment(AttachmentReq req);
	//逻辑删除附件
	public Res<Integer> delAttachment(int attachmentId, int operatorId, String operatorName);
	//附件信息
	public Res<AttachmentDto> attachmentDetailById(int attachmentId);
	//附件列表
	public List<AttachmentDto> attachmentList(AttachmentDataTablesReq req);
	//附件列表长度
	public int attachmentCount(AttachmentDataTablesReq req);
}
