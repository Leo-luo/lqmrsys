package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TrialDataTablesReq {
	@ApiModelProperty(value = "用户ID")
	private int userId;
	@ApiModelProperty(value = "审核/审批数据ID")
	private int trialDataId;
	@ApiModelProperty(value = "类型:需求表(sys_demand),配对表(sys_financing_matching)")
	private String trialType;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTrialDataId() {
		return trialDataId;
	}
	public void setTrialDataId(int trialDataId) {
		this.trialDataId = trialDataId;
	}
	public String getTrialType() {
		return trialType;
	}
	public void setTrialType(String trialType) {
		this.trialType = trialType;
	}
}
