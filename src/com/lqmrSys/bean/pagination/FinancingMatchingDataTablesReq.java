package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class FinancingMatchingDataTablesReq {
	@ApiModelProperty(value = "配对登记编号")
	private String matchingCode;
	@ApiModelProperty(value = "需求方名称")
	private String demandName;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
