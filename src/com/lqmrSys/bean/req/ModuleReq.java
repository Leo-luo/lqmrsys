package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ModuleReq extends OperateBean{
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "模块ID串,','逗号隔开")
	private String moduleIds;
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
}
