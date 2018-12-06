package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SupplierDto {
	@ApiModelProperty(value = "供应方ID")
	private int supplierId;
	@ApiModelProperty(value = "供应方自定义编号")
	private String supplierCode;
	@ApiModelProperty(value = "自定义合同号")
	private String contractCode;
	@ApiModelProperty(value = "供应人ID")
	private int customerId;
	@ApiModelProperty(value = "供应人名称")
	private String customerName;
	@ApiModelProperty(value = "供应金额")
	private double supplyAmount;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "月利率")
	private double monthInterestRate;
	@ApiModelProperty(value = "放款宽限天数")
	private int graceDays;
	@ApiModelProperty(value = "投资方式(数据字典)")
	private String investmentMode;
	@ApiModelProperty(value = "投资信息来源渠道(数据字典)")
	private String investmentInfoChannel;
	@ApiModelProperty(value = "结息周期(数据字典)")
	private String interestSettlementCycle;
	@ApiModelProperty(value = "资金账号ID")
	private int cardId;
	@ApiModelProperty(value = "账号类型(数据字典)")
	private String cardType;
	@ApiModelProperty(value = "签约分类(数据字典)")
	private String contractType;
	@ApiModelProperty(value = "客户经理ID")
	private int userId;
	@ApiModelProperty(value = "客户经理名称")
	private String userName;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "数据状态：1-正常 3删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "供应状态")
	private int supplierStatus;
	@ApiModelProperty(value = "供应余额")
	private double supplierBalance;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "供应人类型：公司(sys_company) 客户(sys_customer)")
	private String supplierType;
	@ApiModelProperty(value = "风险标识ID")
	private int signId;
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(double supplyAmount) {
		this.supplyAmount = supplyAmount;
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
	public int getGraceDays() {
		return graceDays;
	}
	public void setGraceDays(int graceDays) {
		this.graceDays = graceDays;
	}
	public String getInvestmentMode() {
		return investmentMode;
	}
	public void setInvestmentMode(String investmentMode) {
		this.investmentMode = investmentMode;
	}
	public String getInvestmentInfoChannel() {
		return investmentInfoChannel;
	}
	public void setInvestmentInfoChannel(String investmentInfoChannel) {
		this.investmentInfoChannel = investmentInfoChannel;
	}
	public String getInterestSettlementCycle() {
		return interestSettlementCycle;
	}
	public void setInterestSettlementCycle(String interestSettlementCycle) {
		this.interestSettlementCycle = interestSettlementCycle;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getSupplierStatus() {
		return supplierStatus;
	}
	public void setSupplierStatus(int supplierStatus) {
		this.supplierStatus = supplierStatus;
	}
	public double getSupplierBalance() {
		return supplierBalance;
	}
	public void setSupplierBalance(double supplierBalance) {
		this.supplierBalance = supplierBalance;
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
