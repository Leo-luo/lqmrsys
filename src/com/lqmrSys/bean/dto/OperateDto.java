package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OperateDto {
	@ApiModelProperty(value = "操作记录表ID")
	private int operateId;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "接口地址")
	private String operateInterface;
	@ApiModelProperty(value = "接口名称")
	private String operateInterfaceName;
	@ApiModelProperty(value = "操作内容")
	private String operateContent;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	public int getOperateId() {
		return operateId;
	}
	public void setOperateId(int operateId) {
		this.operateId = operateId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
