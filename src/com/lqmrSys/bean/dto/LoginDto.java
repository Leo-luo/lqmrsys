package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoginDto {
	@ApiModelProperty(value = "用户ID")
	private int userId;
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "姓名")
	private String name;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
