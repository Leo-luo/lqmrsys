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
import com.lqmrSys.bean.dto.RoleDto;
import com.lqmrSys.bean.pagination.RoleDataTablesReq;
import com.lqmrSys.bean.req.RoleReq;

@Component
public class RoleDao {
	private static Logger logger = Logger.getLogger(RoleDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增角色
	public int addRole(final RoleReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_role (name,remark,data_order,data_status,create_time,update_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?)");
			logger.info("RoleDao.addRole sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getName());
			         ps.setString(2, req.getRemark());
			         ps.setInt(3, req.getDataOrder());
			         ps.setInt(4, Constants.DATA_STATUS_NORMAL);
			         ps.setString(5, date);
			         ps.setString(6, date);
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
	
	//修改角色
	public int modifyRole(RoleReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_role set update_time='"+date+"' ");
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" ,name='"+req.getName()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getDataOrder()!=0){
				sql.append(" ,data_order="+req.getDataOrder()+" ");
			}
			sql.append(" where role_id="+req.getRoleId());
			logger.info("RoleDao.modifyRole sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除角色
	public int delRole(int roleId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_role set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where role_id="+roleId);
			logger.info("RoleDao.delRole sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//角色信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RoleDto roleDetailById(int roleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_role a ");
			sql.append(" WHERE a.role_id="+roleId);
			logger.info("RoleDao.roleDetailById sql = " + sql.toString());
			return (RoleDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(RoleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//角色列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RoleDto> roleList(RoleDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_role a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			sql.append(" ORDER BY a.data_order DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("RoleDao.roleList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RoleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//角色列表长度
	public int roleCount(RoleDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.role_id) ");
			sql.append(" FROM sys_role a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			logger.info("RoleDao.roleCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
