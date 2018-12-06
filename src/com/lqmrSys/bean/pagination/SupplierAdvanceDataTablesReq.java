package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class SupplierAdvanceDataTablesReq {
	@ApiModelProperty(value = "预登记编号")
	private String advanceCode;
	@ApiModelProperty(value = "供应人名称")
	private String customerName;
	@ApiModelProperty(value = "客户经理名称")
	private String userName;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
	public String getAdvanceCode() {
		return advanceCode;
	}
	public void setAdvanceCode(String advanceCode) {
		this.advanceCode = advanceCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
