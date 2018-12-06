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
import com.lqmrSys.bean.dto.CustomerDto;
import com.lqmrSys.bean.pagination.CustomerDataTablesReq;
import com.lqmrSys.bean.req.CustomerReq;

@Component
public class CustomerDao {
	private static Logger logger = Logger.getLogger(CustomerDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增客户
	public int addCustomer(final CustomerReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_customer (customer_code,name,sex,credentials_type,credentials_num,credentials_effective_time,native_place,nation,home_address,birthday,phone_num,contact_num,email,work_company,work_place,position,year_income,marital_status,education,industry_category,belong_area,credit_level,loan_target,credit_num,customer_remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("CustomerDao.addCustomer sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getCustomerCode());
			         ps.setString(2, req.getName());
			         ps.setString(3, req.getSex());
			         ps.setString(4, req.getCredentialsType());
			         ps.setString(5, req.getCredentialsNum());
			         ps.setString(6, req.getCredentialsEffectiveTime());
			         ps.setString(7, req.getNativePlace());
			         ps.setString(8, req.getNation());
			         ps.setString(9, req.getHomeAddress());
			         ps.setString(10, req.getBirthday());
			         ps.setString(11, req.getPhoneNum());
			         ps.setString(12, req.getContactNum());
			         ps.setString(13, req.getEmail());
			         ps.setString(14, req.getWorkCompany());
			         ps.setString(15, req.getWorkPlace());
			         ps.setString(16, req.getPosition());
			         ps.setDouble(17, req.getYearIncome());
			         ps.setString(18, req.getMaritalStatus());
			         ps.setString(19, req.getEducation());
			         ps.setString(20, req.getIndustryCategory());
			         ps.setString(21, req.getBelongArea());
			         ps.setString(22, req.getCreditLevel());
			         ps.setString(23, req.getLoanTarget());
			         ps.setInt(24, req.getCreditNum());
			         ps.setString(25, req.getCustomerRemark());
			         ps.setInt(26, Constants.DATA_STATUS_NORMAL);
			         ps.setString(27, date);
			         ps.setString(28, date);
			         ps.setInt(29, req.getOperatorId());
			         ps.setString(30, req.getOperatorName());
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
	
	//修改客户
	public int modifyCustomer(CustomerReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_customer set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" ,name='"+req.getName()+"' ");
			}
			if(req.getSex()!=null && !"".equals(req.getSex())){
				sql.append(" ,sex='"+req.getSex()+"' ");
			}
			if(req.getCredentialsType()!=null && !"".equals(req.getCredentialsType())){
				sql.append(" ,credentials_type='"+req.getCredentialsType()+"' ");
			}
			if(req.getCredentialsNum()!=null && !"".equals(req.getCredentialsNum())){
				sql.append(" ,credentials_num='"+req.getCredentialsNum()+"' ");
			}
			if(req.getCredentialsEffectiveTime()!=null && !"".equals(req.getCredentialsEffectiveTime())){
				sql.append(" ,credentials_effective_time='"+req.getCredentialsEffectiveTime()+"' ");
			}
			if(req.getNativePlace()!=null && !"".equals(req.getNativePlace())){
				sql.append(" ,native_place='"+req.getNativePlace()+"' ");
			}
			if(req.getNation()!=null && !"".equals(req.getNation())){
				sql.append(" ,nation='"+req.getNation()+"' ");
			}
			if(req.getHomeAddress()!=null && !"".equals(req.getHomeAddress())){
				sql.append(" ,home_address='"+req.getHomeAddress()+"' ");
			}
			if(req.getBirthday()!=null && !"".equals(req.getBirthday())){
				sql.append(" ,birthday='"+req.getBirthday()+"' ");
			}
			if(req.getPhoneNum()!=null && !"".equals(req.getPhoneNum())){
				sql.append(" ,phone_num='"+req.getPhoneNum()+"' ");
			}
			if(req.getContactNum()!=null && !"".equals(req.getContactNum())){
				sql.append(" ,contact_num='"+req.getContactNum()+"' ");
			}
			if(req.getEmail()!=null && !"".equals(req.getEmail())){
				sql.append(" ,email='"+req.getEmail()+"' ");
			}
			if(req.getWorkCompany()!=null && !"".equals(req.getWorkCompany())){
				sql.append(" ,work_company='"+req.getWorkCompany()+"' ");
			}
			if(req.getWorkPlace()!=null && !"".equals(req.getWorkPlace())){
				sql.append(" ,work_place='"+req.getWorkPlace()+"' ");
			}
			if(req.getPosition()!=null && !"".equals(req.getPosition())){
				sql.append(" ,position='"+req.getPosition()+"' ");
			}
			if(req.getYearIncome()!=0){
				sql.append(" ,year_income="+req.getYearIncome()+" ");
			}
			if(req.getMaritalStatus()!=null && !"".equals(req.getMaritalStatus())){
				sql.append(" ,marital_status='"+req.getMaritalStatus()+"' ");
			}
			if(req.getEducation()!=null && !"".equals(req.getEducation())){
				sql.append(" ,education='"+req.getEducation()+"' ");
			}
			if(req.getIndustryCategory()!=null && !"".equals(req.getIndustryCategory())){
				sql.append(" ,industry_category='"+req.getIndustryCategory()+"' ");
			}
			if(req.getBelongArea()!=null && !"".equals(req.getBelongArea())){
				sql.append(" ,belong_area='"+req.getBelongArea()+"' ");
			}
			if(req.getCreditLevel()!=null && !"".equals(req.getCreditLevel())){
				sql.append(" ,credit_level='"+req.getCreditLevel()+"' ");
			}
			if(req.getLoanTarget()!=null && !"".equals(req.getLoanTarget())){
				sql.append(" ,loan_target='"+req.getLoanTarget()+"' ");
			}
			if(req.getCreditNum()!=0){
				sql.append(" ,credit_num="+req.getCreditNum()+" ");
			}
			if(req.getCustomerRemark()!=null && !"".equals(req.getCustomerRemark())){
				sql.append(" ,customer_remark='"+req.getCustomerRemark()+"' ");
			}
			sql.append(" where customer_id="+req.getCustomerId());
			logger.info("CustomerDao.modifyCustomer sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除客户
	public int delCustomer(int customerId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_customer set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where customer_id="+customerId);
			logger.info("CustomerDao.delCustomer sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//客户信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomerDto customerDetailById(int customerId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_customer a ");
			sql.append(" WHERE a.customer_id="+customerId);
			logger.info("CustomerDao.customerDetailById sql = " + sql.toString());
			return (CustomerDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(CustomerDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//客户列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CustomerDto> customerList(CustomerDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_customer a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and a.name like '%"+req.getCustomerName()+"%' ");
			}
			if(req.getCustomerCode()!=null && !"".equals(req.getCustomerCode())){
				sql.append(" and a.customer_code like '%"+req.getCustomerCode()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("CustomerDao.customerList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(CustomerDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//客户列表长度
	public int customerCount(CustomerDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.customer_id) ");
			sql.append(" FROM sys_customer a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and a.name like '%"+req.getCustomerName()+"%' ");
			}
			if(req.getCustomerCode()!=null && !"".equals(req.getCustomerCode())){
				sql.append(" and a.customer_code like '%"+req.getCustomerCode()+"%' ");
			}
			logger.info("CustomerDao.customerCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//客户列表长度
	public int customerAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.customer_id) ");
			sql.append(" FROM sys_customer a ");
			logger.info("CustomerDao.customerAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
