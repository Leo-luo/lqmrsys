package com.lqmrSys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.dto.GuaranteeDto;
import com.lqmrSys.bean.pagination.GuaranteeDataTablesReq;
import com.lqmrSys.bean.req.GuaranteeReq;
import com.lqmrSys.bean.req.RelationshipReq;
import com.lqmrSys.dao.DemandDao;
import com.lqmrSys.dao.FinancingMatchingDao;
import com.lqmrSys.dao.GuaranteeDao;
import com.lqmrSys.dao.RelationshipDao;
import com.lqmrSys.service.GuaranteeService;
@Service
public class GuaranteeServiceImpl implements GuaranteeService {
	
	@Autowired
	private GuaranteeDao guaranteeDao;
	@Autowired
	private RelationshipDao relationshipDao;
	@Autowired
	private FinancingMatchingDao financingMatchingDao;
	@Autowired
	private DemandDao demandDao;

	@Override
	public Res<Integer> addGuarantee(GuaranteeReq req) {
		int guaranteeNum = guaranteeDao.guaranteeAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String guaranteeCode = "z10"+sdf.format(new Date());
		if(guaranteeNum<=9){
			guaranteeCode = guaranteeCode + "0000" + guaranteeNum;
		}else if(guaranteeNum<=99){
			guaranteeCode = guaranteeCode + "000" + guaranteeNum;
		}else if(guaranteeNum<=999){
			guaranteeCode = guaranteeCode + "00" + guaranteeNum;
		}else if(guaranteeNum<=9999){
			guaranteeCode = guaranteeCode + "0" + guaranteeNum;
		}else if(guaranteeNum<=99999){
			guaranteeCode = guaranteeCode + guaranteeNum;
		}
		req.setGuaranteeCode(guaranteeCode);
		int result = guaranteeDao.addGuarantee(req);
		if(result!=0){
			//添加人物关系
			FinancingMatchingDto financingMatching = financingMatchingDao.financingMatchingDetailById(req.getFinancingMatchingId());
			if(financingMatching!=null){
				DemandDto demand = demandDao.demandDetailById(financingMatching.getDemandId());
				if(demand!=null){
					RelationshipReq relation = new RelationshipReq();
					relation.setDataFrom(demand.getDemanderType());
					relation.setDataId(demand.getDemanderId());
					relation.setDataRelationshipFrom(req.getGuaranteePeopleType());
					relation.setDataRelationshipId(req.getGuaranteePeopleId());
					relation.setRelationshipDescribe("担保");
					RelationshipReq relation2 = new RelationshipReq();
					relation2.setDataFrom(req.getGuaranteePeopleType());
					relation2.setDataId(req.getGuaranteePeopleId());
					relation2.setDataRelationshipFrom(demand.getDemanderType());
					relation2.setDataRelationshipId(demand.getDemanderId());
					relation2.setRelationshipDescribe("被担保");
					//查重
					int hasRelation = relationshipDao.hasRelationship(relation);
					if(hasRelation==0){
						relationshipDao.addRelationship(relation);
					}
					int hasRelation2 = relationshipDao.hasRelationship(relation2);
					if(hasRelation2==0){
						relationshipDao.addRelationship(relation2);
					}
				}
			}
			
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> modifyGuarantee(GuaranteeReq req) {
		int result = guaranteeDao.modifyGuarantee(req);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delGuarantee(int guaranteeId, int operatorId, String operatorName) {
		int result = guaranteeDao.delGuarantee(guaranteeId, operatorId, operatorName);
		if(result!=0){
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<GuaranteeDto> guaranteeDetailById(int guaranteeId) {
		GuaranteeDto dto = guaranteeDao.guaranteeDetailById(guaranteeId);
		if(dto!=null){
			return new Res<GuaranteeDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<GuaranteeDto>(ResultCodeEnum.DATA_IS_EXISTED.getCode(), ResultCodeEnum.DATA_IS_EXISTED.getMessage(), dto);
	}

	@Override
	public List<GuaranteeDto> guaranteeList(GuaranteeDataTablesReq req) {
		return guaranteeDao.guaranteeList(req);
	}

	@Override
	public int guaranteeCount(GuaranteeDataTablesReq req) {
		return guaranteeDao.guaranteeCount(req);
	}

}
