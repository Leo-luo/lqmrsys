package com.lqmrSys.bean.dto;

import java.util.List;

import com.lqmrSys.bean.GuaranteeBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class FinancingMatchingContractDto {
	@ApiModelProperty(value = "融资配对信息合同ID")
	private int contractId;
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "合同编号")
	private String contractCode;
	@ApiModelProperty(value = "合同分类")
	private String contractClassify;
	@ApiModelProperty(value = "合同类型")
	private String contractType;
	@ApiModelProperty(value = "开始日期")
	private String startTime;
	@ApiModelProperty(value = "截止日期")
	private String endTime;
	@ApiModelProperty(value = "利率")
	private double contractRate;
	@ApiModelProperty(value = "服务费用利率")
	private double serviceRate;
	@ApiModelProperty(value = "逾期利率上浮百分比")
	private double overdueRate;
	@ApiModelProperty(value = "借款用途")
	private String loanPurpose;
	@ApiModelProperty(value = "合同金额")
	private double contractAmount;
	@ApiModelProperty(value = "还款方式")
	private String repaymentMode;
	@ApiModelProperty(value = "账户所属银行")
	private String accountBank;
	@ApiModelProperty(value = "账号")
	private String accountNum;
	@ApiModelProperty(value = "争议解决方式")
	private String disputeMode;
	@ApiModelProperty(value = "签约日期")
	private String signContractTime;
	@ApiModelProperty(value = "签约地点")
	private String signContractPlace;
	@ApiModelProperty(value = "签约人")
	private String signContractPeople;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "担保人列表")
	private List<GuaranteeBean> guaranteeList;
	public List<GuaranteeBean> getGuaranteeList() {
		return guaranteeList;
	}
	public void setGuaranteeList(List<GuaranteeBean> guaranteeList) {
		this.guaranteeList = guaranteeList;
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
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public int getFinancingMatchingId() {
		return financingMatchingId;
	}
	public void setFinancingMatchingId(int financingMatchingId) {
		this.financingMatchingId = financingMatchingId;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractClassify() {
		return contractClassify;
	}
	public void setContractClassify(String contractClassify) {
		this.contractClassify = contractClassify;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
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
	public double getContractRate() {
		return contractRate;
	}
	public void setContractRate(double contractRate) {
		this.contractRate = contractRate;
	}
	public double getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}
	public double getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(double overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public double getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(double contractAmount) {
		this.contractAmount = contractAmount;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getDisputeMode() {
		return disputeMode;
	}
	public void setDisputeMode(String disputeMode) {
		this.disputeMode = disputeMode;
	}
	public String getSignContractTime() {
		return signContractTime;
	}
	public void setSignContractTime(String signContractTime) {
		this.signContractTime = signContractTime;
	}
	public String getSignContractPlace() {
		return signContractPlace;
	}
	public void setSignContractPlace(String signContractPlace) {
		this.signContractPlace = signContractPlace;
	}
	public String getSignContractPeople() {
		return signContractPeople;
	}
	public void setSignContractPeople(String signContractPeople) {
		this.signContractPeople = signContractPeople;
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
}
