package com.lqmrSys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.RelationshipDto;
import com.lqmrSys.bean.pagination.RelationshipDataTablesReq;
import com.lqmrSys.bean.req.RelationshipReq;
import com.lqmrSys.dao.RelationshipDao;
import com.lqmrSys.service.RelationshipService;
@Service
public class RelationshipServiceImpl implements RelationshipService {
	
	@Autowired
	private RelationshipDao relationshipDao;

	@Override
	public Res<Integer> addRelationship(RelationshipReq req) {
		int result = relationshipDao.addRelationship(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyRelationship(RelationshipReq req) {
		int result = relationshipDao.modifyRelationship(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delRelationship(int relationshipId) {
		int result = relationshipDao.delRelationship(relationshipId);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<RelationshipDto> relationshipDetailById(int relationshipId) {
		RelationshipDto dto = relationshipDao.relationshipDetailById(relationshipId);
		if(dto!=null){
			return new Res<RelationshipDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<RelationshipDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<RelationshipDto> relationshipList(RelationshipDataTablesReq req) {
		return relationshipDao.relationshipList(req);
	}

	@Override
	public int relationshipCount(RelationshipDataTablesReq req) {
		return relationshipDao.relationshipCount(req);
	}

}
