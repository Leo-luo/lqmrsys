package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class StatisticsBean {
	@ApiModelProperty(value = "时间")
	private String dataTime;
	@ApiModelProperty(value = "值")
	private double dataValue;
	@ApiModelProperty(value = "名称")
	private String dataName;
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public double getDataValue() {
		return dataValue;
	}
	public void setDataValue(double dataValue) {
		this.dataValue = dataValue;
	}
}
