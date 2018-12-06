package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class LoanReceiptDataTablesReq {
	@ApiModelProperty(value = "借据号")
	private String loadReceiptCode;
	@ApiModelProperty(value = "合同编号")
	private String contractCode;
	@ApiModelProperty(value = "配对登记编号")
	private String matchingCode;
	@ApiModelProperty(value = "需求方名称")
	private String demandName;
	@ApiModelProperty(value = "证件号码")
	private String credentialsNum;
	@ApiModelProperty(value = "融资类型")
	private String financingType;
	@ApiModelProperty(value = "配对状态")
	private int financingMatchingStatus;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
	public String getLoadReceiptCode() {
		return loadReceiptCode;
	}
	public void setLoadReceiptCode(String loadReceiptCode) {
		this.loadReceiptCode = loadReceiptCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getMatchingCode() {
		return matchingCode;
	}
	public void setMatchingCode(String matchingCode) {
		this.matchingCode = matchingCode;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	public String getCredentialsNum() {
		return credentialsNum;
	}
	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
	public int getFinancingMatchingStatus() {
		return financingMatchingStatus;
	}
	public void setFinancingMatchingStatus(int financingMatchingStatus) {
		this.financingMatchingStatus = financingMatchingStatus;
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
