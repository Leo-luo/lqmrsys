package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class AttachmentDataTablesReq {
	@ApiParam(required = true, name = "dataId", value = "数据ID")
	private int dataId;
	@ApiParam(required = true, name = "dataFrom", value = "数据来源:供应方(sys_supplier),需求方(sys_demand),审核/审批(sys_trial),自然人(sys_customer),企业(sys_company)")
	private String dataFrom;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "附件类型:文件夹(folder),文件(file)")
	private String attachmentType;
	@ApiModelProperty(value = "父附件表ID")
	private int parentAttachmentId;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
