package mojo.struts.taglib.layout.formatter;

import javax.servlet.jsp.PageContext;
/**
 * Insert the type's description here.
 * Creation date: (01/12/2001 02:01:10)
 * @author: Eric Amundson
 */
public abstract class AbstractFormatter {
	public abstract String format(Object in_value, String in_format, PageContext in_pageContext) throws FormatException;
}
