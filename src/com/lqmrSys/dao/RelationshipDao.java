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
import com.lqmrSys.bean.dto.RelationshipDto;
import com.lqmrSys.bean.pagination.RelationshipDataTablesReq;
import com.lqmrSys.bean.req.RelationshipReq;

@Component
public class RelationshipDao {
	private static Logger logger = Logger.getLogger(RelationshipDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增关系
	public int addRelationship(final RelationshipReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_relationship (data_from,data_id,data_relationship_from,data_relationship_id,relationship_describe,data_status,create_time,update_time,remark");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?)");
			logger.info("RelationshipDao.addRelationship sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getDataFrom());
			         ps.setInt(2, req.getDataId());
			         ps.setString(3, req.getDataRelationshipFrom());
			         ps.setInt(4, req.getDataRelationshipId());
			         ps.setString(5, req.getRelationshipDescribe());
			         ps.setInt(6, Constants.DATA_STATUS_NORMAL);
			         ps.setString(7, date);
			         ps.setString(8, date);
			         ps.setString(9, req.getRemark());
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
	
	//修改关系
	public int modifyRelationship(RelationshipReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_relationship set update_time='"+date+"' ");
			if(req.getRelationshipDescribe()!=null && !"".equals(req.getRelationshipDescribe())){
				sql.append(" ,relationship_describe='"+req.getRelationshipDescribe()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where relationship_id="+req.getRelationshipId());
			logger.info("RelationshipDao.modifyRelationship sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除关系
	public int delRelationship(int relationshipId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_relationship set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where relationship_id="+relationshipId);
			logger.info("RelationshipDao.delRelationship sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//关系信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RelationshipDto relationshipDetailById(int relationshipId){
		try{
			String dataNameCompanySql = "(SELECT b.company_name FROM sys_company b WHERE b.company_id=a.data_id AND a.`data_from`='sys_company')";
			String dataNameCustomerSql = "(SELECT b.name FROM sys_customer b WHERE b.customer_id=a.data_id AND a.`data_from`='sys_customer')";
			String dataRelationshipNameCompanySql = "(SELECT b.company_name FROM sys_company b WHERE b.company_id=a.data_relationship_id AND a.`data_relationship_from`='sys_company')";
			String dataRelationshipNameCustomerSql = "(SELECT b.name FROM sys_customer b WHERE b.customer_id=a.data_relationship_id AND a.`data_relationship_from`='sys_customer')";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, ");
			sql.append(" (CASE WHEN "+dataNameCompanySql+" IS NOT NULL THEN "+dataNameCompanySql+" ELSE "+dataNameCustomerSql+" END) as dataName, ");
			sql.append(" (CASE WHEN "+dataRelationshipNameCompanySql+" IS NOT NULL THEN "+dataRelationshipNameCompanySql+" ELSE "+dataRelationshipNameCustomerSql+" END) as dataRelationshipName ");
			sql.append(" FROM sys_relationship a ");
			sql.append(" WHERE a.relationship_id="+relationshipId);
			logger.info("RelationshipDao.relationshipDetailById sql = " + sql.toString());
			return (RelationshipDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(RelationshipDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//关系列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationshipDto> relationshipList(RelationshipDataTablesReq req){
		try{
			String dataNameCompanySql = "(SELECT b.company_name FROM sys_company b WHERE b.company_id=a.data_id AND a.`data_from`='sys_company')";
			String dataNameCustomerSql = "(SELECT b.name FROM sys_customer b WHERE b.customer_id=a.data_id AND a.`data_from`='sys_customer')";
			String dataRelationshipNameCompanySql = "(SELECT b.company_name FROM sys_company b WHERE b.company_id=a.data_relationship_id AND a.`data_relationship_from`='sys_company')";
			String dataRelationshipNameCustomerSql = "(SELECT b.name FROM sys_customer b WHERE b.customer_id=a.data_relationship_id AND a.`data_relationship_from`='sys_customer')";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, ");
			sql.append(" (CASE WHEN "+dataNameCompanySql+" IS NOT NULL THEN "+dataNameCompanySql+" ELSE "+dataNameCustomerSql+" END) as dataName, ");
			sql.append(" (CASE WHEN "+dataRelationshipNameCompanySql+" IS NOT NULL THEN "+dataRelationshipNameCompanySql+" ELSE "+dataRelationshipNameCustomerSql+" END) as dataRelationshipName ");
			sql.append(" FROM sys_relationship a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getDataFrom()!=null && !"".equals(req.getDataFrom())){
				sql.append(" and a.data_from like '%"+req.getDataFrom()+"%' ");
			}
			if(req.getDataId()!=0){
				sql.append(" and a.data_id="+req.getDataId()+" ");
			}
			if(req.getRelationshipDescribe()!=null && !"".equals(req.getRelationshipDescribe())){
				sql.append(" and a.relationship_describe like '%"+req.getRelationshipDescribe()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("RelationshipDao.relationshipList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationshipDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//关系列表长度
	public int relationshipCount(RelationshipDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.relationship_id) ");
			sql.append(" FROM sys_relationship a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getDataFrom()!=null && !"".equals(req.getDataFrom())){
				sql.append(" and a.data_from like '%"+req.getDataFrom()+"%' ");
			}
			if(req.getDataId()!=0){
				sql.append(" and a.data_id="+req.getDataId()+" ");
			}
			if(req.getRelationshipDescribe()!=null && !"".equals(req.getRelationshipDescribe())){
				sql.append(" and a.relationship_describe like '%"+req.getRelationshipDescribe()+"%' ");
			}
			logger.info("RelationshipDao.relationshipCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//是否存在
	public int hasRelationship(RelationshipReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.relationship_id) ");
			sql.append(" FROM sys_relationship a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getDataFrom()!=null && !"".equals(req.getDataFrom())){
				sql.append(" and a.data_from like '%"+req.getDataFrom()+"%' ");
			}
			if(req.getDataId()!=0){
				sql.append(" and a.data_id="+req.getDataId()+" ");
			}
			if(req.getRelationshipDescribe()!=null && !"".equals(req.getRelationshipDescribe())){
				sql.append(" and a.relationship_describe like '%"+req.getRelationshipDescribe()+"%' ");
			}
			if(req.getDataRelationshipFrom()!=null && !"".equals(req.getDataRelationshipFrom())){
				sql.append(" and a.data_relationship_from like '%"+req.getDataRelationshipFrom()+"%' ");
			}
			if(req.getDataRelationshipId()!=0){
				sql.append(" and a.data_relationship_id="+req.getDataRelationshipId()+" ");
			}
			logger.info("RelationshipDao.hasRelationship sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
