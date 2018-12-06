package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DemandDto {
	@ApiModelProperty(value = "需求方ID")
	private int demandId;
	@ApiModelProperty(value = "需求方自定义编码")
	private String demandCode;
	@ApiModelProperty(value = "融资类型(数据字典)")
	private String financingType;
	@ApiModelProperty(value = "需求人ID")
	private int demanderId;
	@ApiModelProperty(value = "需求人名称")
	private String demanderName;
	@ApiModelProperty(value = "需求方类型(公司<sys_company>、人<sys_customer>)")
	private String demanderType;
	@ApiModelProperty(value = "还款方式(数据字典)")
	private String repaymentType;
	@ApiModelProperty(value = "需求金额")
	private double demandAmount;
	@ApiModelProperty(value = "结息周期")
	private String interestSettlementCycle;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "有效截止日期")
	private String effectiveEndTime;
	@ApiModelProperty(value = "利率")
	private double interestRate;
	@ApiModelProperty(value = "到账日期")
	private String reachAccountTime;
	@ApiModelProperty(value = "借款期限(月单位)")
	private int loanTerm;
	@ApiModelProperty(value = "客户经理AID")
	private int useraId;
	@ApiModelProperty(value = "客户经理A名称")
	private String useraName;
	@ApiModelProperty(value = "客户经理BID")
	private int userbId;
	@ApiModelProperty(value = "客户经理B名称")
	private String userbName;
	@ApiModelProperty(value = "资金账号ID")
	private int cardId;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "数据状态：1-正常 3删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "需求状态：1-待配对 2-已配对 3-已开户 4-融资成功 5-作废")
	private int demandStatus;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "风险标记ID")
	private int signId;
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public String getDemandCode() {
		return demandCode;
	}
	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
	public int getDemanderId() {
		return demanderId;
	}
	public void setDemanderId(int demanderId) {
		this.demanderId = demanderId;
	}
	public String getDemanderName() {
		return demanderName;
	}
	public void setDemanderName(String demanderName) {
		this.demanderName = demanderName;
	}
	public String getDemanderType() {
		return demanderType;
	}
	public void setDemanderType(String demanderType) {
		this.demanderType = demanderType;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public double getDemandAmount() {
		return demandAmount;
	}
	public void setDemandAmount(double demandAmount) {
		this.demandAmount = demandAmount;
	}
	public String getInterestSettlementCycle() {
		return interestSettlementCycle;
	}
	public void setInterestSettlementCycle(String interestSettlementCycle) {
		this.interestSettlementCycle = interestSettlementCycle;
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
	public String getEffectiveEndTime() {
		return effectiveEndTime;
	}
	public void setEffectiveEndTime(String effectiveEndTime) {
		this.effectiveEndTime = effectiveEndTime;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String getReachAccountTime() {
		return reachAccountTime;
	}
	public void setReachAccountTime(String reachAccountTime) {
		this.reachAccountTime = reachAccountTime;
	}
	public int getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	public int getUseraId() {
		return useraId;
	}
	public void setUseraId(int useraId) {
		this.useraId = useraId;
	}
	public String getUseraName() {
		return useraName;
	}
	public void setUseraName(String useraName) {
		this.useraName = useraName;
	}
	public int getUserbId() {
		return userbId;
	}
	public void setUserbId(int userbId) {
		this.userbId = userbId;
	}
	public String getUserbName() {
		return userbName;
	}
	public void setUserbName(String userbName) {
		this.userbName = userbName;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
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
	public int getDemandStatus() {
		return demandStatus;
	}
	public void setDemandStatus(int demandStatus) {
		this.demandStatus = demandStatus;
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
