package com.lqmrSys.bean.pagination;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class OperateDataTablesReq{
	@ApiModelProperty(value = "操作人员名称")
	private String operatorNameSer;
	@ApiModelProperty(value = "接口名称")
	private String operateInterfaceNameSer;
	@ApiModelProperty(value = "操作内容")
	private String operateContentSer;
	@ApiModelProperty(value = "备注")
	private String remarkSer;
	@ApiParam(value = "创建时间:开始时间 yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;
	@ApiParam(value = "创建时间:结束时间 yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
	public String getOperatorNameSer() {
		return operatorNameSer;
	}
	public void setOperatorNameSer(String operatorNameSer) {
		this.operatorNameSer = operatorNameSer;
	}
	public String getOperateInterfaceNameSer() {
		return operateInterfaceNameSer;
	}
	public void setOperateInterfaceNameSer(String operateInterfaceNameSer) {
		this.operateInterfaceNameSer = operateInterfaceNameSer;
	}
	public String getOperateContentSer() {
		return operateContentSer;
	}
	public void setOperateContentSer(String operateContentSer) {
		this.operateContentSer = operateContentSer;
	}
	public String getRemarkSer() {
		return remarkSer;
	}
	public void setRemarkSer(String remarkSer) {
		this.remarkSer = remarkSer;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
