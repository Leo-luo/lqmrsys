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
import com.lqmrSys.bean.dto.SupplierDto;
import com.lqmrSys.bean.pagination.SupplierDataTablesReq;
import com.lqmrSys.bean.req.SupplierReq;

@Component
public class SupplierDao {
	private static Logger logger = Logger.getLogger(SupplierDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ModuleDao moduleDao;
	
	//新增供应方
	public int addSupplier(final SupplierReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_supplier (supplier_code,contract_code,customer_id,supply_amount,start_time,end_time,month_interest_rate,grace_days,investment_mode,investment_info_channel,interest_settlement_cycle,card_id,card_type,contract_type,user_id,remark,data_status,create_time,update_time,supplier_status,supplier_balance,operator_id,operator_name,supplier_type");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("SupplierDao.addSupplier sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getSupplierCode());
			         ps.setString(2, req.getContractCode());
			         ps.setInt(3, req.getCustomerId());
			         ps.setDouble(4, req.getSupplyAmount());
			         ps.setString(5, req.getStartTime());
			         ps.setString(6, req.getEndTime());
			         ps.setDouble(7, req.getMonthInterestRate());
			         ps.setInt(8, req.getGraceDays());
			         ps.setString(9, req.getInvestmentMode());
			         ps.setString(10, req.getInvestmentInfoChannel());
			         ps.setString(11, req.getInterestSettlementCycle());
			         ps.setInt(12, req.getCardId());
			         ps.setString(13, req.getCardType());
			         ps.setString(14, req.getContractType());
			         ps.setInt(15, req.getUserId());
			         ps.setString(16, req.getRemark());
			         ps.setInt(17, Constants.DATA_STATUS_NORMAL);
			         ps.setString(18, date);
			         ps.setString(19, date);
			         ps.setInt(20, Constants.DATA_STATUS_NORMAL);
			         ps.setDouble(21, req.getSupplyAmount());
			         ps.setInt(22, req.getOperatorId());
			         ps.setString(23, req.getOperatorName());
			         ps.setString(24, req.getSupplierType());
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
	
	//修改供应方
	public int modifySupplier(SupplierReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_supplier set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" ,contract_code='"+req.getContractCode()+"' ");
			}
			if(req.getCustomerId()!=0){
				sql.append(" ,customer_id="+req.getCustomerId()+" ");
			}
			if(req.getSupplyAmount()!=0){
				sql.append(" ,supply_amount="+req.getSupplyAmount()+" ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime())){
				sql.append(" ,start_time='"+req.getStartTime()+"' ");
			}
			if(req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" ,end_time='"+req.getEndTime()+"' ");
			}
			if(req.getMonthInterestRate()!=0){
				sql.append(" ,month_interest_rate="+req.getMonthInterestRate()+" ");
			}
			if(req.getGraceDays()!=0){
				sql.append(" ,grace_days="+req.getGraceDays()+" ");
			}
			if(req.getInvestmentMode()!=null && !"".equals(req.getInvestmentMode())){
				sql.append(" ,investment_mode='"+req.getInvestmentMode()+"' ");
			}
			if(req.getInvestmentInfoChannel()!=null && !"".equals(req.getInvestmentInfoChannel())){
				sql.append(" ,investment_info_channel='"+req.getInvestmentInfoChannel()+"' ");
			}
			if(req.getInterestSettlementCycle()!=null && !"".equals(req.getInterestSettlementCycle())){
				sql.append(" ,interest_settlement_cycle='"+req.getInterestSettlementCycle()+"' ");
			}
			if(req.getCardId()!=0){
				sql.append(" ,card_id="+req.getCardId()+" ");
			}
			if(req.getCardType()!=null && !"".equals(req.getCardType())){
				sql.append(" ,card_type='"+req.getCardType()+"' ");
			}
			if(req.getUserId()!=0){
				sql.append(" ,user_id="+req.getUserId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getSupplierType()!=null && !"".equals(req.getSupplierType())){
				sql.append(" ,supplier_type='"+req.getSupplierType()+"' ");
			}
			sql.append(" where supplier_id="+req.getSupplierId());
			logger.info("SupplierDao.modifySupplier sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除供应方
	public int delSupplier(int supplierId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_supplier set update_time='"+date+"' ");
			sql.append(" ,data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+" ");
			sql.append(" ,operator_name='"+operatorName+"' ");
			sql.append(" where supplier_id="+supplierId);
			logger.info("SupplierDao.delSupplier sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//供应方信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SupplierDto supplierDetailById(int supplierId){
		try{
			String customerNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.customer_id)";
			String companyNameSql = "(select com.company_name from sys_company com where com.company_id=a.customer_id)";

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, c.name as userName, (case when a.supplier_type='sys_customer' then "+customerNameSql+" else "+companyNameSql+" end) as customerName ");
			sql.append(" FROM sys_supplier a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.supplier_id="+supplierId);
			logger.info("SupplierDao.supplierDetailById sql = " + sql.toString());
			return (SupplierDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(SupplierDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//供应方列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SupplierDto> supplierList(SupplierDataTablesReq req){
		try{
			//用户数据权限
			String userDataStr = moduleDao.userDataStr(req.getOperatorId());
			String userDataSql = " (";
			if(userDataStr!=null && !"".equals(userDataStr)){
				String[] dataStr = userDataStr.split(",");
				for (int i = 0; i < dataStr.length; i++) {
					userDataSql = userDataSql + " a.user_id=" + dataStr[i];
					if(i!=(dataStr.length-1)){
						userDataSql = userDataSql + " or ";
					}else{
						userDataSql = userDataSql + ") ";
					}
				}
			}
			
			String customerNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.customer_id)";
			String companyNameSql = "(select com.company_name from sys_company com where com.company_id=a.customer_id)";
			String riskSignSql = "(select rs.sign_id from sys_risk_sign rs where rs.data_from='sys_supplier' and rs.data_id=a.supplier_id and rs.data_status!="+Constants.DATA_STATUS_DELETE+")";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select total.* from ( ");
			sql.append("SELECT a.*,c.name as userName ");
			sql.append(" ,(case when a.supplier_type='sys_customer' then "+customerNameSql+" else "+companyNameSql+" end) as customerName ");
			sql.append(" ,(case when "+riskSignSql+"!=0 then "+riskSignSql+" else 0 end) as signId ");
			sql.append(" FROM sys_supplier a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and "+userDataStr);
			sql.append(" )total ");
			//筛选条件
			if(req.getSupplierCode()!=null && !"".equals(req.getSupplierCode())){
				sql.append(" and total.supplier_code like '%"+req.getSupplierCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and total.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and total.userName like '%"+req.getUserName()+"%' ");
			}
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and total.customerName like '%"+req.getCustomerName()+"%' ");
			}
			//配对列表筛选字段
			if(req.getSupplierBalanceStart()!=0){
				sql.append(" and total.supplier_balance>="+req.getSupplierBalanceStart()+" ");
			}
			if(req.getSupplierBalanceEnd()!=0){
				sql.append(" and total.supplier_balance<="+req.getSupplierBalanceStart()+" ");
			}
			if(req.getOrderField()!=null && !"".equals(req.getOrderField()) && req.getSortType()!=null && !"".equals(req.getSortType())){
				sql.append(" ORDER BY ");
				if("供应金额".equals(req.getOrderField())){
					sql.append(" total.supply_amount ");
				}else if("供应余额".equals(req.getOrderField())){
					sql.append(" total.supplier_balance ");
				}else if("供应开始日期".equals(req.getOrderField())){
					sql.append(" total.start_time ");
				}else if("供应结束日期".equals(req.getOrderField())){
					sql.append(" total.end_time ");
				}
				if("升序".equals(req.getSortType())){
					sql.append(" ASC ");
				}else if("降序".equals(req.getSortType())){
					sql.append(" DESC ");
				}
			}else{
				sql.append(" ORDER BY total.create_time DESC ");
			}
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("SupplierDao.supplierList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(SupplierDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//供应方列表长度
	public int supplierCount(SupplierDataTablesReq req){
		try{
			//用户数据权限
			String userDataStr = moduleDao.userDataStr(req.getOperatorId());
			String userDataSql = " (";
			if(userDataStr!=null && !"".equals(userDataStr)){
				String[] dataStr = userDataStr.split(",");
				for (int i = 0; i < dataStr.length; i++) {
					userDataSql = userDataSql + " a.user_id=" + dataStr[i];
					if(i!=(dataStr.length-1)){
						userDataSql = userDataSql + " or ";
					}else{
						userDataSql = userDataSql + ") ";
					}
				}
			}
			
			String customerNameSql = "(select cus.name from sys_customer cus where cus.customer_id=a.customer_id)";
			String companyNameSql = "(select com.company_name from sys_company com where com.company_id=a.customer_id)";
			
			StringBuffer sql = new StringBuffer();
			sql.append("select COUNT(*) from ( ");
			sql.append("SELECT a.*, c.name as userName, (case when a.supplier_type='sys_customer' then "+customerNameSql+" else "+companyNameSql+" end) as customerName ");
			sql.append(" FROM sys_supplier a ");
			sql.append(" left join sys_user c on c.user_id=a.user_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			sql.append(" and "+userDataStr);
			sql.append(" )total ");
			//筛选条件
			if(req.getSupplierCode()!=null && !"".equals(req.getSupplierCode())){
				sql.append(" and total.supplier_code like '%"+req.getSupplierCode()+"%' ");
			}
			if(req.getContractCode()!=null && !"".equals(req.getContractCode())){
				sql.append(" and total.contract_code like '%"+req.getContractCode()+"%' ");
			}
			if(req.getUserName()!=null && !"".equals(req.getUserName())){
				sql.append(" and total.userName like '%"+req.getUserName()+"%' ");
			}
			if(req.getCustomerName()!=null && !"".equals(req.getCustomerName())){
				sql.append(" and total.customerName like '%"+req.getCustomerName()+"%' ");
			}
			//配对列表筛选字段
			if(req.getSupplierBalanceStart()!=0){
				sql.append(" and total.supplier_balance>="+req.getSupplierBalanceStart()+" ");
			}
			if(req.getSupplierBalanceEnd()!=0){
				sql.append(" and total.supplier_balance<="+req.getSupplierBalanceStart()+" ");
			}
			logger.info("SupplierDao.supplierCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//供应方列表长度
	public int supplierAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.supplier_id) ");
			sql.append(" FROM sys_supplier a ");
			logger.info("SupplierDao.supplierAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//修改供应方余额
	public int modifySupplierBalance(int supplierId, double supplierBalance){
		StringBuffer sql = new StringBuffer();
		try{
			sql.append("update sys_supplier set supplier_balance="+supplierBalance+" ");
			sql.append(" where supplier_id="+supplierId);
			logger.info("SupplierDao.modifySupplierBalance sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
