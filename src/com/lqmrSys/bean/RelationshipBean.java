package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RelationshipBean {
	@ApiModelProperty(value = "关系ID")
	private int relationshipId;
	@ApiModelProperty(value = "数据来源：sys_company,sys_customer")
	private String dataFrom;
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "数据名称")
	private String dataName;
	@ApiModelProperty(value = "数据来源：sys_company,sys_customer")
	private String dataRelationshipFrom;
	@ApiModelProperty(value = "关系人ID")
	private int dataRelationshipId;
	@ApiModelProperty(value = "关系人名称")
	private String dataRelationshipName;
	@ApiModelProperty(value = "关系描述:朋友、亲戚、夫妻、子女、父母、担保")
	private String relationshipDescribe;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
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
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
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
	public String getDataRelationshipName() {
		return dataRelationshipName;
	}
	public void setDataRelationshipName(String dataRelationshipName) {
		this.dataRelationshipName = dataRelationshipName;
	}
	public String getRelationshipDescribe() {
		return relationshipDescribe;
	}
	public void setRelationshipDescribe(String relationshipDescribe) {
		this.relationshipDescribe = relationshipDescribe;
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
