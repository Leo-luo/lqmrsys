package com.lqmrSys.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lqmrSys.bean.LoanImportDataBean;
import com.lqmrSys.bean.SupplierImportDataBean;
import com.lqmrSys.dao.ImportDataDao;
import com.lqmrSys.service.ExcelService;
import com.utils.ExcelUtil;
@Service
public class ExcelServiceImpl implements ExcelService {
	private static Logger logger = Logger.getLogger(ExcelServiceImpl.class);
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private ImportDataDao importDataDao;

	@Override
	public int supplierExcelToDataBase(MultipartFile file) throws ParseException {
		try {
			List<SupplierImportDataBean> addList = new ArrayList<SupplierImportDataBean>(); 
			List<String[]> list = ExcelUtil.readExcel(file);
			if(list!=null && list.size()>0){
				SupplierImportDataBean bean = null;
				for (String[] strings : list) {
					bean = new SupplierImportDataBean();
					String errMsg="";
					
					String supplierCode = strings[0];
					if(!"".equals(supplierCode) && supplierCode!=null){
						bean.setSupplierCode(supplierCode);
					}else{
						errMsg +="【"+supplierCode+"】"+"融资供应编号为空;";
					}
					String customContractCode = strings[1];
					if(!"".equals(customContractCode) && customContractCode!=null){
						bean.setCustomContractCode(customContractCode);
					}else{
						errMsg +="【"+customContractCode+"】"+"自定义合同号为空;";
					}
					String customVoucherCode = strings[2];
					if(!"".equals(customVoucherCode) && customVoucherCode!=null){
						bean.setCustomVoucherCode(customVoucherCode);
					}else{
						errMsg +="【"+customVoucherCode+"】"+"自定义凭证号为空;";
					}
					String cusManager = strings[3];
					if(!"".equals(cusManager) && cusManager!=null){
						bean.setCusManager(cusManager);
					}else{
						errMsg +="【"+cusManager+"】"+"客户经理为空;";
					}
					String supplierName = strings[4];
					if(!"".equals(supplierName) && supplierName!=null){
						bean.setSupplierName(supplierName);
					}else{
						errMsg +="【"+supplierName+"】"+"融资供应方名称为空;";
					}
					String supplierCertificateCode = strings[5];
					if(!"".equals(supplierCertificateCode) && supplierCertificateCode!=null){
						bean.setSupplierCertificateCode(supplierCertificateCode);
					}else{
						errMsg +="【"+supplierCertificateCode+"】"+"供应方证件号/机构号为空;";
					}
					String contactPhone = strings[6];
					if(!"".equals(contactPhone) && contactPhone!=null){
						bean.setContactPhone(contactPhone);
					}else{
						errMsg +="【"+contactPhone+"】"+"联系电话为空;";
					}
					String investmentMode = strings[7];
					if(!"".equals(investmentMode) && investmentMode!=null){
						bean.setInvestmentMode(investmentMode);
					}else{
						errMsg +="【"+investmentMode+"】"+"投资方式为空;";
					}
					String contractType = strings[8];
					if(!"".equals(contractType) && contractType!=null){
						bean.setContractType(contractType);
					}else{
						errMsg +="【"+contractType+"】"+"签约分类为空;";
					}
					String supplyAmount = strings[9];
					if(!"".equals(supplyAmount) && supplyAmount!=null){
						bean.setSupplyAmount(Double.parseDouble(supplyAmount));
					}else{
						errMsg +="【"+supplyAmount+"】"+"供应金额为空;";
					}
					String supplyBalanceAmount = strings[10];
					if(!"".equals(supplyBalanceAmount) && supplyBalanceAmount!=null){
						bean.setSupplyBalanceAmount(Double.parseDouble(supplyBalanceAmount));
					}else{
						errMsg +="【"+supplyBalanceAmount+"】"+"当前供应余额为空;";
					}
					String canPairAmount = strings[11];
					if(!"".equals(canPairAmount) && canPairAmount!=null){
						bean.setCanPairAmount(Double.parseDouble(canPairAmount));
					}else{
						errMsg +="【"+canPairAmount+"】"+"当前可配对金额为空;";
					}
					String settlementAmount = strings[12];
					if(!"".equals(settlementAmount) && settlementAmount!=null){
						bean.setSettlementAmount(Double.parseDouble(settlementAmount));
					}else{
						errMsg +="【"+settlementAmount+"】"+"当前已结算金额为空;";
					}
					String timeUsableAmount = strings[13];
					if(!"".equals(timeUsableAmount) && timeUsableAmount!=null){
						bean.setTimeUsableAmount(Double.parseDouble(timeUsableAmount));
					}else{
						errMsg +="【"+timeUsableAmount+"】"+"时点日终可用金额为空;";
					}
					String timeSettlementAmount = strings[14];
					if(!"".equals(timeSettlementAmount) && timeSettlementAmount!=null){
						bean.setTimeSettlementAmount(Double.parseDouble(timeSettlementAmount));
					}else{
						errMsg +="【"+timeSettlementAmount+"】"+"时点日终已结算金额为空;";
					}
					String timeUnsettlementAmount = strings[15];
					if(!"".equals(timeUnsettlementAmount) && timeUnsettlementAmount!=null){
						bean.setTimeUnsettlementAmount(Double.parseDouble(timeUnsettlementAmount));
					}else{
						errMsg +="【"+timeUnsettlementAmount+"】"+"时点日终未结算金额为空;";
					}
					String replacePayAmount = strings[16];
					if(!"".equals(replacePayAmount) && replacePayAmount!=null){
						bean.setReplacePayAmount(Double.parseDouble(replacePayAmount));
					}else{
						errMsg +="【"+replacePayAmount+"】"+"垫付本金为空;";
					}
					String takenReplacePayAmount = strings[17];
					if(!"".equals(takenReplacePayAmount) && takenReplacePayAmount!=null){
						bean.setTakenReplacePayAmount(Double.parseDouble(takenReplacePayAmount));
					}else{
						errMsg +="【"+takenReplacePayAmount+"】"+"收回垫付本金为空;";
					}
					String supplyRate = strings[18];
					if(!"".equals(supplyRate) && supplyRate!=null){
						bean.setSupplyRate(Double.parseDouble(supplyRate));
					}else{
						errMsg +="【"+supplyRate+"】"+"利率（‰）为空;";
					}
					String knotCycle = strings[19];
					if(!"".equals(knotCycle) && knotCycle!=null){
						bean.setKnotCycle(knotCycle);
					}else{
						errMsg +="【"+knotCycle+"】"+"结息周期为空;";
					}
					String entrustStartTime = strings[20];
					if(!"".equals(entrustStartTime) && entrustStartTime!=null){
						bean.setEntrustStartTime(sdf2.format(sdf1.parse(entrustStartTime.substring(1, entrustStartTime.length()))));
					}else{
						errMsg +="【"+entrustStartTime+"】"+"委托出借开始日期为空;";
					}
					String entrustEndTime = strings[21];
					if(!"".equals(entrustEndTime) && entrustEndTime!=null){
						bean.setEntrustEndTime(sdf2.format(sdf1.parse(entrustEndTime.substring(1, entrustEndTime.length()))));
					}else{
						errMsg +="【"+entrustEndTime+"】"+"委托出借截止日期为空;";
					}
					String graceDays = strings[22];
					if(!"".equals(graceDays) && graceDays!=null){
						bean.setGraceDays(Integer.valueOf(graceDays.substring(1, graceDays.length())));
					}else{
						errMsg +="【"+graceDays+"】"+"放款宽限天数为空;";
					}
					String supplyStartTime = strings[23];
					if(!"".equals(supplyStartTime) && supplyStartTime!=null){
						bean.setSupplyStartTime(sdf2.format(sdf1.parse(supplyStartTime.substring(1, supplyStartTime.length()))));
					}else{
						errMsg +="【"+supplyStartTime+"】"+"供应开始日期为空;";
					}
					String supplyEndTime = strings[24];
					if(!"".equals(supplyEndTime) && supplyEndTime!=null){
						bean.setSupplyEndTime(sdf2.format(sdf1.parse(supplyEndTime.substring(1, supplyEndTime.length()))));
					}else{
						errMsg +="【"+supplyEndTime+"】"+"供应截止日期为空;";
					}
					String actuallyGraceDays = strings[25];
					if(!"".equals(actuallyGraceDays) && actuallyGraceDays!=null){
						bean.setActuallyGraceDays(Integer.valueOf(actuallyGraceDays.substring(1, actuallyGraceDays.length())));
					}else{
						errMsg +="【"+actuallyGraceDays+"】"+"实际宽限天数为空;";
					}
					String interestIncome = strings[26];
					if(!"".equals(interestIncome) && interestIncome!=null){
						bean.setInterestIncome(Double.parseDouble(interestIncome));
					}else{
						errMsg +="【"+interestIncome+"】"+"利息收入为空;";
					}
					String serviceChargeIncome = strings[27];
					if(!"".equals(serviceChargeIncome) && serviceChargeIncome!=null){
						bean.setServiceChargeIncome(Double.parseDouble(serviceChargeIncome));
					}else{
						errMsg +="【"+serviceChargeIncome+"】"+"服务费收入为空;";
					}
					String payInterestAmount = strings[28];
					if(!"".equals(payInterestAmount) && payInterestAmount!=null){
						bean.setPayInterestAmount(Double.parseDouble(payInterestAmount));
					}else{
						errMsg +="【"+payInterestAmount+"】"+"已支付利息为空;";
					}
					String interestBalanceAmount = strings[29];
					if(!"".equals(interestBalanceAmount) && interestBalanceAmount!=null){
						if("--".equals(interestBalanceAmount)){
							interestBalanceAmount = "0";
						}
						bean.setInterestBalanceAmount(Double.parseDouble(interestBalanceAmount));
					}else{
						errMsg +="【"+interestBalanceAmount+"】"+"预计剩余利息为空;";
					}
					String interestAccount = strings[30];
					if(!"".equals(interestAccount) && interestAccount!=null){
						bean.setInterestAccount(interestAccount);
					}else{
						errMsg +="【"+interestAccount+"】"+"利息帐号为空;";
					}
					String interestAccountBank = strings[31];
					if(!"".equals(interestAccountBank) && interestAccountBank!=null){
						bean.setInterestAccountBank(interestAccountBank);
					}else{
						errMsg +="【"+interestAccountBank+"】"+"利息帐号银行为空;";
					}
					String principalAccount = strings[32];
					if(!"".equals(principalAccount) && principalAccount!=null){
						bean.setPrincipalAccount(principalAccount);
					}else{
						errMsg +="【"+principalAccount+"】"+"本金帐号为空;";
					}
					String principalAccountBank = strings[33];
					if(!"".equals(principalAccountBank) && principalAccountBank!=null){
						bean.setPrincipalAccountBank(principalAccountBank);
					}else{
						errMsg +="【"+principalAccountBank+"】"+"本金帐号银行为空;";
					}
					String accountType = strings[34];
					if(!"".equals(accountType) && accountType!=null){
						bean.setAccountType(accountType);
					}else{
						errMsg +="【"+supplierCode+"】"+"帐号类型为空;";
					}
					String accountUcode = strings[35];
					if(!"".equals(accountUcode) && accountUcode!=null){
						bean.setAccountUcode(accountUcode);
					}else{
						errMsg +="【"+accountUcode+"】"+"账户U盾编号为空;";
					}
					String accountNo = strings[36];
					if(!"".equals(accountNo) && accountNo!=null){
						bean.setAccountNo(accountNo);
					}else{
						errMsg +="【"+accountNo+"】"+"卡号为空;";
					}
					String informationSource = strings[37];
					if(!"".equals(informationSource) && informationSource!=null){
						bean.setInformationSource(informationSource.substring(1, informationSource.length()));
					}else{
						errMsg +="【"+informationSource+"】"+"投资信息来源渠道为空;";
					}
					String idleDays = strings[38];
					if(!"".equals(idleDays) && idleDays!=null){
						bean.setIdleDays(Integer.valueOf(idleDays));
					}else{
						errMsg +="【"+idleDays+"】"+"最近闲置天数为空;";
					}
					String otherDemand = strings[39];
					if(!"".equals(otherDemand) && otherDemand!=null){
						bean.setOtherDemand(otherDemand);
					}else{
						errMsg +="【"+otherDemand+"】"+"其他要求为空;";
					}
					String supplyStatus = strings[40];
					if(!"".equals(supplyStatus) && supplyStatus!=null){
						bean.setSupplyStatus(supplyStatus);
					}else{
						errMsg +="【"+supplyStatus+"】"+"状态为空;";
					}
					
					//查重
					if(!"".equals(bean.getSupplierCode()) && bean.getSupplierCode()!=null){
						int checkBean = importDataDao.hasSupplierImportData(bean.getSupplierCode());
						if(checkBean>0){
							errMsg +="【"+bean.getSupplierCode()+"】"+"融资供应编号已存在;";
						}else{
							addList.add(bean);
						}
					}
					//输出报错日志
					if(!"".equals(errMsg)){
						logger.info("ExcelServiceImpl.supplierExcelToDataBase:"+errMsg);
					}
				}
				
				if(addList!=null && addList.size()>0){
					if(importDataDao.addSupplierImportDataList(addList)){
						return 1;
					}
				}else{
					return 1;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int loanExcelToDataBase(MultipartFile file) throws ParseException {
		try {
			List<LoanImportDataBean> addList = new ArrayList<LoanImportDataBean>(); 
			List<String[]> list = ExcelUtil.readExcel(file);
			if(list!=null && list.size()>0){
				LoanImportDataBean bean = null;
				for (String[] strings : list) {
					bean = new LoanImportDataBean();
					String errMsg="";
					
					String loanCode = strings[0];
					if(!"".equals(loanCode) && loanCode!=null){
						bean.setLoanCode(loanCode);
					}else{
						errMsg +="【"+loanCode+"】"+"借据号为空;";
					}
					String contractCode = strings[1];
					if(!"".equals(contractCode) && contractCode!=null){
						bean.setContractCode(contractCode);
					}else{
						errMsg +="【"+contractCode+"】"+"合同号为空;";
					}
					String customContractCode = strings[2];
					if(!"".equals(customContractCode) && customContractCode!=null){
						bean.setCustomContractCode(customContractCode);
					}else{
						errMsg +="【"+customContractCode+"】"+"自定义合同号为空;";
					}
					String contractType = strings[3];
					if(!"".equals(contractType) && contractType!=null){
						bean.setContractType(contractType);
					}else{
						errMsg +="【"+contractType+"】"+"合同类型为空;";
					}
					String financingDemanderName = strings[4];
					if(!"".equals(financingDemanderName) && financingDemanderName!=null){
						bean.setFinancingDemanderName(financingDemanderName);
					}else{
						errMsg +="【"+financingDemanderName+"】"+"融资需求方为空;";
					}
					String cusManagera = strings[5];
					if(!"".equals(cusManagera) && cusManagera!=null){
						bean.setCusManagera(cusManagera.substring(1, cusManagera.length()));
					}else{
						errMsg +="【"+cusManagera+"】"+"客户经理A为空;";
					}
					String cusManagerb = strings[6];
					if(!"".equals(cusManagerb) && cusManagerb!=null){
						bean.setCusManagerb(cusManagerb.substring(1, cusManagerb.length()));
					}else{
						errMsg +="【"+cusManagerb+"】"+"客户经理B为空;";
					}
					String financingType = strings[7];
					if(!"".equals(financingType) && financingType!=null){
						bean.setFinancingType(financingType.substring(1, financingType.length()));
					}else{
						errMsg +="【"+financingType+"】"+"融资类型为空;";
					}
					String loanAmount = strings[8];
					if(!"".equals(loanAmount) && loanAmount!=null){
						bean.setLoanAmount(Double.parseDouble(loanAmount));
					}else{
						errMsg +="【"+loanAmount+"】"+"借款金额为空;";
					}
					String alreadyPayAmount = strings[9];
					if(!"".equals(alreadyPayAmount) && alreadyPayAmount!=null){
						bean.setAlreadyPayAmount(Double.parseDouble(alreadyPayAmount));
					}else{
						errMsg +="【"+alreadyPayAmount+"】"+"已收回金额为空;";
					}
					String balanceAmount = strings[10];
					if(!"".equals(balanceAmount) && balanceAmount!=null){
						bean.setBalanceAmount(Double.parseDouble(balanceAmount));
					}else{
						errMsg +="【"+balanceAmount+"】"+"当前余额为空;";
					}
					String supplierBalanceAmount = strings[11];
					if(!"".equals(supplierBalanceAmount) && supplierBalanceAmount!=null){
						bean.setSupplierBalanceAmount(Double.parseDouble(supplierBalanceAmount));
					}else{
						errMsg +="【"+supplierBalanceAmount+"】"+"当前供应方余额为空;";
					}
					String ownBalanceAmount = strings[12];
					if(!"".equals(ownBalanceAmount) && ownBalanceAmount!=null){
						bean.setOwnBalanceAmount(Double.parseDouble(ownBalanceAmount));
					}else{
						errMsg +="【"+ownBalanceAmount+"】"+"当前自有资金余额为空;";
					}
					String timeBalanceAmount = strings[13];
					if(!"".equals(timeBalanceAmount) && timeBalanceAmount!=null){
						bean.setTimeBalanceAmount(Double.parseDouble(timeBalanceAmount));
					}else{
						errMsg +="【"+timeBalanceAmount+"】"+"日终时点余额为空;";
					}
					String timeInterestAmount = strings[14];
					if(!"".equals(timeInterestAmount) && timeInterestAmount!=null){
						bean.setTimeInterestAmount(Double.parseDouble(timeInterestAmount));
					}else{
						errMsg +="【"+timeInterestAmount+"】"+"日终时点已收利息为空;";
					}
					String loanRate = strings[15];
					if(!"".equals(loanRate) && loanRate!=null){
						bean.setLoanRate(Double.parseDouble(loanRate));
					}else{
						errMsg +="【"+loanRate+"】"+"借款利率‰为空;";
					}
					String serviceRate = strings[16];
					if(!"".equals(serviceRate) && serviceRate!=null){
						bean.setServiceRate(Double.parseDouble(serviceRate));
					}else{
						errMsg +="【"+serviceRate+"】"+"服务费用利率‰为空;";
					}
					String loanStartTime = strings[17];
					if(!"".equals(loanStartTime) && loanStartTime!=null){
						bean.setLoanStartTime(sdf2.format(sdf1.parse(loanStartTime)));
					}else{
						errMsg +="【"+loanStartTime+"】"+"借款开始日期为空;";
					}
					String loanEndTime = strings[18];
					if(!"".equals(loanEndTime) && loanEndTime!=null){
						bean.setLoanEndTime(sdf2.format(sdf1.parse(loanEndTime)));
					}else{
						errMsg +="【"+loanEndTime+"】"+"借款截止日期为空;";
					}
					String loanDays = strings[19];
					if(!"".equals(loanDays) && loanDays!=null){
						bean.setLoanDays(Integer.valueOf(loanDays));
					}else{
						errMsg +="【"+loanDays+"】"+"借款天数为空;";
					}
					String planInterestAmount = strings[20];
					if(!"".equals(planInterestAmount) && planInterestAmount!=null){
						bean.setPlanInterestAmount(Double.parseDouble(planInterestAmount));
					}else{
						errMsg +="【"+planInterestAmount+"】"+"预计应收利息为空;";
					}
					String payInterestAmount = strings[21];
					if(!"".equals(payInterestAmount) && payInterestAmount!=null){
						bean.setPayInterestAmount(Double.parseDouble(payInterestAmount));
					}else{
						errMsg +="【"+payInterestAmount+"】"+"已收利息为空;";
					}
					String payServiceAmount = strings[22];
					if(!"".equals(payServiceAmount) && payServiceAmount!=null){
						bean.setPayServiceAmount(Double.parseDouble(payServiceAmount));
					}else{
						errMsg +="【"+payServiceAmount+"】"+"已收服务费为空;";
					}
					String hangupTotalAmount = strings[23];
					if(!"".equals(hangupTotalAmount) && hangupTotalAmount!=null){
						bean.setHangupTotalAmount(Double.parseDouble(hangupTotalAmount));
					}else{
						errMsg +="【"+hangupTotalAmount+"】"+"挂起合计金额为空;";
					}
					String repaymentMode = strings[24];
					if(!"".equals(repaymentMode) && repaymentMode!=null){
						bean.setRepaymentMode(repaymentMode.substring(1, repaymentMode.length()));
					}else{
						errMsg +="【"+repaymentMode+"】"+"还款方式为空;";
					}
					String supplyAmount = strings[25];
					if(!"".equals(supplyAmount) && supplyAmount!=null){
						bean.setSupplyAmount(Double.parseDouble(supplyAmount));
					}else{
						errMsg +="【"+supplyAmount+"】"+"供应方出借金额为空;";
					}
					String ownAmount = strings[26];
					if(!"".equals(ownAmount) && ownAmount!=null){
						bean.setOwnAmount(Double.parseDouble(ownAmount));
					}else{
						errMsg +="【"+ownAmount+"】"+"自有资金出借金额为空;";
					}
					String replacePayAmount = strings[27];
					if(!"".equals(replacePayAmount) && replacePayAmount!=null){
						bean.setReplacePayAmount(Double.parseDouble(replacePayAmount));
					}else{
						errMsg +="【"+replacePayAmount+"】"+"垫付金额为空;";
					}
					String takenReplacePayAmount = strings[28];
					if(!"".equals(takenReplacePayAmount) && takenReplacePayAmount!=null){
						bean.setTakenReplacePayAmount(Double.parseDouble(takenReplacePayAmount));
					}else{
						errMsg +="【"+takenReplacePayAmount+"】"+"收回垫付金额为空;";
					}
					String industryType = strings[29];
					if(!"".equals(industryType) && industryType!=null){
						bean.setIndustryType(industryType);
					}else{
						errMsg +="【"+industryType+"】"+"行业分类为空;";
					}
					String belongArea = strings[30];
					if(!"".equals(belongArea) && belongArea!=null){
						bean.setBelongArea(belongArea);
					}else{
						errMsg +="【"+belongArea+"】"+"所属区域为空;";
					}
					String workAddress = strings[31];
					if(!"".equals(workAddress) && workAddress!=null){
						bean.setWorkAddress(workAddress);
					}else{
						errMsg +="【"+workAddress+"】"+"单位地址为空;";
					}
					String loanPurpose = strings[32];
					if(!"".equals(loanPurpose) && loanPurpose!=null){
						bean.setLoanPurpose(loanPurpose);
					}else{
						errMsg +="【"+loanPurpose+"】"+"借款用途为空;";
					}
					String certificateNo = strings[33];
					if(!"".equals(certificateNo) && certificateNo!=null){
						bean.setCertificateNo(certificateNo);
					}else{
						errMsg +="【"+certificateNo+"】"+"证件号码/贷款卡号为空;";
					}
					String contactPhone = strings[34];
					if(!"".equals(contactPhone) && contactPhone!=null){
						bean.setContactPhone(contactPhone);
					}else{
						errMsg +="【"+contactPhone+"】"+"手机号码为空;";
					}
					String organizationNo = strings[35];
					if(!"".equals(organizationNo) && organizationNo!=null){
						bean.setOrganizationNo(organizationNo);
					}else{
						errMsg +="【"+organizationNo+"】"+"机构号为空;";
					}
					String businessLicenseNo = strings[36];
					if(!"".equals(businessLicenseNo) && businessLicenseNo!=null){
						bean.setBusinessLicenseNo(businessLicenseNo);
					}else{
						errMsg +="【"+businessLicenseNo+"】"+"营业执照号为空;";
					}
					String legalRepresentative = strings[37];
					if(!"".equals(legalRepresentative) && legalRepresentative!=null){
						bean.setLegalRepresentative(legalRepresentative);
					}else{
						errMsg +="【"+legalRepresentative+"】"+"法人代表为空;";
					}
					String guarantee = strings[38];
					if(!"".equals(guarantee) && guarantee!=null){
						bean.setGuarantee(guarantee);
					}else{
						errMsg +="【"+guarantee+"】"+"担保人为空;";
					}
					String riskClassify = strings[39];
					if(!"".equals(riskClassify) && riskClassify!=null){
						bean.setRiskClassify(riskClassify);
					}else{
						errMsg +="【"+riskClassify+"】"+"风险五级分类为空;";
					}
					String badDebt = strings[40];
					if(!"".equals(badDebt) && badDebt!=null){
						bean.setBadDebt(badDebt);
					}else{
						errMsg +="【"+badDebt+"】"+"呆账为空;";
					}
					String isExtension = strings[41];
					if(!"".equals(isExtension) && isExtension!=null){
						bean.setIsExtension(isExtension);
					}else{
						errMsg +="【"+isExtension+"】"+"是否展期为空;";
					}
					String loanRemark = strings[42];
					if(!"".equals(loanRemark) && loanRemark!=null){
						bean.setLoanRemark(loanRemark);
					}else{
						errMsg +="【"+loanRemark+"】"+"备注为空;";
					}
					
					//查重
					if(!"".equals(bean.getLoanCode()) && bean.getLoanCode()!=null){
						int checkBean = importDataDao.hasLoanImportData(bean.getLoanCode());
						if(checkBean>0){
							errMsg +="【"+bean.getLoanCode()+"】"+"借据号已存在;";
						}else{
							addList.add(bean);
						}
					}
					//输出报错日志
					if(!"".equals(errMsg)){
						logger.info("ExcelServiceImpl.loanExcelToDataBase:"+errMsg);
					}
				}
				
				if(addList!=null && addList.size()>0){
					if(importDataDao.addLoanImportDataList(addList)){
						return 1;
					}
				}else{
					return 1;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
}
