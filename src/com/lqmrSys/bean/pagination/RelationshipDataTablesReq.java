package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class RelationshipDataTablesReq {
	@ApiModelProperty(value = "数据来源：sys_company,sys_customer")
	private String dataFrom;
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "关系描述:朋友、亲戚、夫妻、子女、父母、担保")
	private String relationshipDescribe;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
	public String getRelationshipDescribe() {
		return relationshipDescribe;
	}
	public void setRelationshipDescribe(String relationshipDescribe) {
		this.relationshipDescribe = relationshipDescribe;
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
