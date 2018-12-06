package com.lqmrSys.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.bean.GuaranteeBean;
import com.lqmrSys.bean.SupplierMatchingBean;
import com.lqmrSys.bean.dto.FinancingMatchingAllDetailDto;
import com.lqmrSys.bean.dto.FinancingMatchingContractDto;
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.dto.GuaranteeDto;
import com.lqmrSys.bean.dto.SupplierDto;
import com.lqmrSys.bean.pagination.FinancingMatchingDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingReq;
import com.lqmrSys.dao.DemandDao;
import com.lqmrSys.dao.FinancingMatchingContractDao;
import com.lqmrSys.dao.FinancingMatchingDao;
import com.lqmrSys.dao.GuaranteeDao;
import com.lqmrSys.dao.SupplierDao;
import com.lqmrSys.dao.SupplierMachingDao;
import com.lqmrSys.service.FinancingMatchingService;
import com.utils.BeanUtil;
@Service
public class FinancingMatchingServiceImpl implements FinancingMatchingService {
	@Autowired
	private FinancingMatchingDao financingMatchingDao;
	@Autowired
	private SupplierMachingDao supplierMachingDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private DemandDao demandDao;
	@Autowired
	private FinancingMatchingContractDao financingMatchingContractDao;
	@Autowired
	private GuaranteeDao guaranteeDao;
	
