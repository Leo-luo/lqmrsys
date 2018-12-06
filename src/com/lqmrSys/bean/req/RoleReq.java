package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoleReq extends OperateBean{
	@ApiModelProperty(value = "角色ID")
	private int roleId;
	@ApiModelProperty(value = "角色名称")
	private String name;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "排序值:越大越靠前")
	private int dataOrder;
	@ApiModelProperty(value = "模块ID串,','逗号隔开")
	private String moduleIds;
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
