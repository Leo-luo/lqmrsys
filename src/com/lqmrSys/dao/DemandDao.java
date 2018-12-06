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
import com.lqmrSys.bean.dto.DemandDto;
import com.lqmrSys.bean.pagination.DemandDataTablesReq;
import com.lqmrSys.bean.req.DemandReq;

@Component
public class DemandDao {
	private static Logger logger = Logger.getLogger(DemandDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增需求方
	public int addDemand(final DemandReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_demand (demand_code,financing_type,demander_id,demander_type,repayment_type,demand_amount,interest_settlement_cycle,start_time,end_time,effective_end_time,interest_rate,reach_account_time,loan_term,usera_id,userb_id,card_id,remark,data_status,create_time,update_time,demand_status,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("DemandDao.addDemand sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getDemandCode());
			         ps.setString(2, req.getFinancingType());
			         ps.setInt(3, req.getDemanderId());
			         ps.setString(4, req.getDemanderType());
			         ps.setString(5, req.getRepaymentType());
			         ps.setDouble(6, req.getDemandAmount());
			         ps.setString(7, req.getInterestSettlementCycle());
			         ps.setString(8, req.getStartTime());
			         ps.setString(9, req.getEndTime());
			         ps.setString(10, req.getEffectiveEndTime());
			         ps.setDouble(11, req.getInterestRate());
			         ps.setString(12, req.getReachAccountTime());
			         ps.setInt(13, req.getLoanTerm());
			         ps.setInt(14, req.getUseraId());
			         ps.setInt(15, req.getUserbId());
			         ps.setInt(16, req.getCardId());
			         ps.setString(17, req.getRemark());
			         ps.setInt(18, Constants.DATA_STATUS_NORMAL);
			         ps.setString(19, date);
			         ps.setString(20, date);
			         ps.setInt(21, Constants.DATA_STATUS_NORMAL);
			         ps.setInt(22, req.getOperatorId());
			         ps.setString(23, req.getOperatorName());
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
	
	//修改需求方
	public int modifyDemand(DemandReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_demand set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" ,financing_type='"+req.getFinancingType()+"' ");
			}
			if(req.getDemanderId()!=0){
				sql.append(" ,demander_id="+req.getDemanderId()+" ");
			}
			if(req.getDemanderType()!=null && !"".equals(req.getDemanderType())){
				sql.append(" ,demander_type='"+req.getDemanderType()+"' ");
			}
			if(req.getRepaymentType()!=null && !"".equals(req.getRepaymentType())){
				sql.append(" ,repayment_type='"+req.getRepaymentType()+"' ");
			}
			if(req.getDemandAmount()!=0){
				sql.append(" ,demand_amount="+req.getDemandAmount()+" ");
			}
			if(req.getInterestSettlementCycle()!=null && !"".equals(req.getInterestSettlementCycle())){
				sql.append(" ,interest_settlement_cycle='"+req.getInterestSettlementCycle()+"' ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime())){
				sql.append(" ,start_time='"+req.getStartTime()+"' ");
			}
			if(req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" ,end_time='"+req.getEndTime()+"' ");
			}
			if(req.getEffectiveEndTime()!=null && !"".equals(req.getEffectiveEndTime())){
				sql.append(" ,effective_end_time='"+req.getEffectiveEndTime()+"' ");
			}
			if(req.getInterestRate()!=0){
				sql.append(" ,interest_rate="+req.getInterestRate()+" ");
			}
			if(req.getReachAccountTime()!=null && !"".equals(req.getReachAccountTime())){
				sql.append(" ,reach_account_time='"+req.getReachAccountTime()+"' ");
			}
			if(req.getLoanTerm()!=0){
				sql.append(" ,loan_term="+req.getLoanTerm()+" ");
			}
			if(req.getUseraId()!=0){
				sql.append(" ,usera_id="+req.getUseraId()+" ");
			}
			if(req.getUserbId()!=0){
				sql.append(" ,userb_id="+req.getUserbId()+" ");
			}
			if(req.getCardId()!=0){
				sql.append(" ,card_id="+req.getCardId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where demand_id="+req.getDemandId());
			logger.info("DemandDao.modifyDemand sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除需求方
	public int delDemand(int demandId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_demand set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where demand_id="+demandId);
			logger.info("DemandDao.delDemand sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//需求方信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DemandDto demandDetailById(int demandId){
		try{
			StringBuffer sql = new StringBuffer();
//			sql.append("SELECT a.*, c.name as useraName, d.name as userbName ");
//			if(Constants.SYS_DEMAND_COMPANY.equals(demanderType)){
//				sql.append(" ,b.company_name as demanderName");
//			}else if(Constants.SYS_DEMAND_CUSTOMER.equals(demanderType)){
//				sql.append(" ,b.name as demanderName");
//			}
//			sql.append(" FROM sys_demand a ");
//			if(Constants.SYS_DEMAND_COMPANY.equals(demanderType)){
//				sql.append(" left jojn sys_company b on b.company_id=a.demander_id ");
//			}else if(Constants.SYS_DEMAND_CUSTOMER.equals(demanderType)){
//				sql.append(" left jojn sys_customer b on b.customer_id=a.demander_id ");
//			}
//			sql.append(" left join sys_user c on c.user_id=a.usera_id ");
//			sql.append(" left join sys_user d on d.user_id=a.userb_id ");
//			sql.append(" WHERE a.demand_id="+demandId);
			
			sql.append("SELECT a.*, c.name as useraName, d.name as userbName, b.company_name as demanderName ");
			sql.append("FROM sys_demand a ");
			sql.append("left join sys_company b on b.company_id=a.demander_id ");
			sql.append("left join sys_user c on c.user_id=a.usera_id ");
			sql.append("left join sys_user d on d.user_id=a.userb_id ");
			sql.append("WHERE a.demand_id="+demandId+" and a.demander_type='sys_company' ");
			sql.append("union ");
			sql.append("SELECT a.*, c.name as useraName, d.name as userbName, b.name as demanderName ");
			sql.append("FROM sys_demand a ");
			sql.append("left join sys_customer b on b.customer_id=a.demander_id ");
			sql.append("left join sys_user c on c.user_id=a.usera_id ");
			sql.append("left join sys_user d on d.user_id=a.userb_id ");
			sql.append("WHERE a.demand_id="+demandId+" and a.demander_type='sys_customer' ");
			logger.info("DemandDao.demandDetailById sql = " + sql.toString());
			return (DemandDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(DemandDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//需求方列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DemandDto> demandList(DemandDataTablesReq req){
		try{
			String customerNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.demander_id)";
			String companyNameSql = "(select com.company_name from sys_company com where com.company_id=a.demander_id)";
			String riskSignSql = "(select rs.sign_id from sys_risk_sign rs where rs.data_from='sys_demand' and rs.data_id=a.demand_id and rs.data_status!="+Constants.DATA_STATUS_DELETE+")";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select total.* from(");
			sql.append(" SELECT a.*, c.name as useraName, d.name as userbName ");
			sql.append(" ,(case when a.demander_type='sys_customer' then "+customerNameSql+" else "+companyNameSql+" end) as demanderName ");
			sql.append(" ,(case when "+riskSignSql+"!=0 then "+riskSignSql+" else 0 end) as signId ");
			sql.append("FROM sys_demand a ");
			sql.append("left join sys_user c on c.user_id=a.usera_id ");
			sql.append("left join sys_user d on d.user_id=a.userb_id ");
			sql.append("WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and (c.name like '%"+req.getUserName()+"%' or d.name like '%"+req.getUserName()+"%') ");
			}
			if(req.getDemandCode()!=null && !"".equals(req.getDemandCode())){
				sql.append(" and a.demand_code like '%"+req.getDemandCode()+"%' ");
			}
			sql.append(") total ");
			if(req.getDemanderName()!=null && !"".equals(req.getDemanderName())){
				sql.append(" and total.demanderName like '%"+req.getDemanderName()+"%' ");
			}
			
			sql.append(" ORDER BY total.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("DemandDao.demandList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(DemandDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//需求方列表长度
	public int demandCount(DemandDataTablesReq req){
		try{
			String customerNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.demander_id)";
			String companyNameSql = "(select com.company_name from sys_company com where com.company_id=a.demander_id)";
			String riskSignSql = "(select rs.sign_id from sys_risk_sign rs where rs.data_from='sys_demand' and rs.data_id=a.demand_id and rs.data_status!="+Constants.DATA_STATUS_DELETE+")";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from(");
			sql.append(" SELECT a.*, c.name as useraName, d.name as userbName ");
			sql.append(" ,(case when a.demander_type='sys_customer' then "+customerNameSql+" else "+companyNameSql+" end) as demanderName ");
			sql.append(" ,(case when "+riskSignSql+"!=0 then "+riskSignSql+" else 0 end) as signId ");
			sql.append("FROM sys_demand a ");
			sql.append("left join sys_user c on c.user_id=a.usera_id ");
			sql.append("left join sys_user d on d.user_id=a.userb_id ");
			sql.append("WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and (c.name like '%"+req.getUserName()+"%' or d.name like '%"+req.getUserName()+"%') ");
			}
			if(req.getDemandCode()!=null && !"".equals(req.getDemandCode())){
				sql.append(" and a.demand_code like '%"+req.getDemandCode()+"%' ");
			}
			sql.append(") total ");
			if(req.getDemanderName()!=null && !"".equals(req.getDemanderName())){
				sql.append(" and total.demanderName like '%"+req.getDemanderName()+"%' ");
			}
			logger.info("DemandDao.demandCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//需求方列表长度
	public int demandAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.demand_id) ");
			sql.append(" FROM sys_demand a ");
			logger.info("DemandDao.demandAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改需求方状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废
	public int modifyDemandStatus(int demandId, int demandStatus){
		StringBuffer sql = new StringBuffer();
		try{
			sql.append("update sys_demand set demand_status="+demandStatus+" ");
			sql.append(" where demand_id="+demandId);
			logger.info("DemandDao.modifyDemand sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
