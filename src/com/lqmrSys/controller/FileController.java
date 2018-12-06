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
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/file")
@Api(value = "文件", description = "文件相关接口")
public class FileController {
	
	@Autowired  
	private HttpServletRequest request;
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/supplierFileUpload")
    @ApiOperation(value = "供应方附件上传", notes = "供应方附件上传")
    public Res<String> supplierFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/file"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/supplierFile";
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
            	return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), "/file/supplierFile/"+ fileName);
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<String>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), null);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/demandFileUpload")
    @ApiOperation(value = "需求方附件上传", notes = "需求方附件上传")
    public Res<String> demandFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/file"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/demandFile";
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
            	return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), "/file/demandFile/"+ fileName);
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<String>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), null);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/trialFileUpload")
    @ApiOperation(value = "审核、审批附件上传", notes = "审核、审批附件上传")
    public Res<String> trialFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/file"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/trialFile";
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
            	return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), "/file/trialFile/"+ fileName);
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<String>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), null);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/customerFileUpload")
    @ApiOperation(value = "自然人附件上传", notes = "自然人附件上传")
    public Res<String> customerFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/file"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/customerFile";
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
            	return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), "/file/customerFile/"+ fileName);
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<String>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), null);
	}
	
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/companyFileUpload")
    @ApiOperation(value = "企业附件上传", notes = "企业附件上传")
    public Res<String> companyFileUpload(@RequestParam("file") MultipartFile file){
		System.out.println("fucking spring3 MVC upload file with Multipart form");
		String saveurl = request.getSession().getServletContext().getRealPath("/file"); 
		try {  
            long size = file.getSize();  
            byte[] data = new byte[(int) size];  
            InputStream input = file.getInputStream();  
            input.read(data);  
              
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            saveurl = saveurl+"/companyFile";
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
            	return new Res<String>(ResultCodeEnum.OPERATION_SUCCESS.getCode(), ResultCodeEnum.OPERATION_SUCCESS.getMessage(), "/file/companyFile/"+ fileName);
            }
	    } catch (Exception e) {  
	    	e.printStackTrace(); 
	    }
		return new Res<String>(ResultCodeEnum.UPLOAD_ERROR.getCode(), ResultCodeEnum.UPLOAD_ERROR.getMessage(), null);
	}
}
