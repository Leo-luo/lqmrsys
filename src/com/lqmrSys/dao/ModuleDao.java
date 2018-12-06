package com.lqmrSys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.Constants;
import com.lqmrSys.bean.dto.RelationModuleDto;
import com.lqmrSys.bean.dto.UserDataDto;
import com.lqmrSys.bean.req.RelationModuleReq;

@Component
public class ModuleDao {
	private static Logger logger = Logger.getLogger(ModuleDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//模块列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationModuleDto> moduleList(int parentModuleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.module_id as moduleId, a.name as moduleName, a.parent_module_id as parentModuleId  ");
			sql.append(" FROM sys_module a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.parent_module_id="+parentModuleId+" ");
			sql.append(" order by a.module_id asc ");
			logger.info("ModuleDao.moduleList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationModuleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//批量新增角色模块关联
	public boolean addRoleModList(final List<RelationModuleReq> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_role_module (role_id,module_id,data_status,create_time,update_time ");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?)");
			logger.info("ModuleDao.addRoleModList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
	            	 ps.setInt(1, list.get(i).getDataId());
			         ps.setInt(2, list.get(i).getModuleId());
			         ps.setInt(3, Constants.DATA_STATUS_NORMAL);
			         ps.setString(4, date);
			         ps.setString(5, date);
	               }
	               public int getBatchSize()
	               {
	                return list.size();
	               }
	        });
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//物理删除角色模块关联
	public void delRoleMod(int roleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM sys_role_module WHERE role_id=" + roleId);
			logger.info("ModuleDao.delRoleMod sql = " + sql.toString());
			jdbcTemplate.execute(sql.toString());
		}catch(Exception e){
//			e.printStackTrace();
			return ;
		}
	}
	
	//角色模块列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationModuleDto> roleModuleList(int roleId, int parentModuleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.module_id as moduleId, b.name as moduleName, b.parent_module_id as parentModuleId  ");
			sql.append(" FROM sys_role_module a,sys_module b ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" AND b.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.module_id=b.module_id ");
			sql.append(" and a.role_id="+roleId+" ");
			sql.append(" and b.parent_module_id="+parentModuleId+" ");
			logger.info("ModuleDao.roleModuleList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationModuleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//角色模块列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationModuleDto> roleModuleList2(int roleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.module_id as moduleId, b.name as moduleName, b.parent_module_id as parentModuleId  ");
			sql.append(" FROM sys_role_module a,sys_module b ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" AND b.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.module_id=b.module_id ");
			sql.append(" and a.role_id="+roleId+" ");
//			sql.append(" and b.parent_module_id="+parentModuleId+" ");
			logger.info("ModuleDao.roleModuleList2 sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationModuleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//批量新增人员模块关联
	public boolean addUserModList(final List<RelationModuleReq> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_user_module (user_id,module_id,data_status,create_time,update_time ");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?)");
			logger.info("ModuleDao.addUserModList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
	            	 ps.setInt(1, list.get(i).getDataId());
			         ps.setInt(2, list.get(i).getModuleId());
			         ps.setInt(3, Constants.DATA_STATUS_NORMAL);
			         ps.setString(4, date);
			         ps.setString(5, date);
	               }
	               public int getBatchSize()
	               {
	                return list.size();
	               }
	        });
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//物理删除人员模块关联
	public void delUserMod(int userId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM sys_user_module WHERE user_id=" + userId);
			logger.info("ModuleDao.delUserMod sql = " + sql.toString());
			jdbcTemplate.execute(sql.toString());
		}catch(Exception e){
//				e.printStackTrace();
			return ;
		}
	}
	
	//人员模块列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationModuleDto> userModuleList(int userId, int parentModuleId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.module_id as moduleId, b.name as moduleName, b.parent_module_id as parentModuleId  ");
			sql.append(" FROM sys_user_module a,sys_module b ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" AND b.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.module_id=b.module_id ");
			sql.append(" and a.user_id="+userId+" ");
			sql.append(" and b.parent_module_id="+parentModuleId+" ");
			logger.info("ModuleDao.userModuleList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationModuleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//人员模块列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RelationModuleDto> userModuleList2(int userId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.module_id as moduleId, b.name as moduleName, b.parent_module_id as parentModuleId  ");
			sql.append(" FROM sys_user_module a,sys_module b ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" AND b.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.module_id=b.module_id ");
			sql.append(" and a.user_id="+userId+" ");
			logger.info("ModuleDao.userModuleList2 sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationModuleDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//批量新增人员数据权限
	public boolean addUserDataList(final List<RelationModuleReq> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_user_data (user_id,data_id,data_type,data_status,create_time,update_time ");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?)");
			logger.info("ModuleDao.addUserDataList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
	            	 ps.setInt(1, list.get(i).getDataId());
	            	 ps.setInt(2, list.get(i).getModuleId());
			         ps.setString(3, "");
			         ps.setInt(4, Constants.DATA_STATUS_NORMAL);
			         ps.setString(5, date);
			         ps.setString(6, date);
	               }
	               public int getBatchSize()
	               {
	                return list.size();
	               }
	        });
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//物理删除人员数据权限
	public void delUserData(int userId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM sys_user_data WHERE user_id=" + userId);
			logger.info("ModuleDao.delUserData sql = " + sql.toString());
			jdbcTemplate.execute(sql.toString());
		}catch(Exception e){
//					e.printStackTrace();
			return ;
		}
	}
	
	//人员数据权限串
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String userDataStr(int userId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.data_id  ");
			sql.append(" FROM sys_user_data a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.user_id="+userId+" ");
			logger.info("ModuleDao.userDataStr sql = " + sql.toString());
			List<UserDataDto> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(UserDataDto.class));
			String str = "";
			if(list!=null && list.size()>0){
				for (UserDataDto bean : list) {
					str = str+bean.getDataId()+",";
				}
				if(str.length()>0){
					str = str.substring(0, str.length()-1);
				}
			}
			return str;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//用户是否拥有权限
	public int hasModule(int userId, String moduleCode){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) ");
			sql.append(" from sys_user_module a, sys_module b ");
			sql.append(" where a.data_status!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" and a.module_id=b.module_id ");
			sql.append(" and a.user_id="+userId+" ");
			sql.append(" and b.module_code='"+moduleCode+"' ");
			logger.info("ModuleDao.hasModule sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
