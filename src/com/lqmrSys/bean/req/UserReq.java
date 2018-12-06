package com.lqmrSys.bean.req;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserReq extends OperateBean{
	@ApiModelProperty(value = "用户ID")
	private int userId;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "身份证号")
	private String idcardNum;
	@ApiModelProperty(value = "出生日期:yyyy-MM-dd")
	private String birthday;
	@ApiModelProperty(value = "籍贯")
	private String nativePlace;
	@ApiModelProperty(value = "家庭住址")
	private String homeAddress;
	@ApiModelProperty(value = "联系电话")
	private String contactNum;
	@ApiModelProperty(value = "手机号码")
	private String phoneNum;
	@ApiModelProperty(value = "电子邮箱")
	private String email;
	@ApiModelProperty(value = "部门ID")
	private int deptId;
	@ApiModelProperty(value = "角色ID")
	private int roleId;
	@ApiModelProperty(value = "备注")
	private String remark;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcardNum() {
		return idcardNum;
	}
	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
