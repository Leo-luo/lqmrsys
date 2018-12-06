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
import com.lqmrSys.bean.dto.RefuseDto;
import com.lqmrSys.bean.pagination.RefuseDataTablesReq;
import com.lqmrSys.bean.req.RefuseReq;

@Component
public class RefuseDao {
	private static Logger logger = Logger.getLogger(RefuseDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增拒贷客户
	public int addRefuse(final RefuseReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_refuse (data_id,data_from,remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?)");
			logger.info("RefuseDao.addRefuse sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getDataId());
			         ps.setString(2, req.getDataFrom());
			         ps.setString(3, req.getRemark());
			         ps.setInt(4, Constants.DATA_STATUS_NORMAL);
			         ps.setString(5, date);
			         ps.setString(6, date);
			         ps.setInt(7, req.getOperatorId());
			         ps.setString(8, req.getOperatorName());
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
	
	//修改拒贷客户
	public int modifyRefuse(RefuseReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_refuse set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getDataId()!=0){
				sql.append(" ,data_id="+req.getDataId()+" ");
			}
			if(req.getDataFrom()!=null && !"".equals(req.getDataFrom())){
				sql.append(" ,data_from='"+req.getDataFrom()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where refuse_id="+req.getRefuseId());
			logger.info("RefuseDao.modifyRefuse sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除拒贷客户
	public int delRefuse(int refuseId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_refuse set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where refuse_id="+refuseId);
			logger.info("RefuseDao.delRefuse sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//拒贷客户列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RefuseDto> refuseList(RefuseDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ");
			sql.append(" ( ");
			sql.append(" SELECT a.*, b.`company_name` AS dataName, b.`corporation_idcard` AS dataCertificate FROM sys_refuse a, sys_company b WHERE a.`data_from`='sys_company' AND a.data_status!="+Constants.DATA_STATUS_DELETE+" AND a.`data_id`=b.`company_id` ");
			sql.append(" UNION ");
			sql.append(" SELECT a.*, b.name AS dataName, b.credentials_num AS dataCertificate FROM sys_refuse a, sys_customer b WHERE a.`data_from`='sys_customer' AND a.data_status!="+Constants.DATA_STATUS_DELETE+" AND a.`data_id`=b.customer_id ");
			sql.append(" )total ");
			sql.append(" WHERE 1=1 ");
			//筛选条件
			if(req.getDataName()!=null && !"".equals(req.getDataName())){
				sql.append(" and total.dataName like '%"+req.getDataName()+"%' ");
			}
			if(req.getDataCertificate()!=null && !"".equals(req.getDataCertificate())){
				sql.append(" and total.dataCertificate like '%"+req.getDataCertificate()+"%' ");
			}
			sql.append(" ORDER BY total.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("RefuseDao.refuseList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RefuseDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//拒贷客户列表长度
	public int refuseCount(RefuseDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) FROM ");
			sql.append(" ( ");
			sql.append(" SELECT a.*, b.`company_name` AS dataName, b.`corporation_idcard` AS dataCertificate FROM sys_refuse a, sys_company b WHERE a.`data_from`='sys_company' AND a.data_status!="+Constants.DATA_STATUS_DELETE+" AND a.`data_id`=b.`company_id` ");
			sql.append(" UNION ");
			sql.append(" SELECT a.*, b.name AS dataName, b.credentials_num AS dataCertificate FROM sys_refuse a, sys_customer b WHERE a.`data_from`='sys_customer' AND a.data_status!="+Constants.DATA_STATUS_DELETE+" AND a.`data_id`=b.customer_id ");
			sql.append(" )total ");
			sql.append(" WHERE 1=1 ");
			//筛选条件
			if(req.getDataName()!=null && !"".equals(req.getDataName())){
				sql.append(" and total.dataName like '%"+req.getDataName()+"%' ");
			}
			if(req.getDataCertificate()!=null && !"".equals(req.getDataCertificate())){
				sql.append(" and total.dataCertificate like '%"+req.getDataCertificate()+"%' ");
			}
			logger.info("RefuseDao.refuseCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//是否为拒贷客户
	public int hasRefuse(int dataId, String dataFrom){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.refuse_id) FROM sys_refuse a where a.data_id="+dataId+" and a.data_from='"+dataFrom+"' and a.data_status!="+Constants.DATA_STATUS_DELETE);
			logger.info("RefuseDao.hasRefuse sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
//			e.printStackTrace();
			return 0;
		}
	}
	
}
