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
import com.lqmrSys.bean.dto.UserDto;
import com.lqmrSys.bean.pagination.UserDataTablesReq;
import com.lqmrSys.bean.req.UserReq;

@Component
public class UserDao {
	private static Logger logger = Logger.getLogger(UserDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增人员
	public int addUser(final UserReq req, final String account, final String pwd){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_user (account,pwd,name,sex,idcard_num,birthday,native_place,home_address,contact_num,phone_num,email,dept_id,role_id,remark,data_status,create_time,update_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("UserDao.addUser sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, account);
			         ps.setString(2, pwd);
			         ps.setString(3, req.getName());
			         ps.setString(4, req.getSex());
			         ps.setString(5, req.getIdcardNum());
			         ps.setString(6, req.getBirthday());
			         ps.setString(7, req.getNativePlace());
			         ps.setString(8, req.getHomeAddress());
			         ps.setString(9, req.getContactNum());
			         ps.setString(10, req.getPhoneNum());
			         ps.setString(11, req.getEmail());
			         ps.setInt(12, req.getDeptId());
			         ps.setInt(13, req.getRoleId());
			         ps.setString(14, req.getRemark());
			         ps.setInt(15, Constants.DATA_STATUS_NORMAL);
			         ps.setString(16, date);
			         ps.setString(17, date);
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
	
	//修改人员信息
	public int modifyUser(UserReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_user set update_time='"+date+"' ");
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" ,name='"+req.getName()+"' ");
			}
			if(req.getSex()!=null && !"".equals(req.getSex())){
				sql.append(" ,sex='"+req.getSex()+"' ");
			}
			if(req.getIdcardNum()!=null && !"".equals(req.getIdcardNum())){
				sql.append(" ,idcard_num='"+req.getIdcardNum()+"' ");
			}
			if(req.getBirthday()!=null && !"".equals(req.getBirthday())){
				sql.append(" ,birthday='"+req.getBirthday()+"' ");
			}
			if(req.getNativePlace()!=null && !"".equals(req.getNativePlace())){
				sql.append(" ,native_place='"+req.getNativePlace()+"' ");
			}
			if(req.getHomeAddress()!=null && !"".equals(req.getHomeAddress())){
				sql.append(" ,home_address='"+req.getHomeAddress()+"' ");
			}
			if(req.getContactNum()!=null && !"".equals(req.getContactNum())){
				sql.append(" ,contact_num='"+req.getContactNum()+"' ");
			}
			if(req.getPhoneNum()!=null && !"".equals(req.getPhoneNum())){
				sql.append(" ,phone_num='"+req.getPhoneNum()+"' ");
			}
			if(req.getEmail()!=null && !"".equals(req.getEmail())){
				sql.append(" ,email='"+req.getEmail()+"' ");
			}
			if(req.getDeptId()!=0){
				sql.append(" ,dept_id="+req.getDeptId()+" ");
			}
			if(req.getRoleId()!=0){
				sql.append(" ,role_id="+req.getRoleId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where user_id="+req.getUserId());
			logger.info("UserDao.modifyUser sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除用户信息
	public int delUser(int userId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_user set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where user_id='"+userId+"'");
			logger.info("UserDao.delUser sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//重置用户密码
	public int resetUserPwd(int userId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_user set update_time='"+date+"', pwd='123456'");
			sql.append(" where user_id='"+userId+"'");
			logger.info("UserDao.delUser sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//人员信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserDto userDetail(int userId, String account){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, b.name as deptName, c.name as roleName ");
			sql.append(" FROM sys_user a ");
			sql.append(" left join sys_dept b on b.dept_id=a.dept_id ");
			sql.append(" left join sys_role c on c.role_id=a.role_id ");
			sql.append(" WHERE 1=1 ");
			if(userId!=0){
				sql.append(" AND a.user_id="+userId+" ");
			}
			if(account!=null && !"".equals(account)){
				sql.append(" AND a.account='"+account+"' ");
			}
			logger.info("UserDao.userDetail sql = " + sql.toString());
			return (UserDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(UserDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//人员列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserDto> userList(UserDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, b.name as deptName, c.name as roleName ");
			sql.append(" FROM sys_user a ");
			sql.append(" left join sys_dept b on b.dept_id=a.dept_id ");
			sql.append(" left join sys_role c on c.role_id=a.role_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" and a.name!='admin' ");
			//筛选条件
			if(req.getAccount()!=null && !"".equals(req.getAccount())){
				sql.append(" and a.account like '%"+req.getAccount()+"%' ");
			}
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			if(req.getDeptId()!=0){
				sql.append(" and a.dept_id="+req.getDeptId()+" ");
			}
			if(req.getRoleId()!=0){
				sql.append(" and a.role_id="+req.getRoleId()+" ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("UserDao.userList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(UserDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//人员列表长度
	public int userCount(UserDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.user_id) ");
			sql.append(" FROM sys_user a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE+" and a.name!='admin' ");
			//筛选条件
			if(req.getAccount()!=null && !"".equals(req.getAccount())){
				sql.append(" and a.account like '%"+req.getAccount()+"%' ");
			}
			if(req.getName()!=null && !"".equals(req.getName())){
				sql.append(" and a.name like '%"+req.getName()+"%' ");
			}
			if(req.getDeptId()!=0){
				sql.append(" and a.dept_id="+req.getDeptId()+" ");
			}
			if(req.getRoleId()!=0){
				sql.append(" and a.role_id="+req.getRoleId()+" ");
			}
			logger.info("UserDao.userCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//所有人员数量
	public int allUserNum(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.user_id) ");
			sql.append(" FROM sys_user a ");
			sql.append(" WHERE a.name!='admin' ");
			logger.info("UserDao.allUserNum sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改用户密码
	public int modifyUserPwd(int userId, String pwd){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_user set update_time='"+date+"', pwd='"+pwd+"'");
			sql.append(" where user_id='"+userId+"'");
			logger.info("UserDao.modifyUserPwd sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
