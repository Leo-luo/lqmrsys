package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RepaymentBean {
	@ApiModelProperty(value = "还款表ID")
	private int repaymentId;
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "借据表ID")
	private int loanReceiptId;
	@ApiModelProperty(value = "计划还款开始时间")
	private String planStartTime;
	@ApiModelProperty(value = "计划还款截止时间")
	private String planEndTime;
	@ApiModelProperty(value = "计划还款本金金额")
	private double planPrincipalAmount;
	@ApiModelProperty(value = "计划还款利息金额")
	private double planInterestAmount;
	@ApiModelProperty(value = "计划还款服务金额")
	private double planServiceAmount;
	@ApiModelProperty(value = "借款利率")
	private double interestRate;
	@ApiModelProperty(value = "服务利率")
	private double serviceRate;
	@ApiModelProperty(value = "实际还款本金金额")
	private double actualPrincipalAmount;
	@ApiModelProperty(value = "实际还款利息金额")
	private double actualInterestAmount;
	@ApiModelProperty(value = "实际还款服务金额")
	private double actualServiceAmount;
	@ApiModelProperty(value = "实际还款日期")
	private String payTime;
	@ApiModelProperty(value = "还款状态:1-未还款 3-已还款")
	private int payStatus;
	@ApiModelProperty(value = "数据状态：1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "还款类型:本息/服务费")
	private String repaymentType;
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public int getRepaymentId() {
		return repaymentId;
	}
	public void setRepaymentId(int repaymentId) {
		this.repaymentId = repaymentId;
	}
	public int getFinancingMatchingId() {
		return financingMatchingId;
	}
	public void setFinancingMatchingId(int financingMatchingId) {
		this.financingMatchingId = financingMatchingId;
	}
	public int getLoanReceiptId() {
		return loanReceiptId;
	}
	public void setLoanReceiptId(int loanReceiptId) {
		this.loanReceiptId = loanReceiptId;
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
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}
	public double getActualPrincipalAmount() {
		return actualPrincipalAmount;
	}
	public void setActualPrincipalAmount(double actualPrincipalAmount) {
		this.actualPrincipalAmount = actualPrincipalAmount;
	}
	public double getActualInterestAmount() {
		return actualInterestAmount;
	}
	public void setActualInterestAmount(double actualInterestAmount) {
		this.actualInterestAmount = actualInterestAmount;
	}
	public double getActualServiceAmount() {
		return actualServiceAmount;
	}
	public void setActualServiceAmount(double actualServiceAmount) {
		this.actualServiceAmount = actualServiceAmount;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public int getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
