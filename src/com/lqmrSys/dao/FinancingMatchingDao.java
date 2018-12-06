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
import com.lqmrSys.bean.dto.FinancingMatchingDto;
import com.lqmrSys.bean.pagination.FinancingMatchingDataTablesReq;
import com.lqmrSys.bean.req.FinancingMatchingReq;

@Component
public class FinancingMatchingDao {
	private static Logger logger = Logger.getLogger(FinancingMatchingDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增融资配对信息
	public int addFinancingMatching(final FinancingMatchingReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_financing_matching (matching_code,financing_type,demand_id,financing_amount,start_time,end_time,own_month_rate,own_service_rate,supplier_month_rate,supplier_service_rate,overdue_rate,repayment_mode,interest_settlement_cycle,service_pay_mode,service_pay_date,guarantee_mode,loan_purpose_classify,card_id,loan_purpose_remark,register_date,usera_id,userb_id,remark,data_status,create_time,update_time,operator_id,operator_name,financing_matching_status");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			logger.info("FinancingMatchingDao.addFinancingMatching sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getMatchingCode());
			         ps.setString(2, req.getFinancingType());
			         ps.setInt(3, req.getDemandId());
			         ps.setDouble(4, req.getFinancingAmount());
			         ps.setString(5, req.getStartTime());
			         ps.setString(6, req.getEndTime());
			         ps.setDouble(7, req.getOwnMonthRate());
			         ps.setDouble(8, req.getOwnServiceRate());
			         ps.setDouble(9, req.getSupplierMonthRate());
			         ps.setDouble(10, req.getSupplierServiceRate());
			         ps.setDouble(11, req.getOverdueRate());
			         ps.setString(12, req.getRepaymentMode());
			         ps.setString(13, req.getInterestSettlementCycle());
			         ps.setString(14, req.getServicePayMode());
			         ps.setInt(15, req.getServicePayDate());
			         ps.setString(16, req.getGuaranteeMode());
			         ps.setString(17, req.getLoanPurposeClassify());
			         ps.setInt(18, req.getCardId());
			         ps.setString(19, req.getLoanPurposeRemark());
			         ps.setString(20, req.getRegisterDate());
			         ps.setInt(21, req.getUseraId());
			         ps.setInt(22, req.getUserbId());
			         ps.setString(23, req.getRemark());
			         ps.setInt(24, Constants.DATA_STATUS_NORMAL);
			         ps.setString(25, date);
			         ps.setString(26, date);
			         ps.setInt(27, req.getOperatorId());
			         ps.setString(28, req.getOperatorName());
			         ps.setInt(29, Constants.DATA_STATUS_NORMAL);
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
	
	//修改融资配对信息
	public int modifyFinancingMatching(FinancingMatchingReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_financing_matching set update_time='"+date+"' ");
			sql.append(" ,operator_id="+req.getOperatorId()+" ");
			sql.append(" ,operator_name='"+req.getOperatorName()+"' ");
			if(req.getFinancingType()!=null && !"".equals(req.getFinancingType())){
				sql.append(" ,financing_type='"+req.getFinancingType()+"' ");
			}
			if(req.getFinancingAmount()!=0){
				sql.append(" ,financing_amount="+req.getFinancingAmount()+" ");
			}
			if(req.getStartTime()!=null && !"".equals(req.getStartTime())){
				sql.append(" ,start_time='"+req.getStartTime()+"' ");
			}
			if(req.getEndTime()!=null && !"".equals(req.getEndTime())){
				sql.append(" ,end_time='"+req.getEndTime()+"' ");
			}
			if(req.getOwnMonthRate()!=-1){
				sql.append(" ,own_month_rate="+req.getOwnMonthRate()+" ");
			}
			if(req.getOwnServiceRate()!=-1){
				sql.append(" ,own_service_rate="+req.getOwnServiceRate()+" ");
			}
			if(req.getSupplierMonthRate()!=-1){
				sql.append(" ,supplier_month_rate="+req.getSupplierMonthRate()+" ");
			}
			if(req.getSupplierServiceRate()!=-1){
				sql.append(" ,supplier_service_rate="+req.getSupplierServiceRate()+" ");
			}
			if(req.getOverdueRate()!=-1){
				sql.append(" ,overdue_rate="+req.getOverdueRate()+" ");
			}
			if(req.getRepaymentMode()!=null && !"".equals(req.getRepaymentMode())){
				sql.append(" ,repayment_mode='"+req.getRepaymentMode()+"' ");
			}
			if(req.getInterestSettlementCycle()!=null && !"".equals(req.getInterestSettlementCycle())){
				sql.append(" ,interest_settlement_cycle='"+req.getInterestSettlementCycle()+"' ");
			}
			if(req.getServicePayMode()!=null && !"".equals(req.getServicePayMode())){
				sql.append(" ,service_pay_mode='"+req.getServicePayMode()+"' ");
			}
			if(req.getServicePayDate()!=0){
				sql.append(" ,service_pay_date="+req.getServicePayDate()+" ");
			}
			if(req.getGuaranteeMode()!=null && !"".equals(req.getGuaranteeMode())){
				sql.append(" ,guarantee_mode='"+req.getGuaranteeMode()+"' ");
			}
			if(req.getLoanPurposeClassify()!=null && !"".equals(req.getLoanPurposeClassify())){
				sql.append(" ,loan_purpose_classify='"+req.getLoanPurposeClassify()+"' ");
			}
			if(req.getCardId()!=0){
				sql.append(" ,card_id="+req.getCardId()+" ");
			}
			if(req.getLoanPurposeRemark()!=null && !"".equals(req.getLoanPurposeRemark())){
				sql.append(" ,loan_purpose_remark='"+req.getLoanPurposeRemark()+"' ");
			}
			if(req.getRegisterDate()!=null && !"".equals(req.getRegisterDate())){
				sql.append(" ,register_date='"+req.getRegisterDate()+"' ");
			}
			if(req.getUseraId()!=0){
				sql.append(" ,usera_id="+req.getUseraId()+" ");
			}
			if(req.getUserbId()!=0){
				sql.append(" ,userb_id="+req.getUserbId()+" ");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			sql.append(" where financing_matching_id="+req.getFinancingMatchingId());
			logger.info("FinancingMatchingDao.modifyFinancingMatching sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除融资配对信息
	public int delFinancingMatching(int financingMatchingId, int operatorId, String operatorName){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_financing_matching set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" ,operator_id="+operatorId+", operator_name='"+operatorName+"' ");
			sql.append(" where financing_matching_id="+financingMatchingId);
			logger.info("FinancingMatchingDao.delFinancingMatching sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FinancingMatchingDto financingMatchingDetailById(int financingMatchingId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, b.name as useraName, c.name as userbName, d.demandName ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" left join sys_user b on b.user_id=a.usera_id ");
			sql.append(" left join sys_user c on c.user_id=a.userb_id ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT com.company_name AS demandName, dem.demand_id FROM sys_company com,sys_demand dem WHERE com.company_id=dem.`demander_id` AND dem.demander_type='sys_company' ");
			sql.append(" UNION ");
			sql.append(" select cus.name as demandName, dem.demand_id from sys_customer cus,sys_demand dem where cus.customer_id=dem.`demander_id` AND dem.demander_type='sys_customer' ");
			sql.append(" )d ON d.demand_id=a.demand_id ");
			sql.append(" WHERE a.financing_matching_id="+financingMatchingId);
			logger.info("FinancingMatchingDao.financingMatchingDetailById sql = " + sql.toString());
			return (FinancingMatchingDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//融资配对信息列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<FinancingMatchingDto> financingMatchingList(FinancingMatchingDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, b.name as useraName, c.name as userbName, d.demandName ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" left join sys_user b on b.user_id=a.usera_id ");
			sql.append(" left join sys_user c on c.user_id=a.userb_id ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT com.company_name AS demandName, dem.demand_id FROM sys_company com,sys_demand dem WHERE com.company_id=dem.`demander_id` AND dem.demander_type='sys_company' ");
			sql.append(" UNION ");
			sql.append(" select cus.name as demandName, dem.demand_id from sys_customer cus,sys_demand dem where cus.customer_id=dem.`demander_id` AND dem.demander_type='sys_customer' ");
			sql.append(" )d ON d.demand_id=a.demand_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and a.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" ,and d.demandName like '%"+req.getDemandName()+"%' ");
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("FinancingMatchingDao.financingMatchingList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//融资配对信息长度
	public int financingMatchingCount(FinancingMatchingDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.financing_matching_id) ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" left join sys_user b on b.user_id=a.usera_id ");
			sql.append(" left join sys_user c on c.user_id=a.userb_id ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT com.company_name AS demandName, dem.demand_id FROM sys_company com,sys_demand dem WHERE com.company_id=dem.`demander_id` AND dem.demander_type='sys_company' ");
			sql.append(" UNION ");
			sql.append(" select cus.name as demandName, dem.demand_id from sys_customer cus,sys_demand dem where cus.customer_id=dem.`demander_id` AND dem.demander_type='sys_customer' ");
			sql.append(" )d ON d.demand_id=a.demand_id ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getMatchingCode()!=null && !"".equals(req.getMatchingCode())){
				sql.append(" and a.matching_code like '%"+req.getMatchingCode()+"%' ");
			}
			if(req.getDemandName()!=null && !"".equals(req.getDemandName())){
				sql.append(" ,and d.demandName like '%"+req.getDemandName()+"%' ");
			}
			logger.info("FinancingMatchingDao.financingMatchingCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息数量
	public int financingMatchingAllCount(){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.financing_matching_id) ");
			sql.append(" FROM sys_financing_matching a ");
			logger.info("FinancingMatchingDao.financingMatchingAllCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//融资配对信息详情
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FinancingMatchingDto financingMatchingDetailByCode(String matchingCode){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.*, b.name as useraName, c.name as userbName, d.demandName ");
			sql.append(" FROM sys_financing_matching a ");
			sql.append(" left join sys_user b on b.user_id=a.usera_id ");
			sql.append(" left join sys_user c on c.user_id=a.userb_id ");
			sql.append(" LEFT JOIN ( ");
			sql.append(" SELECT com.company_name AS demandName, dem.demand_id FROM sys_company com,sys_demand dem WHERE com.company_id=dem.`demander_id` AND dem.demander_type='sys_company' ");
			sql.append(" UNION ");
			sql.append(" select cus.name as demandName, dem.demand_id from sys_customer cus,sys_demand dem where cus.customer_id=dem.`demander_id` AND dem.demander_type='sys_customer' ");
			sql.append(" )d ON d.demand_id=a.demand_id ");
			sql.append(" WHERE a.matching_code='"+matchingCode+"'");
			logger.info("FinancingMatchingDao.financingMatchingDetailByCode sql = " + sql.toString());
			return (FinancingMatchingDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(FinancingMatchingDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
