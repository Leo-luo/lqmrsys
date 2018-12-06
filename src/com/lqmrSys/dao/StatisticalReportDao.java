package com.lqmrSys.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lqmrSys.bean.SupplyExpireRemindBean;
import com.lqmrSys.bean.dto.LoanExpireRemindDto;

@Component
public class StatisticalReportDao {
	private static Logger logger = Logger.getLogger(StatisticalReportDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//供应方到期提醒列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SupplyExpireRemindBean> supplyExpireRemindList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select total.* from ( ");
			sql.append("SELECT ");
			sql.append("a.`supplier_id` AS supplierId, a.`supplier_code` AS supplierCode,b.`company_name` AS supplierName,c.name AS managerName, a.`supply_amount` AS supplyAmount, '本金' AS settlementType, a.`month_interest_rate` AS monthInterestRate, a.`start_time` AS supplyStartTime, a.`end_time` AS supplyEndTime, ");
			sql.append("(SELECT GROUP_CONCAT(mr.matching_start_time) FROM sys_matching_relation mr, sys_financing_matching fm WHERE mr.financing_matching_id=fm.financing_matching_id AND mr.supplier_id=a.supplier_id AND mr.data_status!=3 AND fm.data_status!=3 AND fm.financing_matching_status>=3 GROUP BY mr.supplier_id ORDER BY mr.matching_start_time ASC) AS matchingStartTimeStr, ");
			sql.append("(SELECT GROUP_CONCAT(mr.matching_end_time) FROM sys_matching_relation mr, sys_financing_matching fm WHERE mr.financing_matching_id=fm.financing_matching_id AND mr.supplier_id=a.supplier_id AND mr.data_status!=3 AND fm.data_status!=3 AND fm.financing_matching_status>=3 GROUP BY mr.supplier_id ORDER BY mr.matching_start_time ASC) AS matchingEndTimeStr, ");
			sql.append("(case when d.sign_id!=0 then d.sign_id else 0 end) as signId ");
			sql.append("FROM sys_supplier a ");
			sql.append("LEFT JOIN sys_company b ON b.`company_id`=a.`supplier_id` ");
			sql.append("LEFT JOIN sys_user c ON c.`user_id`=a.`user_id` ");
			sql.append("left join sys_risk_sign d on d.data_id=a.supplier_id and d.data_from='sys_supplier' ");
			sql.append("WHERE a.`supplier_type`='sys_company' AND a.`data_status`!=3 AND b.`data_status`!=3 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				sql.append(" and a.end_time>='"+startTime+"' and a.end_time<='"+endTime+"' ");
			}
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("a.`supplier_id` AS supplierId, a.`supplier_code` AS supplierCode,b.`name` AS supplierName,c.name AS managerName, a.`supply_amount` AS supplyAmount, '本金' AS settlementType, a.`month_interest_rate` AS monthInterestRate, a.`start_time` AS supplyStartTime, a.`end_time` AS supplyEndTime, ");
			sql.append("(SELECT GROUP_CONCAT(mr.matching_start_time) FROM sys_matching_relation mr, sys_financing_matching fm WHERE mr.financing_matching_id=fm.financing_matching_id AND mr.supplier_id=a.supplier_id AND mr.data_status!=3 AND fm.data_status!=3 AND fm.financing_matching_status>=3 GROUP BY mr.supplier_id ORDER BY mr.matching_start_time ASC) AS matchingStartTimeStr, ");
			sql.append("(SELECT GROUP_CONCAT(mr.matching_end_time) FROM sys_matching_relation mr, sys_financing_matching fm WHERE mr.financing_matching_id=fm.financing_matching_id AND mr.supplier_id=a.supplier_id AND mr.data_status!=3 AND fm.data_status!=3 AND fm.financing_matching_status>=3 GROUP BY mr.supplier_id ORDER BY mr.matching_start_time ASC) AS matchingEndTimeStr, ");
			sql.append("(case when d.sign_id!=0 then d.sign_id else 0 end) as signId ");
			sql.append("FROM sys_supplier a ");
			sql.append("LEFT JOIN sys_customer b ON b.`customer_id`=a.`supplier_id` ");
			sql.append("LEFT JOIN sys_user c ON c.`user_id`=a.`user_id` ");
			sql.append("left join sys_risk_sign d on d.data_id=a.supplier_id and d.data_from='sys_supplier' ");
			sql.append("WHERE a.`supplier_type`='sys_customer' AND a.`data_status`!=3 AND b.`data_status`!=3 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				sql.append(" and a.end_time>='"+startTime+"' and a.end_time<='"+endTime+"' ");
			}
			sql.append(")total ");
			sql.append("order by total.supplyEndTime asc ");
			logger.info("StatisticalReportDao.supplyExpireRemindList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(SupplyExpireRemindBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//借款到期提醒列表-逾期借款提醒列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LoanExpireRemindDto> loanExpireRemindList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("c.`loan_receipt_code` AS loanReceiptCode, a.`repayment_type` AS repaymentType, b.demanderName, b.manageraName, b.managerbName, a.`plan_start_time` AS planStartTime, a.`plan_end_time` AS planEndTime, a.`plan_principal_amount` AS planPrincipalAmount, a.`plan_interest_amount` AS planInterestAmount, a.`plan_service_amount` AS planServiceAmount, 0 AS totalAmount ");
			sql.append("FROM sys_repayment a ");
			sql.append("LEFT JOIN ");
			sql.append("( ");
			sql.append(" SELECT a.`financing_matching_id` AS financingMatchingId,c.`company_name` AS demanderName, d.name AS manageraName, e.name AS managerbName ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" LEFT JOIN sys_demand b ON b.`demand_id`=a.demand_id ");
			sql.append(" LEFT JOIN sys_company c ON c.company_id=b.`demander_id` ");
			sql.append(" LEFT JOIN sys_user d ON d.`user_id`=a.usera_id ");
			sql.append(" LEFT JOIN sys_user e ON e.`user_id`=a.userb_id ");
			sql.append(" WHERE a.`data_status`!=3 AND b.`data_status`!=3 AND c.`data_status`!=3 AND b.`demander_type`='sys_company' ");
			sql.append(" UNION ");
			sql.append(" SELECT a.`financing_matching_id` AS financingMatchingId,c.`name` AS demanderName, d.name AS manageraName, e.name AS managerbName ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" LEFT JOIN sys_demand b ON b.`demand_id`=a.demand_id ");
			sql.append(" LEFT JOIN sys_customer c ON c.customer_id=b.`demander_id` ");
			sql.append(" LEFT JOIN sys_user d ON d.`user_id`=a.usera_id ");
			sql.append(" LEFT JOIN sys_user e ON e.`user_id`=a.userb_id ");
			sql.append(" WHERE a.`data_status`!=3 AND b.`data_status`!=3 AND c.`data_status`!=3 AND b.`demander_type`='sys_customer' ");
			sql.append(") b ON b.financingMatchingId=a.`financing_matching_id` ");
			sql.append("LEFT JOIN sys_loan_receipt c ON c.`loan_receipt_id`=a.`loan_receipt_id` ");
			sql.append("WHERE a.`data_status`!=3 AND c.`data_status`!=3 ");
			sql.append(" and a.pay_status=1 ");
			if(startTime!=null && !"".equals(startTime)){
				sql.append(" and a.plan_end_time>='"+startTime+"' ");
			}
			if(endTime!=null && !"".equals(endTime)){
				sql.append(" and a.plan_end_time<='"+endTime+"' ");
			}
			sql.append("ORDER BY a.`plan_end_time` ASC ");
			logger.info("StatisticalReportDao.loanExpireRemindList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LoanExpireRemindDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
