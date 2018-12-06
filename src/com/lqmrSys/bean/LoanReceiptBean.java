package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoanReceiptBean {
	@ApiModelProperty(value = "借据表ID")
	private int loanReceiptId;
	@ApiModelProperty(value = "借据号")
	private String loanReceiptCode;
	@ApiModelProperty(value = "融资配对信息表ID")
	private int financingMatchingId;
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
	public int getLoanReceiptId() {
		return loanReceiptId;
	}
	public void setLoanReceiptId(int loanReceiptId) {
		this.loanReceiptId = loanReceiptId;
	}
	public String getLoanReceiptCode() {
		return loanReceiptCode;
	}
	public void setLoanReceiptCode(String loanReceiptCode) {
		this.loanReceiptCode = loanReceiptCode;
	}
	public int getFinancingMatchingId() {
		return financingMatchingId;
	}
	public void setFinancingMatchingId(int financingMatchingId) {
		this.financingMatchingId = financingMatchingId;
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
