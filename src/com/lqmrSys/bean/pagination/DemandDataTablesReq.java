package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class DemandDataTablesReq {
	@ApiModelProperty(value = "需求方自定义编码")
	private String demandCode;
	@ApiModelProperty(value = "需求人名称")
	private String demanderName;
	@ApiModelProperty(value = "客户经理名称")
	private String userName;
	@ApiParam(required = false, name = "demanderType", value = "需求方类型(公司<sys_company>、人<sys_customer>)")
	private String demanderType;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
	public String getDemanderType() {
		return demanderType;
	}
	public void setDemanderType(String demanderType) {
		this.demanderType = demanderType;
	}
	public String getDemandCode() {
		return demandCode;
	}
	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}
	public String getDemanderName() {
		return demanderName;
	}
	public void setDemanderName(String demanderName) {
		this.demanderName = demanderName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
