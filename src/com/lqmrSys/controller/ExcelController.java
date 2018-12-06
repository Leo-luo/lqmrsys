package com.lqmrSys.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.Res;
import com.common.ResultCodeEnum;
import com.lqmrSys.service.ExcelService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/excel")
@Api(value = "excel", description = "excel相关接口")
public class ExcelController {
	@Autowired  
	private HttpServletRequest request;
	@Autowired
	private ExcelService excelService;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/supplier_excel")
    @ApiOperation(value = "供应方台账excel导入", notes = "供应方台账excel导入")
    public Res<Integer> supplierExcel(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/excel"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/supplierExcel";
            File outFile = new File(saveurl);  
            String tmpFileName=file.getOriginalFilename();
            String fileName = formatter.format(new Date()) + tmpFileName.substring(tmpFileName.lastIndexOf("."), tmpFileName.length());
            if (!outFile.exists()) {  
            	outFile.mkdirs();  
            }  
            outFile.setWritable(true);
            if(!outFile.exists()) {  
                outFile.createNewFile();  
                System.out.println("full path = " + outFile.getAbsolutePath());  
            } else {  
                System.out.println("full path = " + outFile.getAbsolutePath());  
            }  
            FileOutputStream outStream = new FileOutputStream(new File(saveurl +"/"+ fileName)); 
              
            outStream.write(data);  
            outStream.close();  
            input.close();

            if(outFile.exists()){
            	int result = excelService.supplierExcelToDataBase(file);
            	if(result!=0){
            		return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
            	}
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<Integer>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), 0);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/loan_excel")
    @ApiOperation(value = "借款情况台账excel导入", notes = "借款情况台账excel导入")
    public Res<Integer> loanExcel(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/excel"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/loanExcel";
            File outFile = new File(saveurl);  
            String tmpFileName=file.getOriginalFilename();
            String fileName = formatter.format(new Date()) + tmpFileName.substring(tmpFileName.lastIndexOf("."), tmpFileName.length());
            if (!outFile.exists()) {  
            	outFile.mkdirs();  
            }  
            outFile.setWritable(true);
            if(!outFile.exists()) {  
                outFile.createNewFile();  
                System.out.println("full path = " + outFile.getAbsolutePath());  
            } else {  
                System.out.println("full path = " + outFile.getAbsolutePath());  
            }  
            FileOutputStream outStream = new FileOutputStream(new File(saveurl +"/"+ fileName)); 
              
            outStream.write(data);  
            outStream.close();  
            input.close();

            if(outFile.exists()){
            	int result = excelService.loanExcelToDataBase(file);
            	if(result!=0){
            		return new Res<Integer>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), result);
            	}
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<Integer>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), 0);
	}
}
