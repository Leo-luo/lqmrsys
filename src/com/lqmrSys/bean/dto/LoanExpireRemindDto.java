package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoanExpireRemindDto {
	@ApiModelProperty(value = "借据号")
	private String loanReceiptCode;
	@ApiModelProperty(value = "还款类型:本息/服务费")
	private String repaymentType;
	@ApiModelProperty(value = "融资需求方")
	private String demanderName;
	@ApiModelProperty(value = "客户经理A")
	private String manageraName;
	@ApiModelProperty(value = "客户经理B")
	private String managerbName;
	@ApiModelProperty(value = "计划还款开始时间")
	private String planStartTime;
	@ApiModelProperty(value = "接话还款截止时间")
	private String planEndTime;
	@ApiModelProperty(value = "应还本金")
	private double planPrincipalAmount;
	@ApiModelProperty(value = "应还利息")
	private double planInterestAmount;
	@ApiModelProperty(value = "应还服务费")
	private double planServiceAmount;
	@ApiModelProperty(value = "总金额")
	private double totalAmount;
	public String getLoanReceiptCode() {
		return loanReceiptCode;
	}
	public void setLoanReceiptCode(String loanReceiptCode) {
		this.loanReceiptCode = loanReceiptCode;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getDemanderName() {
		return demanderName;
	}
	public void setDemanderName(String demanderName) {
		this.demanderName = demanderName;
	}
	public String getManageraName() {
		return manageraName;
	}
	public void setManageraName(String manageraName) {
		this.manageraName = manageraName;
	}
	public String getManagerbName() {
		return managerbName;
	}
	public void setManagerbName(String managerbName) {
		this.managerbName = managerbName;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public double getPlanPrincipalAmount() {
		return planPrincipalAmount;
	}
	public void setPlanPrincipalAmount(double planPrincipalAmount) {
		this.planPrincipalAmount = planPrincipalAmount;
	}
	public double getPlanInterestAmount() {
		return planInterestAmount;
	}
	public void setPlanInterestAmount(double planInterestAmount) {
		this.planInterestAmount = planInterestAmount;
	}
	public double getPlanServiceAmount() {
		return planServiceAmount;
	}
	public void setPlanServiceAmount(double planServiceAmount) {
		this.planServiceAmount = planServiceAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
