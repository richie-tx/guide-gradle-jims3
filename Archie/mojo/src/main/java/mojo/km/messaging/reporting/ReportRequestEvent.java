/*
 * Created on Mar 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.messaging.reporting;

import java.io.OutputStream;
import mojo.km.messaging.RequestEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author RDassharma
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportRequestEvent extends RequestEvent {

	private ArrayList dataObjects = new ArrayList();
	
	private String reportName = "";
	
	public ReportRequestEvent()
	{
	}
	
	public void addDataObject( Object bean )
	{
		dataObjects.add( bean );
	}
	
	public Iterator getDataObjects()
	{
		return dataObjects.iterator();
	}

	/**
	 * @return
	 */
	public String getReportName()
	{
		return reportName;
	}

	/**
	 * @param string
	 */
	public void setReportName(String string)
	{
		reportName = string;
	}

}
