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
import com.lqmrSys.bean.dto.CompanyDto;
import com.lqmrSys.bean.pagination.CompanyDataTablesReq;
import com.lqmrSys.bean.req.CompanyReq;

@Component
public class CompanyDao {
	private static Logger logger = Logger.getLogger(CompanyDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增企业
	public int addCompany(final CompanyReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_company (company_code,company_name,company_name_short,registered_capital,capital_type,loan_num,register_time,register_address,shareholder_num,company_phone,company_address,company_homepage,fax_num,corporation_name,corporation_sex,corporation_contact_num,corporation_idcard,corporation_email,corporation_phone_num,corporation_home_address,social_credit_code,industry_category,credit_level,company_nature,belong_area,loan_target,operate_place_ownership,operate_place_area,company_remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("CompanyDao.addCompany sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getCompanyCode());
			         ps.setString(2, req.getCompanyName());
			         ps.setString(3, req.getCompanyNameShort());
			         ps.setDouble(4, req.getRegisteredCapital());
			         ps.setString(5, req.getCapitalType());
			         ps.setInt(6, req.getLoanNum());
			         ps.setString(7, req.getRegisterTime());
			         ps.setString(8, req.getRegisterAddress());
			         ps.setInt(9, req.getShareholderNum());
			         ps.setString(10, req.getCompanyPhone());
			         ps.setString(11, req.getCompanyAddress());
			         ps.setString(12, req.getCompanyHomepage());
			         ps.setString(13, req.getFaxNum());
			         ps.setString(14, req.getCorporationName());
			         ps.setString(15, req.getCorporationSex());
			         ps.setString(16, req.getCorporationContactNum());
			         ps.setString(17, req.getCorporationIdcard());
			         ps.setString(18, req.getCorporationEmail());
			         ps.setString(19, req.getCorporationPhoneNum());
			         ps.setString(20, req.getCorporationHomeAddress());
			         ps.setString(21, req.getSocialCreditCode());
			         ps.setString(22, req.getIndustryCategory());
			         ps.setString(23, req.getCreditLevel());
			         ps.setString(24, req.getCompanyNature());
			         ps.setString(25, req.getBelongArea());
			         ps.setString(26, req.getLoanTarget());
			         ps.setString(27, req.getOperatePlaceOwnership());
			         ps.setDouble(28, req.getOperatePlaceArea());
			         ps.setString(29, req.getCompanyRemark());
			         ps.setInt(30, Constants.DATA_STATUS_NORMAL);
			         ps.setString(31, date);
			         ps.setString(32, date);
			         ps.setInt(33, req.getOperatorId());
			         ps.setString(34, req.getOperatorName());
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
	
	//修改企业
	public int modifyCompany(CompanyReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_company set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getCompanyName()!=null && !"".equals(req.getCompanyName())){
				sql.append(" ,company_name='"+req.getCompanyName()+"' ");
			}
			if(req.getCompanyNameShort()!=null && !"".equals(req.getCompanyNameShort())){
				sql.append(" ,company_name_short='"+req.getCompanyNameShort()+"' ");
			}
			if(req.getRegisteredCapital()!=0){
				sql.append(" ,registered_capital="+req.getRegisteredCapital()+" ");
			}
			if(req.getCapitalType()!=null && !"".equals(req.getCapitalType())){
				sql.append(" ,capital_type='"+req.getCapitalType()+"' ");
			}
			if(req.getLoanNum()!=0){
				sql.append(" ,loan_num="+req.getLoanNum()+" ");
			}
			if(req.getRegisterTime()!=null && !"".equals(req.getRegisterTime())){
				sql.append(" ,register_time='"+req.getRegisterTime()+"' ");
			}
			if(req.getRegisterAddress()!=null && !"".equals(req.getRegisterAddress())){
				sql.append(" ,register_address='"+req.getRegisterAddress()+"' ");
			}
			if(req.getShareholderNum()!=0){
				sql.append(" ,shareholder_num="+req.getShareholderNum()+" ");
			}
			if(req.getCompanyPhone()!=null && !"".equals(req.getCompanyPhone())){
				sql.append(" ,company_phone='"+req.getCompanyPhone()+"' ");
			}
			if(req.getCompanyAddress()!=null && !"".equals(req.getCompanyAddress())){
				sql.append(" ,company_address='"+req.getCompanyAddress()+"' ");
			}
			if(req.getCompanyHomepage()!=null && !"".equals(req.getCompanyHomepage())){
				sql.append(" ,company_homepage='"+req.getCompanyHomepage()+"' ");
			}
			if(req.getFaxNum()!=null && !"".equals(req.getFaxNum())){
				sql.append(" ,fax_num='"+req.getFaxNum()+"' ");
			}
			if(req.getCorporationName()!=null && !"".equals(req.getCorporationName())){
				sql.append(" ,corporation_name='"+req.getCorporationName()+"' ");
			}
			if(req.getCorporationSex()!=null && !"".equals(req.getCorporationSex())){
				sql.append(" ,corporation_sex='"+req.getCorporationSex()+"' ");
			}
			if(req.getCorporationContactNum()!=null && !"".equals(req.getCorporationContactNum())){
				sql.append(" ,corporation_contact_num='"+req.getCorporationContactNum()+"' ");
			}
			if(req.getCorporationIdcard()!=null && !"".equals(req.getCorporationIdcard())){
				sql.append(" ,corporation_idcard='"+req.getCorporationIdcard()+"' ");
			}
			if(req.getCorporationEmail()!=null && !"".equals(req.getCorporationEmail())){
				sql.append(" ,corporation_email='"+req.getCorporationEmail()+"' ");
			}
			if(req.getCorporationPhoneNum()!=null && !"".equals(req.getCorporationPhoneNum())){
				sql.append(" ,corporation_phone_num='"+req.getCorporationPhoneNum()+"' ");
			}
			if(req.getCorporationHomeAddress()!=null && !"".equals(req.getCorporationHomeAddress())){
				sql.append(" ,corporation_home_address='"+req.getCorporationHomeAddress()+"' ");
			}
			if(req.getSocialCreditCode()!=null && !"".equals(req.getSocialCreditCode())){
				sql.append(" ,social_credit_code='"+req.getSocialCreditCode()+"' ");
			}
			if(req.getIndustryCategory()!=null && !"".equals(req.getIndustryCategory())){
				sql.append(" ,industry_category='"+req.getIndustryCategory()+"' ");
			}
			if(req.getCreditLevel()!=null && !"".equals(req.getCreditLevel())){
				sql.append(" ,credit_level='"+req.getCreditLevel()+"' ");
			}
			if(req.getCompanyNature()!=null && !"".equals(req.getCompanyNature())){
				sql.append(" ,company_nature='"+req.getCompanyNature()+"' ");
			}
			if(req.getBelongArea()!=null && !"".equals(req.getBelongArea())){
				sql.append(" ,belong_area='"+req.getBelongArea()+"' ");
			}
			if(req.getLoanTarget()!=null && !"".equals(req.getLoanTarget())){
				sql.append(" ,loan_target='"+req.getLoanTarget()+"' ");
			}
			if(req.getOperatePlaceOwnership()!=null && !"".equals(req.getOperatePlaceOwnership())){
				sql.append(" ,operate_place_ownership='"+req.getOperatePlaceOwnership()+"' ");
			}
			if(req.getOperatePlaceArea()!=0){
				sql.append(" ,operate_place_area="+req.getOperatePlaceArea()+" ");
			}
			if(req.getCompanyRemark()!=null && !"".equals(req.getCompanyRemark())){
				sql.append(" ,company_remark='"+req.getCompanyRemark()+"' ");
			}
			sql.append(" where company_id="+req.getCompanyId());
			logger.info("CompanyDao.modifyCompany sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除企业
	public int delCompany(int companyId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_company set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where company_id="+companyId);
			logger.info("CompanyDao.delCompany sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//企业信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CompanyDto companyDetailById(int companyId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_company a ");
			sql.append(" WHERE a.company_id="+companyId);
			logger.info("CompanyDao.companyDetailById sql = " + sql.toString());
			return (CompanyDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(CompanyDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//企业列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CompanyDto> companyList(CompanyDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_company a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCompanyName()!=null && !"".equals(req.getCompanyName())){
				sql.append(" and a.company_name like '%"+req.getCompanyName()+"%' ");
			}
			if(req.getCompanyCode()!=null && !"".equals(req.getCompanyCode())){
				sql.append(" and a.company_code like '%"+req.getCompanyCode()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("CompanyDao.companyList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(CompanyDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//企业列表长度
	public int companyCount(CompanyDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.company_id) ");
			sql.append(" FROM sys_company a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCompanyName()!=null && !"".equals(req.getCompanyName())){
				sql.append(" and a.company_name like '%"+req.getCompanyName()+"%' ");
			}
			if(req.getCompanyCode()!=null && !"".equals(req.getCompanyCode())){
				sql.append(" and a.company_code like '%"+req.getCompanyCode()+"%' ");
			}
			logger.info("CompanyDao.companyCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//企业列表长度
	public int companyAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.company_id) ");
			sql.append(" FROM sys_company a ");
			logger.info("CompanyDao.companyAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
