package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AttachmentReq extends OperateBean{
	@ApiModelProperty(value = "附件表ID")
	private int attachmentId;
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "数据来源:供应方(sys_supplier),需求方(sys_demand),审核/审批(sys_trial)")
	private String dataFrom;
	@ApiModelProperty(value = "文件路径")
	private String filePath;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "附件类型:文件夹(folder),文件(file)")
	private String attachmentType;
	@ApiModelProperty(value = "父附件表ID")
	private int parentAttachmentId;
	@ApiModelProperty(value = "附件名称")
	private String attachmentName;
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public int getParentAttachmentId() {
		return parentAttachmentId;
	}
	public void setParentAttachmentId(int parentAttachmentId) {
		this.parentAttachmentId = parentAttachmentId;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
}
