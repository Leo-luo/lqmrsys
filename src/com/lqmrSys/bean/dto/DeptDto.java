package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeptDto {
	@ApiModelProperty(value = "部门ID")
	private int deptId;
	@ApiModelProperty(value = "部门名称")
	private String name;
	@ApiModelProperty(value = "上级部门ID")
	private int parentDeptId;
	@ApiModelProperty(value = "上级部门名称")
	private String parentDeptName;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "排序值:越大越靠前")
	private int dataOrder;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
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
	public String getParentDeptName() {
		return parentDeptName;
	}
	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
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
	public int getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
