/**
 * 
 */
package tig.timelog.util;

import java.text.ParseException;
import java.util.Map;

import jxl.common.Logger;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @author Zero.Zhang
 * 
 */
public class DateConvert extends StrutsTypeConverter {

	protected Logger log = Logger.getLogger(DateConvert.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util
	 * .Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (java.sql.Date.class == toClass) {
			String dateStr = values[0];
			java.sql.Date d = null;
			if (!dateStr.equals("")) {
				try {
					d = DateUtil.string2Date(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			return d;
		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util
	 * .Map, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof java.util.Date) {
			java.sql.Date d = (java.sql.Date) o;
			return DateUtil.date2String(d);
		}

		return o.toString();
	}

}
