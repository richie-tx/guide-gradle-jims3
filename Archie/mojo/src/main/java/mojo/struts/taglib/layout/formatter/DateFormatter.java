package mojo.struts.taglib.layout.formatter;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.jsp.PageContext;


/**
 * Example of a formatter. This one only knows how to format a date.
 * 
 * @version 	1.0
 * @author 	JN Ribette
 */
public class DateFormatter extends DispatchFormatter {
	public String date(Object in_value, PageContext in_pageContext) {
		Date lc_date = (Date) in_value;
		if (lc_date==null) {
			return null;
		} else {
	     	DateFormat lc_format = DateFormat.getDateInstance(DateFormat.SHORT, in_pageContext.getRequest().getLocale());
	     	return lc_format.format(lc_date);     
		}
	}
}
