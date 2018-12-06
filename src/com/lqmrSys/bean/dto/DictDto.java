package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DictDto{
	@ApiModelProperty(value = "字典ID")
	private int dictId;
	@ApiModelProperty(value = "字典类型编码")
	private String typeCode;
	@ApiModelProperty(value = "字典编码")
	private String dictCode;
	@ApiModelProperty(value = "字典内容")
	private String content;
	@ApiModelProperty(value = "父字典ID")
	private int parentDictId;
	@ApiModelProperty(value = "字典备注")
	private String remark;
	@ApiModelProperty(value = "数据排序值,越大越靠前")
	private int dataOrder;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParentDictId() {
		return parentDictId;
	}
	public void setParentDictId(int parentDictId) {
		this.parentDictId = parentDictId;
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
