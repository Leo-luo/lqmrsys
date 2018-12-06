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
import com.lqmrSys.bean.SupplierMatchingBean;

@Component
public class SupplierMachingDao {
	private static Logger logger = Logger.getLogger(SupplierMachingDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增配对关联信息
	public int addSupplierMaching(final SupplierMatchingBean req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_matching_relation (financing_matching_id,supplier_id,matching_amount,matching_type,remark,data_status,create_time,update_time,operator_id,operator_name,matching_start_time,matching_end_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("SupplierMachingDao.addSupplierMaching sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getFinancingMatchingId());
			         ps.setInt(2, req.getSupplierId());
			         ps.setDouble(3, req.getMatchingAmount());
			         ps.setInt(4, req.getMatchingType());
			         ps.setString(5, req.getRemark());
			         ps.setInt(6, Constants.DATA_STATUS_NORMAL);
			         ps.setString(7, date);
			         ps.setString(8, date);
			         ps.setInt(9, req.getOperatorId());
			         ps.setString(10, req.getOperatorName());
			         ps.setString(11, req.getMatchingStartTime());
			         ps.setString(12, req.getMatchingEndTime());
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
	
	//逻辑删除配对关联信息
	public int delSupplierMaching(int matchingRelationId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_matching_relation set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+", operator_name='"+operatorName+"' ");
			sql.append(" where matching_relation_id="+matchingRelationId);
			logger.info("SupplierMachingDao.delSupplierMaching sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//配对关联信息列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SupplierMatchingBean> supplierMachingList(int financingMatchingId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_matching_relation a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.financing_matching_id="+financingMatchingId);
			sql.append(" ORDER BY a.create_time DESC ");
			logger.info("SupplierMachingDao.supplierMachingList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(SupplierMatchingBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
