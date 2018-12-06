package com.lqmrSys.bean.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CustomerDto {
	@ApiModelProperty(value = "客户ID")
	private int customerId;
	@ApiModelProperty(value = "客户自定义编码")
	private String customerCode;
	@ApiModelProperty(value = "客户名称")
	private String name;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "证件类型")
	private String credentialsType;
	@ApiModelProperty(value = "证件号码")
	private String credentialsNum;
	@ApiModelProperty(value = "证件有效期")
	private String credentialsEffectiveTime;
	@ApiModelProperty(value = "籍贯")
	private String nativePlace;
	@ApiModelProperty(value = "民族")
	private String nation;
	@ApiModelProperty(value = "家庭住址")
	private String homeAddress;
	@ApiModelProperty(value = "出生日期")
	private String birthday;
	@ApiModelProperty(value = "手机号码")
	private String phoneNum;
	@ApiModelProperty(value = "联系电话")
	private String contactNum;
	@ApiModelProperty(value = "电子邮件")
	private String email;
	@ApiModelProperty(value = "工作单位")
	private String workCompany;
	@ApiModelProperty(value = "单位地址")
	private String workPlace;
	@ApiModelProperty(value = "职位(数据字典)")
	private String position;
	@ApiModelProperty(value = "年收入")
	private double yearIncome;
	@ApiModelProperty(value = "婚姻状况(数据字典)")
	private String maritalStatus;
	@ApiModelProperty(value = "学历(数据字典)")
	private String education;
	@ApiModelProperty(value = "行业分类(数据字典)")
	private String industryCategory;
	@ApiModelProperty(value = "所属区域(数据字典)")
	private String belongArea;
	@ApiModelProperty(value = "信用等级(数据字典)")
	private String creditLevel;
	@ApiModelProperty(value = "借款对象(数据字典)")
	private String loanTarget;
	@ApiModelProperty(value = "信誉度")
	private int creditNum;
	@ApiModelProperty(value = "备注")
	private String customerRemark;
	@ApiModelProperty(value = "数据状态:1-正常 3-删除")
	private int dataStatus;
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "修改时间")
	private String updateTime;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	public String getCredentialsType() {
		return credentialsType;
	}
	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}
	public String getCredentialsNum() {
		return credentialsNum;
	}
	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}
	public String getCredentialsEffectiveTime() {
		return credentialsEffectiveTime;
	}
	public void setCredentialsEffectiveTime(String credentialsEffectiveTime) {
		this.credentialsEffectiveTime = credentialsEffectiveTime;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkCompany() {
		return workCompany;
	}
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(double yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getLoanTarget() {
		return loanTarget;
	}
	public void setLoanTarget(String loanTarget) {
		this.loanTarget = loanTarget;
	}
	public int getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(int creditNum) {
		this.creditNum = creditNum;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
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
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
