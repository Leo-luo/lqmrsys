package com.lqmrSys.bean.dto;

import java.util.List;

import com.lqmrSys.bean.SupplierMatchingBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class FinancingMatchingDto {
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "配对登记编号")
	private String matchingCode;
	@ApiModelProperty(value = "融资类型")
	private String financingType;
	@ApiModelProperty(value = "需求方ID")
	private int demandId;
	@ApiModelProperty(value = "需求方名称")
	private String demandName;
	@ApiModelProperty(value = "融资金额")
	private double financingAmount;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "月利率(自有资金)")
	private double ownMonthRate;
	@ApiModelProperty(value = "服务费用利率(自有资金)")
	private double ownServiceRate;
	@ApiModelProperty(value = "月利率(供应方资金)")
	private double supplierMonthRate;
	@ApiModelProperty(value = "服务费用利率(供应方资金)")
	private double supplierServiceRate;
	@ApiModelProperty(value = "逾期利率上浮百分比")
	private double overdueRate;
	@ApiModelProperty(value = "还款方式")
	private String repaymentMode;
	@ApiModelProperty(value = "结息周期")
	private String interestSettlementCycle;
	@ApiModelProperty(value = "服务费支付方式")
	private String servicePayMode;
	@ApiModelProperty(value = "服务费支付日")
	private int servicePayDate;
	@ApiModelProperty(value = "提供担保方式")
	private String guaranteeMode;
	@ApiModelProperty(value = "借款用途分类")
	private String loanPurposeClassify;
	@ApiModelProperty(value = "资金账号ID")
	private int cardId;
	@ApiModelProperty(value = "资金账号详情类")
	private BankCardDto bankCardBean;
	@ApiModelProperty(value = "借款用途摘要")
	private String loanPurposeRemark;
	@ApiModelProperty(value = "登记日期")
	private String registerDate;
	@ApiModelProperty(value = "主管客户经理AID")
	private int useraId;
	@ApiModelProperty(value = "主管客户经理A名称")
	private String useraName;
	@ApiModelProperty(value = "主管客户经理BID")
	private int userbId;
	@ApiModelProperty(value = "主管客户经理B名称")
	private String userbName;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "配对关联表列表")
	private List<SupplierMatchingBean> supplierList;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "配对状态")
	private int financingMatchingStatus;
	public int getFinancingMatchingStatus() {
		return financingMatchingStatus;
	}
	public void setFinancingMatchingStatus(int financingMatchingStatus) {
		this.financingMatchingStatus = financingMatchingStatus;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
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
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
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
	public double getOwnMonthRate() {
		return ownMonthRate;
	}
	public void setOwnMonthRate(double ownMonthRate) {
		this.ownMonthRate = ownMonthRate;
	}
	public double getOwnServiceRate() {
		return ownServiceRate;
	}
	public void setOwnServiceRate(double ownServiceRate) {
		this.ownServiceRate = ownServiceRate;
	}
	public double getSupplierMonthRate() {
		return supplierMonthRate;
	}
	public void setSupplierMonthRate(double supplierMonthRate) {
		this.supplierMonthRate = supplierMonthRate;
	}
	public double getSupplierServiceRate() {
		return supplierServiceRate;
	}
	public void setSupplierServiceRate(double supplierServiceRate) {
		this.supplierServiceRate = supplierServiceRate;
	}
	public double getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(double overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	public String getInterestSettlementCycle() {
		return interestSettlementCycle;
	}
	public void setInterestSettlementCycle(String interestSettlementCycle) {
		this.interestSettlementCycle = interestSettlementCycle;
	}
	public String getServicePayMode() {
		return servicePayMode;
	}
	public void setServicePayMode(String servicePayMode) {
		this.servicePayMode = servicePayMode;
	}
	public int getServicePayDate() {
		return servicePayDate;
	}
	public void setServicePayDate(int servicePayDate) {
		this.servicePayDate = servicePayDate;
	}
	public String getGuaranteeMode() {
		return guaranteeMode;
	}
	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	public String getLoanPurposeClassify() {
		return loanPurposeClassify;
	}
	public void setLoanPurposeClassify(String loanPurposeClassify) {
		this.loanPurposeClassify = loanPurposeClassify;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public BankCardDto getBankCardBean() {
		return bankCardBean;
	}
	public void setBankCardBean(BankCardDto bankCardBean) {
		this.bankCardBean = bankCardBean;
	}
	public String getLoanPurposeRemark() {
		return loanPurposeRemark;
	}
	public void setLoanPurposeRemark(String loanPurposeRemark) {
		this.loanPurposeRemark = loanPurposeRemark;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
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
	public List<SupplierMatchingBean> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<SupplierMatchingBean> supplierList) {
		this.supplierList = supplierList;
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
}
