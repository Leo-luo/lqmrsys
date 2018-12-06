package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RelationModuleDto {
	@ApiModelProperty(value = "模块ID")
	private int moduleId;
	@ApiModelProperty(value = "模块名称")
	private String moduleName;
	@ApiModelProperty(value = "父模块ID")
	private int parentModuleId;
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getParentModuleId() {
		return parentModuleId;
	}
	public void setParentModuleId(int parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
}
