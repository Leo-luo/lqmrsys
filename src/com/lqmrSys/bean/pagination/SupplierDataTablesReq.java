package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class SupplierDataTablesReq {
	@ApiModelProperty(value = "供应方自定义编号")
	private String supplierCode;
	@ApiModelProperty(value = "自定义合同号")
	private String contractCode;
	@ApiModelProperty(value = "供应人名称")
	private String customerName;
	@ApiModelProperty(value = "客户经理名称")
	private String userName;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
    @ApiParam(required = true, name = "operatorId", value = "操作人员ID")
    private int operatorId;
    
    //配对列表筛选字段
    @ApiModelProperty(value = "余额开始数值")
    private double supplierBalanceStart;
    @ApiModelProperty(value = "余额结束数值")
    private double supplierBalanceEnd;
    @ApiModelProperty(value = "排序字段：供应金额/供应余额/供应开始日期/供应结束日期")
    private String orderField;
    @ApiModelProperty(value = "排序方式：升序/降序")
    private String sortType;
    
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public double getSupplierBalanceStart() {
		return supplierBalanceStart;
	}
	public void setSupplierBalanceStart(double supplierBalanceStart) {
		this.supplierBalanceStart = supplierBalanceStart;
	}
	public double getSupplierBalanceEnd() {
		return supplierBalanceEnd;
	}
	public void setSupplierBalanceEnd(double supplierBalanceEnd) {
		this.supplierBalanceEnd = supplierBalanceEnd;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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
