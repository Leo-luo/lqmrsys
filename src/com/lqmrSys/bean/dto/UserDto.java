package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户返回类")
public class UserDto {
	@ApiModelProperty(value = "用户ID")
	private int userId;
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "密码")
	private String pwd;
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
	@ApiModelProperty(value = "部门名称")
	private String deptName;
	@ApiModelProperty(value = "角色ID")
	private int roleId;
	@ApiModelProperty(value = "角色名称")
	private String roleName;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
