/*
 * Created on Dec 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.messaging.reporting;

/**
 * @author eamundson
 * 
 * Responsible for request background print jobs.
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PrintRequestEvent extends ReportRequestEvent
{

	private String printer = "default";
	/**
	 * @return
	 */
	public String getPrinter()
	{
		return printer;
	}

	/**
	 * @param string
	 */
	public void setPrinter(String string)
	{
		printer = string;
	}

}
