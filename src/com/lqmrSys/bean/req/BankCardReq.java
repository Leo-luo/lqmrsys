package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class BankCardReq extends OperateBean{
	@ApiModelProperty(value = "银行卡ID")
	private int cardId;
	@ApiModelProperty(value = "银行卡号")
	private String cardNum;
	@ApiModelProperty(value = "所属银行")
	private String bankName;
	@ApiModelProperty(value = "开户行")
	private String accountBank;
	@ApiModelProperty(value = "银行卡类型:自然人(sys_customer),企业(sys_company),供应方(sys_supply),需求方(sys_demand)")
	private String cardType;
	@ApiModelProperty(value = "关联ID")
	private int relationId;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "银行卡分类：本金、收息")
	private String cardClassify;
	public String getCardClassify() {
		return cardClassify;
	}
	public void setCardClassify(String cardClassify) {
		this.cardClassify = cardClassify;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
