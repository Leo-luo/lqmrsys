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
import com.lqmrSys.bean.OperateBean;
import com.lqmrSys.bean.dto.OperateDto;
import com.lqmrSys.bean.pagination.OperateDataTablesReq;
import com.utils.DateUtils;

@Component
public class OperateDao {
	private static Logger logger = Logger.getLogger(OperateDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增操作日志
	public int addOperate(final OperateBean req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_operate (operator_id,operator_name,operate_interface,operate_interface_name,operate_content,remark,data_status,create_time,update_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?)");
			logger.info("OperateDao.addOperate sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getOperatorId());
			         ps.setString(2, req.getOperatorName());
			         ps.setString(3, req.getOperateInterface());
			         ps.setString(4, req.getOperateInterfaceName());
			         ps.setString(5, req.getOperateContent());
			         ps.setString(6, req.getOperateRemark());
			         ps.setInt(7, Constants.DATA_STATUS_NORMAL);
			         ps.setString(8, date);
			         ps.setString(9, date);
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
	
	//操作日志列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OperateDto> operateList(OperateDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_operate a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getOperatorNameSer()!=null && !"".equals(req.getOperatorNameSer())){
				sql.append(" and a.operator_name like '%"+req.getOperatorNameSer()+"%' ");
			}
			if(req.getOperateInterfaceNameSer()!=null && !"".equals(req.getOperateInterfaceNameSer())){
				sql.append(" and a.operate_interface_name like '%"+req.getOperateInterfaceNameSer()+"%' ");
			}
			if(req.getOperateContentSer()!=null && !"".equals(req.getOperateContentSer())){
				sql.append(" and a.operate_content like '%"+req.getOperateContentSer()+"%' ");
			}
			if(req.getRemarkSer()!=null && !"".equals(req.getRemarkSer())){
				sql.append(" and a.remark like '%"+req.getRemarkSer()+"%' ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime()) && req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" and a.create_time>='"+DateUtils.fromDateToFormatString(req.getStartTime(), "yyyy-MM-dd")+" 00:00:00'");
				sql.append(" and a.create_time<='"+DateUtils.fromDateToFormatString(req.getEndTime(), "yyyy-MM-dd")+" 23:59:59'");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("OperateDao.operateList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(OperateDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//操作日志列表长度
	public int operateCount(OperateDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.operate_id) ");
			sql.append(" FROM sys_operate a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getOperatorNameSer()!=null && !"".equals(req.getOperatorNameSer())){
				sql.append(" and a.operator_name like '%"+req.getOperatorNameSer()+"%' ");
			}
			if(req.getOperateInterfaceNameSer()!=null && !"".equals(req.getOperateInterfaceNameSer())){
				sql.append(" and a.operate_interface_name like '%"+req.getOperateInterfaceNameSer()+"%' ");
			}
			if(req.getOperateContentSer()!=null && !"".equals(req.getOperateContentSer())){
				sql.append(" and a.operate_content like '%"+req.getOperateContentSer()+"%' ");
			}
			if(req.getRemarkSer()!=null && !"".equals(req.getRemarkSer())){
				sql.append(" and a.remark like '%"+req.getRemarkSer()+"%' ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime()) && req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" and a.create_time>='"+DateUtils.fromDateToFormatString(req.getStartTime(), "yyyy-MM-dd")+" 00:00:00'");
				sql.append(" and a.create_time<='"+DateUtils.fromDateToFormatString(req.getEndTime(), "yyyy-MM-dd")+" 23:59:59'");
			}
			logger.info("OperateDao.operateCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
