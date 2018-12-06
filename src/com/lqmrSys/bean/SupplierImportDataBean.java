package com.lqmrSys.bean;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SupplierImportDataBean extends OperateBean{
	@ApiModelProperty(value = "供应方台账导入数据ID")
	private int importDataId;
	@ApiModelProperty(value = "融资供应编号")
	private String supplierCode;
	@ApiModelProperty(value = "自定义合同号")
	private String customContractCode;
	@ApiModelProperty(value = "自定义凭证号")
	private String customVoucherCode;
	@ApiModelProperty(value = "客户经理")
	private String cusManager;
	@ApiModelProperty(value = "融资供应方名称")
	private String supplierName;
	@ApiModelProperty(value = "供应方证件号/机构号")
	private String supplierCertificateCode;
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	@ApiModelProperty(value = "投资方式")
	private String investmentMode;
	@ApiModelProperty(value = "签约分类")
	private String contractType;
	@ApiModelProperty(value = "供应金额")
	private double supplyAmount;
	@ApiModelProperty(value = "当前供应余额")
	private double supplyBalanceAmount;
	@ApiModelProperty(value = "当前可配对金额")
	private double canPairAmount;
	@ApiModelProperty(value = "当前已结算金额")
	private double settlementAmount;
	@ApiModelProperty(value = "时点日终可用金额")
	private double timeUsableAmount;
	@ApiModelProperty(value = "时点日终已结算金额")
	private double timeSettlementAmount;
	@ApiModelProperty(value = "时点日终未结算金额")
	private double timeUnsettlementAmount;
	@ApiModelProperty(value = "垫付本金")
	private double replacePayAmount;
	@ApiModelProperty(value = "收回垫付本金")
	private double takenReplacePayAmount;
	@ApiModelProperty(value = "利率（‰）")
	private double supplyRate;
	@ApiModelProperty(value = "结息周期")
	private String knotCycle;
	@ApiModelProperty(value = "委托出借开始日期")
	private String entrustStartTime;
	@ApiModelProperty(value = "委托出借截止日期")
	private String entrustEndTime;
	@ApiModelProperty(value = "放款宽限天数")
	private int graceDays;
	@ApiModelProperty(value = "供应开始日期")
	private String supplyStartTime;
	@ApiModelProperty(value = "供应截止日期")
	private String supplyEndTime;
	@ApiModelProperty(value = "实际宽限天数")
	private int actuallyGraceDays;
	@ApiModelProperty(value = "利息收入")
	private double interestIncome;
	@ApiModelProperty(value = "服务费收入")
	private double serviceChargeIncome;
	@ApiModelProperty(value = "已支付利息")
	private double payInterestAmount;
	@ApiModelProperty(value = "预计剩余利息")
	private double interestBalanceAmount;
	@ApiModelProperty(value = "利息帐号")
	private String interestAccount;
	@ApiModelProperty(value = "利息帐号银行")
	private String interestAccountBank;
	@ApiModelProperty(value = "本金帐号")
	private String principalAccount;
	@ApiModelProperty(value = "本金帐号银行")
	private String principalAccountBank;
	@ApiModelProperty(value = "帐号类型")
	private String accountType;
	@ApiModelProperty(value = "账户U盾编号")
	private String accountUcode;
	@ApiModelProperty(value = "卡号")
	private String accountNo;
	@ApiModelProperty(value = "投资信息来源渠道")
	private String informationSource;
	@ApiModelProperty(value = "最近闲置天数")
	private int idleDays;
	@ApiModelProperty(value = "其他要求")
	private String otherDemand;
	@ApiModelProperty(value = "状态")
	private String supplyStatus;
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
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getCustomContractCode() {
		return customContractCode;
	}
	public void setCustomContractCode(String customContractCode) {
		this.customContractCode = customContractCode;
	}
	public String getCustomVoucherCode() {
		return customVoucherCode;
	}
	public void setCustomVoucherCode(String customVoucherCode) {
		this.customVoucherCode = customVoucherCode;
	}
	public String getCusManager() {
		return cusManager;
	}
	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCertificateCode() {
		return supplierCertificateCode;
	}
	public void setSupplierCertificateCode(String supplierCertificateCode) {
		this.supplierCertificateCode = supplierCertificateCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getInvestmentMode() {
		return investmentMode;
	}
	public void setInvestmentMode(String investmentMode) {
		this.investmentMode = investmentMode;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public double getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(double supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public double getSupplyBalanceAmount() {
		return supplyBalanceAmount;
	}
	public void setSupplyBalanceAmount(double supplyBalanceAmount) {
		this.supplyBalanceAmount = supplyBalanceAmount;
	}
	public double getCanPairAmount() {
		return canPairAmount;
	}
	public void setCanPairAmount(double canPairAmount) {
		this.canPairAmount = canPairAmount;
	}
	public double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public double getTimeUsableAmount() {
		return timeUsableAmount;
	}
	public void setTimeUsableAmount(double timeUsableAmount) {
		this.timeUsableAmount = timeUsableAmount;
	}
	public double getTimeSettlementAmount() {
		return timeSettlementAmount;
	}
	public void setTimeSettlementAmount(double timeSettlementAmount) {
		this.timeSettlementAmount = timeSettlementAmount;
	}
	public double getTimeUnsettlementAmount() {
		return timeUnsettlementAmount;
	}
	public void setTimeUnsettlementAmount(double timeUnsettlementAmount) {
		this.timeUnsettlementAmount = timeUnsettlementAmount;
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
	public double getSupplyRate() {
		return supplyRate;
	}
	public void setSupplyRate(double supplyRate) {
		this.supplyRate = supplyRate;
	}
	public String getKnotCycle() {
		return knotCycle;
	}
	public void setKnotCycle(String knotCycle) {
		this.knotCycle = knotCycle;
	}
	public String getEntrustStartTime() {
		return entrustStartTime;
	}
	public void setEntrustStartTime(String entrustStartTime) {
		this.entrustStartTime = entrustStartTime;
	}
	public String getEntrustEndTime() {
		return entrustEndTime;
	}
	public void setEntrustEndTime(String entrustEndTime) {
		this.entrustEndTime = entrustEndTime;
	}
	public int getGraceDays() {
		return graceDays;
	}
	public void setGraceDays(int graceDays) {
		this.graceDays = graceDays;
	}
	public String getSupplyStartTime() {
		return supplyStartTime;
	}
	public void setSupplyStartTime(String supplyStartTime) {
		this.supplyStartTime = supplyStartTime;
	}
	public String getSupplyEndTime() {
		return supplyEndTime;
	}
	public void setSupplyEndTime(String supplyEndTime) {
		this.supplyEndTime = supplyEndTime;
	}
	public int getActuallyGraceDays() {
		return actuallyGraceDays;
	}
	public void setActuallyGraceDays(int actuallyGraceDays) {
		this.actuallyGraceDays = actuallyGraceDays;
	}
	public double getInterestIncome() {
		return interestIncome;
	}
	public void setInterestIncome(double interestIncome) {
		this.interestIncome = interestIncome;
	}
	public double getServiceChargeIncome() {
		return serviceChargeIncome;
	}
	public void setServiceChargeIncome(double serviceChargeIncome) {
		this.serviceChargeIncome = serviceChargeIncome;
	}
	public double getPayInterestAmount() {
		return payInterestAmount;
	}
	public void setPayInterestAmount(double payInterestAmount) {
		this.payInterestAmount = payInterestAmount;
	}
	public double getInterestBalanceAmount() {
		return interestBalanceAmount;
	}
	public void setInterestBalanceAmount(double interestBalanceAmount) {
		this.interestBalanceAmount = interestBalanceAmount;
	}
	public String getInterestAccount() {
		return interestAccount;
	}
	public void setInterestAccount(String interestAccount) {
		this.interestAccount = interestAccount;
	}
	public String getInterestAccountBank() {
		return interestAccountBank;
	}
	public void setInterestAccountBank(String interestAccountBank) {
		this.interestAccountBank = interestAccountBank;
	}
	public String getPrincipalAccount() {
		return principalAccount;
	}
	public void setPrincipalAccount(String principalAccount) {
		this.principalAccount = principalAccount;
	}
	public String getPrincipalAccountBank() {
		return principalAccountBank;
	}
	public void setPrincipalAccountBank(String principalAccountBank) {
		this.principalAccountBank = principalAccountBank;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountUcode() {
		return accountUcode;
	}
	public void setAccountUcode(String accountUcode) {
		this.accountUcode = accountUcode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getInformationSource() {
		return informationSource;
	}
	public void setInformationSource(String informationSource) {
		this.informationSource = informationSource;
	}
	public int getIdleDays() {
		return idleDays;
	}
	public void setIdleDays(int idleDays) {
		this.idleDays = idleDays;
	}
	public String getOtherDemand() {
		return otherDemand;
	}
	public void setOtherDemand(String otherDemand) {
		this.otherDemand = otherDemand;
	}
	public String getSupplyStatus() {
		return supplyStatus;
	}
	public void setSupplyStatus(String supplyStatus) {
		this.supplyStatus = supplyStatus;
	}
}
