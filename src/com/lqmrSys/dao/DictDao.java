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
import com.lqmrSys.bean.dto.DictDto;
import com.lqmrSys.bean.pagination.DictDataTablesReq;
import com.lqmrSys.bean.req.DictReq;

@Component
public class DictDao {
	private static Logger logger = Logger.getLogger(DictDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增数据字典
	public int addDict(final DictReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_dict (type_code,dict_code,content,parent_dict_id,remark,data_order,data_status,create_time,update_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?)");
			logger.info("DictDao.addDict sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getTypeCode());
			         ps.setString(2, req.getDictCode());
			         ps.setString(3, req.getContent());
			         ps.setInt(4, req.getParentDictId());
			         ps.setString(5, req.getRemark());
			         ps.setInt(6, req.getDataOrder());
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
	
	//修改数据字典
	public int modifyDict(DictReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_dict set update_time='"+date+"' ");
			if(req.getTypeCode()!=null && !"".equals(req.getTypeCode())){
				sql.append(" ,type_code='"+req.getTypeCode()+"' ");
			}
			if(req.getContent()!=null && !"".equals(req.getContent())){
				sql.append(" ,content='"+req.getContent()+"' ");
			}
			if(req.getParentDictId()!=0){
				sql.append(" ,parent_dict_id="+req.getParentDictId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getDataOrder()!=0){
				sql.append(" ,data_order="+req.getDataOrder()+" ");
			}
			if(req.getDictCode()!=null && !"".equals(req.getDictCode())){
				sql.append(" ,dict_code='"+req.getDictCode()+"' ");
			}
			sql.append(" where dict_id="+req.getDictId());
			logger.info("DictDao.modifyDict sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除数据字典
	public int delDict(int dictId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_dict set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where dict_id="+dictId);
			logger.info("DictDao.delDict sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//数据字典信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DictDto dictDetailById(int dictId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dict a ");
			sql.append(" WHERE a.dict_id="+dictId);
			logger.info("DictDao.dictDetailById sql = " + sql.toString());
			return (DictDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(DictDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//数据字典列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DictDto> dictList(DictDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dict a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getTypeCode()!=null && !"".equals(req.getTypeCode())){
				sql.append(" and a.type_code like '%"+req.getTypeCode()+"%' ");
			}
			if(req.getContent()!=null && !"".equals(req.getContent())){
				sql.append(" and a.content like '%"+req.getContent()+"%' ");
			}
			if(req.getDictCode()!=null && !"".equals(req.getDictCode())){
				sql.append(" and a.dict_code like '%"+req.getDictCode()+"%' ");
			}
			sql.append(" ORDER BY a.data_order DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("DictDao.dictList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(DictDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//数据字典列表长度
	public int dictCount(DictDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.dict_id) ");
			sql.append(" FROM sys_dict a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getTypeCode()!=null && !"".equals(req.getTypeCode())){
				sql.append(" and a.type_code like '%"+req.getTypeCode()+"%' ");
			}
			if(req.getContent()!=null && !"".equals(req.getContent())){
				sql.append(" and a.content like '%"+req.getContent()+"%' ");
			}
			if(req.getDictCode()!=null && !"".equals(req.getDictCode())){
				sql.append(" and a.dict_code like '%"+req.getDictCode()+"%' ");
			}
			logger.info("DictDao.dictCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//数据字典列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DictDto> dictListByDictCodeAndParentId(String dictCode, int parentDictId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dict a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.type_code='dict-content' ");
			sql.append(" and a.dict_code='"+dictCode+"' and a.parent_dict_id="+parentDictId+" ");
			sql.append(" ORDER BY a.data_order DESC ");
			logger.info("DictDao.dictListByDictCodeAndParentId sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(DictDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//数据字典-系统参数
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DictDto dictData(String dictCode){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_dict a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.type_code='dict-data' ");
			sql.append(" and a.dict_code='"+dictCode+"' ");
			logger.info("DictDao.dictListByDictCodeAndParentId sql = " + sql.toString());
			return (DictDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(DictDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
