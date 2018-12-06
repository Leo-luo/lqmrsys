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
import com.lqmrSys.bean.dto.SupplierAdvanceDto;
import com.lqmrSys.bean.pagination.SupplierAdvanceDataTablesReq;
import com.lqmrSys.bean.req.SupplierAdvanceReq;

@Component
public class SupplierAdvanceDao {
	private static Logger logger = Logger.getLogger(SupplierAdvanceDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增供应方预登记
	public int addSupplierAdvance(final SupplierAdvanceReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_supplier_advance (advance_code,customer_name,investment_mode,certificate_type,certificate_num,contact_phone,contact_address,register_time,register_amount,start_time,end_time,month_interest_rate,supply_term,capital_reach_time,user_id,notice_time,other_requirement,remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("SupplierAdvanceDao.addSupplierAdvance sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getAdvanceCode());
			         ps.setString(2, req.getCustomerName());
			         ps.setString(3, req.getInvestmentMode());
			         ps.setString(4, req.getCertificateType());
			         ps.setString(5, req.getCertificateNum());
			         ps.setString(6, req.getContactPhone());
			         ps.setString(7, req.getContactAddress());
			         ps.setString(8, req.getRegisterTime());
			         ps.setDouble(9, req.getRegisterAmount());
			         ps.setString(10, req.getStartTime());
			         ps.setString(11, req.getEndTime());
			         ps.setDouble(12, req.getMonthInterestRate());
			         ps.setString(13, req.getSupplyTerm());
			         ps.setString(14, req.getCapitalReachTime());
			         ps.setInt(15, req.getUserId());
			         ps.setString(16, req.getNoticeTime());
			         ps.setString(17, req.getOtherRequirement());
			         ps.setString(18, req.getRemark());
			         ps.setInt(19, Constants.DATA_STATUS_NORMAL);
			         ps.setString(20, date);
			         ps.setString(21, date);
			         ps.setInt(22, req.getOperatorId());
			         ps.setString(23, req.getOperatorName());
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
	
	//修改供应方预登记
	public int modifySupplierAdvance(SupplierAdvanceReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_supplier_advance set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" ,customer_name='"+req.getCustomerName()+"' ");
			}
			if(req.getInvestmentMode()!=null && !"".equals(req.getInvestmentMode())){
				sql.append(" ,investment_mode='"+req.getInvestmentMode()+"' ");
			}
			if(req.getCertificateType()!=null && !"".equals(req.getCertificateType())){
				sql.append(" ,certificate_type='"+req.getCertificateType()+"' ");
			}
			if(req.getCertificateNum()!=null && !"".equals(req.getCertificateNum())){
				sql.append(" ,certificate_num='"+req.getCertificateNum()+"' ");
			}
			if(req.getContactPhone()!=null && !"".equals(req.getContactPhone())){
				sql.append(" ,contact_phone='"+req.getContactPhone()+"' ");
			}
			if(req.getContactAddress()!=null && !"".equals(req.getContactAddress())){
				sql.append(" ,contact_address='"+req.getContactAddress()+"' ");
			}
			if(req.getRegisterTime()!=null && !"".equals(req.getRegisterTime())){
				sql.append(" ,register_time='"+req.getRegisterTime()+"' ");
			}
			if(req.getRegisterAmount()!=0){
				sql.append(" ,register_amount="+req.getRegisterAmount()+" ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime())){
				sql.append(" ,start_time='"+req.getStartTime()+"' ");
			}
			if(req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" ,end_time='"+req.getEndTime()+"' ");
			}
			if(req.getMonthInterestRate()!=0){
				sql.append(" ,month_interest_rate="+req.getMonthInterestRate()+" ");
			}
			if(req.getSupplyTerm()!=null && !"".equals(req.getSupplyTerm())){
				sql.append(" ,supply_term='"+req.getSupplyTerm()+"' ");
			}
			if(req.getCapitalReachTime()!=null && !"".equals(req.getCapitalReachTime())){
				sql.append(" ,capital_reach_time='"+req.getCapitalReachTime()+"' ");
			}
			if(req.getUserId()!=0){
				sql.append(" ,user_id="+req.getUserId()+" ");
			}
			if(req.getNoticeTime()!=null && !"".equals(req.getNoticeTime())){
				sql.append(" ,notice_time='"+req.getNoticeTime()+"' ");
			}
			if(req.getOtherRequirement()!=null && !"".equals(req.getOtherRequirement())){
				sql.append(" ,other_requirement='"+req.getOtherRequirement()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where advance_id="+req.getAdvanceId());
			logger.info("SupplierAdvanceDao.modifySupplierAdvance sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除供应方预登记
	public int delSupplierAdvance(int advanceId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_supplier_advance set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where advance_id="+advanceId);
			logger.info("SupplierAdvanceDao.delSupplierAdvance sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//供应方预登记信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SupplierAdvanceDto supplierAdvanceDetailById(int advanceId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, c.name as userName");
			sql.append(" FROM sys_supplier_advance a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.advance_id="+advanceId);
			logger.info("SupplierAdvanceDao.supplierAdvanceDetailById sql = " + sql.toString());
			return (SupplierAdvanceDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(SupplierAdvanceDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//供应方预登记列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SupplierAdvanceDto> supplierAdvanceList(SupplierAdvanceDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*,c.name as userName ");
			sql.append(" FROM sys_supplier_advance a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getAdvanceCode()!=null && !"".equals(req.getAdvanceCode())){
				sql.append(" and a.advance_code like '%"+req.getAdvanceCode()+"%' ");
			}
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and a.customer_name like '%"+req.getCustomerName()+"%' ");
			}
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and c.name like '%"+req.getUserName()+"%' ");
			}
			sql.append(" order by a.create_time desc ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("SupplierAdvanceDao.supplierAdvanceList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(SupplierAdvanceDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//供应方预登记列表长度
	public int supplierAdvanceCount(SupplierAdvanceDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select COUNT(a.advance_id) ");
			sql.append(" FROM sys_supplier_advance a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getAdvanceCode()!=null && !"".equals(req.getAdvanceCode())){
				sql.append(" and a.advance_code like '%"+req.getAdvanceCode()+"%' ");
			}
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and a.customer_name like '%"+req.getCustomerName()+"%' ");
			}
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and c.name like '%"+req.getUserName()+"%' ");
			}
			logger.info("SupplierAdvanceDao.supplierAdvanceCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//供应方预登记列表长度
	public int supplierAdvanceAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.advance_id) ");
			sql.append(" FROM sys_supplier_advance a ");
			logger.info("SupplierAdvanceDao.supplierAdvanceAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
