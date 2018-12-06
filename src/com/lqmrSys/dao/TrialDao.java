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
import com.lqmrSys.bean.dto.TrialDto;
import com.lqmrSys.bean.pagination.TrialDataTablesReq;
import com.lqmrSys.bean.req.TrialReq;

@Component
public class TrialDao {
	private static Logger logger = Logger.getLogger(TrialDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//批量新增角色模块关联
	public boolean addTrialList(final List<TrialReq> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_trial (user_id,user_name,trial_type,trial_status,node,trial_node,end_node,remark,data_status,create_time,update_time,operator_id,operator_name,trial_data_id");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("TrialDao.addTrialList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			       	 ps.setInt(1, list.get(i).getUserId());
			         ps.setString(2, list.get(i).getUserName());
			         ps.setString(3, list.get(i).getTrialType());
			         ps.setInt(4, 0);
			         ps.setInt(5, list.get(i).getNode());
			         ps.setInt(6, list.get(i).getTrialNode());
			         ps.setInt(7, list.get(i).getEndNode());
			         ps.setString(8, list.get(i).getRemark());
			         ps.setInt(9, Constants.DATA_STATUS_NORMAL);
			         ps.setString(10, date);
			         ps.setString(11, date);
			         ps.setInt(12, list.get(i).getOperatorId());
			         ps.setString(13, list.get(i).getOperatorName());
			         ps.setInt(14, list.get(i).getTrialDataId());
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
	
	//逻辑删除所有审核-审批节点
	public int delTrial(String trialType, int trialDataId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_trial set update_time='"+date+"',data_status="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where trial_type='"+trialType+"' and trial_data_id="+trialDataId);
			logger.info("TrialDao.delTrial sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改审核-审批节点状态
	public int modifyTrialStatus(TrialReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_trial set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			sql.append(" ,trial_status="+req.getTrialStatus()+" ");
			sql.append(" ,remark='"+req.getRemark()+"'");
			sql.append(" where trial_id="+req.getTrialId());
			logger.info("TrialDao.modifyTrialStatus sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改审核-审批节点
	public int modifyNode(String trialType, int trialDataId, int operatorId, String operatorName, int node){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_trial set update_time='"+date+"' ");
//			sql.append(" ,operator_id="+operatorId+" ");
//			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" ,node="+node+" ");
			sql.append(" where trial_type='"+trialType+"' and trial_data_id="+trialDataId);
			sql.append(" and data_status!="+Constants.DATA_STATUS_DELETE);
			logger.info("TrialDao.modifyNode sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//审核-审批节点信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TrialDto trialDetail(int trialId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_trial a ");
			sql.append(" where a.trial_id="+trialId);
			logger.info("TrialDao.trialDetail sql = " + sql.toString());
			return (TrialDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(TrialDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//审核-审批节点列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TrialDto> trialList(TrialDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_trial a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and a.trial_status!=1 ");
			//筛选条件
			if(req.getUserId()!=0){
				sql.append(" and a.user_id="+req.getUserId()+" ");
			}
			if(req.getTrialDataId()!=0){
				sql.append(" and a.trial_data_id="+req.getTrialDataId()+" ");
			}
			if(req.getTrialType()!=null && !"".equals(req.getTrialType())){
				sql.append(" and a.trial_type like '%"+req.getTrialType()+"%' ");
			}
			sql.append(" ORDER BY a.trial_node ASC ");
			logger.info("TrialDao.trialList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(TrialDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
