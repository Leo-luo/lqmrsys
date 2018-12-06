package com.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class POIExcelUtil {
	private static Logger logger = Logger.getLogger(POIExcelUtil.class);
	
	private final static String xls = "xls";  
    private final static String xlsx = "xlsx";  
      
    /** 
     * 读入excel文件，解析后返回 
     * @param file 
     * @throws IOException  
     */  
    public static List<String[]> readExcel(MultipartFile file) throws IOException{  
        //检查文件  
        checkFile(file);  
        //获得Workbook工作薄对象  
        Workbook workbook = getWorkBook(file);  
        //设置汉字字体
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("楷体");
        cellStyle.setFont(font);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
        List<String[]> list = new ArrayList<String[]>();  
        if(workbook != null){  
//          for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){} 
            //获得当前sheet工作表  
            Sheet sheet = workbook.getSheetAt(0);  
            if(sheet == null){  
            	return null;  
            }  
            //获得当前sheet的开始行  
            int firstRowNum  = sheet.getFirstRowNum();  
            //获得当前sheet的结束行  
            int lastRowNum = sheet.getLastRowNum(); 
            
            Row firstRow = sheet.getRow(0);
            
            //获得当前行的开始列  
            int firstCellNum = firstRow.getFirstCellNum(); 
            //获得当前行的结束列  
            int lastCellNum = firstRow.getLastCellNum()+1;
            //循环除了第一.二行的所有行  
            for(int rowNum = firstRowNum+2;rowNum <= lastRowNum;rowNum++){  
                //获得当前行  
                Row row = sheet.getRow(rowNum);  
                if(row == null){  
                    continue;  
                }  
                //定义存储数据数组
                String[] cells = new String[lastCellNum+1];  
                //循环当前行  
                for(int cellNum = firstCellNum; cellNum <= lastCellNum;cellNum++){  
                    Cell cell = row.getCell(cellNum);  
                    cells[cellNum] = getCellValue(cell, cellStyle);  
                }  
                list.add(cells);  
            }  
            workbook.close();  
        }  
        return list;  
    }  
    public static void checkFile(MultipartFile file) throws IOException{  
        //判断文件是否存在  
        if(null == file){  
            logger.error("文件不存在！");  
            throw new FileNotFoundException("文件不存在！");  
        }  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //判断文件是否是excel文件  
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){  
            logger.error(fileName + "不是excel文件");  
            throw new IOException(fileName + "不是excel文件");  
        }  
    }  
    public static Workbook getWorkBook(MultipartFile file) {  
        //获得文件名  
        String fileName = file.getOriginalFilename();  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
            //获取excel文件的io流  
            InputStream is = file.getInputStream();  
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith(xls)){  
                //2003  
                workbook = new HSSFWorkbook(is);  
            }else if(fileName.endsWith(xlsx)){  
                //2007  
                workbook = new XSSFWorkbook(is);  
            }  
        } catch (IOException e) {  
            logger.info(e.getMessage());  
        }  
        return workbook;  
    }  
    @SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell, CellStyle cellStyle){  
        String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_NUMERIC: //数字  
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");  
                    Date date = cell.getDateCellValue();  
                    cellValue = sdf.format(date);  
                } else if (cell.getCellStyle().getDataFormat() == 58) {  
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                    double value = cell.getNumericCellValue();  
                    Date date = org.apache.poi.ss.usermodel.DateUtil  
                            .getJavaDate(value);  
                    cellValue = sdf.format(date);  
                } else {  
                	cell.setCellType(Cell.CELL_TYPE_STRING); 
                	cellValue = String.valueOf(cell.getStringCellValue());     
                }  
                break;  
            case Cell.CELL_TYPE_STRING: //字符串  
            	cell.setCellStyle(cellStyle);
                cellValue = ToDBC(String.valueOf(cell.getStringCellValue()));  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
            	 try {
            		 cellValue = String.valueOf(cell.getNumericCellValue());
            	 } catch (IllegalStateException e) {
            		 cellValue = String.valueOf(cell.getRichStringCellValue());
            	 }
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";  
                break;  
            default:  
                cellValue = "未知类型";  
                break;  
        }  
        System.out.println("======>cellVaule:"+cellValue);
        return cellValue;  
    }  
    
	//  转全角的函数(SBC case)  
	// 任意字符串  
	// 全角字符串  
	// 全角空格为12288，半角空格为32  
	// 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248  
	public static String ToSBC(String input) {  
	    // 半角转全角：  
	    char[] c = input.toCharArray();  
	    for (int i = 0; i< c.length; i++) {  
	        if (c[i] == 32) {  
	        c[i] = (char) 12288;  
	        continue;  
	        }  
	        if (c[i]< 127)  
	            c[i] = (char) (c[i] + 65248);  
	    }  
	    return new String(c);  
	}  
	  
	  
	// 转半角的函数(DBC case)  
	// 任意字符串  
	// 半角字符串  
	// 全角空格为12288，半角空格为32  
	// 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248  
	public static String ToDBC(String input) {  
	    //全角转半角  
	    char[] c = input.toCharArray();  
	    for (int i = 0; i< c.length; i++) {  
	        if (c[i] == 12288) {  
	            c[i] = (char) 32;  
	            continue;  
	        }  
	        if (c[i]> 65280&& c[i]< 65375)  
	            c[i] = (char) (c[i] - 65248);  
	    }  
	    return new String(c);  
	}  
    
}
