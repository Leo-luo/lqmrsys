package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoanReceiptDto {
	@ApiModelProperty(value = "借据表ID")
	private int loadReceiptId;
	@ApiModelProperty(value = "借据号")
	private String loadReceiptCode;
	@ApiModelProperty(value = "融资配对信息合同ID")
	private int contractId;
	@ApiModelProperty(value = "合同编号")
	private String contractCode;
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "配对登记编号")
	private String matchingCode;
	@ApiModelProperty(value = "需求方ID")
	private int demandId;
	@ApiModelProperty(value = "需求方名称")
	private String demandName;
	@ApiModelProperty(value = "证件号码")
	private String credentialsNum;
	@ApiModelProperty(value = "融资类型")
	private String financingType;
	@ApiModelProperty(value = "登记日期")
	private String registerDate;
	@ApiModelProperty(value = "融资金额")
	private double financingAmount;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "配对状态")
	private int financingMatchingStatus;
	public int getLoadReceiptId() {
		return loadReceiptId;
	}
	public void setLoadReceiptId(int loadReceiptId) {
		this.loadReceiptId = loadReceiptId;
	}
	public String getLoadReceiptCode() {
		return loadReceiptCode;
	}
	public void setLoadReceiptCode(String loadReceiptCode) {
		this.loadReceiptCode = loadReceiptCode;
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
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	public String getCredentialsNum() {
		return credentialsNum;
	}
	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public double getFinancingAmount() {
		return financingAmount;
	}
	public void setFinancingAmount(double financingAmount) {
		this.financingAmount = financingAmount;
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
	public int getFinancingMatchingStatus() {
		return financingMatchingStatus;
	}
	public void setFinancingMatchingStatus(int financingMatchingStatus) {
		this.financingMatchingStatus = financingMatchingStatus;
	}
}
