package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeptReq extends OperateBean{
	@ApiModelProperty(value = "部门ID")
	private int deptId;
	@ApiModelProperty(value = "部门名称")
	private String name;
	@ApiModelProperty(value = "上级部门ID")
	private int parentDeptId;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "排序值:越大越靠前")
	private int dataOrder;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentDeptId() {
		return parentDeptId;
	}
	public void setParentDeptId(int parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(int dataOrder) {
		this.dataOrder = dataOrder;
	}
}
