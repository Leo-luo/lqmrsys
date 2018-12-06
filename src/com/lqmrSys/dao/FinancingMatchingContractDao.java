package com.lqmrSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.common.Constants;
import com.lqmrSys.bean.dto.FinancingMatchingContractDto;
import com.lqmrSys.bean.pagination.FinancingMatchingContractDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingContractReq;

@Component
public class FinancingMatchingContractDao {
	private static Logger logger = Logger.getLogger(FinancingMatchingContractDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增融资配对信息合同
	public int addFinancingMatchingContract(final FinancingMatchingContractReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_financing_matching_contract (financing_matching_id,contract_code,contract_classify,contract_type,start_time,end_time,contract_rate,service_rate,overdue_rate,loan_purpose,contract_amount,repayment_mode,account_bank,account_num,dispute_mode,sign_contract_time,sign_contract_place,sign_contract_people,remark,data_status,create_time,update_time,operator_id,operator_name,custom_contract_code");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("FinancingMatchingContractDao.addFinancingMatchingContract sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getFinancingMatchingId());
			         ps.setString(2, req.getContractCode());
			         ps.setString(3, req.getContractClassify());
			         ps.setString(4, req.getContractType());
			         ps.setString(5, req.getStartTime());
			         ps.setString(6, req.getEndTime());
			         ps.setDouble(7, req.getContractRate());
			         ps.setDouble(8, req.getServiceRate());
			         ps.setDouble(9, req.getOverdueRate());
			         ps.setString(10, req.getLoanPurpose());
			         ps.setDouble(11, req.getContractAmount());
			         ps.setString(12, req.getRepaymentMode());
			         ps.setString(13, req.getAccountBank());
			         ps.setString(14, req.getAccountNum());
			         ps.setString(15, req.getDisputeMode());
			         ps.setString(16, req.getSignContractTime());
			         ps.setString(17, req.getSignContractPlace());
			         ps.setString(18, req.getSignContractPeople());
			         ps.setString(19, req.getRemark());
			         ps.setInt(20, Constants.DATA_STATUS_NORMAL);
			         ps.setString(21, date);
			         ps.setString(22, date);
			         ps.setInt(23, req.getOperatorId());
			         ps.setString(24, req.getOperatorName());
			         ps.setString(25, req.getCustomContractCode());
			         return ps;
			     }
				}, keyHolder);
			int pkid = keyHolder.getKey().intValue();
			return pkid;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改融资配对信息合同
	public int modifyFinancingMatchingContract(FinancingMatchingContractReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_financing_matching_contract set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getContractClassify()!=null && !"".equals(req.getContractClassify())){
				sql.append(" ,contract_classify='"+req.getContractClassify()+"' ");
			}
			if(req.getContractType()!=null && !"".equals(req.getContractType())){
				sql.append(" ,contract_type='"+req.getContractType()+"' ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime())){
				sql.append(" ,start_time='"+req.getStartTime()+"' ");
			}
			if(req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" ,end_time='"+req.getEndTime()+"' ");
			}
			if(req.getContractRate()!=-1){
				sql.append(" ,contract_rate="+req.getContractRate()+" ");
			}
			if(req.getServiceRate()!=-1){
				sql.append(" ,service_rate="+req.getServiceRate()+" ");
			}
			if(req.getOverdueRate()!=-1){
				sql.append(" ,overdue_rate="+req.getOverdueRate()+" ");
			}
			if(req.getLoanPurpose()!=null && !"".equals(req.getLoanPurpose())){
				sql.append(" ,loan_purpose='"+req.getLoanPurpose()+"' ");
			}
			if(req.getContractAmount()!=-1){
				sql.append(" ,contract_amount="+req.getContractAmount()+" ");
			}
			if(req.getRepaymentMode()!=null && !"".equals(req.getRepaymentMode())){
				sql.append(" ,repayment_mode='"+req.getRepaymentMode()+"' ");
			}
			if(req.getAccountBank()!=null && !"".equals(req.getAccountBank())){
				sql.append(" ,account_bank='"+req.getAccountBank()+"' ");
			}
			if(req.getAccountNum()!=null && !"".equals(req.getAccountNum())){
				sql.append(" ,account_num='"+req.getAccountNum()+"' ");
			}
			if(req.getSignContractTime()!=null && !"".equals(req.getSignContractTime())){
				sql.append(" ,sign_contract_time='"+req.getSignContractTime()+"' ");
			}
			if(req.getSignContractPlace()!=null && !"".equals(req.getSignContractPlace())){
				sql.append(" ,sign_contract_place='"+req.getSignContractPlace()+"' ");
			}
			if(req.getSignContractPeople()!=null && !"".equals(req.getSignContractPeople())){
				sql.append(" ,sign_contract_people='"+req.getSignContractPeople()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getCustomContractCode()!=null && !"".equals(req.getCustomContractCode())){
				sql.append(" ,custom_contract_code='"+req.getCustomContractCode()+"' ");
			}
			sql.append(" where contract_id="+req.getContractId());
			logger.info("FinancingMatchingContractDao.modifyFinancingMatchingContract sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除融资配对信息合同
	public int delFinancingMatchingContract(int contractId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_financing_matching_contract set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+", operator_name='"+operatorName+"' ");
			sql.append(" where contract_id="+contractId);
			logger.info("FinancingMatchingContractDao.delFinancingMatchingContract sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息合同详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FinancingMatchingContractDto financingMatchingContractDetailById(int contractId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_financing_matching_contract a ");
			sql.append(" WHERE a.contract_id="+contractId);
			logger.info("FinancingMatchingContractDao.financingMatchingContractDetailById sql = " + sql.toString());
			return (FinancingMatchingContractDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingContractDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//融资配对信息合同列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<FinancingMatchingContractDto> financingMatchingContractList(FinancingMatchingContractDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_financing_matching_contract a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getFinancingMatchingId()!=0){
				sql.append(" and a.financing_matching_id="+req.getFinancingMatchingId()+" ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" ,and d.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getContractType()!=null && !"".equals(req.getContractType())){
				sql.append(" ,and d.contract_type like '%"+req.getContractType()+"%' ");
			}
			if(req.getCustomContractCode()!=null && !"".equals(req.getCustomContractCode())){
				sql.append(" ,and d.custom_contract_code like '%"+req.getCustomContractCode()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("FinancingMatchingContractDao.financingMatchingContractList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingContractDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//融资配对信息合同列表长度
	public int financingMatchingContractCount(FinancingMatchingContractDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.contract_id) ");
			sql.append(" FROM sys_financing_matching_contract a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getFinancingMatchingId()!=0){
				sql.append(" and a.financing_matching_id="+req.getFinancingMatchingId()+" ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" ,and d.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getContractType()!=null && !"".equals(req.getContractType())){
				sql.append(" ,and d.contract_type like '%"+req.getContractType()+"%' ");
			}
			if(req.getCustomContractCode()!=null && !"".equals(req.getCustomContractCode())){
				sql.append(" ,and d.custom_contract_code like '%"+req.getCustomContractCode()+"%' ");
			}
			logger.info("FinancingMatchingContractDao.financingMatchingContractCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息合同列表长度
	public int financingMatchingContractAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.contract_id) ");
			sql.append(" FROM sys_financing_matching_contract a ");
			logger.info("FinancingMatchingContractDao.financingMatchingContractAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息合同列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<FinancingMatchingContractDto> financingMatchingContractListByFinancingMatchingId(int financingMatchingId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_financing_matching_contract a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.financing_matching_id="+financingMatchingId+" ");
			sql.append(" ORDER BY a.create_time DESC ");
			logger.info("FinancingMatchingContractDao.financingMatchingContractListByFinancingMatchingId sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingContractDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
