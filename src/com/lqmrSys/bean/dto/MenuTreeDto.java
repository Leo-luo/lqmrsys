package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class MenuTreeDto {
	@ApiModelProperty(value = "菜单编号")
	private String id;
	@ApiModelProperty(value = "菜单名称")
	private String label;
	@ApiModelProperty(value = "父菜单编号")
    private String parent;
	@ApiModelProperty(value = "子属性，大于0则有，其他无")
	private Integer children = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
}
