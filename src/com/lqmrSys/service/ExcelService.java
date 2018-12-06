package com.lqmrSys.service;

import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
	//供应方台账excel导入
	public int supplierExcelToDataBase(MultipartFile file) throws ParseException;
	//借款情况台账excel导入
	public int loanExcelToDataBase(MultipartFile file) throws ParseException;
	

}
