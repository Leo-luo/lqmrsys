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
import com.lqmrSys.bean.dto.BankCardDto;
import com.lqmrSys.bean.pagination.BankCardDataTablesReq;
import com.lqmrSys.bean.req.BankCardReq;

@Component
public class BankCardDao {
	private static Logger logger = Logger.getLogger(BankCardDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//新增银行卡
	public int addBankCard(final BankCardReq req){
		try{
			if(req == null){
				return 0;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("insert into sys_bank_card (card_num,bank_name,account_bank,card_type,relation_id,remark,data_status,create_time,update_time,card_classify");
			sql.append(" ) values ( ");
			sql.append("?,?,?,?,?,?,?,?,?,?)");
			logger.info("BankCardDao.addBankCard sql = " + sql.toString());
			final String sqlStr = sql.toString();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection conn)  
			         throws SQLException {  
			         PreparedStatement ps = conn.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS); 
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			       	 String date = sdf.format(new Date());
			         ps.setString(1, req.getCardNum());
			         ps.setString(2, req.getBankName());
			         ps.setString(3, req.getAccountBank());
			         ps.setString(4, req.getCardType());
			         ps.setInt(5, req.getRelationId());
			         ps.setString(6, req.getRemark());
			         ps.setInt(7, Constants.DATA_STATUS_NORMAL);
			         ps.setString(8, date);
			         ps.setString(9, date);
			         ps.setString(10, req.getCardClassify());
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
	
	//修改银行卡
	public int modifyBankCard(BankCardReq req){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_bank_card set update_time='"+date+"' ");
			if(req.getCardNum()!=null && !"".equals(req.getCardNum())){
				sql.append(" ,card_num='"+req.getCardNum()+"' ");
			}
			if(req.getBankName()!=null && !"".equals(req.getBankName())){
				sql.append(" ,bank_name='"+req.getBankName()+"' ");
			}
			if(req.getAccountBank()!=null && !"".equals(req.getAccountBank())){
				sql.append(" ,account_bank='"+req.getAccountBank()+"'");
			}
			if(req.getRemark()!=null && !"".equals(req.getRemark())){
				sql.append(" ,remark='"+req.getRemark()+"' ");
			}
			if(req.getCardClassify()!=null && !"".equals(req.getCardClassify())){
				sql.append(" ,card_classify='"+req.getCardClassify()+"' ");
			}
			sql.append(" where card_id="+req.getCardId());
			logger.info("BankCardDao.modifyBankCard sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//逻辑删除银行卡
	public int delBankCard(int cardId){
		StringBuffer sql = new StringBuffer();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       	String date = sdf.format(new Date());
			sql.append("update sys_bank_card set update_time='"+date+"', data_status="+Constants.DATA_STATUS_DELETE);
			sql.append(" where card_id="+cardId);
			logger.info("BankCardDao.delBankCard sql = " + sql.toString());
			return jdbcTemplate.update(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	//银行卡信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BankCardDto bankCardDetailById(int cardId){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_bank_card a ");
			sql.append(" WHERE a.card_id="+cardId);
			logger.info("BankCardDao.bankCardDetailById sql = " + sql.toString());
			return (BankCardDto)jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(BankCardDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//银行卡列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BankCardDto> bankCardList(BankCardDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT a.* ");
			sql.append(" FROM sys_bank_card a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCardType()!=null && !"".equals(req.getCardType())){
				sql.append(" and a.card_type like '%"+req.getCardType()+"%' ");
			}
			if(req.getRelationId()!=0){
				sql.append(" and a.relation_id="+req.getRelationId());
			}
			sql.append(" ORDER BY a.create_time DESC ");
			sql.append(" limit ").append(req.getStart()).append(",").append(req.getLength());
			logger.info("BankCardDao.bankCardList sql = " + sql.toString());
		    return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(BankCardDto.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//银行卡列表长度
	public int bankCardCount(BankCardDataTablesReq req){
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(a.card_id) ");
			sql.append(" FROM sys_bank_card a ");
			sql.append(" WHERE a.data_status!="+Constants.DATA_STATUS_DELETE);
			//筛选条件
			if(req.getCardType()!=null && !"".equals(req.getCardType())){
				sql.append(" and a.card_type like '%"+req.getCardType()+"%' ");
			}
			if(req.getRelationId()!=0){
				sql.append(" and a.relation_id="+req.getRelationId());
			}
			logger.info("BankCardDao.bankCardCount sql = " + sql.toString());
		    return jdbcTemplate.queryForInt(sql.toString());
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
