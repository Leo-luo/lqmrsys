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
import com.lqmrSys.bean.RepaymentBean;
import com.lqmrSys.bean.req.RepaymentReq;

@Component
public class RepaymentDao {
	private static Logger logger = Logger.getLogger(RepaymentDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增还款计划
	public int addRepayment(final RepaymentBean req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_repayment (financing_matching_id,loan_receipt_id,plan_start_time,plan_end_time,plan_principal_amount,plan_interest_amount,plan_service_amount,interest_rate,service_rate,actual_principal_amount,actual_interest_amount,actual_service_amount,pay_time,pay_status,data_status,create_time,update_time,operator_id,operator_name,repayment_type");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("RepaymentDao.addRepayment sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setInt(1, req.getFinancingMatchingId());
			         ps.setInt(2, req.getLoanReceiptId());
			         ps.setString(3, req.getPlanStartTime());
			         ps.setString(4, req.getPlanEndTime());
			         ps.setDouble(5, req.getPlanPrincipalAmount());
			         ps.setDouble(6, req.getPlanInterestAmount());
			         ps.setDouble(7, req.getPlanServiceAmount());
			         ps.setDouble(8, req.getInterestRate());
			         ps.setDouble(9, req.getServiceRate());
			         ps.setDouble(10, req.getActualPrincipalAmount());
			         ps.setDouble(11, req.getActualInterestAmount());
			         ps.setDouble(12, req.getActualServiceAmount());
			         ps.setString(13, req.getPayTime());
			         ps.setInt(14, Constants.DATA_STATUS_NORMAL);
			         ps.setInt(15, Constants.DATA_STATUS_NORMAL);
			         ps.setString(16, date);
			         ps.setString(17, date);
			         ps.setInt(18, req.getOperatorId());
			         ps.setString(19, req.getOperatorName());
			         ps.setString(20, req.getRepaymentType());
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
	
	//修改还款计划
	public int modifyRepayment(RepaymentReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	       	String date = sdf.format(new Date());
	       	String date2 = sdf2.format(new Date());
			sql.append("update sys_repayment set update_time='"+date+"',pay_time='"+date2+"' "+",pay_status="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			sql.append(" ,actual_principal_amount="+req.getActualPrincipalAmount()+" ");
			sql.append(" ,actual_interest_amount="+req.getActualInterestAmount()+" ");
			sql.append(" ,actual_service_amount="+req.getActualServiceAmount()+" ");
			sql.append(" where repayment_id="+req.getRepaymentId());
			logger.info("RepaymentDao.modifyRepayment sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//还款计划列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RepaymentBean> repaymentList(int financingMatchingId, int loanReceiptId, String repaymentType){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_repayment a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(financingMatchingId!=0){
				sql.append(" and a.financing_matching_id="+financingMatchingId+" ");
			}
			if(financingMatchingId!=0){
				sql.append(" and a.loan_receipt_id="+loanReceiptId+" ");
			}
			if(repaymentType!=null && !"".equals(repaymentType)){
				sql.append(" and a.repayment_type like '%"+repaymentType+"%' ");
			}
			sql.append(" ORDER BY a.plan_start_time ASC ");
			logger.info("CompanyDao.companyList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RepaymentBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
