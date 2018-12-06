package com.lqmrSys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lqmrSys.bean.LoanImportDataBean;
import com.lqmrSys.bean.StatisticsBean;
import com.lqmrSys.bean.StatisticsUserBean;
import com.lqmrSys.bean.SupplierImportDataBean;
@Component
public class ImportDataDao {
	private static Logger logger = Logger.getLogger(ImportDataDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//批量新增供应方台账导入数据
	public boolean addSupplierImportDataList(final List<SupplierImportDataBean> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_supplier_import_data (supplier_code,custom_contract_code,custom_voucher_code,cus_manager,supplier_name,supplier_certificate_code,contact_phone,investment_mode,contract_type,supply_amount,supply_balance_amount,can_pair_amount,settlement_amount,time_usable_amount,time_settlement_amount,time_unsettlement_amount,replace_pay_amount,taken_replace_pay_amount,supply_rate,knot_cycle,entrust_start_time,entrust_end_time,grace_days,supply_start_time,supply_end_time,actually_grace_days,interest_income,service_charge_income,pay_interest_amount,interest_balance_amount,interest_account,interest_account_bank,principal_account,principal_account_bank,account_type,account_ucode,account_no,information_source,idle_days,other_demand,supply_status,import_time");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("ImportDataDao.addSupplierImportDataList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
	            	 ps.setString(1, list.get(i).getSupplierCode());
			         ps.setString(2, list.get(i).getCustomContractCode());
			         ps.setString(3, list.get(i).getCustomVoucherCode());
			         ps.setString(4, list.get(i).getCusManager());
			         ps.setString(5, list.get(i).getSupplierName());
			         ps.setString(6, list.get(i).getSupplierCertificateCode());
			         ps.setString(7, list.get(i).getContactPhone());
			         ps.setString(8, list.get(i).getInvestmentMode());
			         ps.setString(9, list.get(i).getContractType());
			         ps.setDouble(10, list.get(i).getSupplyAmount());
			         ps.setDouble(11, list.get(i).getSupplyBalanceAmount());
			         ps.setDouble(12, list.get(i).getCanPairAmount());
			         ps.setDouble(13, list.get(i).getSettlementAmount());
			         ps.setDouble(14, list.get(i).getTimeUsableAmount());
			         ps.setDouble(15, list.get(i).getTimeSettlementAmount());
			         ps.setDouble(16, list.get(i).getTimeUnsettlementAmount());
			         ps.setDouble(17, list.get(i).getReplacePayAmount());
			         ps.setDouble(18, list.get(i).getTakenReplacePayAmount());
			         ps.setDouble(19, list.get(i).getSupplyRate());
			         ps.setString(20, list.get(i).getKnotCycle());
			         ps.setString(21, list.get(i).getEntrustStartTime());
			         ps.setString(22, list.get(i).getEntrustEndTime());
			         ps.setInt(23, list.get(i).getGraceDays());
			         ps.setString(24, list.get(i).getSupplyStartTime());
			         ps.setString(25, list.get(i).getSupplyEndTime());
			         ps.setInt(26, list.get(i).getActuallyGraceDays());
			         ps.setDouble(27, list.get(i).getInterestIncome());
			         ps.setDouble(28, list.get(i).getServiceChargeIncome());
			         ps.setDouble(29, list.get(i).getPayInterestAmount());
			         ps.setDouble(30, list.get(i).getInterestBalanceAmount());
			         ps.setString(31, list.get(i).getInterestAccount());
			         ps.setString(32, list.get(i).getInterestAccountBank());
			         ps.setString(33, list.get(i).getPrincipalAccount());
			         ps.setString(34, list.get(i).getPrincipalAccountBank());
			         ps.setString(35, list.get(i).getAccountType());
			         ps.setString(36, list.get(i).getAccountUcode());
			         ps.setString(37, list.get(i).getAccountNo());
			         ps.setString(38, list.get(i).getInformationSource());
			         ps.setInt(39, list.get(i).getIdleDays());
			         ps.setString(40, list.get(i).getOtherDemand());
			         ps.setString(41, list.get(i).getSupplyStatus());
			         ps.setString(42, date);
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
	
	//供应方台账导入数据是否已存在
	public int hasSupplierImportData(String supplierCode){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.import_data_id) ");
			sql.append(" FROM sys_supplier_import_data a ");
			sql.append(" WHERE a.supplier_code='"+supplierCode+"'");
			logger.info("ImportDataDao.hasSupplierImportData sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//批量新增借款情况台账导入数据
	public boolean addLoanImportDataList(final List<LoanImportDataBean> list){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_loan_import_data (loan_code,contract_code,custom_contract_code,contract_type,financing_demander_name,cus_managera,cus_managerb,financing_type,loan_amount,already_pay_amount,balance_amount,supplier_balance_amount,own_balance_amount,time_balance_amount,time_interest_amount,loan_rate,service_rate,loan_start_time,loan_end_time,loan_days,plan_interest_amount,pay_interest_amount,pay_service_amount,hangup_total_amount,repayment_mode,supply_amount,own_amount,replace_pay_amount,taken_replace_pay_amount,industry_type,belong_area,work_address,loan_purpose,certificate_no,contact_phone,organization_no,business_license_no,legal_representative,guarantee,risk_classify,bad_debt,is_extension,loan_remark,import_time ");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("ImportDataDao.addLoanImportDataList sql = " + sql.toString());
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){
	            public void setValues(PreparedStatement ps,int i)throws SQLException
	               {
	            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
	            	 ps.setString(1, list.get(i).getLoanCode());
			         ps.setString(2, list.get(i).getContractCode());
			         ps.setString(3, list.get(i).getCustomContractCode());
			         ps.setString(4, list.get(i).getContractType());
			         ps.setString(5, list.get(i).getFinancingDemanderName());
			         ps.setString(6, list.get(i).getCusManagera());
			         ps.setString(7, list.get(i).getCusManagerb());
			         ps.setString(8, list.get(i).getFinancingType());
			         ps.setDouble(9, list.get(i).getLoanAmount());
			         ps.setDouble(10, list.get(i).getAlreadyPayAmount());
			         ps.setDouble(11, list.get(i).getBalanceAmount());
			         ps.setDouble(12, list.get(i).getSupplierBalanceAmount());
			         ps.setDouble(13, list.get(i).getOwnBalanceAmount());
			         ps.setDouble(14, list.get(i).getTimeBalanceAmount());
			         ps.setDouble(15, list.get(i).getTimeInterestAmount());
			         ps.setDouble(16, list.get(i).getLoanRate());
			         ps.setDouble(17, list.get(i).getServiceRate());
			         ps.setString(18, list.get(i).getLoanStartTime());
			         ps.setString(19, list.get(i).getLoanEndTime());
			         ps.setInt(20, list.get(i).getLoanDays());
			         ps.setDouble(21, list.get(i).getPlanInterestAmount());
			         ps.setDouble(22, list.get(i).getPayInterestAmount());
			         ps.setDouble(23, list.get(i).getPayServiceAmount());
			         ps.setDouble(24, list.get(i).getHangupTotalAmount());
			         ps.setString(25, list.get(i).getRepaymentMode());
			         ps.setDouble(26, list.get(i).getSupplyAmount());
			         ps.setDouble(27, list.get(i).getOwnAmount());
			         ps.setDouble(28, list.get(i).getReplacePayAmount());
			         ps.setDouble(29, list.get(i).getTakenReplacePayAmount());
			         ps.setString(30, list.get(i).getIndustryType());
			         ps.setString(31, list.get(i).getBelongArea());
			         ps.setString(32, list.get(i).getWorkAddress());
			         ps.setString(33, list.get(i).getLoanPurpose());
			         ps.setString(34, list.get(i).getCertificateNo());
			         ps.setString(35, list.get(i).getContactPhone());
			         ps.setString(36, list.get(i).getOrganizationNo());
			         ps.setString(37, list.get(i).getBusinessLicenseNo());
			         ps.setString(38, list.get(i).getLegalRepresentative());
			         ps.setString(39, list.get(i).getGuarantee());
			         ps.setString(40, list.get(i).getRiskClassify());
			         ps.setString(41, list.get(i).getBadDebt());
			         ps.setString(42, list.get(i).getIsExtension());
			         ps.setString(43, list.get(i).getLoanRemark());
			         ps.setString(44, date);
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
	
	//借款情况台账导入数据是否已存在
	public int hasLoanImportData(String loanCode){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.import_data_id) ");
			sql.append(" FROM sys_loan_import_data a ");
			sql.append(" WHERE a.loan_code='"+loanCode+"'");
			logger.info("ImportDataDao.hasLoanImportData sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//供应总额
	public double totalSupplyAmount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUM(a.supply_amount-a.settlement_amount) FROM sys_supplier_import_data a");
			logger.info("ImportDataDao.totalSupplyAmount sql = " + sql.toString());
			return (Double)jdbcTemplate.queryForObject(sql.toString(), Double.class);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//贷款总额
	public double totalLoanAmount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUM(a.balance_amount) FROM sys_loan_import_data a");
			logger.info("ImportDataDao.totalLoanAmount sql = " + sql.toString());
			return (Double)jdbcTemplate.queryForObject(sql.toString(), Double.class);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//可配对金额
	public double canMatchingAmount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUM(a.can_pair_amount) FROM sys_supplier_import_data a");
			logger.info("ImportDataDao.canMatchingAmount sql = " + sql.toString());
			return (Double)jdbcTemplate.queryForObject(sql.toString(), Double.class);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//历年供应金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> yearSupplyAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT DATE_FORMAT(a.entrust_start_time, '%Y') AS dataTime, SUM(a.supply_amount) AS dataValue ");
			sql.append(" FROM sys_supplier_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				sql.append(" and a.entrust_start_time>='"+startTime+"-01-01' and a.entrust_start_time<='"+endTime+"-12-31' ");
			}
			sql.append(" GROUP BY DATE_FORMAT(a.entrust_start_time, '%Y') ");
			sql.append(") total ORDER BY total.dataTime ASC ");
			logger.info("ImportDataDao.yearSupplyAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//月供应金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> monthSupplyAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT DATE_FORMAT(a.entrust_start_time, '%Y-%m') AS dataTime, SUM(a.supply_amount) AS dataValue ");
			sql.append(" FROM sys_supplier_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				Date time = new SimpleDateFormat("yyyy-MM").parse(endTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(time);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" and a.entrust_start_time>='"+startTime+"-01' and a.entrust_start_time<='"+lastday+"' ");
			}else{
				String firstday = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" AND a.entrust_start_time>='"+firstday+"' ");
				sql.append(" AND a.entrust_start_time<='"+lastday+"' ");
			}
			sql.append(" GROUP BY DATE_FORMAT(a.entrust_start_time, '%Y-%m') ");
			sql.append(") total ORDER BY total.dataTime ASC ");
			logger.info("ImportDataDao.monthSupplyAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//历年贷款金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> yearLoanAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT DATE_FORMAT(a.loan_start_time, '%Y') AS dataTime, SUM(a.loan_amount) AS dataValue ");
			sql.append(" FROM sys_loan_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				sql.append(" and a.loan_start_time>='"+startTime+"-01-01' and a.loan_start_time<='"+endTime+"-12-31' ");
			}
			sql.append(" GROUP BY DATE_FORMAT(a.loan_start_time, '%Y') ");
			sql.append(") total ORDER BY total.dataTime ASC ");
			logger.info("ImportDataDao.yearLoanAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//月贷款金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> monthLoanAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT DATE_FORMAT(a.loan_start_time, '%Y-%m') AS dataTime, SUM(a.loan_amount) AS dataValue ");
			sql.append(" FROM sys_loan_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				Date time = new SimpleDateFormat("yyyy-MM").parse(endTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(time);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" and a.loan_start_time>='"+startTime+"-01' and a.loan_start_time<='"+lastday+"' ");
			}else{
				String firstday = new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" AND a.loan_start_time>='"+firstday+"' ");
				sql.append(" AND a.loan_start_time<='"+lastday+"' ");
			}
			sql.append(" GROUP BY DATE_FORMAT(a.loan_start_time, '%Y-%m') ");
			sql.append(") total ORDER BY total.dataTime ASC ");
			logger.info("ImportDataDao.monthLoanAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//客户经理出借金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> managerSupplyAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT SUM(a.supply_amount) AS dataValue, a.cus_manager as dataName ");
			sql.append(" FROM sys_supplier_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				Date time = new SimpleDateFormat("yyyy-MM").parse(endTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(time);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" and a.entrust_start_time>='"+startTime+"-01' and a.entrust_start_time<='"+lastday+"' ");
			}else{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cale = null;
				String firstday, lastday;
				// 获取前月的第一天
		        cale = Calendar.getInstance();
		        cale.add(Calendar.MONTH, 0);
		        cale.set(Calendar.DAY_OF_MONTH, 1);
		        firstday = format.format(cale.getTime());
		        // 获取前月的最后一天
		        cale = Calendar.getInstance();
		        cale.add(Calendar.MONTH, 1);
		        cale.set(Calendar.DAY_OF_MONTH, 0);
		        lastday = format.format(cale.getTime());
				sql.append(" AND a.entrust_start_time>='"+firstday+"' ");
				sql.append(" AND a.entrust_start_time<='"+lastday+"' ");
			}
			sql.append(" GROUP BY a.cus_manager ");
			sql.append(") total ORDER BY total.dataValue DESC ");
			logger.info("ImportDataDao.managerSupplyAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//客户经理贷款金额列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsBean> managerLoanAmountList(String startTime, String endTime){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT total.* FROM ( ");
			sql.append(" SELECT SUM(a.loan_amount) AS dataValue, a.cus_managera as dataName ");
			sql.append(" FROM sys_loan_import_data a where 1=1 ");
			if(startTime!=null && !"".equals(startTime) && endTime!=null && !"".equals(endTime)){
				Date time = new SimpleDateFormat("yyyy-MM").parse(endTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(time);
				calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				sql.append(" and a.loan_start_time>='"+startTime+"-01' and a.loan_start_time<='"+lastday+"' ");
			}else{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cale = null;
				String firstday, lastday;
				// 获取前月的第一天
		        cale = Calendar.getInstance();
		        cale.add(Calendar.MONTH, 0);
		        cale.set(Calendar.DAY_OF_MONTH, 1);
		        firstday = format.format(cale.getTime());
		        // 获取前月的最后一天
		        cale = Calendar.getInstance();
		        cale.add(Calendar.MONTH, 1);
		        cale.set(Calendar.DAY_OF_MONTH, 0);
		        lastday = format.format(cale.getTime());
		        sql.append(" AND a.loan_start_time>='"+firstday+"' ");
				sql.append(" AND a.loan_start_time<='"+lastday+"' ");
			}
			sql.append(" GROUP BY a.cus_managera ");
			sql.append(") total ORDER BY total.dataValue DESC ");
			logger.info("ImportDataDao.managerLoanAmountList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//客户经理出借-贷款统计列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StatisticsUserBean> managerSupplayAndLoanList(String startTime, String endTime){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cale = null;
			String firstday, lastday;
			// 获取前月的第一天
	        cale = Calendar.getInstance();
	        cale.add(Calendar.MONTH, 0);
	        cale.set(Calendar.DAY_OF_MONTH, 1);
	        firstday = format.format(cale.getTime());
	        // 获取前月的最后一天
	        cale = Calendar.getInstance();
	        cale.add(Calendar.MONTH, 1);
	        cale.set(Calendar.DAY_OF_MONTH, 0);
	        lastday = format.format(cale.getTime());
	        
	        if(startTime!=null && !"".equals(startTime)){
	        	firstday = startTime;
	        }
	        if(endTime!=null && !"".equals(endTime)){
	        	lastday = endTime;
	        }
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("t.cusManager, SUM(t.supplyNum) AS supplyNum, SUM(t.supplyAmount) AS supplyAmount, SUM(t.totalSupplyNum) AS totalSupplyNum, SUM(t.totalSupplyAmount) AS totalSupplyAmount, SUM(t.loanNum) AS loanNum, SUM(t.loanAmount) AS loanAmount, SUM(t.totalLoanNum) AS totalLoanNum, SUM(t.totalLoanAmount) AS totalLoanAmount ");
			sql.append("FROM ( ");
			//#当月出借
			sql.append(" SELECT a.`cus_manager` AS cusManager, COUNT(*) AS supplyNum, SUM(a.supply_amount) AS supplyAmount, 0 AS totalSupplyNum, 0 AS totalSupplyAmount, 0 AS loanNum, 0 AS loanAmount, 0 AS totalLoanNum, 0 AS totalLoanAmount ");
			sql.append(" FROM sys_supplier_import_data a ");
			sql.append(" WHERE a.entrust_start_time>='"+firstday+"' AND a.entrust_start_time<='"+lastday+"' ");
			sql.append(" GROUP BY a.`cus_manager` ");
			sql.append(" UNION ");
			//#累计出借
			sql.append(" SELECT a.`cus_manager` AS cusManager, 0 AS supplyNum, 0 AS supplyAmount, COUNT(*) AS totalSupplyNum, SUM(a.supply_amount) AS totalSupplyAmount, 0 AS loanNum, 0 AS loanAmount, 0 AS totalLoanNum, 0 AS totalLoanAmount ");
			sql.append(" FROM sys_supplier_import_data a  ");
			sql.append(" GROUP BY a.`cus_manager` ");
			sql.append(" UNION ");
			//#当月贷款
			sql.append(" SELECT a.`cus_managera` AS cusManager, 0 AS supplyNum, 0 AS supplyAmount, 0 AS totalSupplyNum, 0 AS totalSupplyAmount, COUNT(*) AS loanNum, SUM(a.loan_amount) AS loanAmount, 0 AS totalLoanNum, 0 AS totalLoanAmount ");
			sql.append(" FROM sys_loan_import_data a ");
			sql.append(" WHERE a.loan_start_time>='"+firstday+"' AND a.loan_start_time<='"+lastday+"' ");
			sql.append(" GROUP BY a.`cus_managera` ");
			sql.append(" UNION ");
			//#累计贷款
			sql.append(" SELECT a.`cus_managera` AS cusManager, 0 AS supplyNum, 0 AS supplyAmount, 0 AS totalSupplyNum, 0 AS totalSupplyAmount, 0 AS loanNum, 0 AS loanAmount, COUNT(*) AS totalLoanNum, SUM(a.loan_amount) AS totalLoanAmount ");
			sql.append(" FROM sys_loan_import_data a ");
			sql.append(" GROUP BY a.`cus_managera` ");
			sql.append(") t GROUP BY t.cusManager ");
			logger.info("ImportDataDao.managerSupplayAndLoanList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(StatisticsUserBean.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
