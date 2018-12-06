package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RepaymentReq extends OperateBean{
	@ApiModelProperty(value = "还款表ID")
	private int repaymentId;
	@ApiModelProperty(value = "实际还款本金金额")
	private double actualPrincipalAmount;
	@ApiModelProperty(value = "实际还款利息金额")
	private double actualInterestAmount;
	@ApiModelProperty(value = "实际还款服务金额")
	private double actualServiceAmount;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public int getRepaymentId() {
		return repaymentId;
	}
	public void setRepaymentId(int repaymentId) {
		this.repaymentId = repaymentId;
	}
	public double getActualPrincipalAmount() {
		return actualPrincipalAmount;
	}
	public void setActualPrincipalAmount(double actualPrincipalAmount) {
		this.actualPrincipalAmount = actualPrincipalAmount;
	}
	public double getActualInterestAmount() {
		return actualInterestAmount;
	}
	public void setActualInterestAmount(double actualInterestAmount) {
		this.actualInterestAmount = actualInterestAmount;
	}
	public double getActualServiceAmount() {
		return actualServiceAmount;
	}
	public void setActualServiceAmount(double actualServiceAmount) {
		this.actualServiceAmount = actualServiceAmount;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
}
