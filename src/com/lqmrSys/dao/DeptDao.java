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
import com.lqmrSys.bean.dto.DeptDto;
import com.lqmrSys.bean.pagination.DeptDataTablesReq;
import com.lqmrSys.bean.req.DeptReq;

@Component
public class DeptDao {
	private static Logger logger = Logger.getLogger(DeptDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增部门
	public int addDept(final DeptReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_dept (name,parent_dept_id,remark,data_order,data_status,create_time,update_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?)");
			logger.info("DeptDao.addDept sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getName());
			         ps.setInt(2, req.getParentDeptId());
			         ps.setString(3, req.getRemark());
			         ps.setInt(4, req.getDataOrder());
			         ps.setInt(5, Constants.DATA_STATUS_NORMAL);
			         ps.setString(6, date);
			         ps.setString(7, date);
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
	
	//修改部门
	public int modifyDept(DeptReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_dept set update_time='"+date+"' ");
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" ,name='"+req.getName()+"' ");
			}
			if(req.getParentDeptId()!=0){
				sql.append(" ,parent_dept_id="+req.getParentDeptId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getDataOrder()!=0){
				sql.append(" ,data_order="+req.getDataOrder()+" ");
			}
			sql.append(" where dept_id="+req.getDeptId());
			logger.info("DeptDao.modifyDept sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除部门
	public int delDept(int deptId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_dept set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where dept_id="+deptId);
			logger.info("DeptDao.delDept sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//部门信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeptDto deptDetailById(int deptId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dept a ");
			sql.append(" WHERE a.dept_id="+deptId);
			logger.info("DeptDao.deptDetailById sql = " + sql.toString());
			return (DeptDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(DeptDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//部门列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DeptDto> deptList(DeptDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dept a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			sql.append(" ORDER BY a.data_order DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("DeptDao.deptList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(DeptDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//部门列表长度
	public int deptCount(DeptDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.dept_id) ");
			sql.append(" FROM sys_dept a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			logger.info("DeptDao.deptCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//部门列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DeptDto> deptListByParentDeptId(int deptId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dept a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.parent_dept_id="+deptId+" ");
			sql.append(" ORDER BY a.data_order DESC ");
			logger.info("DeptDao.deptList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(DeptDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
