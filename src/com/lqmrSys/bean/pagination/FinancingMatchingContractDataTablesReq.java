package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class FinancingMatchingContractDataTablesReq {
	@ApiModelProperty(value = "融资配对信息ID")
	private int financingMatchingId;
	@ApiModelProperty(value = "合同编号")
	private String contractCode;
	@ApiModelProperty(value = "自定义合同编号")
	private String customContractCode;
	@ApiModelProperty(value = "合同类型")
	private String contractType;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
	public String getCustomContractCode() {
		return customContractCode;
	}
	public void setCustomContractCode(String customContractCode) {
		this.customContractCode = customContractCode;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
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
