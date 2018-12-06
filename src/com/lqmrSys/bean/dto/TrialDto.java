package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TrialDto {
	@ApiModelProperty(value = "审核审批表ID")
	private int trialId;
	@ApiModelProperty(value = "审核/审批人ID")
	private int userId;
	@ApiModelProperty(value = "审核/审批人名称")
	private String userName;
	@ApiModelProperty(value = "类型:需求表(sys_demand),配对表(sys_financing_matching)")
	private String trialType;
	@ApiModelProperty(value = "审核/审批状态:0-待审核/审批 1-审核/审批通过 3-审核/审批不通过")
	private int trialStatus;
	@ApiModelProperty(value = "节点")
	private int node;
	@ApiModelProperty(value = "审核节点")
	private int trialNode;
	@ApiModelProperty(value = "终点节点")
	private int endNode;
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
	@ApiModelProperty(value = "审核/审批数据ID")
	private int trialDataId;
	@ApiModelProperty(value = "需求方数据详情")
	private DemandDto demandDetail;
	@ApiModelProperty(value = "借款详情")
	private LoanReceiptDtob loanReceiptDetail;
	public LoanReceiptDtob getLoanReceiptDetail() {
		return loanReceiptDetail;
	}
	public void setLoanReceiptDetail(LoanReceiptDtob loanReceiptDetail) {
		this.loanReceiptDetail = loanReceiptDetail;
	}
	public DemandDto getDemandDetail() {
		return demandDetail;
	}
	public void setDemandDetail(DemandDto demandDetail) {
		this.demandDetail = demandDetail;
	}
	public int getTrialDataId() {
		return trialDataId;
	}
	public void setTrialDataId(int trialDataId) {
		this.trialDataId = trialDataId;
	}
	public int getTrialId() {
		return trialId;
	}
	public void setTrialId(int trialId) {
		this.trialId = trialId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrialType() {
		return trialType;
	}
	public void setTrialType(String trialType) {
		this.trialType = trialType;
	}
	public int getTrialStatus() {
		return trialStatus;
	}
	public void setTrialStatus(int trialStatus) {
		this.trialStatus = trialStatus;
	}
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public int getTrialNode() {
		return trialNode;
	}
	public void setTrialNode(int trialNode) {
		this.trialNode = trialNode;
	}
	public int getEndNode() {
		return endNode;
	}
	public void setEndNode(int endNode) {
		this.endNode = endNode;
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
