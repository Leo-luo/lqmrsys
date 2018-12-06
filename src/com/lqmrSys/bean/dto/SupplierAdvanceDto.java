package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SupplierAdvanceDto {
	@ApiModelProperty(value = "预登记ID")
	private int advanceId;
	@ApiModelProperty(value = "预登记编号")
	private String advanceCode;
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	@ApiModelProperty(value = "投资方式")
	private String investmentMode;
	@ApiModelProperty(value = "证件类型")
	private String certificateType;
	@ApiModelProperty(value = "证件号")
	private String certificateNum;
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	@ApiModelProperty(value = "联系地址")
	private String contactAddress;
	@ApiModelProperty(value = "登记时间")
	private String registerTime;
	@ApiModelProperty(value = "登记金额")
	private double registerAmount;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "利率")
	private double monthInterestRate;
	@ApiModelProperty(value = "供应期限")
	private String supplyTerm;
	@ApiModelProperty(value = "资金到位速度")
	private String capitalReachTime;
	@ApiModelProperty(value = "客户经理ID")
	private int userId;
	@ApiModelProperty(value = "客户经理名称")
	private String userName;
	@ApiModelProperty(value = "通知时间")
	private String noticeTime;
	@ApiModelProperty(value = "其它要求")
	private String otherRequirement;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAdvanceCode() {
		return advanceCode;
	}
	public void setAdvanceCode(String advanceCode) {
		this.advanceCode = advanceCode;
	}
	public int getAdvanceId() {
		return advanceId;
	}
	public void setAdvanceId(int advanceId) {
		this.advanceId = advanceId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getInvestmentMode() {
		return investmentMode;
	}
	public void setInvestmentMode(String investmentMode) {
		this.investmentMode = investmentMode;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public double getRegisterAmount() {
		return registerAmount;
	}
	public void setRegisterAmount(double registerAmount) {
		this.registerAmount = registerAmount;
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
	public double getMonthInterestRate() {
		return monthInterestRate;
	}
	public void setMonthInterestRate(double monthInterestRate) {
		this.monthInterestRate = monthInterestRate;
	}
	public String getSupplyTerm() {
		return supplyTerm;
	}
	public void setSupplyTerm(String supplyTerm) {
		this.supplyTerm = supplyTerm;
	}
	public String getCapitalReachTime() {
		return capitalReachTime;
	}
	public void setCapitalReachTime(String capitalReachTime) {
		this.capitalReachTime = capitalReachTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getOtherRequirement() {
		return otherRequirement;
	}
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
