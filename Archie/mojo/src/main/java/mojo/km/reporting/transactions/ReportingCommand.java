/*
 * Created on Mar 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.reporting.transactions;


import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;

import java.io.OutputStream;

import mojo.km.reporting.IReport;
import mojo.km.reporting.ReportManager;

/**
 * @author Rajib Das Sharma
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportingCommand implements ICommand {

	public void execute(IEvent event) 
	{
		ReportRequestEvent reqEvent = (ReportRequestEvent)event;
		try
		{
		    
			IReport report = ReportManager.getInstance(reqEvent.getReportName());// ReportName passed to the reqEvent is actually the contextKey
			IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
			ReportResponseEvent respEvent = new ReportResponseEvent();
			respEvent.setContentType(report.getContentType());
			respEvent.setContent(report.getByteOutput(reqEvent));
			respEvent.setFileName(report.getTemplate());
			dispatch.postEvent(respEvent);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onRegister(IEvent event){
	}

	public void onUnregister(IEvent event){
	}
	
	public void update(Object updateObject){
	}

}
