package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoanImportDataBean extends OperateBean{
	@ApiModelProperty(value = "借款情况台账导入数据ID")
	private int importDataId;
	@ApiModelProperty(value = "借据号")
	private String loanCode;
	@ApiModelProperty(value = "合同号")
	private String contractCode;
	@ApiModelProperty(value = "自定义合同号")
	private String customContractCode;
	@ApiModelProperty(value = "合同类型")
	private String contractType;
	@ApiModelProperty(value = "融资需求方")
	private String financingDemanderName;
	@ApiModelProperty(value = "客户经理A")
	private String cusManagera;
	@ApiModelProperty(value = "客户经理B")
	private String cusManagerb;
	@ApiModelProperty(value = "融资类型")
	private String financingType;
	@ApiModelProperty(value = "借款金额")
	private double loanAmount;
	@ApiModelProperty(value = "已收回金额")
	private double alreadyPayAmount;
	@ApiModelProperty(value = "当前余额")
	private double balanceAmount;
	@ApiModelProperty(value = "当前供应方余额")
	private double supplierBalanceAmount;
	@ApiModelProperty(value = "当前自有资金余额")
	private double ownBalanceAmount;
	@ApiModelProperty(value = "日终时点余额")
	private double timeBalanceAmount;
	@ApiModelProperty(value = "日终时点已收利息")
	private double timeInterestAmount;
	@ApiModelProperty(value = "借款利率‰")
	private double loanRate;
	@ApiModelProperty(value = "服务费用利率‰")
	private double serviceRate;
	@ApiModelProperty(value = "借款开始日期")
	private String loanStartTime;
	@ApiModelProperty(value = "借款截止日期")
	private String loanEndTime;
	@ApiModelProperty(value = "借款天数")
	private int loanDays;
	@ApiModelProperty(value = "预计应收利息")
	private double planInterestAmount;
	@ApiModelProperty(value = "已收利息")
	private double payInterestAmount;
	@ApiModelProperty(value = "已收服务费")
	private double payServiceAmount;
	@ApiModelProperty(value = "挂起合计金额")
	private double hangupTotalAmount;
	@ApiModelProperty(value = "还款方式")
	private String repaymentMode;
	@ApiModelProperty(value = "供应方出借金额")
	private double supplyAmount;
	@ApiModelProperty(value = "自有资金出借金额")
	private double ownAmount;
	@ApiModelProperty(value = "垫付金额")
	private double replacePayAmount;
	@ApiModelProperty(value = "收回垫付金额")
	private double takenReplacePayAmount;
	@ApiModelProperty(value = "行业分类")
	private String industryType;
	@ApiModelProperty(value = "所属区域")
	private String belongArea;
	@ApiModelProperty(value = "单位地址")
	private String workAddress;
	@ApiModelProperty(value = "借款用途")
	private String loanPurpose;
	@ApiModelProperty(value = "证件号码/贷款卡号")
	private String certificateNo;
	@ApiModelProperty(value = "手机号码")
	private String contactPhone;
	@ApiModelProperty(value = "机构号")
	private String organizationNo;
	@ApiModelProperty(value = "营业执照号")
	private String businessLicenseNo;
	@ApiModelProperty(value = "法人代表")
	private String legalRepresentative;
	@ApiModelProperty(value = "担保人")
	private String guarantee;
	@ApiModelProperty(value = "风险五级分类")
	private String riskClassify;
	@ApiModelProperty(value = "呆账")
	private String badDebt;
	@ApiModelProperty(value = "是否展期")
	private String isExtension;
	@ApiModelProperty(value = "备注")
	private String loanRemark;
	@ApiModelProperty(value = "导入时间")
	private String importTime;
	public String getImportTime() {
		return importTime;
	}
	public void setImportTime(String importTime) {
		this.importTime = importTime;
	}
	public int getImportDataId() {
		return importDataId;
	}
	public void setImportDataId(int importDataId) {
		this.importDataId = importDataId;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getCustomContractCode() {
		return customContractCode;
	}
	public void setCustomContractCode(String customContractCode) {
		this.customContractCode = customContractCode;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getFinancingDemanderName() {
		return financingDemanderName;
	}
	public void setFinancingDemanderName(String financingDemanderName) {
		this.financingDemanderName = financingDemanderName;
	}
	public String getCusManagera() {
		return cusManagera;
	}
	public void setCusManagera(String cusManagera) {
		this.cusManagera = cusManagera;
	}
	public String getCusManagerb() {
		return cusManagerb;
	}
	public void setCusManagerb(String cusManagerb) {
		this.cusManagerb = cusManagerb;
	}
	public String getFinancingType() {
		return financingType;
	}
	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getAlreadyPayAmount() {
		return alreadyPayAmount;
	}
	public void setAlreadyPayAmount(double alreadyPayAmount) {
		this.alreadyPayAmount = alreadyPayAmount;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public double getSupplierBalanceAmount() {
		return supplierBalanceAmount;
	}
	public void setSupplierBalanceAmount(double supplierBalanceAmount) {
		this.supplierBalanceAmount = supplierBalanceAmount;
	}
	public double getOwnBalanceAmount() {
		return ownBalanceAmount;
	}
	public void setOwnBalanceAmount(double ownBalanceAmount) {
		this.ownBalanceAmount = ownBalanceAmount;
	}
	public double getTimeBalanceAmount() {
		return timeBalanceAmount;
	}
	public void setTimeBalanceAmount(double timeBalanceAmount) {
		this.timeBalanceAmount = timeBalanceAmount;
	}
	public double getTimeInterestAmount() {
		return timeInterestAmount;
	}
	public void setTimeInterestAmount(double timeInterestAmount) {
		this.timeInterestAmount = timeInterestAmount;
	}
	public double getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(double loanRate) {
		this.loanRate = loanRate;
	}
	public double getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}
	public String getLoanStartTime() {
		return loanStartTime;
	}
	public void setLoanStartTime(String loanStartTime) {
		this.loanStartTime = loanStartTime;
	}
	public String getLoanEndTime() {
		return loanEndTime;
	}
	public void setLoanEndTime(String loanEndTime) {
		this.loanEndTime = loanEndTime;
	}
	public int getLoanDays() {
		return loanDays;
	}
	public void setLoanDays(int loanDays) {
		this.loanDays = loanDays;
	}
	public double getPlanInterestAmount() {
		return planInterestAmount;
	}
	public void setPlanInterestAmount(double planInterestAmount) {
		this.planInterestAmount = planInterestAmount;
	}
	public double getPayInterestAmount() {
		return payInterestAmount;
	}
	public void setPayInterestAmount(double payInterestAmount) {
		this.payInterestAmount = payInterestAmount;
	}
	public double getPayServiceAmount() {
		return payServiceAmount;
	}
	public void setPayServiceAmount(double payServiceAmount) {
		this.payServiceAmount = payServiceAmount;
	}
	public double getHangupTotalAmount() {
		return hangupTotalAmount;
	}
	public void setHangupTotalAmount(double hangupTotalAmount) {
		this.hangupTotalAmount = hangupTotalAmount;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	public double getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(double supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public double getOwnAmount() {
		return ownAmount;
	}
	public void setOwnAmount(double ownAmount) {
		this.ownAmount = ownAmount;
	}
	public double getReplacePayAmount() {
		return replacePayAmount;
	}
	public void setReplacePayAmount(double replacePayAmount) {
		this.replacePayAmount = replacePayAmount;
	}
	public double getTakenReplacePayAmount() {
		return takenReplacePayAmount;
	}
	public void setTakenReplacePayAmount(double takenReplacePayAmount) {
		this.takenReplacePayAmount = takenReplacePayAmount;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getOrganizationNo() {
		return organizationNo;
	}
	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getRiskClassify() {
		return riskClassify;
	}
	public void setRiskClassify(String riskClassify) {
		this.riskClassify = riskClassify;
	}
	public String getBadDebt() {
		return badDebt;
	}
	public void setBadDebt(String badDebt) {
		this.badDebt = badDebt;
	}
	public String getIsExtension() {
		return isExtension;
	}
	public void setIsExtension(String isExtension) {
		this.isExtension = isExtension;
	}
	public String getLoanRemark() {
		return loanRemark;
	}
	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}
}
