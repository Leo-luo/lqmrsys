package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class GuaranteeDto {
	@ApiModelProperty(value = "担保表ID")
	private int guaranteeId;
	@ApiModelProperty(value = "担保人ID")
	private int guaranteePeopleId;
	@ApiModelProperty(value = "担保人名称")
	private String guaranteePeopleName;
	@ApiModelProperty(value = "担保人类型")
	private String guaranteePeopleType;
	@ApiModelProperty(value = "担保方式")
	private String guaranteeMode;
	@ApiModelProperty(value = "担保合同编号")
	private String guaranteeCode;
	@ApiModelProperty(value = "担保到期日期")
	private String guaranteeEndTime;
	@ApiModelProperty(value = "资金账号ID")
	private int cardId;
	@ApiModelProperty(value = "保证金额")
	private double guaranteeAmount;
	@ApiModelProperty(value = "融资配对信息表ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "融资配对信息合同表ID")
	private int contractId;
	@ApiModelProperty(value = "自定义担保合同编号")
	private String customGuaranteeCode;
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
	@ApiModelProperty(value = "担保物名称")
	private String guaranteeThingName;
	@ApiModelProperty(value = "担保物类型")
	private String guaranteeThingType;
	@ApiModelProperty(value = "他项权利证号")
	private String otherCertificatesNum;
	@ApiModelProperty(value = "评估机构")
	private String evaluationAgency;
	@ApiModelProperty(value = "评估价值")
	private String evaluateValue;
	@ApiModelProperty(value = "抵押价值")
	private String mortgageValue;
	@ApiModelProperty(value = "数量")
	private String guaranteeThingNum;
	@ApiModelProperty(value = "计量单位")
	private String meteringUnit;
	@ApiModelProperty(value = "登记机构")
	private String registerAgency;
	public String getOtherCertificatesNum() {
		return otherCertificatesNum;
	}
	public void setOtherCertificatesNum(String otherCertificatesNum) {
		this.otherCertificatesNum = otherCertificatesNum;
	}
	public String getGuaranteeThingName() {
		return guaranteeThingName;
	}
	public void setGuaranteeThingName(String guaranteeThingName) {
		this.guaranteeThingName = guaranteeThingName;
	}
	public String getGuaranteeThingType() {
		return guaranteeThingType;
	}
	public void setGuaranteeThingType(String guaranteeThingType) {
		this.guaranteeThingType = guaranteeThingType;
	}
	public String getEvaluationAgency() {
		return evaluationAgency;
	}
	public void setEvaluationAgency(String evaluationAgency) {
		this.evaluationAgency = evaluationAgency;
	}
	public String getEvaluateValue() {
		return evaluateValue;
	}
	public void setEvaluateValue(String evaluateValue) {
		this.evaluateValue = evaluateValue;
	}
	public String getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(String mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	public String getGuaranteeThingNum() {
		return guaranteeThingNum;
	}
	public void setGuaranteeThingNum(String guaranteeThingNum) {
		this.guaranteeThingNum = guaranteeThingNum;
	}
	public String getMeteringUnit() {
		return meteringUnit;
	}
	public void setMeteringUnit(String meteringUnit) {
		this.meteringUnit = meteringUnit;
	}
	public String getRegisterAgency() {
		return registerAgency;
	}
	public void setRegisterAgency(String registerAgency) {
		this.registerAgency = registerAgency;
	}
	public String getGuaranteePeopleName() {
		return guaranteePeopleName;
	}
	public void setGuaranteePeopleName(String guaranteePeopleName) {
		this.guaranteePeopleName = guaranteePeopleName;
	}
	public int getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(int guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public int getGuaranteePeopleId() {
		return guaranteePeopleId;
	}
	public void setGuaranteePeopleId(int guaranteePeopleId) {
		this.guaranteePeopleId = guaranteePeopleId;
	}
	public String getGuaranteePeopleType() {
		return guaranteePeopleType;
	}
	public void setGuaranteePeopleType(String guaranteePeopleType) {
		this.guaranteePeopleType = guaranteePeopleType;
	}
	public String getGuaranteeMode() {
		return guaranteeMode;
	}
	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	public String getGuaranteeCode() {
		return guaranteeCode;
	}
	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}
	public String getGuaranteeEndTime() {
		return guaranteeEndTime;
	}
	public void setGuaranteeEndTime(String guaranteeEndTime) {
		this.guaranteeEndTime = guaranteeEndTime;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public double getGuaranteeAmount() {
		return guaranteeAmount;
	}
	public void setGuaranteeAmount(double guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
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
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getCustomGuaranteeCode() {
		return customGuaranteeCode;
	}
	public void setCustomGuaranteeCode(String customGuaranteeCode) {
		this.customGuaranteeCode = customGuaranteeCode;
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