	@Override
	public Res<Integer> addFinancingMatching(FinancingMatchingReq req) {
		int financingMatchingNum = financingMatchingDao.financingMatchingAllCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String matchingCode = "ln"+sdf.format(new Date());
		if(financingMatchingNum<=9){
			matchingCode = matchingCode + "0000" + financingMatchingNum;
		}else if(financingMatchingNum<=99){
			matchingCode = matchingCode + "000" + financingMatchingNum;
		}else if(financingMatchingNum<=999){
			matchingCode = matchingCode + "00" + financingMatchingNum;
		}else if(financingMatchingNum<=9999){
			matchingCode = matchingCode + "0" + financingMatchingNum;
		}else if(financingMatchingNum<=99999){
			matchingCode = matchingCode + financingMatchingNum;
		}
		req.setMatchingCode(matchingCode);
		int result = financingMatchingDao.addFinancingMatching(req);//新增融资配对信息
		if(result!=0){
			//获取配对关联信息列表，循环新增，计算供应方余额并修改
			List<SupplierMatchingBean> supplierList = req.getSupplierList();
			if(supplierList!=null && supplierList.size()>0){
				for (SupplierMatchingBean supplierMatchingBean : supplierList) {
					supplierMatchingBean.setFinancingMatchingId(result);
					supplierMatchingBean.setMatchingStartTime(req.getStartTime());
					supplierMatchingBean.setMatchingEndTime(req.getEndTime());
					int result2 = supplierMachingDao.addSupplierMaching(supplierMatchingBean);
					if(result2!=0){
						SupplierDto supplierDto = supplierDao.supplierDetailById(supplierMatchingBean.getSupplierId());
						BigDecimal b1 = new BigDecimal(Double.toString(supplierDto.getSupplierBalance()));
						BigDecimal b2 = new BigDecimal(Double.toString(supplierMatchingBean.getMatchingAmount()));
						Double supplierBalance = (b1.subtract(b2)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						supplierDao.modifySupplierBalance(supplierDto.getSupplierId(), supplierBalance);
					}
				}
			}
			//修改需求方状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废
			demandDao.modifyDemandStatus(req.getDemandId(), 2);
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}
	
	@Override
	public Res<Integer> modifyFinancingMatching(FinancingMatchingReq req) {
		int result = financingMatchingDao.modifyFinancingMatching(req);//修改融资配对信息
		if(result!=0){
			//获取配对关联信息列表
			List<SupplierMatchingBean> supplierList = req.getSupplierList();
			if(supplierList!=null && supplierList.size()>0){
				//获取旧匹配关联信息,循环删除并计算供应方余额
				List<SupplierMatchingBean> supplierMachingList = supplierMachingDao.supplierMachingList(req.getFinancingMatchingId());
				if(supplierMachingList!=null && supplierMachingList.size()>0){
					for (SupplierMatchingBean supplierMatchingBean : supplierMachingList) {
						int result2 = supplierMachingDao.delSupplierMaching(supplierMatchingBean.getMatchingRelationId(), req.getOperatorId(), req.getOperatorName());
						if(result2!=0){
							SupplierDto supplierDto = supplierDao.supplierDetailById(supplierMatchingBean.getSupplierId());
							BigDecimal b1 = new BigDecimal(Double.toString(supplierDto.getSupplierBalance()));
							BigDecimal b2 = new BigDecimal(Double.toString(supplierMatchingBean.getMatchingAmount()));
							Double supplierBalance = (b1.add(b2)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
							supplierDao.modifySupplierBalance(supplierDto.getSupplierId(), supplierBalance);
						}
					}
				}
				//循环新增并计算供应方余额
				for (SupplierMatchingBean supplierMatchingBean : supplierList) {
					supplierMatchingBean.setFinancingMatchingId(result);
					supplierMatchingBean.setMatchingStartTime(req.getStartTime());
					supplierMatchingBean.setMatchingEndTime(req.getEndTime());
					int result2 = supplierMachingDao.addSupplierMaching(supplierMatchingBean);
					if(result2!=0){
						SupplierDto supplierDto = supplierDao.supplierDetailById(supplierMatchingBean.getSupplierId());
						BigDecimal b1 = new BigDecimal(Double.toString(supplierDto.getSupplierBalance()));
						BigDecimal b2 = new BigDecimal(Double.toString(supplierMatchingBean.getMatchingAmount()));
						Double supplierBalance = (b1.subtract(b2)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						supplierDao.modifySupplierBalance(supplierDto.getSupplierId(), supplierBalance);
					}
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public Res<Integer> delFinancingMatching(int financingMatchingId, int operatorId, String operatorName) {
		int result = financingMatchingDao.delFinancingMatching(financingMatchingId, operatorId, operatorName);
		if(result!=0){
			//修改需求方状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废
			FinancingMatchingDto financingMatchingDto = financingMatchingDao.financingMatchingDetailById(financingMatchingId);
			demandDao.modifyDemandStatus(financingMatchingDto.getDemandId(), 1);
			//获取旧匹配关联信息,循环删除并计算供应方余额
			List<SupplierMatchingBean> supplierMachingList = supplierMachingDao.supplierMachingList(financingMatchingId);
			if(supplierMachingList!=null && supplierMachingList.size()>0){
				for (SupplierMatchingBean supplierMatchingBean : supplierMachingList) {
					int result2 = supplierMachingDao.delSupplierMaching(supplierMatchingBean.getMatchingRelationId(), operatorId, operatorName);
					if(result2!=0){
						SupplierDto supplierDto = supplierDao.supplierDetailById(supplierMatchingBean.getSupplierId());
						BigDecimal b1 = new BigDecimal(Double.toString(supplierDto.getSupplierBalance()));
						BigDecimal b2 = new BigDecimal(Double.toString(supplierMatchingBean.getMatchingAmount()));
						Double supplierBalance = (b1.add(b2)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						supplierDao.modifySupplierBalance(supplierDto.getSupplierId(), supplierBalance);
					}
				}
			}
			return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
		}
		return new Res<Integer>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), result);
	}

	@Override
	public List<FinancingMatchingDto> financingMatchingList(FinancingMatchingDataTablesReq req) {
		return financingMatchingDao.financingMatchingList(req);
	}

	@Override
	public int financingMatchingCount(FinancingMatchingDataTablesReq req) {
		return financingMatchingDao.financingMatchingCount(req);
	}

	@Override
	public Res<FinancingMatchingDto> financingMatchingDetail(int financingMatchingId) {
		FinancingMatchingDto dto = financingMatchingDao.financingMatchingDetailById(financingMatchingId);
		if(dto!=null){
			List<SupplierMatchingBean> supplierMachingList = supplierMachingDao.supplierMachingList(financingMatchingId);
			if(supplierMachingList!=null && supplierMachingList.size()>0){
				dto.setSupplierList(supplierMachingList);
			}
			return new Res<FinancingMatchingDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<FinancingMatchingDto>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), dto);
	}

	@Override
	public Res<FinancingMatchingAllDetailDto> financingMatchingDetailAll(String matchingCode) {
		FinancingMatchingDto bean = financingMatchingDao.financingMatchingDetailByCode(matchingCode);
		if(bean!=null){
			FinancingMatchingAllDetailDto dto = BeanUtil.changeEntity(FinancingMatchingAllDetailDto.class, bean);
			List<SupplierMatchingBean> supplierMachingList = supplierMachingDao.supplierMachingList(dto.getFinancingMatchingId());
			if(supplierMachingList!=null && supplierMachingList.size()>0){
				dto.setSupplierList(supplierMachingList);
			}
			List<FinancingMatchingContractDto> contractList = financingMatchingContractDao.financingMatchingContractListByFinancingMatchingId(dto.getFinancingMatchingId());
			if(contractList!=null && contractList.size()>0){
				for (FinancingMatchingContractDto financingMatchingContractDto : contractList) {
					List<GuaranteeDto> guaranteeDtoList = guaranteeDao.guaranteeListByContractId(financingMatchingContractDto.getContractId());
					if(guaranteeDtoList!=null && guaranteeDtoList.size()>0){
						List<GuaranteeBean> guaranteeList = new ArrayList<GuaranteeBean>();
						for (GuaranteeDto guaranteeDto : guaranteeDtoList) {
							GuaranteeBean guarantee = BeanUtil.changeEntity(GuaranteeBean.class, guaranteeDto);
							guaranteeList.add(guarantee);
						}
						financingMatchingContractDto.setGuaranteeList(guaranteeList);
					}
				}
				dto.setContractList(contractList);
			}
			return new Res<FinancingMatchingAllDetailDto>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), dto);
		}
		return new Res<FinancingMatchingAllDetailDto>(ResultCodeEnum.SYSTEM_ERROR.getCode(), ResultCodeEnum.SYSTEM_ERROR.getMessage(), null);
	}

}
