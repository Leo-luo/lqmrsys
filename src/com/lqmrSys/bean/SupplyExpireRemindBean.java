package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SupplyExpireRemindBean {
	@ApiModelProperty(value = "供应方ID")
	private int supplierId;
	@ApiModelProperty(value = "融资供应编号")
	private String supplierCode;
	@ApiModelProperty(value = "融资供应方")
	private String supplierName;
	@ApiModelProperty(value = "客户经理")
	private String managerName;
	@ApiModelProperty(value = "金额")
	private double supplyAmount;
	@ApiModelProperty(value = "结算类型")
	private String settlementType;
	@ApiModelProperty(value = "利率‰")
	private double monthInterestRate;
	@ApiModelProperty(value = "委托出借开始日")
	private String supplyStartTime;
	@ApiModelProperty(value = "委托出借到期日")
	private String supplyEndTime;
	@ApiModelProperty(value = "实际计息开始日字符串")
	private String matchingStartTimeStr;
	@ApiModelProperty(value = "实际计息截止日字符串")
	private String matchingEndTimeStr;
	@ApiModelProperty(value = "风险标识ID")
	private int signId;
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public double getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(double supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}
	public double getMonthInterestRate() {
		return monthInterestRate;
	}
	public void setMonthInterestRate(double monthInterestRate) {
		this.monthInterestRate = monthInterestRate;
	}
	public String getSupplyStartTime() {
		return supplyStartTime;
	}
	public void setSupplyStartTime(String supplyStartTime) {
		this.supplyStartTime = supplyStartTime;
	}
	public String getSupplyEndTime() {
		return supplyEndTime;
	}
	public void setSupplyEndTime(String supplyEndTime) {
		this.supplyEndTime = supplyEndTime;
	}
	public String getMatchingStartTimeStr() {
		return matchingStartTimeStr;
	}
	public void setMatchingStartTimeStr(String matchingStartTimeStr) {
		this.matchingStartTimeStr = matchingStartTimeStr;
	}
	public String getMatchingEndTimeStr() {
		return matchingEndTimeStr;
	}
	public void setMatchingEndTimeStr(String matchingEndTimeStr) {
		this.matchingEndTimeStr = matchingEndTimeStr;
	}
}
