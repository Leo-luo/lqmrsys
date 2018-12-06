package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoanReceiptDtob {
	@ApiModelProperty(value = "借据表ID")
	private int loanReceiptId;
	@ApiModelProperty(value = "借据号")
	private String loanReceiptCode;
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "配对登记编号")
	private String matchingCode;
	@ApiModelProperty(value = "融资配对信息合同ID")
	private int contractId;
	@ApiModelProperty(value = "合同编号")
	private String contractCode;
	@ApiModelProperty(value = "自定义合同编号")
	private String customContractCode;
	@ApiModelProperty(value = "融资需求登记表ID")
	private int demandId;
	@ApiModelProperty(value = "需求方ID")
	private String demanderId;
	@ApiModelProperty(value = "需求方名称")
	private String demanderName;
	@ApiModelProperty(value = "需求方证件号码")
	private String demanderIdcard;
	@ApiModelProperty(value = "金额")
	private double financingAmount;
	@ApiModelProperty(value = "余额")
	private double balanceAmount;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "融资类型")
	private String financingType;
	public int getLoanReceiptId() {
		return loanReceiptId;
	}
	public void setLoanReceiptId(int loanReceiptId) {
		this.loanReceiptId = loanReceiptId;
	}
	public String getLoanReceiptCode() {
		return loanReceiptCode;
	}
	public void setLoanReceiptCode(String loanReceiptCode) {
		this.loanReceiptCode = loanReceiptCode;
	}
	public int getFinancingMatchingId() {
		return financingMatchingId;
	}
	public void setFinancingMatchingId(int financingMatchingId) {
		this.financingMatchingId = financingMatchingId;
	}
	public String getMatchingCode() {
		return matchingCode;
	}
	public void setMatchingCode(String matchingCode) {
		this.matchingCode = matchingCode;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getCustomContractCode() {
		return customContractCode;
	}
	public void setCustomContractCode(String customContractCode) {
		this.customContractCode = customContractCode;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public String getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(String demanderId) {
		this.demanderId = demanderId;
	}
	public String getDemanderName() {
		return demanderName;
	}
	public void setDemanderName(String demanderName) {
		this.demanderName = demanderName;
	}
	public String getDemanderIdcard() {
		return demanderIdcard;
	}
	public void setDemanderIdcard(String demanderIdcard) {
		this.demanderIdcard = demanderIdcard;
	}
	public double getFinancingAmount() {
		return financingAmount;
	}
	public void setFinancingAmount(double financingAmount) {
		this.financingAmount = financingAmount;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
}
