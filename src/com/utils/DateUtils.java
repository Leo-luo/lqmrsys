package com.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * 把date对象转换成日期字符串，可以指定格式
     * 
     * @param date
     *        日期对象
     * @param dateFormat
     *        日期格式
     * @return
     */
    public static String fromDateToFormatString(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern(dateFormat);
            return format.format(date);
        } catch (Exception e) {
            logger.error("日期转换字符串出错!", e);
            return null;
        }
    }
    
    public static void main(String[] args) throws ParseException{
    	int days = 5;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		date = calendar.getTime();
    	System.out.println(sdf.format(date));
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	ArrayList<String> list = new ArrayList<String>();
//    	Date d1 = sdf.parse("2016-11-01");//定义起始日期
//
//    	Date d2 = sdf.parse("2016-12-31");//定义结束日期
//
//    	Calendar dd = Calendar.getInstance();//定义日期实例
//
//    	dd.setTime(d1);//设置日期起始时间
//
//    	while(dd.getTime().before(d2)){//判断是否到结束日期
//    		dd.add(Calendar.DAY_OF_MONTH, -1);
//	    	String str = sdf.format(dd.getTime());
//	    	list.add(str);
//	    	dd.add(Calendar.DAY_OF_MONTH, 1);
//	    	String str2 = sdf.format(dd.getTime());
//	    	list.add(str2);
//	    	dd.add(Calendar.MONTH, 6);//进行当前日期月份加1
//    	}
//    	if("01".equals("2015-12-01".substring(8, 10))){
//	    	list.remove(0);
//		}
//    	list.add(sdf.format(d2.getTime()));
//    	for (int i = 0; i < list.size(); i++) {
//			int next = i+1;
//    		Date startDay = sdf.parse(list.get(i));
//    		Date endDay = sdf.parse(list.get(next));
//    		int days = (int)((endDay.getTime()-startDay.getTime())/(1000*3600*24))+1;
//    		if(next==(list.size()-1)){
//    			System.out.println(list.get(i)+"/"+list.get(next)+"/"+days+"=最后月，本息全还");
//			}else{
//				System.out.println(list.get(i)+"/"+list.get(next)+"/"+days);
//			}
//    		i++;
//		}
//    	BigDecimal thousand = new BigDecimal(1000);
//		BigDecimal monthDays = new BigDecimal(30);
//		BigDecimal decimal1 = new BigDecimal(1000000);
//		System.out.println(decimal1.doubleValue());
//		BigDecimal decimal2 = new BigDecimal(6.5);
//		BigDecimal decimal3 = new BigDecimal(6.5);
//		BigDecimal decimal4 = new BigDecimal(30);
//		Double planInterestAmount = decimal1.multiply(decimal2).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//		Double planServiceAmount = decimal1.multiply(decimal3).divide(thousand,2,BigDecimal.ROUND_HALF_UP).divide(monthDays,2,BigDecimal.ROUND_HALF_UP).multiply(decimal4).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//		System.out.println(decimal1.doubleValue());
//		System.out.println(planInterestAmount);
//		System.out.println(planServiceAmount);
    }
}
