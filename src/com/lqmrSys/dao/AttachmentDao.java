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
import com.lqmrSys.bean.dto.AttachmentDto;
import com.lqmrSys.bean.pagination.AttachmentDataTablesReq;
import com.lqmrSys.bean.req.AttachmentReq;
@Component
public class AttachmentDao {
	private static Logger logger = Logger.getLogger(AttachmentDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增附件
	public int addAttachment(final AttachmentReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_attachment (data_id,data_from,file_path,remark,data_status,create_time,update_time,operator_id,operator_name,attachment_type,parent_attachment_id,attachment_name ");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("AttachmentDao.addAttachment sql = " + sql.toString());
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
			         ps.setString(3, req.getFilePath());
			         ps.setString(4, req.getRemark());
			         ps.setInt(5, Constants.DATA_STATUS_NORMAL);
			         ps.setString(6, date);
			         ps.setString(7, date);
			         ps.setInt(8, req.getOperatorId());
			         ps.setString(9, req.getOperatorName());
			         ps.setString(10, req.getAttachmentType());
			         ps.setInt(11, req.getParentAttachmentId());
			         ps.setString(12, req.getAttachmentName());
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
	
	//修改附件
	public int modifyAttachment(AttachmentReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_attachment set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getFilePath()!=null && !"".equals(req.getFilePath())){
				sql.append(" ,file_path='"+req.getFilePath()+"' ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getAttachmentType()!=null && !"".equals(req.getAttachmentType())){
				sql.append(" ,attachment_type='"+req.getAttachmentType()+"' ");
			}
			if(req.getParentAttachmentId()!=0){
				sql.append(" ,parent_attachment_id="+req.getParentAttachmentId()+" ");
			}
			if(req.getAttachmentName()!=null && !"".equals(req.getAttachmentName())){
				sql.append(" ,attachment_name='"+req.getAttachmentName()+"' ");
			}
			sql.append(" where attachment_id="+req.getAttachmentId());
			logger.info("AttachmentDao.modifyAttachment sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除附件
	public int delAttachment(int attachmentId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_attachment set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+", operator_name='"+operatorName+"' ");
			sql.append(" where attachment_id="+attachmentId);
			logger.info("AttachmentDao.delAttachment sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//附件信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AttachmentDto attachmentDetailById(int attachmentId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_attachment a ");
			sql.append(" WHERE a.attachment_id="+attachmentId);
			logger.info("AttachmentDao.attachmentDetailById sql = " + sql.toString());
			return (AttachmentDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(AttachmentDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//附件列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<AttachmentDto> attachmentList(AttachmentDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_attachment a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.data_id="+req.getDataId()+" ");
			sql.append(" and a.data_from='"+req.getDataFrom()+"' ");
			//筛选条件
			if(req.getOperatorName()!=null && !"".equals(req.getOperatorName())){
				sql.append(" and a.operator_name like '%"+req.getOperatorName()+"%' ");
			}
			if(req.getAttachmentType()!=null && !"".equals(req.getAttachmentType())){
				sql.append(" ,and a.attachment_type like '%"+req.getAttachmentType()+"%' ");
			}
			if(req.getParentAttachmentId()!=0){
				sql.append(" ,and a.parent_attachment_id="+req.getParentAttachmentId()+" ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("AttachmentDao.attachmentList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(AttachmentDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//附件列表长度
	public int attachmentCount(AttachmentDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.attachment_id) ");
			sql.append(" FROM sys_attachment a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.data_id="+req.getDataId()+" ");
			sql.append(" and a.data_from='"+req.getDataFrom()+"' ");
			//筛选条件
			if(req.getOperatorName()!=null && !"".equals(req.getOperatorName())){
				sql.append(" and a.operator_name like '%"+req.getOperatorName()+"%' ");
			}
			if(req.getAttachmentType()!=null && !"".equals(req.getAttachmentType())){
				sql.append(" ,and a.attachment_type like '%"+req.getAttachmentType()+"%' ");
			}
			if(req.getParentAttachmentId()!=0){
				sql.append(" ,and a.parent_attachment_id="+req.getParentAttachmentId()+" ");
			}
			logger.info("AttachmentDao.attachmentCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
