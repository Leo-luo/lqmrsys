package com.lqmrSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.common.Constants;
import com.lqmrSys.bean.req.RiskSignReq;

@Component
public class RiskSignDao {
	private static Logger logger = Logger.getLogger(RiskSignDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增风险标记
	public int addRiskSign(final RiskSignReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_risk_sign (sign_type,data_id,data_from,remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?)");
			logger.info("RiskSignDao.addRiskSign sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getSignType());
			         ps.setInt(2, req.getDataId());
			         ps.setString(3, req.getDataFrom());
			         ps.setString(4, req.getRemark());
			         ps.setInt(5, Constants.DATA_STATUS_NORMAL);
			         ps.setString(6, date);
			         ps.setString(7, date);
			         ps.setInt(8, req.getOperatorId());
			         ps.setString(9, req.getOperatorName());
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
	
	//逻辑删除风险标记
	public int delRiskSign(int signId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_risk_sign set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where sign_id="+signId);
			logger.info("RiskSignDao.delRiskSign sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
