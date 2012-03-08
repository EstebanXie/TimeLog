package tig.timelog.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	private final static String datePattern = "yyyy/MM/dd";

	public static String date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(date);
	}
  
  public static String date2String(java.util.Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
    return sdf.format(date);
  }
	
	public static Date string2Date(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		java.util.Date date = sdf.parse(strDate);
		return new Date(date.getTime());
	}
	
	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
  
  public static String date2String(java.util.Date date, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }
}
