package com.lqmrSys.service;

import java.util.List;

import com.common.Res;
import com.lqmrSys.bean.dto.RelationshipDto;
import com.lqmrSys.bean.pagination.RelationshipDataTablesReq;
import com.lqmrSys.bean.req.RelationshipReq;

public interface RelationshipService {
	//新增关系
	public Res<Integer> addRelationship(RelationshipReq req);
	//修改关系
	public Res<Integer> modifyRelationship(RelationshipReq req);
	//逻辑删除关系
	public Res<Integer> delRelationship(int relationshipId);
	//关系信息
	public Res<RelationshipDto> relationshipDetailById(int relationshipId);
	//关系列表
	public List<RelationshipDto> relationshipList(RelationshipDataTablesReq req);
	//关系列表长度
	public int relationshipCount(RelationshipDataTablesReq req);
}
