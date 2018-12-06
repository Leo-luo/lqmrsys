package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class GuaranteeDataTablesReq {
	@ApiModelProperty(value = "担保合同编号")
	private String guaranteeCode;
	@ApiModelProperty(value = "自定义担保合同编号")
	private String customGuaranteeCode;
	@ApiModelProperty(value = "担保人名称")
	private String guaranteePeopleName;
	@ApiModelProperty(value = "融资配对信息合同表ID")
	private int contractId;
	@ApiModelProperty(value = "融资配对信息表ID")
	private int financingMatchingId;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
	public String getGuaranteeCode() {
		return guaranteeCode;
	}
	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}
	public String getCustomGuaranteeCode() {
		return customGuaranteeCode;
	}
	public void setCustomGuaranteeCode(String customGuaranteeCode) {
		this.customGuaranteeCode = customGuaranteeCode;
	}
	public String getGuaranteePeopleName() {
		return guaranteePeopleName;
	}
	public void setGuaranteePeopleName(String guaranteePeopleName) {
		this.guaranteePeopleName = guaranteePeopleName;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
}
