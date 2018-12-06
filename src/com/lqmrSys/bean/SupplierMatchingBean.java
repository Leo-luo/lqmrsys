package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SupplierMatchingBean {
	@ApiModelProperty(value = "配对关联表ID")
	private int matchingRelationId;
	@ApiModelProperty(value = "融资配对信息表ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "供应方ID")
	private int supplierId;
	@ApiModelProperty(value = "配对金额")
	private double matchingAmount;
	@ApiModelProperty(value = "配对类型:1-供应方 2-自有")
	private int matchingType;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "匹配开始日期")
	private String matchingStartTime;
	@ApiModelProperty(value = "匹配截止日期")
	private String matchingEndTime;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String endTime;
	public int getMatchingRelationId() {
		return matchingRelationId;
	}
	public void setMatchingRelationId(int matchingRelationId) {
		this.matchingRelationId = matchingRelationId;
	}
	public int getFinancingMatchingId() {
		return financingMatchingId;
	}
	public void setFinancingMatchingId(int financingMatchingId) {
		this.financingMatchingId = financingMatchingId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public double getMatchingAmount() {
		return matchingAmount;
	}
	public void setMatchingAmount(double matchingAmount) {
		this.matchingAmount = matchingAmount;
	}
	public int getMatchingType() {
		return matchingType;
	}
	public void setMatchingType(int matchingType) {
		this.matchingType = matchingType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMatchingStartTime() {
		return matchingStartTime;
	}
	public void setMatchingStartTime(String matchingStartTime) {
		this.matchingStartTime = matchingStartTime;
	}
	public String getMatchingEndTime() {
		return matchingEndTime;
	}
	public void setMatchingEndTime(String matchingEndTime) {
		this.matchingEndTime = matchingEndTime;
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
