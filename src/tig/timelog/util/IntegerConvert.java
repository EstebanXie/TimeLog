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
public class IntegerConvert extends StrutsTypeConverter {
	
	protected Logger log = Logger.getLogger(IntegerConvert.class);

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
		if (Integer.class == toClass) {
			String integerStr = values[0];
			Integer d = null;
			if (!integerStr.equals("")) {
				d = Integer.valueOf(integerStr);
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
		if (o instanceof Integer) {
			Integer d = (Integer) o;
			return d.toString();
		}
		
		return o.toString();
	}

}
