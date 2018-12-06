package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RelationshipReq extends OperateBean{
	@ApiModelProperty(value = "关系ID")
	private int relationshipId;
	@ApiModelProperty(value = "数据来源：sys_company,sys_customer")
	private String dataFrom;
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "数据来源：sys_company,sys_customer")
	private String dataRelationshipFrom;
	@ApiModelProperty(value = "关系人ID")
	private int dataRelationshipId;
	@ApiModelProperty(value = "关系描述:朋友、亲戚、夫妻、子女、父母、担保")
	private String relationshipDescribe;
	@ApiModelProperty(value = "备注")
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataRelationshipFrom() {
		return dataRelationshipFrom;
	}
	public void setDataRelationshipFrom(String dataRelationshipFrom) {
		this.dataRelationshipFrom = dataRelationshipFrom;
	}
	public int getDataRelationshipId() {
		return dataRelationshipId;
	}
	public void setDataRelationshipId(int dataRelationshipId) {
		this.dataRelationshipId = dataRelationshipId;
	}
	public String getRelationshipDescribe() {
		return relationshipDescribe;
	}
	public void setRelationshipDescribe(String relationshipDescribe) {
		this.relationshipDescribe = relationshipDescribe;
	}
}
