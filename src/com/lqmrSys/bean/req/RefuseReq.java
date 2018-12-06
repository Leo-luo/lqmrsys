package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RefuseReq extends OperateBean{
	@ApiModelProperty(value = "拒贷ID")
	private int refuseId;
	@ApiModelProperty(value = "客户/企业ID")
	private int dataId;
	@ApiModelProperty(value = "来源：公司(sys_company),个人(sys_customer)")
	private String dataFrom;
	@ApiModelProperty(value = "备注")
	private String remark;
	public int getRefuseId() {
		return refuseId;
	}
	public void setRefuseId(int refuseId) {
		this.refuseId = refuseId;
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
