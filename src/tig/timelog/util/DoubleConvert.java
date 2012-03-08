/**
 * 
 */
package tig.timelog.util;

import java.math.BigDecimal;
import java.util.Map;

import jxl.common.Logger;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @author Zero.Zhang
 * 
 */
public class DoubleConvert extends StrutsTypeConverter {
	
	protected Logger log = Logger.getLogger(DoubleConvert.class);

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
		if (Double.class == toClass) {
			String doubleStr = values[0];
			Double d = null;
			if (!doubleStr.equals("")) {
				d = Double.parseDouble(doubleStr);
			}
			return d;
		}
		return 0;

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
		if (o instanceof Double) {
			Double d = (Double) o;
			BigDecimal b = new BigDecimal(d.doubleValue()).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			return b.toString();
		}
		
		return o.toString();

	}

}
