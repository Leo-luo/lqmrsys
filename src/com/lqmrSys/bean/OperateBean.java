package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiParam;

public class OperateBean {
	@ApiParam(required = true, name = "operatorId", value = "操作人员ID")
	private int operatorId;
	@ApiParam(required = true, name = "operatorName", value = "操作人员名称")
	private String operatorName;
	@ApiParam(required = false, name = "operateInterface", value = "接口地址")
	private String operateInterface;
	@ApiParam(required = true, name = "operateInterfaceName", value = "接口名称")
	private String operateInterfaceName;
	@ApiParam(required = false, name = "operateContent", value = "操作内容")
	private String operateContent;
	@ApiParam(required = false, name = "operateRemark", value = "备注")
	private String operateRemark;
	public String getOperateRemark() {
		return operateRemark;
	}
	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
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
	public String getOperateInterface() {
		return operateInterface;
	}
	public void setOperateInterface(String operateInterface) {
		this.operateInterface = operateInterface;
	}
	public String getOperateInterfaceName() {
		return operateInterfaceName;
	}
	public void setOperateInterfaceName(String operateInterfaceName) {
		this.operateInterfaceName = operateInterfaceName;
	}
	public String getOperateContent() {
		return operateContent;
	}
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
}
