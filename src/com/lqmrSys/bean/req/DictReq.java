package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DictReq extends OperateBean{
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
}
