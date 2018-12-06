package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RefuseDto {
	@ApiModelProperty(value = "拒贷ID")
	private int refuseId;
	@ApiModelProperty(value = "客户/企业ID")
	private int dataId;
	@ApiModelProperty(value = "来源：公司(sys_company),个人(sys_customer)")
	private String dataFrom;
	@ApiModelProperty(value = "名称")
	private String dataName;
	@ApiModelProperty(value = "证件号")
	private String dataCertificate;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
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
	public int getRefuseId() {
		return refuseId;
	}
	public void setRefuseId(int refuseId) {
		this.refuseId = refuseId;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataCertificate() {
		return dataCertificate;
	}
	public void setDataCertificate(String dataCertificate) {
		this.dataCertificate = dataCertificate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
