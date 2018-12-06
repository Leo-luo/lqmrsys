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
import com.lqmrSys.bean.GuaranteeBean;
import com.lqmrSys.bean.dto.GuaranteeDto;
import com.lqmrSys.bean.pagination.GuaranteeDataTablesReq;
import com.lqmrSys.bean.req.GuaranteeReq;

@Component
public class GuaranteeDao {
	private static Logger logger = Logger.getLogger(GuaranteeDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增担保
	public int addGuarantee(final GuaranteeReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_guarantee (guarantee_people_id,guarantee_people_type,guarantee_mode,guarantee_code,guarantee_end_time,card_id,guarantee_amount,financing_matching_id,remark,data_status,create_time,update_time,operator_id,operator_name,contract_id,custom_guarantee_code,guarantee_thing_name,guarantee_thing_type,other_certificates_num,evaluation_agency,evaluate_value,mortgage_value,guarantee_thing_num,metering_unit,register_agency");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("GuaranteeDao.addGuarantee sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getGuaranteePeopleId());
			         ps.setString(2, req.getGuaranteePeopleType());
			         ps.setString(3, req.getGuaranteeMode());
			         ps.setString(4, req.getGuaranteeCode());
			         ps.setString(5, req.getGuaranteeEndTime());
			         ps.setInt(6, req.getCardId());
			         ps.setDouble(7, req.getGuaranteeAmount());
			         ps.setInt(8, req.getFinancingMatchingId());
			         ps.setString(9, req.getRemark());
			         ps.setInt(10, Constants.DATA_STATUS_NORMAL);
			         ps.setString(11, date);
			         ps.setString(12, date);
			         ps.setInt(13, req.getOperatorId());
			         ps.setString(14, req.getOperatorName());
			         ps.setInt(15, req.getContractId());
			         ps.setString(16, req.getCustomGuaranteeCode());
			         ps.setString(17, req.getGuaranteeThingName());
			         ps.setString(18, req.getGuaranteeThingType());
			         ps.setString(19, req.getOtherCertificatesNum());
			         ps.setString(20, req.getEvaluationAgency());
			         ps.setString(21, req.getEvaluateValue());
			         ps.setString(22, req.getMortgageValue());
			         ps.setString(23, req.getGuaranteeThingNum());
			         ps.setString(24, req.getMeteringUnit());
			         ps.setString(25, req.getRegisterAgency());
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
	
	//新增担保
	public int addGuarantee2(final GuaranteeBean req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_guarantee (guarantee_people_id,guarantee_people_type,guarantee_mode,guarantee_code,guarantee_end_time,card_id,guarantee_amount,financing_matching_id,remark,data_status,create_time,update_time,operator_id,operator_name,contract_id,custom_guarantee_code,guarantee_thing_name,guarantee_thing_type,other_certificates_num,evaluation_agency,evaluate_value,mortgage_value,guarantee_thing_num,metering_unit,register_agency");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("GuaranteeDao.addGuarantee sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getGuaranteePeopleId());
			         ps.setString(2, req.getGuaranteePeopleType());
			         ps.setString(3, req.getGuaranteeMode());
			         ps.setString(4, req.getGuaranteeCode());
			         ps.setString(5, req.getGuaranteeEndTime());
			         ps.setInt(6, req.getCardId());
			         ps.setDouble(7, req.getGuaranteeAmount());
			         ps.setInt(8, req.getFinancingMatchingId());
			         ps.setString(9, req.getRemark());
			         ps.setInt(10, Constants.DATA_STATUS_NORMAL);
			         ps.setString(11, date);
			         ps.setString(12, date);
			         ps.setInt(13, req.getOperatorId());
			         ps.setString(14, req.getOperatorName());
			         ps.setInt(15, req.getContractId());
			         ps.setString(16, req.getCustomGuaranteeCode());
			         ps.setString(17, req.getGuaranteeThingName());
			         ps.setString(18, req.getGuaranteeThingType());
			         ps.setString(19, req.getOtherCertificatesNum());
			         ps.setString(20, req.getEvaluationAgency());
			         ps.setString(21, req.getEvaluateValue());
			         ps.setString(22, req.getMortgageValue());
			         ps.setString(23, req.getGuaranteeThingNum());
			         ps.setString(24, req.getMeteringUnit());
			         ps.setString(25, req.getRegisterAgency());
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
	
	//修改担保
	public int modifyGuarantee(GuaranteeReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_guarantee set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getGuaranteePeopleId()!=0){
				sql.append(" ,guarantee_people_id='"+req.getGuaranteePeopleId()+"' ");
			}
			if(req.getGuaranteePeopleType()!=null && !"".equals(req.getGuaranteePeopleType())){
				sql.append(" ,guarantee_people_type='"+req.getGuaranteePeopleType()+"' ");
			}
			if(req.getGuaranteeMode()!=null && !"".equals(req.getGuaranteeMode())){
				sql.append(" ,guarantee_mode='"+req.getGuaranteeMode()+"' ");
			}
			if(req.getGuaranteeEndTime()!=null && !"".equals(req.getGuaranteeEndTime())){
				sql.append(" ,guarantee_end_time='"+req.getGuaranteeEndTime()+"' ");
			}
			if(req.getCardId()!=0){
				sql.append(" ,card_id="+req.getCardId()+" ");
			}
			if(req.getGuaranteeAmount()!=0){
				sql.append(" ,guarantee_amount="+req.getGuaranteeAmount()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getCustomGuaranteeCode()!=null && !"".equals(req.getCustomGuaranteeCode())){
				sql.append(" ,custom_guarantee_code='"+req.getCustomGuaranteeCode()+"' ");
			}
			if(req.getGuaranteeThingName()!=null && !"".equals(req.getGuaranteeThingName())){
				sql.append(" ,guarantee_thing_name='"+req.getGuaranteeThingName()+"' ");
			}
			if(req.getGuaranteeThingType()!=null && !"".equals(req.getGuaranteeThingType())){
				sql.append(" ,guarantee_thing_type='"+req.getGuaranteeThingType()+"' ");
			}
			if(req.getOtherCertificatesNum()!=null && !"".equals(req.getOtherCertificatesNum())){
				sql.append(" ,other_certificates_num='"+req.getOtherCertificatesNum()+"' ");
			}
			if(req.getEvaluationAgency()!=null && !"".equals(req.getEvaluationAgency())){
				sql.append(" ,evaluation_agency='"+req.getEvaluationAgency()+"' ");
			}
			if(req.getEvaluateValue()!=null && !"".equals(req.getEvaluateValue())){
				sql.append(" ,evaluate_value='"+req.getEvaluateValue()+"' ");
			}
			if(req.getMortgageValue()!=null && !"".equals(req.getMortgageValue())){
				sql.append(" ,mortgage_value='"+req.getMortgageValue()+"' ");
			}
			if(req.getGuaranteeThingNum()!=null && !"".equals(req.getGuaranteeThingNum())){
				sql.append(" ,guarantee_thing_num='"+req.getGuaranteeThingNum()+"' ");
			}
			if(req.getMeteringUnit()!=null && !"".equals(req.getMeteringUnit())){
				sql.append(" ,metering_unit='"+req.getMeteringUnit()+"' ");
			}
			if(req.getRegisterAgency()!=null && !"".equals(req.getRegisterAgency())){
				sql.append(" ,register_agency='"+req.getRegisterAgency()+"' ");
			}
			sql.append(" where guarantee_id="+req.getGuaranteeId());
			logger.info("GuaranteeDao.modifyGuarantee sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除担保
	public int delGuarantee(int guaranteeId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_guarantee set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+", operator_name='"+operatorName+"' ");
			sql.append(" where guarantee_id="+guaranteeId);
			logger.info("GuaranteeDao.delGuarantee sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//担保详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GuaranteeDto guaranteeDetailById(int guaranteeId){
		try{
			String comNameSql = "(select com.company_name from sys_company com where com.company_id=a.guarantee_people_id)";
			String cusNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.guarantee_people_id)";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, ");
			sql.append(" (CASE WHEN a.`guarantee_people_type`='sys_company' THEN "+comNameSql+" ELSE "+cusNameSql+" END) AS guaranteePeopleName ");
			sql.append(" FROM sys_guarantee a ");
			sql.append(" WHERE a.guarantee_id="+guaranteeId);
			logger.info("GuaranteeDao.guaranteeDetailById sql = " + sql.toString());
			return (GuaranteeDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(GuaranteeDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//担保列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<GuaranteeDto> guaranteeList(GuaranteeDataTablesReq req){
		try{
			String comNameSql = "(select com.company_name from sys_company com where com.company_id=a.guarantee_people_id)";
			String cusNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.guarantee_people_id)";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, ");
			sql.append(" (CASE WHEN a.`guarantee_people_type`='sys_company' THEN "+comNameSql+" ELSE "+cusNameSql+" END) AS guaranteePeopleName ");
			sql.append(" FROM sys_guarantee a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getGuaranteeCode()!=null && !"".equals(req.getGuaranteeCode())){
				sql.append(" and a.guarantee_code like '%"+req.getGuaranteeCode()+"%' ");
			}
			if(req.getCustomGuaranteeCode()!=null && !"".equals(req.getCustomGuaranteeCode())){
				sql.append(" and a.custom_guarantee_code like '%"+req.getCustomGuaranteeCode()+"%' ");
			}
			if(req.getContractId()!=0){
				sql.append(" and a.contract_id="+req.getContractId());
			}
			if(req.getFinancingMatchingId()!=0){
				sql.append(" and a.financing_matching_id="+req.getFinancingMatchingId());
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("GuaranteeDao.guaranteeList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GuaranteeDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//担保长度
	public int guaranteeCount(GuaranteeDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.guarantee_id) ");
			sql.append(" FROM sys_guarantee a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getGuaranteeCode()!=null && !"".equals(req.getGuaranteeCode())){
				sql.append(" and a.guarantee_code like '%"+req.getGuaranteeCode()+"%' ");
			}
			if(req.getCustomGuaranteeCode()!=null && !"".equals(req.getCustomGuaranteeCode())){
				sql.append(" and a.custom_guarantee_code like '%"+req.getCustomGuaranteeCode()+"%' ");
			}
			if(req.getContractId()!=0){
				sql.append(" and a.contract_id="+req.getContractId());
			}
			if(req.getFinancingMatchingId()!=0){
				sql.append(" and a.financing_matching_id="+req.getFinancingMatchingId());
			}
			logger.info("GuaranteeDao.guaranteeCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//担保长度
	public int guaranteeAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.guarantee_id) ");
			sql.append(" FROM sys_guarantee a ");
			logger.info("GuaranteeDao.guaranteeAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//担保列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<GuaranteeDto> guaranteeListByContractId(int contractId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_guarantee a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.contract_id="+contractId);
			sql.append(" ORDER BY a.create_time DESC ");
			logger.info("GuaranteeDao.guaranteeListByContractId sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(GuaranteeDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
