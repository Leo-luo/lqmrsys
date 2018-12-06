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
import com.lqmrSys.bean.LoanReceiptBean;
import com.lqmrSys.bean.dto.LoanReceiptDto;
import com.lqmrSys.bean.dto.LoanReceiptDtob;
import com.lqmrSys.bean.pagination.LoanReceiptDataTablesReq;
@Component
public class LoanReceiptDao {
	private static Logger logger = Logger.getLogger(LoanReceiptDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增借据
	public int addLoanReceipt(final LoanReceiptBean req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_loan_receipt (loan_receipt_code,financing_matching_id,remark,data_status,create_time,update_time,operator_id,operator_name");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?)");
			logger.info("LoadReceiptDao.addLoanReceipt sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getLoanReceiptCode());
			         ps.setInt(2, req.getFinancingMatchingId());
			         ps.setString(3, req.getRemark());
			         ps.setInt(4, Constants.DATA_STATUS_NORMAL);
			         ps.setString(5, date);
			         ps.setString(6, date);
			         ps.setInt(7, req.getOperatorId());
			         ps.setString(8, req.getOperatorName());
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
	
	//借据列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LoanReceiptDto> loanReceiptList(LoanReceiptDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ab.*,d.demandName,d.credentialsNum FROM ( ");
			sql.append(" SELECT ");
			sql.append(" a.create_time, a.`loan_receipt_id`, a.`load_receipt_code`,c.`contract_id`,c.`contract_code`,a.`financing_matching_id`,b.`matching_code`,b.`demand_id`,b.`financing_type`,b.`register_date`,b.`start_time`,b.`end_time`,b.`financing_matching_status` ");
			sql.append(" FROM sys_loan_receipt a ");
			sql.append(" LEFT JOIN sys_financing_matching b ON b.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append(" LEFT JOIN sys_financing_matching_contract c ON c.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append(" WHERE a.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			//筛选条件
			if(req.getLoadReceiptCode()!=null && !"".equals(req.getLoadReceiptCode())){
				sql.append(" and a.load_receipt_code like '%"+req.getLoadReceiptCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and c.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and b.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" and b.financing_type like '%"+req.getFinancingType()+"%' ");
			}
			if(req.getFinancingMatchingStatus()!=0){
				sql.append(" and b.financing_matching_status ="+req.getFinancingMatchingStatus()+" ");
			}
			sql.append(" )ab ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT ");
			sql.append(" a.`demand_id`,b.`corporation_name` AS demandName, b.`corporation_idcard` AS credentialsNum ");
			sql.append(" FROM sys_demand a,sys_company b ");
			sql.append(" WHERE a.`demander_id`=b.`company_id` AND a.`demander_type`='sys_company' AND a.`data_status`!="+Constants.DATA_STATUS_DELETE+" AND b.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" UNION ");
			sql.append(" SELECT ");
			sql.append(" a.`demand_id`,b.name AS demandName, b.credentials_num AS credentialsNum ");
			sql.append(" FROM sys_demand a,sys_customer b ");
			sql.append(" WHERE a.`demander_id`=b.`customer_id` AND a.`demander_type`='sys_company' AND a.`data_status`!="+Constants.DATA_STATUS_DELETE+" AND b.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" ) d ON d.demand_id=ab.demand_id ");
			sql.append(" WHERE 1=1 ");
			//筛选条件
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" and d.demandName like '%"+req.getDemandName()+"%' ");
			}
			if(req.getCredentialsNum()!=null && !"".equals(req.getCredentialsNum())){
				sql.append(" and d.credentialsNumlike '%"+req.getCredentialsNum()+"%' ");
			}
			sql.append(" ORDER BY ab.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("LoadReceiptDao.loanReceiptList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LoanReceiptDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//借据长度
	public int loanReceiptCount(LoanReceiptDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) FROM ( ");
			sql.append(" SELECT ");
			sql.append(" a.create_time, a.`loan_receipt_id`, a.`load_receipt_code`,c.`contract_id`,c.`contract_code`,a.`financing_matching_id`,b.`matching_code`,b.`demand_id`,b.`financing_type`,b.`register_date`,b.`start_time`,b.`end_time`,b.`financing_matching_status` ");
			sql.append(" FROM sys_loan_receipt a ");
			sql.append(" LEFT JOIN sys_financing_matching b ON b.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append(" LEFT JOIN sys_financing_matching_contract c ON c.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append(" WHERE a.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			//筛选条件
			if(req.getLoadReceiptCode()!=null && !"".equals(req.getLoadReceiptCode())){
				sql.append(" and a.load_receipt_code like '%"+req.getLoadReceiptCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and c.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and b.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" and b.financing_type like '%"+req.getFinancingType()+"%' ");
			}
			if(req.getFinancingMatchingStatus()!=0){
				sql.append(" and b.financing_matching_status ="+req.getFinancingMatchingStatus()+" ");
			}
			sql.append(" )ab ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT ");
			sql.append(" a.`demand_id`,b.`corporation_name` AS demandName, b.`corporation_idcard` AS credentialsNum ");
			sql.append(" FROM sys_demand a,sys_company b ");
			sql.append(" WHERE a.`demander_id`=b.`company_id` AND a.`demander_type`='sys_company' AND a.`data_status`!="+Constants.DATA_STATUS_DELETE+" AND b.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" UNION ");
			sql.append(" SELECT ");
			sql.append(" a.`demand_id`,b.name AS demandName, b.credentials_num AS credentialsNum ");
			sql.append(" FROM sys_demand a,sys_customer b ");
			sql.append(" WHERE a.`demander_id`=b.`customer_id` AND a.`demander_type`='sys_company' AND a.`data_status`!="+Constants.DATA_STATUS_DELETE+" AND b.`data_status`!="+Constants.DATA_STATUS_DELETE+" ");
			sql.append(" ) d ON d.demand_id=ab.demand_id ");
			sql.append(" WHERE 1=1 ");
			//筛选条件
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" and d.demandName like '%"+req.getDemandName()+"%' ");
			}
			if(req.getCredentialsNum()!=null && !"".equals(req.getCredentialsNum())){
				sql.append(" and d.credentialsNumlike '%"+req.getCredentialsNum()+"%' ");
			}
			logger.info("LoadReceiptDao.loanReceiptCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//借据总数据数
	public int loanReceiptAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(a.loan_receipt_id) ");
			sql.append(" FROM sys_loan_receipt a ");
			logger.info("LoadReceiptDao.loanReceiptAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//借款列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LoanReceiptDtob> loanReceiptList2(LoanReceiptDataTablesReq req){
		try{
			String balanceAmountSql = "(SELECT SUM(sr.actual_principal_amount) FROM sys_repayment sr WHERE sr.financing_matching_id=a.financing_matching_id AND sr.loan_receipt_id=a.`loan_receipt_id` AND sr.data_status!=3)";
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.`loan_receipt_id`, a.`loan_receipt_code`,a.`financing_matching_id`, b.`matching_code`, c.`contract_code`, c.`custom_contract_code`, de.demanderName, de.demanderIdcard, ");
			sql.append(" (CASE WHEN de.demander_id>0 THEN de.demander_id ELSE 0 END) as demanderId, ");
			sql.append(" (CASE WHEN de.demand_id>0 THEN de.demand_id ELSE 0 END) as demandId, ");
			sql.append(" (CASE WHEN c.`contract_id`>0 THEN c.`contract_id` ELSE 0 END) as contractId, ");
			sql.append(" (CASE WHEN b.`financing_amount`>0 THEN b.`financing_amount` ELSE 0 END) as financingAmount, ");
			sql.append(" b.`financing_amount`, ");
			sql.append(" (CASE WHEN "+balanceAmountSql+">0 THEN "+balanceAmountSql+" ELSE 0 END) AS balanceAmount, ");
			sql.append("b.`start_time`, b.`end_time`, b.`financing_type` ");
			sql.append("FROM sys_loan_receipt a ");
			sql.append("LEFT JOIN sys_financing_matching b ON b.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN sys_financing_matching_contract c ON c.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN( ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`corporation_name` AS demanderName, sc.`corporation_idcard` AS demanderIdcard FROM sys_demand sda, sys_company sc WHERE sda.`demander_type`='sys_company' AND sda.`demander_id`=sc.`company_id` ");
			sql.append(" UNION ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`name` AS demanderName, sc.`credentials_num` AS demanderIdcard FROM sys_demand sda, sys_customer sc WHERE sda.`demander_type`='sys_customer' AND sda.`demander_id`=sc.`customer_id` ");
			sql.append(" ) de ON de.demand_id = b.`demand_id` ");
			sql.append(" where 1=1 ");
			//筛选条件
			if(req.getLoadReceiptCode()!=null && !"".equals(req.getLoadReceiptCode())){
				sql.append(" and a.load_receipt_code like '%"+req.getLoadReceiptCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and c.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and b.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" and b.financing_type like '%"+req.getFinancingType()+"%' ");
			}
			if(req.getFinancingMatchingStatus()!=0){
				sql.append(" and b.financing_matching_status ="+req.getFinancingMatchingStatus()+" ");
			}
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" and de.demanderName like '%"+req.getDemandName()+"%' ");
			}
			if(req.getCredentialsNum()!=null && !"".equals(req.getCredentialsNum())){
				sql.append(" and de.demanderIdcard like '%"+req.getCredentialsNum()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("LoadReceiptDao.loanReceiptList2 sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LoanReceiptDtob.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//借款长度
	public int loanReceiptCount2(LoanReceiptDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) ");
			sql.append("FROM sys_loan_receipt a ");
			sql.append("LEFT JOIN sys_financing_matching b ON b.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN sys_financing_matching_contract c ON c.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN( ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`corporation_name` AS demanderName, sc.`corporation_idcard` AS demanderIdcard FROM sys_demand sda, sys_company sc WHERE sda.`demander_type`='sys_company' AND sda.`demander_id`=sc.`company_id` ");
			sql.append(" UNION ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`name` AS demanderName, sc.`credentials_num` AS demanderIdcard FROM sys_demand sda, sys_customer sc WHERE sda.`demander_type`='sys_customer' AND sda.`demander_id`=sc.`customer_id` ");
			sql.append(" ) de ON de.demand_id = b.`demand_id` ");
			sql.append(" where 1=1 ");
			//筛选条件
			if(req.getLoadReceiptCode()!=null && !"".equals(req.getLoadReceiptCode())){
				sql.append(" and a.load_receipt_code like '%"+req.getLoadReceiptCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and c.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and b.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" and b.financing_type like '%"+req.getFinancingType()+"%' ");
			}
			if(req.getFinancingMatchingStatus()!=0){
				sql.append(" and b.financing_matching_status ="+req.getFinancingMatchingStatus()+" ");
			}
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" and de.demanderName like '%"+req.getDemandName()+"%' ");
			}
			if(req.getCredentialsNum()!=null && !"".equals(req.getCredentialsNum())){
				sql.append(" and de.demanderIdcard like '%"+req.getCredentialsNum()+"%' ");
			}
			logger.info("LoadReceiptDao.loanReceiptCount2 sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//借款详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LoanReceiptDtob loanReceiptDetail(int loanReceiptId){
		try{
			String balanceAmountSql = "(SELECT SUM(sr.actual_principal_amount) FROM sys_repayment sr WHERE sr.financing_matching_id=a.financing_matching_id AND sr.loan_receipt_id=a.`loan_receipt_id` AND sr.data_status!=3)";
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.`loan_receipt_id`, a.`loan_receipt_code`,a.`financing_matching_id`, b.`matching_code`, c.`contract_code`, c.`custom_contract_code`, de.demanderName, de.demanderIdcard, ");
			sql.append(" (CASE WHEN de.demander_id>0 THEN de.demander_id ELSE 0 END) as demanderId, ");
			sql.append(" (CASE WHEN de.demand_id>0 THEN de.demand_id ELSE 0 END) as demandId, ");
			sql.append(" (CASE WHEN c.`contract_id`>0 THEN c.`contract_id` ELSE 0 END) as contractId, ");
			sql.append(" (CASE WHEN b.`financing_amount`>0 THEN b.`financing_amount` ELSE 0 END) as financingAmount, ");
			sql.append(" b.`financing_amount`, ");
			sql.append(" (CASE WHEN "+balanceAmountSql+">0 THEN "+balanceAmountSql+" ELSE 0 END) AS balanceAmount, ");
			sql.append("b.`start_time`, b.`end_time`, b.`financing_type` ");
			sql.append("FROM sys_loan_receipt a ");
			sql.append("LEFT JOIN sys_financing_matching b ON b.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN sys_financing_matching_contract c ON c.`financing_matching_id`=a.`financing_matching_id` ");
			sql.append("LEFT JOIN( ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`corporation_name` AS demanderName, sc.`corporation_idcard` AS demanderIdcard FROM sys_demand sda, sys_company sc WHERE sda.`demander_type`='sys_company' AND sda.`demander_id`=sc.`company_id` ");
			sql.append(" UNION ");
			sql.append(" SELECT sda.`demand_id`, sda.`demander_id`, sc.`name` AS demanderName, sc.`credentials_num` AS demanderIdcard FROM sys_demand sda, sys_customer sc WHERE sda.`demander_type`='sys_customer' AND sda.`demander_id`=sc.`customer_id` ");
			sql.append(" ) de ON de.demand_id = b.`demand_id` ");
			sql.append(" where a.loan_receipt_id="+loanReceiptId);
			logger.info("LoadReceiptDao.loanReceiptList2 sql = " + sql.toString());
		    return (LoanReceiptDtob)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(LoanReceiptDtob.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
