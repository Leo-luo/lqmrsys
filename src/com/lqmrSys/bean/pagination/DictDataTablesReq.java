package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class DictDataTablesReq{
	@ApiModelProperty(value = "字典类型编码")
	private String typeCode;
	@ApiModelProperty(value = "字典编码")
	private String dictCode;
	@ApiModelProperty(value = "字典内容")
	private String content;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
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
