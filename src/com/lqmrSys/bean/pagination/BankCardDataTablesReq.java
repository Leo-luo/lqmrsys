package com.lqmrSys.bean.pagination;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

public class BankCardDataTablesReq {
	@ApiModelProperty(value = "银行卡类型")
	private String cardType;
	@ApiModelProperty(value = "关联ID")
	private int relationId;
	@ApiParam(required = true, name = "start", value = "开始序号[0-N]")
    private int start;
    @ApiParam(required = true, name = "length", value = "每次请求的容量")
    private int length;
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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getRelationId() {
		return relationId;
	}
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}
}
