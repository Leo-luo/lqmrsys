package com.lqmrSys.bean.req;

import java.util.List;

import com.lqmrSys.bean.OperateBean;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CompanyReq extends OperateBean{
	@ApiModelProperty(value = "企业ID")
	private int companyId;
	@ApiModelProperty(value = "企业自定义编号")
	private String companyCode;
	@ApiModelProperty(value = "企业名称")
	private String companyName;
	@ApiModelProperty(value = "企业简称")
	private String companyNameShort;
	@ApiModelProperty(value = "注册资金")
	private double registeredCapital;
	@ApiModelProperty(value = "资金单位(数据字典)")
	private String capitalType;
	@ApiModelProperty(value = "借款次数")
	private int loanNum;
	@ApiModelProperty(value = "注册日期:yyyy-MM-dd")
	private String registerTime;
	@ApiModelProperty(value = "注册地址")
	private String registerAddress;
	@ApiModelProperty(value = "股东人数")
	private int shareholderNum;
	@ApiModelProperty(value = "电话号码")
	private String companyPhone;
	@ApiModelProperty(value = "单位地址")
	private String companyAddress;
	@ApiModelProperty(value = "单位主页")
	private String companyHomepage;
	@ApiModelProperty(value = "传真号码")
	private String faxNum;
	@ApiModelProperty(value = "法人代表名称")
	private String corporationName;
	@ApiModelProperty(value = "法人代表性别")
	private String corporationSex;
	@ApiModelProperty(value = "法人代表联系电话")
	private String corporationContactNum;
	@ApiModelProperty(value = "法人代表身份证")
	private String corporationIdcard;
	@ApiModelProperty(value = "法人代表电子邮件")
	private String corporationEmail;
	@ApiModelProperty(value = "法人代表手机号码")
	private String corporationPhoneNum;
	@ApiModelProperty(value = "法人家庭住址")
	private String corporationHomeAddress;
	@ApiModelProperty(value = "社会信用代码")
	private String socialCreditCode;
	@ApiModelProperty(value = "行业分类(数据字典)")
	private String industryCategory;
	@ApiModelProperty(value = "信用等级(数据字典)")
	private String creditLevel;
	@ApiModelProperty(value = "企业性质(数据字典)")
	private String companyNature;
	@ApiModelProperty(value = "所属区域(数据字典)")
	private String belongArea;
	@ApiModelProperty(value = "借款对象(数据字典)")
	private String loanTarget;
	@ApiModelProperty(value = "经营场地所有权")
	private String operatePlaceOwnership;
	@ApiModelProperty(value = "经营场地面积")
	private double operatePlaceArea;
	@ApiModelProperty(value = "备注")
	private String companyRemark;
	@ApiModelProperty(value = "操作人员ID")
	private int operatorId;
	@ApiModelProperty(value = "操作人员名称")
	private String operatorName;
	@ApiModelProperty(value = "银行卡列表")
	private List<BankCardReq> cardList;
	public String getLoanTarget() {
		return loanTarget;
	}
	public void setLoanTarget(String loanTarget) {
		this.loanTarget = loanTarget;
	}
	public List<BankCardReq> getCardList() {
		return cardList;
	}
	public void setCardList(List<BankCardReq> cardList) {
		this.cardList = cardList;
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
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNameShort() {
		return companyNameShort;
	}
	public void setCompanyNameShort(String companyNameShort) {
		this.companyNameShort = companyNameShort;
	}
	public double getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getCapitalType() {
		return capitalType;
	}
	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	public int getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(int loanNum) {
		this.loanNum = loanNum;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public int getShareholderNum() {
		return shareholderNum;
	}
	public void setShareholderNum(int shareholderNum) {
		this.shareholderNum = shareholderNum;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyHomepage() {
		return companyHomepage;
	}
	public void setCompanyHomepage(String companyHomepage) {
		this.companyHomepage = companyHomepage;
	}
	public String getFaxNum() {
		return faxNum;
	}
	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	public String getCorporationName() {
		return corporationName;
	}
	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}
	public String getCorporationSex() {
		return corporationSex;
	}
	public void setCorporationSex(String corporationSex) {
		this.corporationSex = corporationSex;
	}
	public String getCorporationContactNum() {
		return corporationContactNum;
	}
	public void setCorporationContactNum(String corporationContactNum) {
		this.corporationContactNum = corporationContactNum;
	}
	public String getCorporationIdcard() {
		return corporationIdcard;
	}
	public void setCorporationIdcard(String corporationIdcard) {
		this.corporationIdcard = corporationIdcard;
	}
	public String getCorporationEmail() {
		return corporationEmail;
	}
	public void setCorporationEmail(String corporationEmail) {
		this.corporationEmail = corporationEmail;
	}
	public String getCorporationPhoneNum() {
		return corporationPhoneNum;
	}
	public void setCorporationPhoneNum(String corporationPhoneNum) {
		this.corporationPhoneNum = corporationPhoneNum;
	}
	public String getCorporationHomeAddress() {
		return corporationHomeAddress;
	}
	public void setCorporationHomeAddress(String corporationHomeAddress) {
		this.corporationHomeAddress = corporationHomeAddress;
	}
	public String getSocialCreditCode() {
		return socialCreditCode;
	}
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	public String getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getCompanyNature() {
		return companyNature;
	}
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getOperatePlaceOwnership() {
		return operatePlaceOwnership;
	}
	public void setOperatePlaceOwnership(String operatePlaceOwnership) {
		this.operatePlaceOwnership = operatePlaceOwnership;
	}
	public double getOperatePlaceArea() {
		return operatePlaceArea;
	}
	public void setOperatePlaceArea(double operatePlaceArea) {
		this.operatePlaceArea = operatePlaceArea;
	}
	public String getCompanyRemark() {
		return companyRemark;
	}
	public void setCompanyRemark(String companyRemark) {
		this.companyRemark = companyRemark;
	}
}
