/*
 * Created on Sep 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.context.multidatasource;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class J204Exception extends RuntimeException
{
	public static String message = null;
	public J204Exception(String msg)
	{
		message = (msg);
	}
	public String getMessage()
	{
		return message;
	}
}
