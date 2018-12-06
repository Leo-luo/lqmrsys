package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RiskSignReq extends OperateBean{
	@ApiModelProperty(value = "标记ID")
	private int signId;
	@ApiModelProperty(value = "标记类型")
	private String signType;
	@ApiModelProperty(value = "数据ID")
	private int dataId;
	@ApiModelProperty(value = "数据来源：供应方(sys_supplier),需求方(sys_demand)")
	private String dataFrom;
	@ApiModelProperty(value = "备注")
	private String remark;
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
