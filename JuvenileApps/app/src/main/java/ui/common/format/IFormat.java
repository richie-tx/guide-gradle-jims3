/*
 * Created on Mar 1, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.format;

import java.text.ParseException;

/**
 * @author Jim Fisher
 *
 */
public interface IFormat
{
	String format(Object obj) throws IllegalArgumentException;
	Object parse(String str);
	boolean isValid();
}
